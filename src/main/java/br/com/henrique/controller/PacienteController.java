package br.com.henrique.controller;

import br.com.henrique.domain.Paciente;
import br.com.henrique.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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
        return new ModelAndView("redirect:/pacientes");
    }

    @GetMapping
    public ModelAndView listar(ModelMap model){
        model.addAttribute("pacientes",service.buscar());
        model.addAttribute("conteudo","paciente/list");
        return new ModelAndView("home",model);
    }

    @GetMapping("/{id}/editar")
    public ModelAndView preAtualizar(@PathVariable("id") Long id, ModelMap model) {
        Paciente paciente = service.buscarPorId(id);
        model.addAttribute("paciente", paciente);
        model.addAttribute("conteudo","/paciente/add");
        return new ModelAndView("home", model);
    }

    @PutMapping("/salvar")
    public ModelAndView atualizar(@Valid @ModelAttribute("paciente") Paciente paciente, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("/paciente/add");
        }

        service.editar(paciente);
        attr.addFlashAttribute("mensagem", "Paciente atualizado com sucesso.");
        return new ModelAndView("redirect:/pacientes");
    }

    @GetMapping("/{id}/remover")
    public ModelAndView excluir(@PathVariable("id") Long id, RedirectAttributes attr){
        service.excluir(id);
        attr.addFlashAttribute("mensagem", "Paciente excluído com sucesso.");
        return new ModelAndView("redirect:/pacientes");
    }

    @GetMapping("/nome")
    public ModelAndView listarPorNome(@RequestParam(value = "nome") String nome, ModelMap model) {

        if (nome == null) {
            return new ModelAndView("redirect:/pacientes");
        }

        model.addAttribute("pacientes", service.buscarPorNome(nome));
        model.addAttribute("conteudo", "/paciente/list");
        return new ModelAndView("home", model);
    }

    @GetMapping("/{id}")
    public ModelAndView listarPorId(@RequestParam(value = "id") Long id, ModelMap model) {

        if (id == null) {
            return new ModelAndView("redirect:/pacientes");
        }

        model.addAttribute("pacientes", service.buscarPorId(id));
        model.addAttribute("conteudo", "/paciente/list");
        return new ModelAndView("home", model);
    }
}
