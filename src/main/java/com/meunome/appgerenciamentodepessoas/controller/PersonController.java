package com.meunome.appgerenciamentodepessoas.controller;

import com.meunome.appgerenciamentodepessoas.dto.MessageResponseDTO;
import com.meunome.appgerenciamentodepessoas.dto.PersonDTO;
import com.meunome.appgerenciamentodepessoas.exceptions.PersonNotfoundException;
import com.meunome.appgerenciamentodepessoas.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    @Autowired
    private PersonService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponseDTO createPerson(@RequestBody @Valid PersonDTO personDTO) {
        return service.createPerson(personDTO);
    }

    @GetMapping
    public List<PersonDTO> getAll(){
        return service.getAllPerson();
    }

    @GetMapping("/{id}")
    public PersonDTO findById(@PathVariable("id") Long id) throws PersonNotfoundException {
            return service.findById(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("id") Long id) throws PersonNotfoundException {
        service.deleteById(id);
    }

    @PutMapping("/{id}")
    public MessageResponseDTO updateById(@PathVariable("id") Long id, @RequestBody @Valid PersonDTO person) throws PersonNotfoundException{
        return service.updateById(id, person);
    }

}
