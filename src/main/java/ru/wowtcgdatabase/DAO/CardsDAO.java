package ru.wowtcgdatabase.DAO;

import org.hibernate.Session;
import ru.wowtcgdatabase.SessionCreator;
import ru.wowtcgdatabase.controller.Request;
import ru.wowtcgdatabase.model.Card;
import java.util.List;

public class CardsDAO {
    private Request request;

    public CardsDAO(Request request) {
        this.request = request;
    }

    public List <Card> getCards () {
        try (Session session = SessionCreator.getSession()) {
            List cards;
            session.beginTransaction();
            cards = session.createQuery("FROM Card where rarity like case when '" + request.getRarity() + "' = 'All' then '%' else '" + request.getRarity() + "' end " +
                    "and type like case when '" + request.getType() + "' = 'All' then '%' else '" + request.getType() + "' end " +
                    "and setName like case when '" + request.getSetName() + "' = 'All' then '%' else '" + request.getSetName() + "' end " +
                    "and cardClass like case when '" + request.getCardClass() + "' = 'All' then '%' else '" + request.getCardClass() + "' end " +
                    "and cost like case when " + request.getCost() + " =-10 then '%' when " + request.getCost() + " =10 then '__' else " + request.getCost() + " end " +
                    "and faction like case when '" + request.getFaction() + "' = 'All' then '%' else '" + request.getFaction() + "' end").list();

            return cards;
        }
    }

    public List <Card> getMyCollection () {
        try (Session session = SessionCreator.getSession()) {
            List cards;
            session.beginTransaction();
            cards = session.createQuery("select cards FROM Card cards inner join MyCollection collection on cards.cardId = collection.cardId " +
                    "where cards.rarity like case when '" + request.getRarity() + "' = 'All' then '%' else '" + request.getRarity() + "' end " +
                    "and cards.type like case when '" + request.getType() + "' = 'All' then '%' else '" + request.getType() + "' end " +
                    "and cards.setName like case when '" + request.getSetName() + "' = 'All' then '%' else '" + request.getSetName() + "' end " +
                    "and cards.cardClass like case when '" + request.getCardClass() + "' = 'All' then '%' else '" + request.getCardClass() + "' end " +
                    "and cards.cost like case when " + request.getCost() + " =-10 then '%' when " + request.getCost() + " =10 then '__' else " + request.getCost() + " end " +
                    "and cards.faction like case when '" + request.getFaction() + "' = 'All' then '%' else '" + request.getFaction() + "' end " +
                    "and collection.customerId = " + request.getCustomerId()).list();

            return cards;
        }
    }
}
