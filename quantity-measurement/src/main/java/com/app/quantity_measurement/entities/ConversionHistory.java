package com.app.quantity_measurement.entities;

import java.time.LocalDateTime;

 
import jakarta.persistence.*;

 
 
@Entity
@Table(name = "conversion_history")
public class ConversionHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Unit unit1;

    private Double value1;

    @Enumerated(EnumType.STRING)
    private Unit unit2;

    private Double value2;

    @Enumerated(EnumType.STRING)
    private UnitType unitType;

    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @Enumerated(EnumType.STRING)
    private ArithmeticType arithmeticType;

    private Double outputValue;

    private String comparisonResult;

    private LocalDateTime convertedAt;

    @PrePersist
    public void prePersist() {
        this.convertedAt = LocalDateTime.now();
    }

    public ConversionHistory() {}

    public Long getId() { return id; }

    public Unit getUnit1() { return unit1; }
    public void setUnit1(Unit unit1) { this.unit1 = unit1; }

    public Double getValue1() { return value1; }
    public void setValue1(Double value1) { this.value1 = value1; }

    public Unit getUnit2() { return unit2; }
    public void setUnit2(Unit unit2) { this.unit2 = unit2; }

    public Double getValue2() { return value2; }
    public void setValue2(Double value2) { this.value2 = value2; }

    public UnitType getUnitType() { return unitType; }
    public void setUnitType(UnitType unitType) { this.unitType = unitType; }

    public OperationType getOperationType() { return operationType; }
    public void setOperationType(OperationType operationType) { this.operationType = operationType; }

    public ArithmeticType getArithmeticType() { return arithmeticType; }
    public void setArithmeticType(ArithmeticType arithmeticType) { this.arithmeticType = arithmeticType; }

    public Double getOutputValue() { return outputValue; }
    public void setOutputValue(Double outputValue) { this.outputValue = outputValue; }

    public String getComparisonResult() { return comparisonResult; }
    public void setComparisonResult(String comparisonResult) { this.comparisonResult = comparisonResult; }

    public LocalDateTime getConvertedAt() { return convertedAt; }
}