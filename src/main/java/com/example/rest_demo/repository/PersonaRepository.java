package com.example.rest_demo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.rest_demo.bean.Persona;


@Repository
public interface PersonaRepository extends CrudRepository<Persona, Long> {

}