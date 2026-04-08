package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bills")
@CrossOrigin
public class BillController {

    @Autowired
    private BillService billService;

    @GetMapping("/list")
    public Object getBillList() {
        return billService.list();
    }
}
