package com.example.shop.service;

import com.example.shop.model.Cart;
import com.example.shop.model.Person;
import com.example.shop.storage.PersonStorage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.example.shop.storage.PersonStorage.personStorageSet;

@Service
public class PersonServiceImpl implements PersonService{

    private PersonStorage personStorage;

    @Autowired
    public PersonServiceImpl(PersonStorage personStorage) {
        this.personStorage = personStorage;
    }

    public Person createPerson(Person person) {
        boolean isNewPerson = personStorageSet.add(person);
        if(isNewPerson) {
            return person;
        }
        return null;
    }

    public Person updatePerson(Person newPerson) {
        Person oldPerson = personStorageSet.stream().filter(p -> p.getId().equals(newPerson.getId())).findFirst().get();
        List<Integer> idCarts = oldPerson.getIdCarts();
        personStorageSet.remove(oldPerson);
        newPerson.setIdCarts(idCarts);
        personStorageSet.add(newPerson);
        return newPerson;
    }

    @Override
    public Person addNewCart(Person person, Integer idCart) {
        List<Integer> ids = new ArrayList<>();
        ids.addAll(person.getIdCarts());
        ids.add(idCart);
        person.setIdCarts(ids);
        return person;
    }

    public boolean deletePerson(Person person) {
        return personStorageSet.remove(person);
    }

    public Person getPersonById(int id) throws MyException {
        try {
            return personStorageSet.stream().filter(p -> p.getId().equals(id)).findFirst().get();
        }catch (Exception ex){
            throw new MyException("Wrong idPerson");
        }
    }

    @Override
    public Set<Person> getAllPersons() {
        return personStorageSet;
    }
}