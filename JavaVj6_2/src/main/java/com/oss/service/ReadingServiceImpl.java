package com.oss.service;

// ReadingService interface


import com.oss.model.Reading;
import com.oss.model.SmartDevice;
import com.oss.repository.ReadingRepository;
import com.oss.repository.SmartDeviceRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.oss.form.ReadingForm;
import java.util.*;import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
public class ReadingServiceImpl implements ReadingService {

    private final ReadingRepository readingRepository;

    @Autowired
    public ReadingServiceImpl(ReadingRepository readingRepository) {
        this.readingRepository = readingRepository;
    }

    @Override
    public List<Reading> getAllReadings() {
        return readingRepository.findAll();
    }
    @Override
    public Page<Reading> getAllReadings(Pageable pageable) {
        return readingRepository.findAll(pageable);
    }
    @Autowired
    private SmartDeviceRepository smartDeviceRepository;  // Assuming you have a SmartDeviceRepository

    @Override
    public void enterReading(ReadingForm readingForm) {
        // Step 1: Create a new Reading entity
        Reading reading = new Reading();

        // Step 2: Set values from ReadingForm to the Reading entity
        reading.setReadingValue(readingForm.getReadingValue());
        reading.setReadingMonth(readingForm.getReadingMonth());
        reading.setReadingYear(readingForm.getReadingYear());

        // Step 3: Retrieve SmartDevice using the smartDeviceId from the form
        Long smartDeviceId = readingForm.getSmartDeviceId();
        Optional<SmartDevice> optionalSmartDevice = smartDeviceRepository.findById(smartDeviceId);

        // Step 4: If SmartDevice doesn't exist, handle it accordingly
        SmartDevice smartDevice = optionalSmartDevice.orElseGet(() -> {
            SmartDevice newSmartDevice = new SmartDevice();
            newSmartDevice.setId(smartDeviceId);
            // Set other properties as needed
            smartDeviceRepository.save(newSmartDevice);  // Save the new SmartDevice to the database
            return newSmartDevice;
        });

        // Step 5: Set the SmartDevice in the Reading entity
        reading.setSmartDevice(smartDevice);

        // Step 6: Save the Reading entity to the database
        readingRepository.save(reading);
    }
}
