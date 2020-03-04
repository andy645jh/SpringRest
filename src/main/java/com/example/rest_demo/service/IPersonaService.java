package com.example.rest_demo.service;


import java.util.List;

import com.example.rest_demo.bean.Persona;

public interface IPersonaService {

    List<Persona> findAll();
    Persona findId(Long id);
    void deleteId(Long id);
    Persona save(Persona p);  
}