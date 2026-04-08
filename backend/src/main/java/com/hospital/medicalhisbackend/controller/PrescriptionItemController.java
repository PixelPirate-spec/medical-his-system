package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.PrescriptionItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/prescriptionitems")
@CrossOrigin
public class PrescriptionItemController {

    @Autowired
    private PrescriptionItemService prescriptionItemService;

    @GetMapping("/list")
    public Object getPrescriptionItemList() {
        return prescriptionItemService.list();
    }
}
