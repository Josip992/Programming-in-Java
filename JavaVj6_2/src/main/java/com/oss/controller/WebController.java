package com.oss.controller;

import com.oss.model.Client;
import com.oss.model.Reading;
import com.oss.model.SmartDevice;
import com.oss.repository.ReadingRepository;
import com.oss.service.ClientService;
import com.oss.service.DeviceService;
import com.oss.service.ReadingService;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.oss.form.ReadingForm;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;

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
@Controller
public class WebController {

    @Autowired
    private ClientService clientService;
    @Autowired
    private DeviceService smartDeviceService;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private ReadingRepository readingRepository;

    @Autowired
    private ReadingService readingService;

    @GetMapping("/")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/enter-reading")
    public String showEnterReadingForm(Model model) {
        List<Client> clients = clientService.getAllClients();
        List<SmartDevice> devices = smartDeviceService.getAllDevices();
        model.addAttribute("clients", clients);
        model.addAttribute("devices", devices);
        return "enter-reading";
    }

    @PostMapping("/enter-reading")
    public String enterReading(@ModelAttribute ReadingForm readingForm) {
        readingService.enterReading(readingForm);
        return "redirect:/display-readings";
    }

    @GetMapping("/display-readings")
    public String displayReadings(Model model,  @PageableDefault(size = 3) Pageable pageable) {
        Page<Reading> page = readingService.getAllReadings(pageable);
        model.addAttribute("readings", page.getContent());
        model.addAttribute("currentPage", page.getNumber());
        model.addAttribute("totalPages", page.getTotalPages());
        return "display-readings";
    }
}

