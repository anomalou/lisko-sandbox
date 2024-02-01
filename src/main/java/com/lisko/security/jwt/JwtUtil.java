package com.lisko.security.jwt;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lisko.exception.JwtCreationException;
import com.lisko.exception.JwtValidationException;
import com.lisko.security.jwt.entity.JwtEntity;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

/**
 * Author: Aleksandr Borodin
 * Creation date: 2/1/24
 */

@Component
@RequiredArgsConstructor
@Slf4j
@ConfigurationProperties(prefix = "lisko")
public class JwtUtil {

    private final JwtConfig config;

    public JwtEntity parse(String token) {
        String[] parts = token.split("\\.");

        if (parts.length != 3) {
            //todo throw ex
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();

        JwtEntity jwt = new JwtEntity();

        log.info("Parsing JWT header...");

        try (JsonParser headerParser = factory.createParser(decode(parts[0]))){
            JsonNode header = mapper.readTree(headerParser);

            JsonNode algorithm = header.get("alg");
            jwt.getHeader().setAlgorithm(algorithm.asText());
        } catch (IOException e) {

        }

        log.info("JWT header parsed");
        log.info("Parsing JWT body...");

        try (JsonParser bodyParser = factory.createParser(decode(parts[1]))){
            JsonNode body = mapper.readTree(bodyParser);

            JsonNode exp = body.get("exp");
            LocalDate expDate = Instant.ofEpochMilli(exp.asLong()).atZone(ZoneId.systemDefault()).toLocalDate();
            jwt.getBody().setExp(expDate);

            JsonNode username = body.get("username");
            jwt.getBody().setUsername(username.asText());
        } catch (IOException e) {

        }

        log.info("JWT body parsed");

        jwt.setSign(parts[2]);

        log.info("JWT parsed");

        return jwt;
    }

    public String create(JwtEntity jwt) {
        try{
            ObjectMapper mapper = new ObjectMapper();
            String header = mapper.writeValueAsString(jwt.getHeader());
            String body = mapper.writeValueAsString(jwt.getBody());
            String sign = encodeSign(header + "." + body, config.getSecret());
            return encode(header.getBytes()) + "." + encode(body.getBytes()) + "." + sign;
        }catch (Exception e){
            log.error("Jwt can't be created: Exception: {}", e.getMessage()); //todo
            throw new JwtCreationException();
        }
    }

    public boolean validate(String token) {
        try{
            JwtEntity jwt = parse(token);

            ObjectMapper mapper = new ObjectMapper();
            String header = mapper.writeValueAsString(jwt.getHeader());
            String body = mapper.writeValueAsString(jwt.getBody());
            String validSign = encodeSign(header + "." + body, config.getSecret());

            if (!validSign.equals(jwt.getSign())) {
                return false;
            }

            if (jwt.getBody().getExp().isBefore(LocalDate.now())) {
                return false;
            }

            return true;
        } catch (Exception e){
            log.error("Jwt not valid. Exception: {}", e.getMessage());
            throw new JwtValidationException();
        }
    }

    private String decode(String part) {
        return new String(Base64.decode(part));
    }

    private String encode(byte[] part){
        return Base64.encode(part);
    }

    private String encodeSign(String data, String secret) throws Exception{
        byte[] secretHash = secret.getBytes(StandardCharsets.UTF_8);
        Mac hs256 = Mac.getInstance("HmacSHA256"); //todo
        SecretKeySpec keySpec = new SecretKeySpec(secretHash, "HmacSHA256");
        hs256.init(keySpec);

        byte[] signedToken = hs256.doFinal(data.getBytes(StandardCharsets.UTF_8));

        return encode(signedToken);
    }

}
