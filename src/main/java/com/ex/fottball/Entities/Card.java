package com.ex.fottball.Entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


public class Card {

    private Integer card_id;
    private String full_name;
    private Integer salary;
    private Integer play_exp;
    private String team;
    private String dob;
    public Card() {
    }

    public Card(Integer card_id,
                String full_name,
                Integer salary,
                Integer play_exp,
                String team,
                String dob) {

        this.full_name = full_name;
        this.salary = salary;
        this.play_exp = play_exp;
        this.team = team;
        this.dob = dob;
    }

    public Card(String full_name,
                Integer salary,
                Integer play_exp,
                String team,
                String dob) {
        this.full_name = full_name;
        this.salary = salary;
        this.play_exp = play_exp;
        this.team = team;
        this.dob = dob;
    }

    public Integer getCard_id() {
        return card_id;
    }

    public void setCard_id(Integer card_id) {
        this.card_id = card_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Integer getPlay_exp() {
        return play_exp;
    }

    public void setPlay_exp(Integer play_exp) {
        this.play_exp = play_exp;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "Card{" +
                "card_id=" + card_id +
                ", full_name='" + full_name + '\'' +
                ", salary=" + salary +
                ", play_exp=" + play_exp +
                ", team='" + team + '\'' +
                ", dob='" + dob + '\'' +
                '}';
    }
}
