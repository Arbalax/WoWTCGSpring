package ru.wowtcgdatabase.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.wowtcgdatabase.DAO.CustomerDAO;
import ru.wowtcgdatabase.model.Customer;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping
public class CustomersController {

    @PostMapping("/user/login")
    public ResponseEntity <List<Customer>> authentication(@RequestBody CustomerRequest requestBody) throws JsonProcessingException {

        CustomerDAO customerDAO = new CustomerDAO(requestBody);
        List loginResponse = customerDAO.logIn();

        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

    @PostMapping("/user/add")
    public ResponseEntity <String> registration(@RequestBody CustomerRequest requestBody) throws JsonProcessingException {

        CustomerDAO customerDAO = new CustomerDAO(requestBody);
        List check = customerDAO.checkLogin();

        if (check.isEmpty()) {
            customerDAO.signUp();
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
