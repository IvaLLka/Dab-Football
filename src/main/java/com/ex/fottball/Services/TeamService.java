package com.ex.fottball.Services;

import com.ex.fottball.Entities.Card;
import com.ex.fottball.Entities.Team;
import com.ex.fottball.Repository.TeamRepositoryI;
import org.springframework.stereotype.Service;
import com.ex.fottball.Repository.CardRepositoryI;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

    public void createTeam(Team team) {
        teamRepository.createTeam(team);
    }

    public  List<Team> getTeams() {
        List<Team> teamList = teamRepository.getTeams();
        return teamList;
    }

    public void deleteTeam(Integer team_id){
        teamRepository.deleteTeam(team_id);
    }

    public Team findTeamById(Integer team_id) throws IOException{
        return teamRepository.findTeamById(team_id);
    }

    public Team update(Team team){
        teamRepository.update(team);
        return teamRepository.findTeamById(team.getTeam_id());
    }


    public List<Card> getCardsInTeam(Integer team_id)throws IOException{
        Team team = findTeamById(team_id);
        List<Card> cards = new ArrayList<>();
        List<Integer> card_id = team.getCards();

        for(int i=0; i<card_id.size(); i++){
            cards.add(cardRepository.findCardById(card_id.get(i)));
        }
        return cards;
    }





}
