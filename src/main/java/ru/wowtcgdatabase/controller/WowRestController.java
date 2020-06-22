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

        System.out.println("Set Name: " + request.getSetName() +
                " Rarity: " + request.getRarity() +
                " Type: " + request.getType() +
                " Faction: " + request.getFaction() +
                " Class: " + request.getCardClass() +
                " Cost: " + request.getCost());

        CardsDAO cardsDAO = new CardsDAO(request);
        List cards = cardsDAO.getMyCollection();

        System.out.println(cards);

        ObjectMapper objectMapper = new ObjectMapper();


        String json = objectMapper.writer().writeValueAsString(cards);

        System.out.println(json);


        return new ResponseEntity<>(json, HttpStatus.OK);
    }

    @PostMapping("/addcard")
    public ResponseEntity <String> addCard(@RequestBody CollectionRequest requestBody) throws JsonProcessingException {

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
    public ResponseEntity <String> deleteCard(@RequestBody CollectionRequest requestBody) throws JsonProcessingException {

        CollectionRequest collectionRequest = new CollectionRequest();
        collectionRequest.setCardId(requestBody.getCardId());
        collectionRequest.setCustomerId(requestBody.getCustomerId());

        System.out.println("Card Id: " + collectionRequest.getCardId() +
                " Customer Id: " + collectionRequest.getCustomerId());

        CollectionDAO collectionDAO = new CollectionDAO(collectionRequest);
        collectionDAO.deleteCard();



        return new ResponseEntity<>(HttpStatus.OK);
    }
}
