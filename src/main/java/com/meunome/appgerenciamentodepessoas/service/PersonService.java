package com.meunome.appgerenciamentodepessoas.service;

import com.meunome.appgerenciamentodepessoas.dto.MessageResponseDTO;
import com.meunome.appgerenciamentodepessoas.dto.PersonDTO;
import com.meunome.appgerenciamentodepessoas.entity.Person;
import com.meunome.appgerenciamentodepessoas.exceptions.PersonNotfoundException;
import com.meunome.appgerenciamentodepessoas.mapper.PersonMapper;
import com.meunome.appgerenciamentodepessoas.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository repository;
    private PersonMapper personMapper = PersonMapper.INSTANCE;


    public MessageResponseDTO createPerson(PersonDTO personDTO) {
        Person personToSave = personMapper.toModel(personDTO);
        repository.save(personToSave);
        return createMessageResponse(personToSave.getId(), "Person created with ID ");
    }

    public List<PersonDTO> getAllPerson(){
        List<PersonDTO> personsDTO = new ArrayList<>();
        repository.findAll().forEach( (person) -> { personsDTO.add(personMapper.toDTO(person)); });
        return personsDTO;
    }

    public PersonDTO findById(Long id) throws PersonNotfoundException{
        return personMapper.toDTO(verifyExists(id));
    }

    public void deleteById(Long id) throws PersonNotfoundException{
        repository.deleteById(verifyExists(id).getId());
    }

    public MessageResponseDTO updateById(Long id, PersonDTO person) throws PersonNotfoundException{
        verifyExists(id);
        Person personToUpdate = personMapper.toModel(person);
        Person updatedPerson = repository.save(personToUpdate);
        return createMessageResponse(updatedPerson.getId(), "Person updated with ID ");
    }

    private Person verifyExists(Long id) throws PersonNotfoundException{
        return repository.findById(id).orElseThrow(() -> new PersonNotfoundException(id));
    }

    private MessageResponseDTO createMessageResponse(Long id, String s){
        return MessageResponseDTO
                .builder()
                .message(s + id)
                .build();
    }
}
