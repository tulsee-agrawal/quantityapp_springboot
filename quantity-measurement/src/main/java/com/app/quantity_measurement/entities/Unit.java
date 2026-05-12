package com.app.quantity_measurement.entities;
import java.util.function.DoubleUnaryOperator;
public enum Unit {

    // LENGTH (base: meter)
    KILOMETER(UnitType.LENGTH, v -> v * 1000, v -> v / 1000),
    METER(UnitType.LENGTH, v -> v, v -> v),
    CENTIMETER(UnitType.LENGTH, v -> v / 100, v -> v * 100),
    MILLIMETER(UnitType.LENGTH, v -> v / 1000, v -> v * 1000),

    // WEIGHT (base: gram)
    KILOGRAM(UnitType.WEIGHT, v -> v * 1000, v -> v / 1000),
    GRAM(UnitType.WEIGHT, v -> v, v -> v),
    MILLIGRAM(UnitType.WEIGHT, v -> v / 1000, v -> v * 1000),
    POUND(UnitType.WEIGHT, v -> v * 453.592, v -> v / 453.592),

    // VOLUME (base: liter)
    LITER(UnitType.VOLUME, v -> v, v -> v),
    MILLILITER(UnitType.VOLUME, v -> v / 1000, v -> v * 1000),
    CUBIC_METER(UnitType.VOLUME, v -> v * 1000, v -> v / 1000),
    GALLON(UnitType.VOLUME, v -> v * 3.78541, v -> v / 3.78541),

    // TEMPERATURE (base: celsius)
    CELSIUS(UnitType.TEMPERATURE, v -> v, v -> v),
    FAHRENHEIT(UnitType.TEMPERATURE, v -> (v - 32) * 5 / 9, v -> (v * 9 / 5) + 32),
    KELVIN(UnitType.TEMPERATURE, v -> v - 273.15, v -> v + 273.15);

    private final UnitType unitType;
    private final DoubleUnaryOperator toBase;
    private final DoubleUnaryOperator fromBase;

    Unit(UnitType unitType, DoubleUnaryOperator toBase, DoubleUnaryOperator fromBase) {
        this.unitType = unitType;
        this.toBase = toBase;
        this.fromBase = fromBase;
    }

    public double convertTo(double value, Unit target) {
        if (this.unitType != target.unitType) {
            throw new IllegalArgumentException("Cannot convert between different unit types");
        }
        return target.fromBase.applyAsDouble(this.getToBase().applyAsDouble(value));
    }

    public int compareWith(double value1, Unit other, double value2) {
        if (this.unitType != other.unitType) {
            throw new IllegalArgumentException("Cannot compare different unit types");
        }
        return Double.compare(this.getToBase().applyAsDouble(value1), other.getToBase().applyAsDouble(value2));
    }

    public UnitType getUnitType() {
        return unitType;
    }

	public DoubleUnaryOperator getToBase() {
		return toBase;
	}
}