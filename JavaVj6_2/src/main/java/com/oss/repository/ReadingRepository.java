package com.oss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.oss.model.Reading;
import com.oss.model.SmartDevice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, Long> {

    @Query("SELECT SUM(r.readingValue) FROM Reading r WHERE r.readingYear = :year")
    Integer getTotalConsumptionForYear(@Param("year") int year);
    @Query("SELECT r.readingMonth AS month, SUM(r.readingValue) AS total " +
            "FROM Reading r WHERE r.readingYear = :year GROUP BY r.readingMonth")
    List<Map<String, Integer>> getMonthlyConsumptionForYear(@Param("year") int year);


    @Query("SELECT SUM(r.readingValue) FROM Reading r " +
            "WHERE r.readingYear = :year AND r.readingMonth = :month")
    Integer getMonthlyConsumptionForMonthInYear(@Param("year") int year, @Param("month") int month);

    List<Reading> findBySmartDevice_Client_Id(Long clientId);
    Optional<Reading> findBySmartDeviceAndReadingMonthAndReadingYear(SmartDevice smartDevice, int readingMonth, int readingYear);

    Page<Reading> findBySmartDevice_Client_Id(Long clientId, Pageable pageable);

}