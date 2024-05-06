package com.example.WebBanVe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebBanVe.entity.Ticket;
import com.example.WebBanVe.repository.TicketRepository;
import com.example.WebBanVe.service.interf.ITicketService;

@Service
public class TicketService implements ITicketService {
    @Autowired
    private TicketRepository repo;

    @Override
    public List<Ticket> getAll() {
        return repo.findAll();
    }

    @Override
    public Ticket getOne(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public boolean insert(Ticket ticket) {
        try {
            repo.save(ticket);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Ticket ticket) {
        try {
            repo.save(ticket);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            repo.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
