package com.ex.fottball.Repository;

import com.ex.fottball.Entities.Card;
import com.ex.fottball.Entities.Team;
import com.fasterxml.jackson.databind.deser.std.UntypedObjectDeserializer;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public interface TeamRepositoryI {

    public void deleteTeam(Integer team_id);
        public Team findTeamById(Integer team_id);

    public void update(Team team);

    public List<Team> getTeams() ;
    public void createTeam(Team team) ;
    public void deleteCardFromTeam(Integer team_id, Integer card_id);
    public void addCardToTeam(Integer card_id, List<Integer> cards);

}
