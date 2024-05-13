package com.example.WebBanVe.controllers.api;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.WebBanVe.entity.Location;
import com.example.WebBanVe.service.interf.ILocationService;

@RestController
@RequestMapping("/api")
public class LocationAPI 
{
    @Autowired
    private ILocationService locationService;

    @GetMapping("/location")
    private ResponseEntity<?> getAll() {
        return ResponseEntity.ok(locationService.getAll());
    }

    @GetMapping("/location/{id}")
    private ResponseEntity<?> getOne(@PathVariable Long id) {
        Location location = locationService.getOne(id);
        if (location != null) {
            return ResponseEntity.ok(location);
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }

    @PostMapping("/location")
    private ResponseEntity<?> insert(@RequestBody Location location) {
        boolean success = locationService.insert(location);
        if (success) {
            return ResponseEntity.status(201).body(location); // 
        } else {
            return ResponseEntity.status(500).body("Could not create location"); 
        }
    }

    @PutMapping("/location")
    private ResponseEntity<?> update(@RequestBody Location location) {
        boolean success = locationService.update(location);
        if (success) {
            return ResponseEntity.ok(location); 
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }

    @DeleteMapping("/location/{id}")
    private ResponseEntity<?> delete(@PathVariable Long id) {
        boolean success = locationService.delete(id);
        if (success) {
            return ResponseEntity.noContent().build(); 
        } else {
            return ResponseEntity.notFound().build(); 
        }
    }
    
    @GetMapping("/districts")
    public List<String> getDistrictsByProvince(@RequestParam String province) {
        List<String> districts = locationService.getDistrict(province);
        return districts;
    }
    
    @GetMapping("/wards")
    public List<String> getWardsByDistrict(
            @RequestParam String province,
            @RequestParam String district) {
        List<String> wards = locationService.getWard(province, district);
        System.out.println(wards);
        return wards;
    }

}
