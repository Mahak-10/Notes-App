package com.notes.notes_service.repository;


import com.notes.notes_service.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotesRepository extends JpaRepository<Note, Long> {

    // Custom finder methods (Spring auto-implements them):
    List<Note> findByUserId(Long userId);

    List<Note> findByTitleContaining(String keyword);
}