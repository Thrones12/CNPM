package com.example.WebBanVe.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//Phong
@Entity
@DiscriminatorValue("ADMIN")
@Table(name="admin")
@Data
@PrimaryKeyJoinColumn(name = "user_id")
@Builder
public class Admin extends User {
}