package org.ijse.nexusestate_aad.controller;

import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.dto.InquiryDTO;
import org.ijse.nexusestate_aad.service.InquiryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inquiries")
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;

    @PostMapping("/send")
    public ResponseEntity<String> sendInquiry(@RequestBody InquiryDTO dto) {
        return ResponseEntity.ok(inquiryService.saveInquiry(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<InquiryDTO>> getAllInquiries() {
        return ResponseEntity.ok(inquiryService.getAllInquiries());
    }
}