package com.example.WebBanVe.service.interf;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.WebBanVe.entity.User;

@Component
public interface IUserService {
	List<User> getAll();

	User getOne(Long id);

	boolean insert(User user);

	boolean update(User user);

	boolean delete(Long id);

}
