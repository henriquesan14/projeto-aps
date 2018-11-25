package br.com.henrique.controller;

import br.com.henrique.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    ConsultaService service;

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

    @ModelAttribute("consultasHoje")
    public long consultasHoje(){
        LocalDate data= LocalDate.now();
        return service.consultasHoje(data);
    }

    @ModelAttribute("consultasAgendadasMes")
    public long consultasAgendadasMes(){
        int mes= LocalDate.now().getMonthValue();
        return service.consultasAgendadasMes(mes);
    }

    @ModelAttribute("consultasRealizadasMes")
    public long consultasRealizadasMes(){
        int mes= LocalDate.now().getMonthValue();
        return service.consultasRealizadasMes(mes);
    }

    @ModelAttribute("consultasMesAnterior")
    public long consultasMesAnterior(){
        int mes= LocalDate.now().getMonthValue() -1;
        return service.consultasRealizadasMes(mes);
    }

}
