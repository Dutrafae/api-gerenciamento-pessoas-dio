package com.meunome.appgerenciamentodepessoas.repository;

import com.meunome.appgerenciamentodepessoas.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {


}
