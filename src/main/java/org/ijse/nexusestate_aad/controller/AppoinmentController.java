package org.ijse.nexusestate_aad.controller;

import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.dto.AppoinmentDTO;
import org.ijse.nexusestate_aad.service.AppoinmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appoinments")
@RequiredArgsConstructor
public class AppoinmentController {

    private final AppoinmentService appoinmentService;

    @PostMapping("/book")
    public ResponseEntity<String> book(@RequestBody AppoinmentDTO dto) {
        return ResponseEntity.ok(appoinmentService.saveAppoinment(dto));
    }

    @GetMapping("/all")
    public ResponseEntity<List<AppoinmentDTO>> getAll() {
        return ResponseEntity.ok(appoinmentService.getAllAppoinments());
    }

    @PutMapping("/status/{id}/{status}")
    public ResponseEntity<String> updateStatus(@PathVariable Long id, @PathVariable String status) {
        return ResponseEntity.ok(appoinmentService.updateStatus(id, status));
    }
}