package com.example.shop.storage;

import com.example.shop.model.Person;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class PersonStorage {
    public static Set<Person> personStorageSet = new HashSet<Person>() {

    };
}
