package com.app.quantity_measurement.dto;
import com.app.quantity_measurement.entities.*;

import lombok.Data;

@Data
public class QuantityRequestDTO {

    private Unit unit1;
    private Double value1;
    private Unit unit2;
    private Double value2;
    private OperationType operationType;
    private ArithmeticType arithmeticType;  // only filled for ARITHMETIC

    public QuantityRequestDTO() {}

    public Unit getUnit1() { return unit1; }
    public void setUnit1(Unit unit1) { this.unit1 = unit1; }

    public Double getValue1() { return value1; }
    public void setValue1(Double value1) { this.value1 = value1; }

    public Unit getUnit2() { return unit2; }
    public void setUnit2(Unit unit2) { this.unit2 = unit2; }

    public Double getValue2() { return value2; }
    public void setValue2(Double value2) { this.value2 = value2; }

    public OperationType getOperationType() { return operationType; }
    public void setOperationType(OperationType operationType) { this.operationType = operationType; }

    public ArithmeticType getArithmeticType() { return arithmeticType; }
    public void setArithmeticType(ArithmeticType arithmeticType) { this.arithmeticType = arithmeticType; }
}