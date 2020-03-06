package com.example.rest_demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest_demo.bean.Persona;
import com.example.rest_demo.service.IPersonaService;



@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE,RequestMethod.PUT})
public class PersonaController {

    @Autowired
    private IPersonaService personaService;
    
    @RequestMapping(value="/personas", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    public List<Persona> findAll(Model model) {

    	return personaService.findAll();
    }      
    
    @RequestMapping(value="/personas/{id}", method=RequestMethod.GET,produces=MediaType.APPLICATION_JSON_VALUE)
    public Persona findPersonas(Model model, @PathVariable Long id) {

    	return personaService.findId(id);
    }  
    
    @RequestMapping(value="/personas/{id}", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updatePersona(@RequestBody Persona persona, @PathVariable long id)
    {
		Persona newPersona = personaService.findId(id);
		if(newPersona!=null)
		{
			newPersona.setFirstname(persona.getFirstname());
			newPersona.setLastname(persona.getLastname());
			Persona pSaved= personaService.save(newPersona);
			if(pSaved.getId().equals(id))
			{
				return new ResponseEntity<>("Data updated successfully", HttpStatus.OK);
			}    			
		}  		
        
        return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);         
    
    }
    
    @RequestMapping(value="/personas/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<?> deletePersona(Model model, @PathVariable Long id) {
    	
    	try {
    		personaService.deleteId(id);
            return new ResponseEntity<>("Data deleted successfully", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Resource not found", HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value="/personas/create", method=RequestMethod.PUT)
    public ResponseEntity<?> createPersona(@RequestBody Persona persona) {

    	Persona p =personaService.save(persona); 
    	if(p != null)
    	{
    		return new ResponseEntity<>("Data created successfully", HttpStatus.ACCEPTED);
    	}
    	return new ResponseEntity<>("Cannot created", HttpStatus.NOT_FOUND);
    }   

}