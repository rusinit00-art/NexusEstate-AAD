package org.ijse.nexusestate_aad.service.impl;

import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.dto.ChatbotDTO;
import org.ijse.nexusestate_aad.entity.CustomerExperienceandAI.Chatbot;
import org.ijse.nexusestate_aad.entity.PropertyFoundation.Property;
import org.ijse.nexusestate_aad.repository.ChatbotRepository;
import org.ijse.nexusestate_aad.repository.PropertyRepository;
import org.ijse.nexusestate_aad.repository.UserRepository;
import org.ijse.nexusestate_aad.service.AIService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AIServiceImpl implements AIService {

    private final PropertyRepository propertyRepository;
    private final ChatbotRepository chatbotRepository;
    private final UserRepository userRepository;

    private final List<String> REAL_ESTATE_KEYWORDS = Arrays.asList(
            "property", "house", "villa", "land", "apartment", "asset", "node", "estate",
            "price", "cost", "budget", "cheap", "lowest", "expensive", "highest", "luxury",
            "buy", "sell", "book", "appointment", "viewing", "visit", "schedule",
            "deed", "legal", "document", "inventory", "total", "count", "list", "all",
            "colombo", "kandy", "galle", "gampaha", "matara", "kurunegala", "negombo"
    );

    @Override
    public ChatbotDTO processChat(ChatbotDTO dto) {
        String prompt = (dto.getPrompt() != null) ? dto.getPrompt().toLowerCase().trim() : "";
        String reply;

        List<Property> allProps = propertyRepository.findAll();

        boolean hasDomainKeyword = REAL_ESTATE_KEYWORDS.stream().anyMatch(prompt::contains);

        if (!hasDomainKeyword) {
            reply = "[Access Restricted]: I am Nexus AI, strictly programmed for NexusEstate Real Estate intelligence. " +
                    "Your query does not relate to our properties, prices, locations, or viewings.";
        } else {
            List<Property> matchedProps = allProps.stream()
                    .filter(p -> {
                        String title = p.getTitle() != null ? p.getTitle().toLowerCase() : "";
                        String desc = p.getDescription() != null ? p.getDescription().toLowerCase() : "";
                        String locStr = p.getLocation() != null ? p.getLocation().toString().toLowerCase() : "";

                        return (prompt.contains("colombo") && (title.contains("colombo") || locStr.contains("colombo"))) ||
                                (prompt.contains("galle") && (title.contains("galle") || locStr.contains("galle"))) ||
                                (prompt.contains("kandy") && (title.contains("kandy") || locStr.contains("kandy"))) ||
                                (title.length() > 0 && prompt.contains(title));
                    })
                    .collect(Collectors.toList());

            if (!matchedProps.isEmpty()) {
                StringBuilder sb = new StringBuilder("Nexus AI Analysis: Found " + matchedProps.size() + " asset node(s) matching your criteria:\n");
                for (Property p : matchedProps) {
                    sb.append("• ").append(p.getTitle()).append(" - LKR ").append(String.format("%,.2f", p.getPrice()))
                            .append(" (Status: ").append(p.getStatus()).append(")\n");
                }
                reply = sb.toString();
            } else if (prompt.contains("cheap") || prompt.contains("lowest") || prompt.contains("budget")) {
                reply = allProps.stream().filter(p -> p.getPrice() != null).min(Comparator.comparing(Property::getPrice))
                        .map(p -> "Lowest Investment Node: '" + p.getTitle() + "' priced at LKR " + String.format("%,.2f", p.getPrice()))
                        .orElse("No property nodes registered yet.");

            } else if (prompt.contains("luxury") || prompt.contains("highest") || prompt.contains("expensive")) {
                reply = allProps.stream().filter(p -> p.getPrice() != null).max(Comparator.comparing(Property::getPrice))
                        .map(p -> "Top Premier Asset: '" + p.getTitle() + "' valued at LKR " + String.format("%,.2f", p.getPrice()))
                        .orElse("No luxury properties registered yet.");


            } else if (prompt.contains("how many") || prompt.contains("total") || prompt.contains("count") || prompt.contains("list") || prompt.contains("all")) {
                reply = "Nexus Inventory Status: There are currently " + allProps.size() + " active property nodes published on the network.";

            } else if (prompt.contains("deed") || prompt.contains("legal") || prompt.contains("document")) {
                reply = "Deed Protocol: Every asset listed in NexusEstate requires valid deed identifiers cross-checked with the Land Registry.";

            } else if (prompt.contains("book") || prompt.contains("appointment") || prompt.contains("viewing") || prompt.contains("visit")) {
                reply = "Viewing Protocol: You can book a viewing by going to 'Properties' and clicking 'Book Now' on your desired asset node.";

            } else {
                reply = "Nexus AI: I analyze live properties in NexusEstate. Ask me about property locations (e.g. 'Colombo', 'Galle'), cheapest properties, or total asset counts.";
            }
        }

        Chatbot entity = new Chatbot();
        entity.setUserPrompt(dto.getPrompt());
        entity.setAiReply(reply);
        entity.setCreatedAt(LocalDateTime.now());

        if (dto.getUserId() != null) {
            userRepository.findById(dto.getUserId()).ifPresent(entity::setUser);
        }

        chatbotRepository.save(entity);

        return new ChatbotDTO(entity.getId(), entity.getUserPrompt(), reply, dto.getUserId(), entity.getCreatedAt());
    }
}