package ru.wowtcgdatabase.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.wowtcgdatabase.DAO.CardsDAO;
import ru.wowtcgdatabase.DAO.CollectionDAO;
import ru.wowtcgdatabase.model.Card;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping
public class CardsController {

    @PostMapping("/card/all")
    public ResponseEntity<List<Card>> cards(@RequestBody Request requestBody) throws JsonProcessingException {

        CardsDAO cardsDAO = new CardsDAO(requestBody);
        List <Card> cards = cardsDAO.getCards();

        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @PostMapping("/card/collection")
    public ResponseEntity <List<Card>> myCollectionCards(@RequestBody Request requestBody) throws JsonProcessingException {

        CardsDAO cardsDAO = new CardsDAO(requestBody);
        List cards = cardsDAO.getMyCollection();

        return new ResponseEntity<>(cards, HttpStatus.OK);
    }

    @PostMapping("/card/add")
    public ResponseEntity <String> addCard(@RequestBody CollectionRequest requestBody) throws JsonProcessingException {

        CollectionDAO collectionDAO = new CollectionDAO(requestBody);
        List check = collectionDAO.getCard();

        if (check.isEmpty()) {
            collectionDAO.addCard();
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/card/delete")
    public ResponseEntity <String> deleteCard(@RequestBody CollectionRequest requestBody) {

        CollectionDAO collectionDAO = new CollectionDAO(requestBody);
        collectionDAO.deleteCard();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
