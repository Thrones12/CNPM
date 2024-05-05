package com.example.WebBanVe.service.interf;

import java.util.List;

import org.springframework.stereotype.Component;

import com.example.WebBanVe.entity.Post;

@Component
public interface IPostService {
	List<Post> getAll();

	Post getOne(Long id);

	boolean insert(Post post);

	boolean update(Post post);

	boolean delete(Long id);

}
