package br.com.henrique.controller;

import br.com.henrique.domain.Paciente;
import br.com.henrique.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("pacientes")
public class PacienteController {

    @Autowired
    PacienteService service;

    @GetMapping("/cadastro")
    public ModelAndView preSalvar(@ModelAttribute("paciente") Paciente paciente){
        return new ModelAndView("home","conteudo","paciente/add");
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@Valid @ModelAttribute("paciente") Paciente paciente, BindingResult result, RedirectAttributes attr) {
        System.out.println(paciente.getDtNascimento());
        if (result.hasErrors()) {
            return new ModelAndView("home","conteudo","paciente/add");
        }

        service.salvar(paciente);
        attr.addFlashAttribute("mensagem", "Paciente cadastrado com sucesso.");
        return new ModelAndView("home","conteudo" ,"paciente/list");
    }

    @GetMapping("/listar")
    public ModelAndView listar(ModelMap model){
        model.addAttribute("pacientes",service.buscar());
        model.addAttribute("conteudo","paciente/list");
        return new ModelAndView("home",model);
    }
}
