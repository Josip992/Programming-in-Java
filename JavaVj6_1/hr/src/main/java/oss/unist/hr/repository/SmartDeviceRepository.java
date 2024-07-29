package oss.unist.hr.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import oss.unist.hr.model.SmartDevice;
    public interface SmartDeviceRepository extends JpaRepository<SmartDevice, Long> {
        Optional<SmartDevice> findByDeviceName(String deviceName);
    }
