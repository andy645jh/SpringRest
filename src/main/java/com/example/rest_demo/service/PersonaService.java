package com.example.rest_demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.rest_demo.bean.Persona;
import com.example.rest_demo.repository.PersonaRepository;


@Service
public class PersonaService implements IPersonaService {

    @Autowired
    private PersonaRepository repository;

    @Override
    public List<Persona> findAll() {

        return (List<Persona>) repository.findAll();
    }
    
    public Persona save(Persona p)    
    {
    	return repository.save(p);
    }
    
    public Persona findId(Long id)
    {
    	return repository.findById(id).get();
    }   

    
    public void deleteId(Long id)
    {
    	repository.deleteById(id);
    }
}