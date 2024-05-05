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

import com.example.WebBanVe.entity.Post;
import com.example.WebBanVe.service.interf.IPostService;

@RestController
@RequestMapping("/api")
public class PostAPI {
	@Autowired
	private IPostService service;
	@GetMapping("/post")
	private ResponseEntity<?> getAll(){
		return ResponseEntity.ok(service.getAll());
	}
	@GetMapping("/post/{id}")
	private ResponseEntity<?> getOne(@PathVariable Long id){
		return ResponseEntity.ok(service.getOne(id));
	}
	@PostMapping("/post")
	private ResponseEntity<?> insert(@RequestBody Post post){
		service.insert(post);
		return ResponseEntity.ok(post);
	}
	@PutMapping("/post")
	private ResponseEntity<?> update(@RequestBody Post post){

		return ResponseEntity.ok(service.update(post));
	}
	@DeleteMapping("/post/{id}")
	private ResponseEntity<?> delete(@PathVariable Long id){

		return ResponseEntity.ok(service.delete(id));
	}
}
