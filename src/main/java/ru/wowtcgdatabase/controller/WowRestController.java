package ru.wowtcgdatabase.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.wowtcgdatabase.DAO.CardsDAO;
import ru.wowtcgdatabase.DAO.CollectionDAO;
import ru.wowtcgdatabase.DAO.CustomerDAO;
import ru.wowtcgdatabase.TestHibernate;
import ru.wowtcgdatabase.model.Card;
import ru.wowtcgdatabase.model.MyCollection;

import java.awt.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping
public class WowRestController {



        @PostMapping("/WowTCGWebserver")
        public ResponseEntity <String> cards(@RequestBody Request requestBody) throws JsonProcessingException {

            Request request = new Request();
            request.setSetName(requestBody.getSetName());
            request.setCardClass(requestBody.getCardClass());
            request.setType(requestBody.getType());
            request.setFaction(requestBody.getFaction());
            request.setRarity(requestBody.getRarity());
            request.setCost(requestBody.getCost());

            System.out.println("Set Name: " + request.getSetName() +
                               " Rarity: " + request.getRarity() +
                               " Type: " + request.getType() +
                               " Faction: " + request.getFaction() +
                               " Class: " + request.getCardClass() +
                               " Cost: " + request.getCost());

            CardsDAO cardsDAO = new CardsDAO(request);
            List cards = cardsDAO.getCards();

        System.out.println(cards);

        ObjectMapper objectMapper = new ObjectMapper();


            String json = objectMapper.writer().writeValueAsString(cards);

            System.out.println(json);


        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping("/MyCollection")
    public ResponseEntity <String> myCollectionCards(@RequestBody Request requestBody) throws JsonProcessingException {

        Request request = new Request();
        request.setSetName(requestBody.getSetName());
        request.setCardClass(requestBody.getCardClass());
        request.setType(requestBody.getType());
        request.setFaction(requestBody.getFaction());
        request.setRarity(requestBody.getRarity());
        request.setCost(requestBody.getCost());
        request.setCustomerId(requestBody.getCustomerId());

        System.out.println("Set Name: " + request.getSetName() +
                " Rarity: " + request.getRarity() +
                " Type: " + request.getType() +
                " Faction: " + request.getFaction() +
                " Class: " + request.getCardClass() +
                " Cost: " + request.getCost() +
                " CustomerId: " + request.getCustomerId());

        CardsDAO cardsDAO = new CardsDAO(request);
        List cards = cardsDAO.getMyCollection();

        System.out.println(cards);

        ObjectMapper objectMapper = new ObjectMapper();


        String json = objectMapper.writer().writeValueAsString(cards);

        System.out.println(json);


        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping("/addcard")
    public ResponseEntity <String> addCard(@RequestBody CollectionRequest requestBody) {

        CollectionRequest collectionRequest = new CollectionRequest();
        collectionRequest.setCardId(requestBody.getCardId());
        collectionRequest.setCustomerId(requestBody.getCustomerId());

        System.out.println("Card Id: " + collectionRequest.getCardId() +
                " Customer Id: " + collectionRequest.getCustomerId());

        CollectionDAO collectionDAO = new CollectionDAO(collectionRequest);
        collectionDAO.addCard();



        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/deletecard")
    public ResponseEntity <String> deleteCard(@RequestBody CollectionRequest requestBody) {

        CollectionRequest collectionRequest = new CollectionRequest();
        collectionRequest.setCardId(requestBody.getCardId());
        collectionRequest.setCustomerId(requestBody.getCustomerId());

        System.out.println("Card Id: " + collectionRequest.getCardId() +
                " Customer Id: " + collectionRequest.getCustomerId());

        CollectionDAO collectionDAO = new CollectionDAO(collectionRequest);
        collectionDAO.deleteCard();



        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/auth")
    public ResponseEntity <String> authentication(@RequestBody CustomerRequest requestBody) throws JsonProcessingException {

        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setLogin(requestBody.getLogin());
        customerRequest.setPassword(requestBody.getPassword());

        System.out.println("Login: " + customerRequest.getLogin() +
                " Password: " + customerRequest.getPassword());

        CustomerDAO customerDAO = new CustomerDAO(customerRequest);
        List loginResponse = customerDAO.logIn();

        System.out.println(loginResponse);

        ObjectMapper objectMapper = new ObjectMapper();


        String json = objectMapper.writer().writeValueAsString(loginResponse);

        System.out.println(json);


        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping("/reg")
    public ResponseEntity <String> registration(@RequestBody CustomerRequest requestBody) throws JsonProcessingException {

        CustomerRequest customerRequest = new CustomerRequest();
        customerRequest.setLogin(requestBody.getLogin());
        customerRequest.setPassword(requestBody.getPassword());
        customerRequest.setCustomerName(requestBody.getCustomerName());

        System.out.println("Login: " + customerRequest.getLogin() +
                " Password: " + customerRequest.getPassword() +
                " Name: " + customerRequest.getCustomerName());

        CustomerDAO customerDAO = new CustomerDAO(customerRequest);
        List check = customerDAO.logIn();

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writer().writeValueAsString(check);
        System.out.println(json);

        if (json.equals("[]")) {
            customerDAO.signUp();
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
