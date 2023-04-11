package com.ex.fottball.Services;

import com.ex.fottball.Entities.Card;
import com.ex.fottball.Entities.Team;
import com.ex.fottball.Repository.CardRepositoryI;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.List;
import java.util.Map;

@Service
public class CardService {
    private final CardRepositoryI cardRepository;
    public CardService(CardRepositoryI cardRepository) {
        this.cardRepository = cardRepository;
    }

    @Async
    public void addCard(Card card) {
        cardRepository.createCard(card);
    }

    @Async
    public  List<Card> getAllCards() {
        List<Card> cardList = cardRepository.getCards();
        return cardList;
    }

    @Async
    public void deleteCard(Integer card_id){
        cardRepository.deleteCard(card_id);
    }

    @Async
    public Card findCardById(Integer card_id){
        return cardRepository.findCardById(card_id);
    }
    @Async
    public Card update(Card card){
        cardRepository.update(card);
        return cardRepository.findCardById(card.getCard_id());
    }
}
