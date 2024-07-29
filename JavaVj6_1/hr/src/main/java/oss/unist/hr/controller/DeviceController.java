package oss.unist.hr.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import oss.unist.hr.model.Client;
import oss.unist.hr.model.Reading;
import oss.unist.hr.model.SmartDevice;
import oss.unist.hr.repository.ClientRepository;
import oss.unist.hr.service.ClientService;
import oss.unist.hr.service.DeviceService;
import oss.unist.hr.repository.ReadingRepository;

//import javax.persistence.EntityNotFoundException;
import jakarta.persistence.*;
import java.util.List;
import java.util.Collections;
import java.util.Optional;


@RestController
@RequestMapping("/api/devices")
public class DeviceController {
    @Autowired
    private ReadingRepository readingRepository;
    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ClientRepository clientRepository;

    @PostMapping("/{clientId}/readings")
    public ResponseEntity<String> addReading(@PathVariable Long clientId) {
        try {
            Client client;
            client = clientService.getClientById(clientId);
            SmartDevice smartDevice = client.getSmartDevice();

            if (smartDevice == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Client does not have a smart device installed.");
            }

            int randomValue = (int) (Math.random() * 100);
            Reading reading = new Reading(randomValue, smartDevice);
            readingRepository.save(reading);

            return ResponseEntity.ok("Reading added successfully.");
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Client not found with ID: " + clientId);
        }
    }

    @GetMapping("/{clientId}/readings")
    public ResponseEntity<List<Reading>> getReadings(@PathVariable Long clientId) {
        try {
            Client client = clientService.getClientById(clientId);
            List<Reading> readings = deviceService.getReadingsForClient(clientId);

            return ResponseEntity.ok(readings);
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Collections.emptyList());
        }
    }
    @PostMapping //radi
    public ResponseEntity<String> addClient(@RequestBody Client newClient) {
        try {
            clientService.addClient(newClient);
            return ResponseEntity.ok("Client added successfully.");
        }  catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding client: " + ex.getMessage());
        }
    }
    @PostMapping("/{clientId}/devices") //radi
    public ResponseEntity<String> addDevice(@PathVariable Long clientId, @RequestBody SmartDevice newDevice) {
        try {
            Client client = clientService.getClientById(clientId);

            newDevice.setClient(client);

            deviceService.addDevice(newDevice);

            return ResponseEntity.ok("Device added successfully.");
        } catch (EntityNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Client not found with ID: " + clientId);
        }
    }
}
