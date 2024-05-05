package com.example.WebBanVe.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.WebBanVe.entity.Post;
import com.example.WebBanVe.repository.PostRepository;
import com.example.WebBanVe.service.interf.IPostService;

@Service
public class PostService implements IPostService{
	@Autowired
	private PostRepository repo; 

	@Override
	public List<Post> getAll() {
		return repo.findAll();
	}

	@Override
	public Post getOne(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public boolean insert(Post post) {
		try {
			repo.save(post);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Post post) {
		try {
			repo.save(post);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Long id) {
		try {
			repo.deleteById(id);
			return true;
		}
		catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
