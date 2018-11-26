package br.com.henrique.controller;


import br.com.henrique.domain.Receita;
import br.com.henrique.service.ConsultaService;
import br.com.henrique.service.ReceitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
@RequestMapping("consultas/{idConsulta}/receitas")
public class ReceitaController {

    @Autowired
    ReceitaService receitaService;

    @Autowired
    ConsultaService consultaService;

    @GetMapping
    public ModelAndView receita(@PathVariable("idConsulta") Long id, ModelMap model){
        model.addAttribute("receitas",receitaService.receitasPorConsulta(id));
        model.addAttribute("consulta",consultaService.buscarPorId(id));
        model.addAttribute("conteudo","consulta/receitas");
        return new ModelAndView("home");
    }

    @GetMapping("/nova")
    public ModelAndView novaReceita(@PathVariable("idConsulta") Long idConsulta,@ModelAttribute("receita") Receita receita,ModelMap model){
        model.addAttribute("consulta",consultaService.buscarPorId(idConsulta));
        receita.setConsulta(consultaService.buscarPorId(idConsulta));
        receita.setDataReceita(LocalDate.now());
        model.addAttribute("conteudo","consulta/novareceita");
        return new ModelAndView("home");
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@Valid @ModelAttribute("receita") Receita receita, BindingResult result, RedirectAttributes attr){

        receitaService.salvar(receita);
        attr.addFlashAttribute("mensagem","Receita cadastrada com sucesso.");
        return new ModelAndView("redirect:/consultas/{idConsulta}/receitas");
    }


}
