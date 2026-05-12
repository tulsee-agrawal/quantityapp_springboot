package com.app.quantity_measurement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.app.quantity_measurement.dto.*;
import com.app.quantity_measurement.services.QuantityService;
import com.app.quantity_measurement.entities.*;


@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/quantity")

public class QuantityController {

    @Autowired
    private QuantityService quantityService;

    // 1. Get all units
    @GetMapping("/units")
    public ResponseEntity<List<Unit>> getAllUnits() {
        return ResponseEntity.ok(quantityService.getAllUnits());
    }

    // 2. Get units by unit type
    @GetMapping("/units/{unitType}")
    public ResponseEntity<List<Unit>> getUnitsByType(@PathVariable UnitType unitType) {
        return ResponseEntity.ok(quantityService.getUnitsByType(unitType));
    }

    // 3. Get all unit types (conversions)
    @GetMapping("/conversions")
    public ResponseEntity<List<UnitType>> getAllUnitTypes() {
        return ResponseEntity.ok(quantityService.getAllUnitTypes());
    }

    // 4. Get conversions by unit type
    @GetMapping("/conversions/{unitType}")
    public ResponseEntity<List<Unit>> getConversionsByUnitType(@PathVariable UnitType unitType) {
        return ResponseEntity.ok(quantityService.getUnitsByType(unitType));
    }

    // 5. Perform conversion
    @PostMapping("/convert")
    public ResponseEntity<QuantityResponseDTO> convert(@RequestBody QuantityRequestDTO request) {
        return ResponseEntity.ok(quantityService.handleConversion(request));
    }

    // 6. Perform comparison
    @PostMapping("/compare")
    public ResponseEntity<QuantityResponseDTO> compare(@RequestBody QuantityRequestDTO request) {
        return ResponseEntity.ok(quantityService.handleComparison(request));
    }

    // 7. Perform arithmetic
    @PostMapping("/arithmetic")
    public ResponseEntity<QuantityResponseDTO> arithmetic(@RequestBody QuantityRequestDTO request) {
        return ResponseEntity.ok(quantityService.handleArithmetic(request));
    }

    @GetMapping("/history")
    public ResponseEntity<List<ConversionHistory>> getHistory() {
        return ResponseEntity.ok(quantityService.getHistory());
    }
}