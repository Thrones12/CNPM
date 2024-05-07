package com.example.WebBanVe.service.interf;

import java.util.List;

import com.example.WebBanVe.entity.Transport;

public interface ITransportService 
{
	List<Transport> getAll();

	Transport getOne(Long id);

	boolean insert(Transport transport);

	boolean update(Transport transport);

	boolean delete(Long id);
}