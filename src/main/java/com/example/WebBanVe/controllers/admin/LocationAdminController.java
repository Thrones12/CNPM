package com.example.WebBanVe.controllers.admin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.WebBanVe.dto.PostDTO;
import com.example.WebBanVe.entity.Location;
import com.example.WebBanVe.entity.Post;
import com.example.WebBanVe.service.interf.ICustomerService;
import com.example.WebBanVe.service.interf.ILocationService;
import com.example.WebBanVe.service.interf.IPostService;

@Controller
@RequestMapping("/admin")
public class LocationAdminController 
{

    @Autowired
    private ILocationService locationService;
    
    @Autowired
    private IPostService postService;
    
    @Autowired
    private  ICustomerService customerService;

    @GetMapping("/location")
    public String getAllLocations(Model model) {
		model.addAttribute("pageName", "location");
        List<Location> locations = locationService.findAllByNameNotNull();
        model.addAttribute("locations", locations);
        return "admin/views/location/list";
    }

    @GetMapping("/location/{id}")
    public String getLocationDetails(@PathVariable Long id, Model model) {
		model.addAttribute("pageName", "location");
        Location location = locationService.getOne(id);
        model.addAttribute("location", location);
        model.addAttribute("posts", location.getPosts());
        model.addAttribute("postDTO", new PostDTO()); // Thêm postDTO vào model
        return "admin/views/location/detail";
    }


    @GetMapping("/location/create")
    public String showCreateLocationForm(Model model) {
		model.addAttribute("pageName", "location");
        model.addAttribute("location", new Location());
        return "admin/views/location/add";
    }

    @PostMapping("/location/create")
    public String createLocation(@ModelAttribute Location location, Model model) {
		model.addAttribute("pageName", "location");
        locationService.insert(location);
        return "redirect:/admin/location";
    }
    
    @PostMapping("/location/{id}/create-post")
    public String createPost(@PathVariable("id") Long locationId, @ModelAttribute PostDTO postDTO, Model model) {
		model.addAttribute("pageName", "location");
        Location location = locationService.getOne(locationId);
        Post post = new Post();
        post.setCustomer(customerService.getOne(1L)); 
        post.setLocation(location);
        post.setContent(postDTO.getContent());
        post.setRating(0);

        if (!postDTO.getPicture().isEmpty()) {
            try {
                String imageName = saveImageToFileSystem(postDTO.getPicture());
                post.setPicture(imageName);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        postService.insert(post);
        return "redirect:/admin/location/{id}";
    }

    private String saveImageToFileSystem(MultipartFile picture) throws IOException
    {
        String imageName = picture.getOriginalFilename();
        Path imagePath = Paths.get("uploads/", imageName);
        Files.copy(picture.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
        return imageName;
    }

    @GetMapping("/location/delete/{id}")
    public String deleteLocation(@PathVariable Long id) {
        locationService.delete(id);
        return "redirect:/admin/location";
    }
}