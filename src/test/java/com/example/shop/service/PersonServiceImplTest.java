package com.example.shop.service;

import com.example.shop.model.Person;
import com.example.shop.model.Product;
import com.example.shop.storage.PersonStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static com.example.shop.storage.PersonStorage.personStorageSet;
import static com.example.shop.storage.ProductStorage.productStorageSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PersonServiceImplTest {

    PersonStorage personStorage;
    PersonServiceImpl personServiceImpl = new PersonServiceImpl(personStorage);

    Person person1;
    Person person2;

    @BeforeEach
    void create() {
        person1 = new Person(1, "Serg", "Serg", 12, "1234567890", "serg@test.ua", new ArrayList<>());
        person2 = new Person(2, "Vova", "Vova", 34, "234567891", "vova@test.ua", new ArrayList<>());
        personServiceImpl.createPerson(person1);
        personServiceImpl.createPerson(person2);
    }

    @Test
    void createPersonTest() {
        Set<Person> expected = personStorageSet;
        Set<Person> actual = new HashSet<>();
        actual.add(person1);
        actual.add(person2);
        assertEquals(expected, actual);
    }

    @Test
    void updatePersonTest() {
        Person person1new = new Person(1, "Booo", "Booo", 12, "1234567890", "serg@test.ua", null);
        personServiceImpl.updatePerson(person1new);
        Set<Person> expected = personStorageSet;
        Set<Person> actual = new HashSet<>();
        actual.add(person1new);
        actual.add(person2);
        assertEquals(expected, actual);
    }

    @Test
    void addNewCartTest() {
        Person expected = personServiceImpl.addNewCart(person1, 1);
        Person actual = new Person(1, "Serg", "Serg", 12, "1234567890", "serg@test.ua",
                new ArrayList<Integer>(){{add(1);}});

        assertEquals(expected.getIdCarts(), actual.getIdCarts());
    }

    @Test
    void deletePersonTest() {
        try {
            personServiceImpl.deletePerson(person1);
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        Set<Person> expected = personStorageSet;
        Set<Person> actual = new HashSet<>();
        actual.add(person2);
        assertEquals(expected, actual);
    }
}
