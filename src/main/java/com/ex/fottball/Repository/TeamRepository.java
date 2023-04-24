package com.ex.fottball.Repository;

import com.ex.fottball.Entities.Card;
import com.ex.fottball.Entities.Team;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class TeamRepository implements TeamRepositoryI{
    private final CardRepository cardRepository;

    public TeamRepository(CardRepository cardRepository) throws IOException {
        this.cardRepository = cardRepository;
    }
    private static String fileP = "src/main/resources/yamlFiles/teams.yaml";
    private static String filePathToCards = "src/main/resources/yamlFiles/cards.yaml";

    private final String fileID = "yamlFiles/teams.yaml";
    ClassPathResource resource = new ClassPathResource(fileID);
    InputStream inputStream = resource.getInputStream();
    ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());


    private final AtomicInteger lastId = new AtomicInteger(getLastId());

    @Async
    public Integer generateId(){
        return lastId.incrementAndGet();
    }

    @Async
    public int getLastId() {
        int lastId = -1;

        try {
            List<Team> teams = objectMapper.readValue(inputStream, new TypeReference<>() {
            });
            if (!teams.isEmpty()) {
                Team team = teams.get(teams.size() - 1);
                lastId = team.getTeam_id();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lastId;
    }

    @Async
    public void createTeam(Team team){
        try{
            List<Team> teams = objectMapper.readValue(new File(fileP), new TypeReference<>() {
            });
            if(team.getTeam_id()==null){
                team.setTeam_id(generateId());
            }
            teams.add(team);
            objectMapper.writeValue(new File(fileP), teams);

        } catch (IOException exception){
            exception.printStackTrace();
        }
    }




    @Async
    public Team findTeamById(Integer team_id){
        List<Team> teamList = getTeams();
        for(Team team: teamList){
            if (team.getTeam_id() == team_id) {
                return team;
            }
        }
        return null;
    }


    @Async
    public void update(Team team) {
        List<Team> teams = getTeams();
        for (int i = 0; i < teams.size(); i++) {
            if (Objects.equals(teams.get(i).getTeam_id(), team.getTeam_id())) {
                teams.set(i, team);
                break;
            }
        }
        try {
            objectMapper.writeValue(new File(fileP), teams);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void deleteTeam(Integer team_id){
        List<Team> teams = getTeams();
        teams.removeIf(team -> Objects.equals(team.getTeam_id(), team_id));
        try {
            objectMapper.writeValue(new File(fileP), teams);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Async
    public void deleteCardFromTeam(Integer team_id, Integer card_id){
        Team team = findTeamById(team_id);
        List<Integer> cardId = team.getCards();
        for( int i=0; i<cardId.size(); i++){
            if(cardId.get(i) == card_id) {
                cardId.remove(i);
            }
        }
        team.setCards(cardId);
        update(team);
    }

    @Async
    public void addCardToTeam(Integer team_id, List<Integer> cards){
        Team team = findTeamById(team_id);
        List<Integer> card_IDs = new ArrayList<>();
        List <Card> cardList = cardRepository.getCards();
        for( int j=0; j<cardList.size(); j++) {
            Integer card = cardList.get(j).getCard_id();
            card_IDs.add(card);
        }
        List <Integer> team_card_id = team.getCards();
        /*for (int i = 0; i < team_card_id.size(); i++) {
            for (int j = 0; j < cards.size(); j++) {
                if (team_card_id.get(i) == cards.get(j)) {
                    for( int k=0; k<card_IDs.size(); k++) {
                        if ( team_card_id.get(i) == card_IDs.get(k)) {
                            card_IDs.remove(k+1);
                        }
                    }
                }
            }
        }*/


        for( int i=0; i<card_IDs.size(); i++){
            for( int j=0; j<cards.size(); j++) {
                if(card_IDs.get(i) == cards.get(j)) {
                    team_card_id.add(i+1);
                }
            }
        }

        team.setCards(team_card_id);
        update(team);
    }

    @Async
    public List<Team> getTeams () {
           try{
               return objectMapper.readValue(new File(fileP), new TypeReference<>() {
               });
           }catch (IOException e) {
               e.printStackTrace();
               return null;
           }
        }
}
