package org.ijse.nexusestate_aad.controller;

import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.dto.ChatbotDTO;
import org.ijse.nexusestate_aad.service.AIService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
@RequiredArgsConstructor
public class AIController {

    private final AIService aiService;

    @PostMapping("/chat")
    public ResponseEntity<ChatbotDTO> chat(@RequestBody ChatbotDTO dto) {
        return ResponseEntity.ok(aiService.processChat(dto));
    }
}