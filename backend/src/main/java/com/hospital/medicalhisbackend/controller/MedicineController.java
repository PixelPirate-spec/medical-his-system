package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/medicines")
@CrossOrigin
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @GetMapping("/list")
    public Object getMedicineList() {
        return medicineService.list();
    }
}
