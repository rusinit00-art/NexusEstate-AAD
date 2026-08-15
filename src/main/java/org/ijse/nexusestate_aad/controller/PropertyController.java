package org.ijse.nexusestate_aad.controller;

import lombok.RequiredArgsConstructor;
import org.ijse.nexusestate_aad.dto.PropertyDTO;
import org.ijse.nexusestate_aad.service.PropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/properties")
@RequiredArgsConstructor
public class PropertyController {

    private final PropertyService propertyService;

    @PostMapping("/save")
    public ResponseEntity<String> save(@RequestBody PropertyDTO dto) {
        return ResponseEntity.ok(propertyService.saveProperty(dto));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody PropertyDTO dto) {
        return ResponseEntity.ok(propertyService.updateProperty(id, dto));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        return ResponseEntity.ok(propertyService.deleteProperty(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<PropertyDTO>> getAll() {
        return ResponseEntity.ok(propertyService.getAllProperties());
    }
}