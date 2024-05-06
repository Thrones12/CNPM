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

import com.example.WebBanVe.entity.Order;
import com.example.WebBanVe.service.interf.IOrderService;

@RestController
@RequestMapping("/api")
public class OrderAPI {

    @Autowired
    private IOrderService service;

    @GetMapping("/order")
    private ResponseEntity<?> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/order/{id}")
    private ResponseEntity<?> getOne(@PathVariable Long id){
        return ResponseEntity.ok(service.getOne(id));
    }

    @PostMapping("/order")
    private ResponseEntity<?> insert(@RequestBody Order order){
        boolean result = service.insert(order);
        if (result) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.badRequest().body("Failed to insert order.");
        }
    }

    @PutMapping("/order")
    private ResponseEntity<?> update(@RequestBody Order order){
        boolean result = service.update(order);
        if (result) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.badRequest().body("Failed to update order.");
        }
    }

    @DeleteMapping("/order/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id){
        boolean result = service.delete(id);
        if (result) {
            return ResponseEntity.ok("Order with id " + id + " has been deleted.");
        } else {
            return ResponseEntity.badRequest().body("Failed to delete order with id " + id);
        }
    }
}
