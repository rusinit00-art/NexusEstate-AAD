package org.ijse.nexusestate_aad.service;

import org.ijse.nexusestate_aad.dto.AppoinmentDTO;
import java.util.List;

public interface AppoinmentService {
    String saveAppoinment(AppoinmentDTO dto);
    List<AppoinmentDTO> getAllAppoinments();
    String updateStatus(Long id, String status);
}