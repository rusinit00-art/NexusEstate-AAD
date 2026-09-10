package org.ijse.nexusestate_aad.service;
import org.ijse.nexusestate_aad.dto.ChatbotDTO;

public interface AIService {
    ChatbotDTO processChat(ChatbotDTO dto);
}