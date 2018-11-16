package br.com.henrique.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/")
    public ModelAndView inicio() {
        return new ModelAndView("home", "conteudo", "analytics");
    }

    @GetMapping("/analytics")
    public ModelAndView analytics() {
        return new ModelAndView("home", "conteudo", "analytics");
    }

    @GetMapping("/add")
    public ModelAndView add() {
        return new ModelAndView("home", "conteudo", "add");
    }

}
