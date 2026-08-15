package org.ijse.nexusestate_aad.service;

import org.ijse.nexusestate_aad.dto.InquiryDTO;
import java.util.List;

public interface InquiryService {
    String saveInquiry(InquiryDTO dto);
    List<InquiryDTO> getAllInquiries();
}
