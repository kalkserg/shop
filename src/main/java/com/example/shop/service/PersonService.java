package com.example.shop.service;

import com.example.shop.model.Cart;
import com.example.shop.model.Person;

import java.util.Set;

public interface PersonService {
    Person createPerson(Person person);
    Person updatePerson(Person person);
    boolean deletePerson(Person person);
    Person getPersonById(int id) throws MyException;
    Set<Person> getAllPersons();
    Person addNewCart(Person person, Integer newIdCart);
}
