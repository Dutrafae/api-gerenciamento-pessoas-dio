package com.meunome.appgerenciamentodepessoas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotfoundException extends Exception{

    public PersonNotfoundException(Long id){
        super("Person with id " + id + " not found!.");
    }
}
