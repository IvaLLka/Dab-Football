package com.ex.fottball.Services;

import com.ex.fottball.Entities.Card;
import com.ex.fottball.Entities.Team;
import com.ex.fottball.Repository.TeamRepositoryI;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import com.ex.fottball.Repository.CardRepositoryI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class TeamService {
    private final CardRepositoryI cardRepository;
    private final TeamRepositoryI teamRepository;

    public TeamService(CardRepositoryI cardRepository, TeamRepositoryI teamRepository) {
        this.cardRepository = cardRepository;
        this.teamRepository = teamRepository;
    }

    /*public void addTeam(Team team)throws IOException {
        teamRepository.addTeam(team);
    }*/

    @Async
    public void createTeam(Team team) {
        teamRepository.createTeam(team);
    }

    @Async
    public  List<Team> getTeams() {
        List<Team> teamList = teamRepository.getTeams();
        return teamList;
    }

    @Async
    public void deleteTeam(Integer team_id){
        teamRepository.deleteTeam(team_id);
    }

    @Async
    public Team findTeamById(Integer team_id) throws IOException{
        return teamRepository.findTeamById(team_id);
    }

    @Async
    public Team update(Team team){
        teamRepository.update(team);
        return teamRepository.findTeamById(team.getTeam_id());
    }

    @Async
    public List<Card> getCardsInTeam(Integer team_id)throws IOException{
        Team team = findTeamById(team_id);
        List<Card> cards = new ArrayList<>();
        List<Integer> card_id = team.getCards();

        for(int i=0; i<card_id.size(); i++){
            cards.add(cardRepository.findCardById(card_id.get(i)));
        }
        return cards;
    }

   @Async
    public void deleteCardFromTeam(Integer team_id, Integer card_id)throws IOException{
        teamRepository.deleteCardFromTeam(team_id, card_id);
   }





}
