package com.ex.fottball.Entities;

import java.util.List;

public class Team {

    private Integer team_id;
    private String team_name;
    private String trainer;
    private String team_dob;
    private Integer num_players;
    private String stadium;
    private Integer card;
    private List<Integer> cards;
    public Team() {

    }



    public Team(Integer team_id,
                String team_name,
                String trainer,
                String team_dob,
                Integer num_players,
                String stadium,
                List<Integer> cards,
                Integer card) {
        this.team_id = team_id;
        this.team_name = team_name;
        this.trainer = trainer;
        this.team_dob = team_dob;
        this.num_players = num_players;
        this.stadium = stadium;
        this.cards = cards;
        this.card = card;
    }

    public Team(String team_name,
                String trainer,
                String team_dob,
                Integer num_players,
                String stadium,
                List<Integer> cards,
                Integer card) {
        this.team_name = team_name;
        this.trainer = trainer;
        this.team_dob = team_dob;
        this.num_players = num_players;
        this.stadium = stadium;
        this.cards = cards;
        this.card = card;
    }



    public Integer getTeam_id() {
        return team_id;
    }

    public void setTeam_id(Integer team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTrainer() {
        return trainer;
    }

    public void setTrainer(String trainer) {
        this.trainer = trainer;
    }

    public String getTeam_dob() {
        return team_dob;
    }

    public void setTeam_dob(String team_dob) {
        this.team_dob = team_dob;
    }

    public Integer getNum_players() {
        return num_players;
    }

    public void setNum_players(Integer num_players) {
        this.num_players = num_players;
    }

    public String getStadium() {
        return stadium;
    }

    public void setStadium(String stadium) {
        this.stadium = stadium;
    }

    public List<Integer> getCards() {
        return cards;
    }

    public void setCards(List<Integer> cards) {
        this.cards = cards;
    }

    public Integer getCard() {
        return card;
    }

    public void setCard(Integer card) {
        this.card = card;
    }

    @Override
    public String toString() {
        return "Team{" +
                "team_id=" + team_id +
                ", team_name='" + team_name + '\'' +
                ", trainer='" + trainer + '\'' +
                ", team_dob='" + team_dob + '\'' +
                ", num_players=" + num_players +
                ", stadium='" + stadium + '\'' +
                ", cards=" + cards +
                ", card=" + card +
                '}';
    }
}
