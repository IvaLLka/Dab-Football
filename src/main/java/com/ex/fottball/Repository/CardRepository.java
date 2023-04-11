package com.ex.fottball.Repository;

import com.ex.fottball.Entities.Card;

import com.ex.fottball.Entities.Team;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class CardRepository implements CardRepositoryI {

    public CardRepository() throws IOException{}
/*private static Integer card_count = 1;*/

    private static String fileP = "src/main/resources/yamlFiles/cards.yaml";
    private final String fileID = "yamlFiles/cards.yaml";
    ClassPathResource resource = new ClassPathResource(fileID);
    InputStream inputStream = resource.getInputStream();
    ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());

    private final AtomicInteger lastCardId = new AtomicInteger(getLastCardId());

    @Async
    public Integer generateCardId(){
        return lastCardId.incrementAndGet();
    }

    @Async
    public int getLastCardId() {
        int lastCardId = -1;

        try {
            List<Card> cards = objectMapper.readValue(inputStream, new TypeReference<>() {
            });
            if (!cards.isEmpty()) {
                Card card = cards.get(cards.size() - 1);
                lastCardId = card.getCard_id();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastCardId;
    }

    @Async
    public void deleteCard(Integer card_id){
        List<Card> cards = getCards();
        cards.removeIf(team -> Objects.equals(team.getCard_id(), card_id));
        try {
            objectMapper.writeValue(new File(fileP), cards);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Async
    public void update(Card card) {
        List<Card> cards = getCards();
        for (int i = 0; i < cards.size(); i++) {
            if (Objects.equals(cards.get(i).getCard_id(), card.getCard_id())) {
                cards.set(i, card);
                break;
            }
        }
        try {
            objectMapper.writeValue(new File(fileP), cards);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void createCard(Card card){
        try{
            List<Card> cards = objectMapper.readValue(new File(fileP), new TypeReference<>() {
            });
            if(card.getCard_id()==null){
                card.setCard_id(generateCardId());
            }
            cards.add(card);
            objectMapper.writeValue(new File(fileP), cards);

        } catch (IOException exception){
            exception.printStackTrace();
        }
    }

    @Async
    public List<Card> getCards ()  {
        try{
            return objectMapper.readValue(new File(fileP), new TypeReference<>() {
            });
        }catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Async
    public Card findCardById(Integer card_id){
        List<Card> cardList = getCards();
        for(Card card: cardList){
            if (card.getCard_id() == card_id) {
                return card;
            }
        }
        return null;
    };

}

