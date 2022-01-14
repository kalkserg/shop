package com.example.shop.controller;

import com.example.shop.model.Cart;
import com.example.shop.model.Person;
import com.example.shop.service.CartService;
import com.example.shop.service.MyException;
import com.example.shop.service.PersonService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Set;

@RestController
public class PersonController {

    private PersonService personService;
    private CartService cartService;

    @Autowired
    public void setPersonService(PersonService personService, CartService cartService) {
        this.personService = personService;
        this.cartService = cartService;
    }

    @PostMapping("/person/createPerson")
    public Person createPerson(@RequestBody(required = false) String str) {
        System.out.println(str);
        JSONObject obj = new JSONObject(str);

        Integer id = null;
        String firstName = null;
        String lastName = null;
        Integer age = null;
        String phoneNumber = null;
        String email = null;
        List<Integer> idCarts = null;

        Person person = new Person();

        try {
            id = obj.getInt("id");
        } catch (Exception ex) {
        }
        try {
            firstName = obj.getString("firstName");
        } catch (Exception ex) {
        }
        try {
            lastName = obj.getString("lastName");
        } catch (Exception ex) {
        }
        try {
            age = obj.getInt("age");
        } catch (Exception ex) {
        }
        try {
            phoneNumber = obj.getString("phoneNumber");
        } catch (Exception ex) {
        }
        try {
            email = obj.getString("email");
        } catch (Exception ex) {
        }

        person.setId(id);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setPhoneNumber(phoneNumber);
        person.setAge(age);
        person.setEmail(email);

        Person newPerson = personService.createPerson(person);
        if (newPerson != null) {
            Integer idCart = cartService.getLastId() + 1;
            cartService.createCart(idCart);
            System.out.println(idCart);
            person.setIdCarts(Collections.singletonList(idCart));
        }
        return newPerson;
    }

    @PostMapping("/person/updatePerson")
    public Person updatePerson(@RequestBody(required = false) String str) {
        System.out.println(str);
        JSONObject obj = new JSONObject(str);

        Integer id = null;
        String firstName = null;
        String lastName = null;
        Integer age = null;
        String phoneNumber = null;
        String email = null;
        List<Integer> idCarts = null;

        Person person = new Person();

        try {
            id = obj.getInt("id");
        } catch (Exception ex) {
        }
        try {
            firstName = obj.getString("firstName");
        } catch (Exception ex) {
        }
        try {
            lastName = obj.getString("lastName");
        } catch (Exception ex) {
        }
        try {
            age = obj.getInt("age");
        } catch (Exception ex) {
        }
        try {
            phoneNumber = obj.getString("phoneNumber");
        } catch (Exception ex) {
        }
        try {
            email = obj.getString("email");
        } catch (Exception ex) {
        }

        person.setId(id);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setPhoneNumber(phoneNumber);
        person.setAge(age);
        person.setEmail(email);

        Person newPerson = personService.updatePerson(person);
        return newPerson;
    }

    @PostMapping("/person/{idPerson}/addNewCart")
    public Person addNewCart(@PathVariable String idPerson) {
        Person person = null;
        try {
            person = personService.getPersonById(Integer.parseInt(idPerson));
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }

        if (person != null) {
            Integer idCart = cartService.createNextCart();;
            personService.addNewCart(person, idCart);
        }
        return person;
    }

    @PostMapping("/person/{idPerson}/delCart/{idCart}")
    public Person delCart(@PathVariable String idPerson, @PathVariable String idCart) {
        Person person = null;

        try {
            person = personService.getPersonById(Integer.parseInt(idPerson));
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }

        if (person != null) {
            try {
                cartService.deleteCart(Integer.valueOf(idCart));
            } catch (MyException e) {
                System.err.println(e.getMessage());
            }
            personService.delCart(person, Integer.valueOf(idCart));
        }
        return person;
    }

    @PostMapping(value = "/person/getAllPersons")
    @ResponseBody
    public Set<Person> getAllPersons() {
        return personService.getAllPersons();
    }

    @PostMapping(value = "/person/{id}")
    public Person getPerson(@PathVariable String id) {
        try {
            return personService.getPersonById(Integer.parseInt(id));
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    @PostMapping(value = "/person/deletePerson/{id}")
    public boolean delPerson(@PathVariable String id) {
        try {
            return personService.deletePerson(personService.getPersonById(Integer.parseInt(id)));
        } catch (MyException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }
}
