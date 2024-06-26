package com.example.WebBanVe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.WebBanVe.entity.Manufacture;

@Repository
public interface ManufactureRepository extends JpaRepository<Manufacture, Long>
{

}