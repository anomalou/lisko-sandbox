package com.lisko.security.jwt;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.lisko.exception.JwtCreationException;
import com.lisko.exception.JwtParserException;
import com.lisko.exception.JwtValidationException;
import com.lisko.security.jwt.entity.JwtEntity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Base64;

/**
 * Author: Aleksandr Borodin
 * Creation date: 2/1/24
 */

@Component
@Slf4j
public class JwtUtil {

    @RequiredArgsConstructor
    @Getter
    private enum SignEncodeAlgorithm {
        HS256("HmacSHA256");

        private final String name;
    }

    @Autowired
    private JwtConfig config;

    public JwtEntity parse(String token) {
        String[] parts = token.split("\\.");

        if (parts.length != 3) {
            //todo throw ex
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        JsonFactory factory = mapper.getFactory();

        JwtEntity jwt = new JwtEntity();

        log.info("Parsing JWT header...");

        try (JsonParser headerParser = factory.createParser(decode(parts[0]))){
            JsonNode header = mapper.readTree(headerParser);

            JsonNode algorithm = header.get("alg");
            jwt.getHeader().setAlgorithm(algorithm.asText());
        } catch (IOException e) {
            throw new JwtParserException();
        }

        log.info("JWT header parsed");
        log.info("Parsing JWT body...");

        try (JsonParser bodyParser = factory.createParser(decode(parts[1]))){
            JsonNode body = mapper.readTree(bodyParser);

            JsonNode exp = body.get("exp");

            LocalDateTime expDate = LocalDateTime.parse(exp.asText());
            jwt.getBody().setExp(expDate);

            JsonNode username = body.get("username");
            jwt.getBody().setUsername(username.asText());
        } catch (IOException e) {
            throw new JwtParserException();
        }

        log.info("JWT body parsed");

        jwt.setSign(parts[2]);

        log.info("JWT parsed");

        return jwt;
    }

    public String generate(String username) {
        try{
            JwtEntity jwt = new JwtEntity();
            jwt.getBody().setUsername(username);
            jwt.getBody().setExp(LocalDateTime.now().plusSeconds(config.getTtl()));

            ObjectMapper mapper = new ObjectMapper();
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            mapper.findAndRegisterModules();

            String header = encode(mapper.writeValueAsString(jwt.getHeader()).getBytes());
            String body = encode(mapper.writeValueAsString(jwt.getBody()).getBytes());
            String sign = encodeSign(header + "." + body, config.getSecret(), SignEncodeAlgorithm.valueOf(jwt.getHeader().getAlgorithm()));
            return header + "." + body + "." + sign;
        }catch (Exception e){
            log.error("Jwt can't be created: Exception: {}", e.getMessage()); //todo
            throw new JwtCreationException();
        }
    }

    public boolean validate(String token) {
        try{
            JwtEntity jwt = parse(token);

            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);

            String header = encode(mapper.writeValueAsString(jwt.getHeader()).getBytes());
            String body = encode(mapper.writeValueAsString(jwt.getBody()).getBytes());
            String validSign = encodeSign(
                    header + "." + body,
                    config.getSecret(),
                    SignEncodeAlgorithm.valueOf(jwt.getHeader().getAlgorithm())
            );

            if (!jwt.getSign().equals(validSign)) {
                return false;
            }

            if (jwt.getBody().getExp().isBefore(LocalDateTime.now())) {
                return false;
            }

            return true;
        } catch (Exception e){
            log.error("Jwt not valid. Exception: {}", e.getMessage());
            throw new JwtValidationException();
        }
    }

    private String decode(String part) {
        return new String(Base64.getUrlDecoder().decode(part));
    }

    private String encode(byte[] part){
        return Base64.getUrlEncoder().withoutPadding().encodeToString(part);
    }

    private String encodeSign(String data, String secret, SignEncodeAlgorithm algorithm) throws Exception{
        byte[] secretHash = secret.getBytes(StandardCharsets.UTF_8);
        Mac hs256 = Mac.getInstance(algorithm.getName()); //todo

        SecretKeySpec keySpec = new SecretKeySpec(secretHash, "HmacSHA256");
        hs256.init(keySpec);

        byte[] signedToken = hs256.doFinal(data.getBytes(StandardCharsets.UTF_8));

        return encode(signedToken);
    }

}
