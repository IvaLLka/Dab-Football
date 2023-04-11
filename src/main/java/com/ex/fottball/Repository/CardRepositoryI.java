package com.ex.fottball.Repository;

import com.ex.fottball.Entities.Card;
import com.ex.fottball.Entities.Team;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface CardRepositoryI {

    public void createCard(Card card);
    public List<Card> getCards ();
    public Card findCardById(Integer card_id);

    public void update(Card card);

    public void deleteCard(Integer card_id);


}