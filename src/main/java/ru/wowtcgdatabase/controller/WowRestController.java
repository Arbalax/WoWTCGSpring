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
import ru.wowtcgdatabase.TestHibernate;
import ru.wowtcgdatabase.model.Card;

import java.awt.*;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/WowTCGWebserver")
public class WowRestController {



//    @PostMapping
//    public ResponseEntity<String> postBorrowBook(@RequestBody Request requestBody) {
//        requestBody.getSetName();
//        requestBody.getCardClass();
//        requestBody.getType();
//        requestBody.getFaction();
//        requestBody.getRarity();
//
//        System.out.println(requestBody.getCardClass());
//        return requestBody.getRarity();
//    };
//    public String list() throws JsonProcessingException {
        @PostMapping
        public ResponseEntity <String> postBorrowBook(@RequestBody Request requestBody) throws JsonProcessingException {

            Request request = new Request();
            request.setSetName(requestBody.getSetName());
            request.setCardClass(requestBody.getCardClass());
            request.setType(requestBody.getType());
            request.setFaction(requestBody.getFaction());
            request.setRarity(requestBody.getRarity());
            request.setCost(requestBody.getCost());

            System.out.println("Rarity: " + request.getRarity() + " Type: " + request.getType() + " Cost: " + request.getCost() + " Faction: " + request.getFaction());

            CardsDAO cardsDAO = new CardsDAO(request);
            List cards = cardsDAO.getCards();
//        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        List cards;
//        Transaction transaction = null;
//        session.beginTransaction();
//        cards = session.createQuery("FROM CardWow where rarity = 'rare' and type = 'ally' and cost = 5 and faction = 'Horde'").list();
//        session.close();
        System.out.println(cards);

        ObjectMapper objectMapper = new ObjectMapper();


            String json = objectMapper.writer().writeValueAsString(cards);

            System.out.println(json);

//        sessionFactory.close();

        return new ResponseEntity<>(json, HttpStatus.OK);
    }
//    public static class RestResponse {
//        private String param;
//
//
//        public String getParam() {
//            return param;
//        }
//
//        public void setParam(String param) {
//            this.param = param;
//        }
//
//
//    }
//    @RequestMapping(value = "/WowTCGWebserver", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
//    public RestResponse restMethod(String name) throws Exception {
//        RestResponse result = new RestResponse();
//
//        SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
//        Session session = sessionFactory.openSession();
//        List<Card> cards;
//        Transaction transaction = null;
//        session.beginTransaction();
//        cards = session.createQuery("FROM CardWow where rarity = 'rare' and type = 'ally' and cost = 5 and faction = 'Horde'").list();
//        session.close();
//        System.out.println(cards);
//
//        ObjectMapper objectMapper = new ObjectMapper();
//
//
//            String json = objectMapper.writer().writeValueAsString(cards);
//
//            System.out.println(json);
//
//
//
//        result.setParam(json);
//
//
//        sessionFactory.close();
//        return result;
//
//
//    }
}
