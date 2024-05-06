package com.example.WebBanVe.controllers.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebBanVe.entity.Passenger;
import com.example.WebBanVe.service.interf.IPassengerService;

@RestController
@RequestMapping("/api")
public class PassengerAPI {
    @Autowired
    private IPassengerService service;

    @GetMapping("/passenger")
    private ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/passenger/{id}")
    private ResponseEntity<?> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @PostMapping("/passenger")
    private ResponseEntity<?> insert(@RequestBody Passenger passenger) {
        service.insert(passenger);
        return ResponseEntity.ok(passenger);
    }

    @PutMapping("/passenger")
    private ResponseEntity<?> update(@RequestBody Passenger passenger) {
        return ResponseEntity.ok(service.update(passenger));
    }

    @DeleteMapping("/passenger/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
