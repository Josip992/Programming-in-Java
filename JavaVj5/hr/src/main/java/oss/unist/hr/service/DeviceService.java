package oss.unist.hr.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import oss.unist.hr.model.Reading;
import oss.unist.hr.model.SmartDevice;
import oss.unist.hr.repository.ReadingRepository;
import oss.unist.hr.repository.SmartDeviceRepository;

import java.util.List;
@Service
public class DeviceService {
    @Autowired
    private SmartDeviceRepository smartDeviceRepository;

    @Autowired
    private ReadingRepository readingRepository;

    public SmartDevice getDeviceByName(String deviceName) {
        return smartDeviceRepository.findByDeviceName(deviceName).orElse(null);
    }

    public List<Reading> getReadingsForClient(Long clientId) {
        return readingRepository.findBySmartDevice_Client_Id(clientId);
    }

    public void addDevice(SmartDevice newDevice) {
        smartDeviceRepository.save(newDevice);
    }
}