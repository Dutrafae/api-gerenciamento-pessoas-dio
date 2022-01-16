package com.meunome.appgerenciamentodepessoas.service;

import com.meunome.appgerenciamentodepessoas.dto.MessageResponseDTO;
import com.meunome.appgerenciamentodepessoas.dto.PersonDTO;
import com.meunome.appgerenciamentodepessoas.entity.Person;
import com.meunome.appgerenciamentodepessoas.repository.PersonRepository;
import com.meunome.appgerenciamentodepessoas.utils.PersonUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Objects;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    PersonRepository personRepository;

    @InjectMocks
    PersonService personService;

    @Test
    void test_given_personDTO_return_saved_message(){
        PersonDTO personDTO = PersonUtils.createFakeDTO();
        Person expectedSavedPerson = PersonUtils.createFakeEntity();

        Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(expectedSavedPerson);

        MessageResponseDTO expectedSuccessMessage = createExpectedMessageResponse(expectedSavedPerson.getId());
        MessageResponseDTO succesMessage = personService.createPerson(personDTO);

        Assertions.assertEquals(expectedSuccessMessage, succesMessage);
    }

    private MessageResponseDTO createExpectedMessageResponse(Long id) {
        return MessageResponseDTO
                .builder()
                .message("Created person with ID " + id)
                .build();
    }

}

