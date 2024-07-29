package com.oss.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import com.oss.model.SmartDevice;

@Repository
public interface SmartDeviceRepository extends JpaRepository<SmartDevice, Long> {
    Optional<SmartDevice> findByDeviceName(String deviceName);
}
