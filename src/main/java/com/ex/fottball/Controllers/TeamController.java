package com.ex.fottball.Controllers;

import com.ex.fottball.Entities.Card;
import com.ex.fottball.Entities.Team;
import com.ex.fottball.Services.CardService;
import com.ex.fottball.Services.TeamService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.springframework.asm.TypeReference;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
public class TeamController {
    private final TeamService teamService;
    private final CardService cardService;
    private static String fileP = "src/main/java/com/ex/fottball/yamlFiles/teams.yaml";
    private static Integer team_count = 1;
    public TeamController(TeamService teamService, CardService cardService) {
        this.teamService = teamService;
        this.cardService = cardService;
    }


    @Async
    @GetMapping("/teams/create-team")
    public String createTeam(Model model){
        List<Card> cardList = cardService.getAllCards();
        model.addAttribute("cards", cardList);
        return "/create-team";
    }
    @Async
    @PostMapping("/teams/form")
    public String processForm(@ModelAttribute Team team) throws IOException {
       teamService.createTeam(team);
        return "redirect:/teams";
    }

    @Async
    @GetMapping("/teams")
    public String getAllTeams( Model model)throws IOException {
        List<Team> teamList = teamService.getTeams();
        model.addAttribute("teams", teamList);
        return "teams";
    }
    /*@GetMapping("/teams/{team_id}")
    public String showById(@PathVariable Integer team_id){
        return "redirect:/teams";
    }*/
    @Async
    @GetMapping("/teams/delete/{team_id}")
    public String deleteTeam(@PathVariable Integer team_id){
        teamService.deleteTeam(team_id);
        return "redirect:/teams";
    }
    @Async
    @GetMapping("/teams/edit/{team_id}")
    public String editTeamById(@PathVariable Integer team_id, Model model ) throws IOException{
        List<Team> teams = new ArrayList<Team>();
        teams.add(teamService.findTeamById(team_id));
        model.addAttribute("teams", teams);
        return "team-edit";
    }

    @Async
    @PostMapping("/teams/edit/{team_id}")
    public String editTeam(@ModelAttribute Team team){
        teamService.update(team);
        return "redirect:/teams";
    }

    @Async
    @PostMapping("/teams/delete-card/{team_id}/{card_id}")
    public String deleteCardFromTeam(@PathVariable Integer team_id, @PathVariable Integer card_id)throws IOException{
        teamService.deleteCardFromTeam(team_id, card_id);
        return "redirect:/teams/details/{team_id}";
    }
    @Async
    @GetMapping("/teams/details/{team_id}")
    public String teamDetail(@PathVariable Integer team_id, Model model)throws IOException{
        List<Team> teamList = new ArrayList<>();
        List<Card> cardList = teamService.getCardsInTeam(team_id);
        teamList.add(teamService.findTeamById(team_id));
        model.addAttribute("teams", teamList);
        model.addAttribute("cards", cardList);
        return "team-detail";
    }

    @GetMapping("/teams/add-cards/{team_id}")
    public String addCard(@PathVariable Integer team_id, Model model) throws IOException {
        List<Card> cards = cardService.getAllCards();
        Team teams = teamService.findTeamById(team_id);
        model.addAttribute("cards", cards);
        model.addAttribute("teams", teams);
        return "add-cards";
    }
    @PostMapping("/teams/add-cards/{team_id}")
    public String addCardInTeam(@PathVariable Integer team_id, @RequestParam(name = "cards") List<Integer> cards){
        teamService.addCardToTeam(team_id, cards);
        return "redirect:/teams/details/{team_id}";
    }












}
