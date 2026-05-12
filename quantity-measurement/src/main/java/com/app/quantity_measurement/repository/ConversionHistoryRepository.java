package com.app.quantity_measurement.repository;
import com.app.quantity_measurement.entities.*;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConversionHistoryRepository extends JpaRepository<ConversionHistory, Long> {

    List<ConversionHistory> findByOperationType(OperationType operationType);
    List<ConversionHistory> findByUnitType(UnitType unitType);
}