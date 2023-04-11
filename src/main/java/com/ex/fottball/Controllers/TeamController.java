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

    /*@RequestMapping("/teams")
    public String getTeams(Model model) throws Exception {
        // Создаем объект ObjectMapper с использованием фабрики YAML.
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

        // Читаем YAML-файл, содержащий список команд.
        List<Team> teams = mapper.readValue(new File(fileP), new TypeReference<>() {});

        // Создаем новый список команд, который будет содержать только те команды, которые принадлежат карточкам из списка cards.
        List<Team> filteredTeams = new ArrayList<>();
        List<Integer> cards = List.of(1, 2, 3); // список карточек
        for (Team team : teams) {
            if (team.getCards().stream().anyMatch(cards::contains)) {
                filteredTeams.add(team);
            }
        }

        // Используем объект ModelAndView для передачи списка отфильтрованных команд в представление JSP.
        model.addAttribute("teams", filteredTeams);
        return "teams";
    }*/

    @Async
    @PostMapping("/teams/edit/{team_id}")
    public String editTeam(@ModelAttribute Team team){
        teamService.update(team);
        return "redirect:/teams";
    }

    /*@GetMapping("/teams/delete-card/{card_id}")
    public String deleteCardFromTeam(@PathVariable Integer card_id){
        teamService.deleteTeam(card_id);
        return "redirect:/teams";
    }*/
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












}
