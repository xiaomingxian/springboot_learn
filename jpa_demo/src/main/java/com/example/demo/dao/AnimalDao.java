package com.example.demo.dao;

import com.example.demo.pojo.Animal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalDao extends JpaRepository<Animal, Integer> {
}
