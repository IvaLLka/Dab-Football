package com.ex.fottball.Services;

import com.ex.fottball.Entities.Card;
import com.ex.fottball.Entities.Team;
import com.ex.fottball.Repository.TeamRepositoryI;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Service
public class TeamService {
    private final TeamRepositoryI teamRepository;

    public TeamService(TeamRepositoryI teamRepository) {
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





}
