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

import com.example.WebBanVe.entity.Ticket;
import com.example.WebBanVe.service.interf.ITicketService;

@RestController
@RequestMapping("/api")
public class TicketAPI {
    @Autowired
    private ITicketService service;

    @GetMapping("/ticket")
    private ResponseEntity<?> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/ticket/{id}")
    private ResponseEntity<?> getOne(@PathVariable Long id) {
        return ResponseEntity.ok(service.getOne(id));
    }

    @PostMapping("/ticket")
    private ResponseEntity<?> insert(@RequestBody Ticket ticket) {
        service.insert(ticket);
        return ResponseEntity.ok(ticket);
    }

    @PutMapping("/ticket")
    private ResponseEntity<?> update(@RequestBody Ticket ticket) {
        return ResponseEntity.ok(service.update(ticket));
    }

    @DeleteMapping("/ticket/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id) {
        return ResponseEntity.ok(service.delete(id));
    }
}
