package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.EquipmentOperatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/equipmentoperators")
@CrossOrigin
public class EquipmentOperatorController {

    @Autowired
    private EquipmentOperatorService equipmentOperatorService;

    @GetMapping("/list")
    public Object getEquipmentOperatorList() {
        return equipmentOperatorService.list();
    }
}
