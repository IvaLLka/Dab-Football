package com.ex.fottball.Controllers;

import com.ex.fottball.Entities.Card;
import com.ex.fottball.Entities.Team;
import com.ex.fottball.Services.CardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.*;

@Controller
public class CardController {
    private final CardService cardService;
    private static String fileP = "src/main/java/com/ex/fottball/yamlFiles/cards.yaml";

    public CardController(CardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping("/cards")
    public String getAllCards(Model model)throws IOException {
        List<Card> cardList = cardService.getAllCards();
        model.addAttribute("cards", cardList);
        return "cards";
    }

    @GetMapping("/cards/create-card")
    public String createCard() {
        return "create-card";
    }

    @PostMapping("/cards/form")
    public String createCard(@ModelAttribute Card card) throws IOException {
        cardService.addCard(card);
        return "redirect:/cards";
    }

   /* @DeleteMapping("/cards/delete/{card_id}")
    public String deleteCardById(@PathVariable Integer card_id){
        cardService.deleteCardById(card_id);
        return "redirect:/cards";
    }*/

    @GetMapping("/cards/delete/{card_id}")
    public String deleteTeam(@PathVariable Integer card_id){
        cardService.deleteCard(card_id);
        return "redirect:/cards";
    }
    @GetMapping("/cards/edit/{card_id}")
    public String editCardById(@PathVariable Integer card_id, Model model ) {
        List<Card> cards = new ArrayList<Card>();
        cards.add(cardService.findCardById(card_id));
        model.addAttribute("cards", cards);
        return "card-edit";
    }

    @PostMapping("/cards/edit/{card_id}")
    public String editCard(@ModelAttribute Card card){
        cardService.update(card);
        return "redirect:/cards";
    }

}
