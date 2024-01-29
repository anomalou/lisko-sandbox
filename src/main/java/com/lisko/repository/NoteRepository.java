package com.lisko.repository;

import com.lisko.entity.Note;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Author: Aleksandr Borodin
 * Creation date: 1/29/24
 */

@Repository
public interface NoteRepository extends PagingAndSortingRepository<Note, Integer> {
}
