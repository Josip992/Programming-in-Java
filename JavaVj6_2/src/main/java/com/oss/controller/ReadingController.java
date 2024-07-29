package com.oss.controller;

import com.oss.model.Client;
import com.oss.model.SmartDevice;
import com.oss.service.ClientService;
import com.oss.service.DeviceService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


//set JAVA_HOME=C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2023.2.5\jbr ili set JAVA_HOME=C:\Users\Josip\AppData\Local\Programs\IntelliJ IDEA Community Edition\jbr
//cd /d I:\JavaProjekti\vj6_2\hr ili cd /d I:\JavaProjekti\vj6_2\hr
//mvnw spring-boot:run
//POST http://localhost:8080/api (dodavanje klijenta)
//POST http://localhost:8080/api/CLIENTID/devices (dodavanje uredaja)
/*{
		"name": "John Doe",
		"address": {
		"street": "123 Main St",
		"city": "YourCity"
		}
}*/
/*{
		"deviceName": "SmartDevice1"
}*/
//http://localhost:8080/h2-console -SHOW TABLES -SELECT * FROM table_name

@RestController
@RequestMapping("/api")
public class ReadingController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<String> addClient(@RequestBody Client newClient) {
        try {
            clientService.addClient(newClient);
            return ResponseEntity.ok("Client added successfully.");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error adding client: " + ex.getMessage());
        }
    }

    @PostMapping("/{clientId}/devices")
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