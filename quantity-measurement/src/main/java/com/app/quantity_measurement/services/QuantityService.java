package com.app.quantity_measurement.services;
import com.app.quantity_measurement.repository.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.quantity_measurement.dto.QuantityRequestDTO;
import com.app.quantity_measurement.dto.QuantityResponseDTO;
import com.app.quantity_measurement.entities.*;

@Service
public class QuantityService {

    @Autowired
    private ConversionHistoryRepository conversionHistoryRepository;

    public QuantityResponseDTO process(QuantityRequestDTO request) {
        switch (request.getOperationType()) {
            case CONVERSION:
                return handleConversion(request);
            case COMPARISON:
                return handleComparison(request);
            case ARITHMETIC:
                return handleArithmetic(request);
            default:
                throw new IllegalArgumentException("Unknown operation type");
        }
    }

    public QuantityResponseDTO handleConversion(QuantityRequestDTO request) {
        double output = request.getUnit1().convertTo(request.getValue1(), request.getUnit2());

        saveHistory(request, output, null);

        QuantityResponseDTO response = new QuantityResponseDTO();
        response.setUnit1(request.getUnit1());
        response.setValue1(request.getValue1());
        response.setUnit2(request.getUnit2());
        response.setUnitType(request.getUnit1().getUnitType());
        response.setOperationType(OperationType.CONVERSION);
        response.setOutputValue(output);
        return response;
    }

    public QuantityResponseDTO handleComparison(QuantityRequestDTO request) {
        int result = request.getUnit1().compareWith(request.getValue1(), request.getUnit2(), request.getValue2());

        String comparisonResult;
        if (result > 0) comparisonResult = "GREATER";
        else if (result < 0) comparisonResult = "LESSER";
        else comparisonResult = "EQUAL";

        saveHistory(request, null, comparisonResult);

        QuantityResponseDTO response = new QuantityResponseDTO();
        response.setUnit1(request.getUnit1());
        response.setValue1(request.getValue1());
        response.setUnit2(request.getUnit2());
        response.setValue2(request.getValue2());
        response.setUnitType(request.getUnit1().getUnitType());
        response.setOperationType(OperationType.COMPARISON);
        response.setComparisonResult(comparisonResult);
        return response;
    }

    public QuantityResponseDTO handleArithmetic(QuantityRequestDTO request) {
        // convert both values to base unit first
        double base1 = request.getUnit1().getToBase().applyAsDouble(request.getValue1());
        double base2 = request.getUnit2().getToBase().applyAsDouble(request.getValue2());

        double output;
        switch (request.getArithmeticType()) {
            case ADD:
                output = base1 + base2;
                break;
            case SUBTRACT:
                output = base1 - base2;
                break;
            case MULTIPLY:
                output = base1 * base2;
                break;
            case DIVIDE:
                if (base2 == 0) throw new IllegalArgumentException("Cannot divide by zero");
                output = base1 / base2;
                break;
            default:
                throw new IllegalArgumentException("Unknown arithmetic type");
        }

        saveHistory(request, output, null);

        QuantityResponseDTO response = new QuantityResponseDTO();
        response.setUnit1(request.getUnit1());
        response.setValue1(request.getValue1());
        response.setUnit2(request.getUnit2());
        response.setValue2(request.getValue2());
        response.setUnitType(request.getUnit1().getUnitType());
        response.setOperationType(OperationType.ARITHMETIC);
        response.setArithmeticType(request.getArithmeticType());
        response.setOutputValue(output);
        return response;
      
    }

    private void saveHistory(QuantityRequestDTO request, Double outputValue, String comparisonResult) {
        ConversionHistory history = new ConversionHistory();
        history.setUnit1(request.getUnit1());
        history.setValue1(request.getValue1());
        history.setUnit2(request.getUnit2());
        history.setValue2(request.getValue2());
        history.setUnitType(request.getUnit1().getUnitType());
        history.setOperationType(request.getOperationType());
        history.setArithmeticType(request.getArithmeticType());
        history.setOutputValue(outputValue);
        history.setComparisonResult(comparisonResult);
        conversionHistoryRepository.save(history);
    }
    public List<Unit> getAllUnits() {
        return Arrays.asList(Unit.values());
    }

    public List<Unit> getUnitsByType(UnitType unitType) {
        return Arrays.stream(Unit.values())
                .filter(u -> u.getUnitType() == unitType)
                .collect(Collectors.toList());
    }

    public List<UnitType> getAllUnitTypes() {
        return Arrays.asList(UnitType.values());
    }

    public List<ConversionHistory> getHistory() {
        return conversionHistoryRepository.findAll();
    }
    public List<ConversionHistory> getHistoryByOperation(OperationType operationType) {
        return conversionHistoryRepository.findByOperationType(operationType);
    }

    public List<ConversionHistory> getHistoryByUnitType(UnitType unitType) {
        return conversionHistoryRepository.findByUnitType(unitType);
    }
}