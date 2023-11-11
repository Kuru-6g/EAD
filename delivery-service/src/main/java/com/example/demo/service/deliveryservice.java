package com.example.demo.service;
import java.util.List;

import com.example.demo.entity.delivery;
import com.example.demo.repository.deliveryrepo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class deliveryservice {
    @Autowired
    private deliveryrepo repository;

    public delivery savedelivery( delivery Delivery){
        return repository.save(Delivery);
    }

    public List<delivery> getDElivery(){
        return repository.findAll();
    }
    public delivery getdeliveryById(int id) {
        return repository.findById(id).orElse(null);
    }
    public String deletedelivery(int id) {
        repository.deleteById(id);
        return "Delivery removed !! " + id;
    }
    public delivery updateDelivery(delivery Delivery) {
        delivery existingDelivery = repository.findById(Delivery.getId ()).orElse(null);
        existingDelivery.setRecipientName(Delivery.getRecipientName());
        existingDelivery.setDeliveryStatus(Delivery.getDeliveryStatus());
        existingDelivery.setDeliveryAddress(Delivery.getDeliveryAddress());
        existingDelivery.setDeliveryDate(Delivery.getDeliveryDate());

        return repository.save(existingDelivery);
    }
}
