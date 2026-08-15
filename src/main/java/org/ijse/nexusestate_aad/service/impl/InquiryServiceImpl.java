package org.ijse.nexusestate_aad.service.impl;

import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.dto.InquiryDTO;
import org.ijse.nexusestate_aad.entity.Interactions.Inquiry;
import org.ijse.nexusestate_aad.repository.InquiryRepository;
import org.ijse.nexusestate_aad.repository.PropertyRepository;
import org.ijse.nexusestate_aad.repository.UserRepository;
import org.ijse.nexusestate_aad.service.InquiryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InquiryServiceImpl implements InquiryService {

    private final InquiryRepository inquiryRepository;
    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;

    @Override
    public String saveInquiry(InquiryDTO dto) {
        Inquiry inquiry = new Inquiry();
        inquiry.setMessage(dto.getMessage());
        inquiry.setDate(LocalDateTime.now());

        inquiry.setProperty(propertyRepository.findById(dto.getPropertyId()).orElse(null));
        inquiry.setUser(userRepository.findById(dto.getUserId()).orElse(null));

        inquiryRepository.save(inquiry);
        return "Inquiry sent successfully!";
    }

    @Override
    public List<InquiryDTO> getAllInquiries() {
        return inquiryRepository.findAll().stream().map(i -> new InquiryDTO(
                i.getId(),
                i.getMessage(),
                i.getDate(),
                i.getProperty() != null ? i.getProperty().getId() : null,
                i.getUser() != null ? i.getUser().getId() : null
        )).collect(Collectors.toList());
    }
}
