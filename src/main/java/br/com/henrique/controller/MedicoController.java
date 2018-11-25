package br.com.henrique.controller;

import br.com.henrique.domain.Especialidade;
import br.com.henrique.domain.Medico;
import br.com.henrique.service.EspecialidadeService;
import br.com.henrique.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    MedicoService service;

    @Autowired
    EspecialidadeService especialidadeService;

    @GetMapping("/cadastro")
    public ModelAndView preSalvar(@ModelAttribute("medico") Medico medico){
        return new ModelAndView("home","conteudo","medico/add");
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@Valid @ModelAttribute("medico") Medico medico, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("home","conteudo","medico/add");
        }

        service.salvar(medico);
        attr.addFlashAttribute("mensagem", "Médico cadastrado com sucesso.");
        return new ModelAndView("redirect:/medicos");
    }

    @GetMapping
    public ModelAndView listar(ModelMap model){
        model.addAttribute("medicos",service.buscar());
        model.addAttribute("conteudo","medico/list");
        return new ModelAndView("home",model);
    }

    @GetMapping("/{id}/editar")
    public ModelAndView preAtualizar(@PathVariable("id") Long id, ModelMap model) {
        Medico medico = service.buscarPorId(id);
        model.addAttribute("medico", medico);
        model.addAttribute("conteudo","/medico/add");
        return new ModelAndView("home", model);
    }

    @PutMapping("/salvar")
    public ModelAndView atualizar(@Valid @ModelAttribute("paciente") Medico medico, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("/medico/add");
        }

        service.editar(medico);
        attr.addFlashAttribute("mensagem", "Médico atualizado com sucesso.");
        return new ModelAndView("redirect:/medicos");
    }

    @GetMapping("/{id}/remover")
    public ModelAndView excluir(@PathVariable("id") Long id, ModelMap model){
        Medico medico = service.buscarPorId(id);
        model.addAttribute("medico",medico);
        model.addAttribute("conteudo","/medico/confirm");
        return new ModelAndView("home",model);
    }

    @GetMapping("/{id}/remover/confirm")
    public ModelAndView confirmar(@PathVariable Long id, RedirectAttributes attr ){
        service.excluir(id);
        attr.addFlashAttribute("mensagem", "Médico excluido com sucesso");
        return new ModelAndView("redirect:/medicos");
    }

    @GetMapping("/nome")
    public ModelAndView listarPorNome(@RequestParam(value = "nome") String nome, ModelMap model) {

        if (nome == null) {
            return new ModelAndView("redirect:/medicos");
        }

        model.addAttribute("medicos", service.buscarPorNome(nome));
        model.addAttribute("conteudo", "/medico/list");
        return new ModelAndView("home", model);
    }

    @GetMapping("/{id}")
    public ModelAndView listarPorId(@RequestParam(value = "id") Long id, ModelMap model) {

        if (id == null) {
            return new ModelAndView("redirect:/medicos");
        }

        model.addAttribute("medicos", service.buscarPorId(id));
        model.addAttribute("conteudo", "/medico/list");
        return new ModelAndView("home", model);
    }

    @GetMapping("/espec")
    public ModelAndView listarPorEspec(@RequestParam(value="espec") String nome, ModelMap model){
        if(nome == null){
            return new ModelAndView("redirect:/medicos");
        }

        model.addAttribute("medicos",service.buscarPorEspec(nome));
        model.addAttribute("conteudo","/medico/list");
        return new ModelAndView("home",model);
    }

    @GetMapping("/{id}/consultas")
    public ModelAndView consultasPorMedico(@PathVariable Long id,ModelMap model){
        model.addAttribute("consultas",service.consultasPorMedico(id));
        model.addAttribute("medico",service.buscarPorId(id));
        model.addAttribute("conteudo","medico/consultas");
        return new ModelAndView("home",model);
    }

    @ModelAttribute("especialidades")
    public List<Especialidade> especialidades(){
        return especialidadeService.buscar();
    }





}
