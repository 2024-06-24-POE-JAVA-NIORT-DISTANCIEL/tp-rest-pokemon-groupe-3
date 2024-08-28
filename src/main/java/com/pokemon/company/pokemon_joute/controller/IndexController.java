package com.pokemon.company.pokemon_joute.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class IndexController {
    @GetMapping("/index")
    public String afficheHello(){
        System.out.println("Le contrôleur Index est appelé");
        return "vue-hello";
    }

    @ModelAttribute("monMessage")
    public String metDansLeContexteCaRoule(){
        return "Ca roule pas mal sur cette page dynamique !!!!";
    }
}
