package com.notes.notes_service.service;


import com.notes.notes_service.model.Note;
import com.notes.notes_service.repository.NotesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor // Lombok creates a constructor for all final fields
public class NotesService {

    private final NotesRepository noteRepository;  // injected automatically
    private final UserValidationService userValidationService;

        public Note createNote(Note note) {
            if (!userValidationService.isValidUser(note.getUserId())) {
                throw new UserNotFoundException(note.getUserId());
            }
            return noteRepository.save(note);
        }

    public Optional<Note> getNoteById(Long id) {
        return noteRepository.findById(id);
    }

    public List<Note> getAllNotes() {
        return noteRepository.findAll();
    }

    public List<Note> getNotesByUser(Long userId) {
        return noteRepository.findByUserId(userId);
    }

    public Note updateNote(Long id, Note updatedNote) {
        return noteRepository.findById(id)
                .map(existing -> {
                    existing.setTitle(updatedNote.getTitle());
                    existing.setContent(updatedNote.getContent());
                    existing.setUserId(updatedNote.getUserId());
                    return noteRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Note not found with id " + id));
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    // --- Extra feature: Share Note ---
    public String generateShareableLink(Long noteId) {
        return "http://localhost:8081/notes/share/" + noteId;
    }

    public List<Note> findNotesByTitle(String title) {
        return noteRepository.findByTitleContaining(title);
    }
}