package br.com.henrique.controller;

import br.com.henrique.domain.Especialidade;
import br.com.henrique.service.EspecialidadeService;
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
@RequestMapping("/especialidades")
public class EspecialidadeController {

    @Autowired
    EspecialidadeService service;


    @GetMapping
    public ModelAndView listar(ModelMap model){
        model.addAttribute("especialidades",service.buscar());
        model.addAttribute("conteudo","especialidade/list");
        return new ModelAndView("home",model);
    }

    @GetMapping("/cadastro")
    public ModelAndView preSalvar(@ModelAttribute Especialidade especialidade){
        return new ModelAndView("home","conteudo","especialidade/add");
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@Valid @ModelAttribute("especialidade") Especialidade especialidade, BindingResult result, RedirectAttributes attr){
        if(result.hasErrors()){
            return new ModelAndView("home","conteudo","especialidade/add");
        }

        service.salvar(especialidade);
        attr.addFlashAttribute("mensagem","Especialidade cadastrada com sucesso.");
        return new ModelAndView("redirect:/especialidades");
    }

    @GetMapping("{id}/editar")
    public ModelAndView preAtualizar(@PathVariable("id") Long id,ModelMap model){
       Especialidade especialidade = service.buscarPorId(id);
       model.addAttribute("especialidade",especialidade);
       model.addAttribute("conteudo","especialidade/add");
       return new ModelAndView("home",model);
    }

    @PutMapping("/salvar")
    public ModelAndView atualizar(@Valid @ModelAttribute("especialidade") Especialidade especialidade,BindingResult result, RedirectAttributes attr){
        if(result.hasErrors()){
            return new ModelAndView("especialidade/add");
        }

        service.editar(especialidade);
        attr.addFlashAttribute("mensagem","Especialidade atualizada com sucesso.");
        return new ModelAndView("redirect:/especialidades");
    }

    @GetMapping("/{id}/remover")
    public ModelAndView excluir(@PathVariable("id") Long id, ModelMap model){
        Especialidade especialidade = service.buscarPorId(id);
        model.addAttribute("especialidade",especialidade);
        model.addAttribute("conteudo","/especialidade/confirm");
        return new ModelAndView("home",model);
    }

    @GetMapping("/{id}/remover/confirm")
    public ModelAndView confirmar(@PathVariable Long id, RedirectAttributes attr ){
        service.excluir(id);
        attr.addFlashAttribute("mensagem", "Especialidade excluida com sucesso");
        return new ModelAndView("redirect:/especialidades");
    }

    @GetMapping("/nome")
    public ModelAndView listarPorNome(@RequestParam(value = "nome") String nome, ModelMap model) {

        if (nome == null) {
            return new ModelAndView("redirect:/especialidades");
        }

        model.addAttribute("especialidades", service.buscarPorNome(nome));
        model.addAttribute("conteudo", "/especialidade/list");
        return new ModelAndView("home", model);
    }

    @GetMapping("/{id}")
    public ModelAndView listarPorId(@RequestParam(value = "id") Long id, ModelMap model) {

        if (id == null) {
            return new ModelAndView("redirect:/especialidadess");
        }

        model.addAttribute("especialidades", service.buscarPorId(id));
        model.addAttribute("conteudo", "/especialidade/list");
        return new ModelAndView("home", model);
    }


}
