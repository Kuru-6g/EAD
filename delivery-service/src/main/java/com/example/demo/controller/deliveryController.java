package com.example.demo.controller;


import com.example.demo.entity.delivery;
import com.example.demo.service.deliveryservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class deliveryController {
    @Autowired
    private deliveryservice service;

    @PostMapping("/addDelivery")
    public delivery addProduct(@RequestBody delivery Delivery) {
        return service.savedelivery(Delivery);
    }
    @GetMapping("/delivery")
    public List<delivery> findAlldelivery() {
        return service.getDElivery();
    }

    @GetMapping("/deliveryById/{id}")
    public delivery getdeliveryById(@PathVariable int id) {
        return service.getdeliveryById(id);
    }
    @PutMapping("/update")
    public delivery updateDelivery(@RequestBody delivery Delivery) {
        return service.updateDelivery(Delivery);
    }
    @DeleteMapping("/delete/{id}")
    public String deletedelivery(@PathVariable int id) {
        return service.deletedelivery(id);
    }

}
