package com.example.WebBanVe.service.interf;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.WebBanVe.entity.Manufacture;

@Component
public interface IManufactureService {
	List<Manufacture> getAll();
	Manufacture getOne(Long id);
    boolean insert(Manufacture manufacture);
    boolean update(Manufacture manufacture);
    boolean delete(Long id);
}