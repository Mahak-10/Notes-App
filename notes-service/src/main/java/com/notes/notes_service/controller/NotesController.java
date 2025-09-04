package com.notes.notes_service.controller;

import com.notes.notes_service.model.Note;
import com.notes.notes_service.service.NotesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NotesController {

    private final NotesService noteService;

    // --- Create Note ---
    @PostMapping
    public ResponseEntity<Note> createNote(@RequestBody Note note) {
        Note created=noteService.createNote(note);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    // --- Get Note by ID ---
    @GetMapping("/{id}")
    public ResponseEntity<Note> getNoteById(@PathVariable Long id) {
        return noteService.getNoteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // --- Get All Notes ---
    @GetMapping
    public ResponseEntity<List<Note>> getAllNotes() {
        return ResponseEntity.ok(noteService.getAllNotes());
    }

    // --- Get Notes by User ---
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Note>> getNotesByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(noteService.getNotesByUser(userId));
    }

    // --- Update Note ---
    @PutMapping("/{id}")
    public ResponseEntity<Note> updateNote(@PathVariable Long id, @RequestBody Note updatedNote) {
        return ResponseEntity.ok(noteService.updateNote(id, updatedNote));
    }

    // --- Delete Note ---
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNote(@PathVariable Long id) {
        noteService.deleteNote(id);
        return ResponseEntity.noContent().build();
    }

    // --- Share Note ---
    @GetMapping("/share/{id}")
    public ResponseEntity<String> shareNoteById(@PathVariable Long id) {
        String link = noteService.generateShareableLink(id);
        return ResponseEntity.ok(link);
    }

    // --- Search Notes by Title ---
    @GetMapping("/search")
    public ResponseEntity<List<Note>> findNotesByTitle(@RequestParam String title) {
        List<Note> notes = noteService.findNotesByTitle(title);
        if (notes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(notes);
    }
}