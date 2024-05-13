package com.example.WebBanVe.controllers.admin;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.nio.file.StandardCopyOption;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.WebBanVe.dto.PostDTO;
import com.example.WebBanVe.entity.Post;
import com.example.WebBanVe.service.interf.ICustomerService;
import com.example.WebBanVe.service.interf.ILocationService;
import com.example.WebBanVe.service.interf.IPostService;

@Controller
@RequestMapping("/admin")
public class PostAdminController {

    @Autowired
    private IPostService postService;
    
    @Autowired
    private  ICustomerService customerService;
    
    @Autowired
    private  ILocationService locationService;

    @GetMapping("/post")
    public String getAllPosts(Model model) {
        List<Post> posts = postService.getAll();
        model.addAttribute("posts", posts);
        return "admin/views/post/list";
    }

    @GetMapping("/post/create")
    public String showCreatePostForm(Model model) 
    {
    	model.addAttribute("customers", customerService.getAll());
    	model.addAttribute("locations", locationService.getAll());
        model.addAttribute("postDTO", new PostDTO());
        return "admin/views/post/add";
    }
    

    
    @PostMapping("/post/create")
    public String createPost(ModelMap model, @ModelAttribute("postDTO") PostDTO dto) 
    {
        Post post = new Post();
        post.setCustomer(customerService.getOne(dto.getCustomerId().longValue()));
        post.setLocation(locationService.getOne(dto.getLocationId().longValue()));
        post.setContent(dto.getContent());
        post.setRating(dto.getRating());

        if (!dto.getPicture().isEmpty()) 
        {
            try 
            {
                String imageName = saveImageToFileSystem(dto.getPicture());
                post.setPicture(imageName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        postService.insert(post);
        return "redirect:/admin/post";
    }

    private String saveImageToFileSystem(MultipartFile picture) throws IOException
    {
        String imageName = picture.getOriginalFilename();
        Path imagePath = Paths.get("uploads/", imageName);
        Files.copy(picture.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
        return imageName;
    }

    @GetMapping("/post/edit/{id}")
    public String showEditPostForm(@PathVariable Long id, Model model) {
        Post post = postService.getOne(id);
        model.addAttribute("post", post);
        return "admin/views/post/update";
    }

    @PostMapping("/post/edit")
    public String updatePost(@ModelAttribute PostDTO postDTO, Model model) {
        Post post = postService.getOne(postDTO.getId());
        post.setContent(postDTO.getContent());
        post.setRating(postDTO.getRating());
        if (!postDTO.getPicture().isEmpty()) {
        	try 
            {
                String imageName = saveImageToFileSystem(postDTO.getPicture());
                post.setPicture(imageName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        postService.update(post);
        return "redirect:/admin/post";
    }


    @GetMapping("/post/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.delete(id);
        return "redirect:/admin/post";
    }
}
