package br.com.henrique.controller;

import br.com.henrique.domain.Consulta;
import br.com.henrique.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    ConsultaService consultaService;

    @GetMapping("/cadastro")
    public ModelAndView preSalvar(@ModelAttribute("consulta") Consulta consulta){
        return new ModelAndView("home","conteudo","consulta/add");
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@Valid @ModelAttribute("paciente") Consulta consulta, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("home","conteudo","consulta/add");
        }

        consultaService.salvar(consulta);
        attr.addFlashAttribute("mensagem", "Consulta cadastrada com sucesso.");
        return new ModelAndView("redirect:/consultas");
    }
}
