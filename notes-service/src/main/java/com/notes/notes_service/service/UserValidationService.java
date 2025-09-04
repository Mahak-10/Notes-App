package com.notes.notes_service.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserValidationService {

    private final RestTemplate restTemplate;

    public boolean isValidUser(Long userId) {
        String url = "http://localhost:8082/users/" + userId; // URL of User microservice
        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);
            return response.getStatusCode().is2xxSuccessful();
        } catch (Exception e) {
            return false; // user not found or User service unavailable
        }
    }
}