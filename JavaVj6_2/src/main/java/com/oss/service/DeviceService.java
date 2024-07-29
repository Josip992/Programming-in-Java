package com.oss.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.oss.model.Reading;
import com.oss.model.SmartDevice;
import com.oss.repository.ReadingRepository;
import com.oss.repository.SmartDeviceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;
@Service
public class DeviceService {
    @Autowired
    private SmartDeviceRepository smartDeviceRepository;

    @Autowired
    private ReadingRepository readingRepository;

    public List<SmartDevice> getAllDevices() {
        return smartDeviceRepository.findAll();
    }

    public SmartDevice getDeviceByName(String deviceName) {
        return smartDeviceRepository.findByDeviceName(deviceName).orElse(null);
    }

    public List<Reading> getReadingsForClient(Long clientId) {
        return readingRepository.findBySmartDevice_Client_Id(clientId);
    }

    public Page<Reading> getPaginatedReadingsForClient(Long clientId, int page, int size, String sortBy, String sortOrder) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.fromString(sortOrder), sortBy));
        return readingRepository.findBySmartDevice_Client_Id(clientId, pageable);
    }
    public void addDevice(SmartDevice newDevice) {
        smartDeviceRepository.save(newDevice);
    }
}