package com.hospital.medicalhisbackend.controller;

import com.hospital.medicalhisbackend.service.BillItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/billitems")
@CrossOrigin
public class BillItemController {

    @Autowired
    private BillItemService billItemService;

    @GetMapping("/list")
    public Object getBillItemList() {
        return billItemService.list();
    }
}
