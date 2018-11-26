package br.com.henrique.controller;

import br.com.henrique.domain.Medicamento;
import br.com.henrique.service.MedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/medicamentos")
public class MedicamentoController {

    @Autowired
    MedicamentoService service;

    @GetMapping
    public ModelAndView listar(ModelMap model){
        model.addAttribute("medicamentos",service.buscar());
        model.addAttribute("conteudo","medicamento/list");
        return new ModelAndView("home",model);
    }

    @GetMapping("/cadastro")
    public ModelAndView preSalvar(@ModelAttribute Medicamento medicamento){
        return new ModelAndView("home","conteudo","medicamento/add");
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@Valid @ModelAttribute("medicamento") Medicamento medicamento, BindingResult result, RedirectAttributes attr){
        if(result.hasErrors()){
            return new ModelAndView("home","conteudo","medicamento/add");
        }

        service.salvar(medicamento);
        attr.addFlashAttribute("mensagem","Medicamento cadastrado com sucesso.");
        return new ModelAndView("redirect:/medicamentos");
    }

    @GetMapping("{id}/editar")
    public ModelAndView preAtualizar(@PathVariable("id") Long id, ModelMap model){
        Medicamento medicamento = service.buscarPorId(id);
        model.addAttribute("medicamento",medicamento);
        model.addAttribute("conteudo","medicamento/add");
        return new ModelAndView("home",model);
    }

    @PutMapping("/salvar")
    public ModelAndView atualizar(@Valid @ModelAttribute("medicamento") Medicamento medicamento,BindingResult result, RedirectAttributes attr){
        if(result.hasErrors()){
            return new ModelAndView("medicamento/add");
        }

        service.editar(medicamento);
        attr.addFlashAttribute("mensagem","Medicamento atualizado com sucesso.");
        return new ModelAndView("redirect:/medicamentos");
    }

    @GetMapping("/{id}/remover")
    public ModelAndView excluir(@PathVariable("id") Long id, ModelMap model){
        Medicamento medicamento = service.buscarPorId(id);
        model.addAttribute("medicamento",medicamento);
        model.addAttribute("conteudo","/medicamento/confirm");
        return new ModelAndView("home",model);
    }

    @GetMapping("/{id}/remover/confirm")
    public ModelAndView confirmar(@PathVariable Long id, RedirectAttributes attr ){
        service.excluir(id);
        attr.addFlashAttribute("mensagem", "Medicamento excluido com sucesso");
        return new ModelAndView("redirect:/medicamentos");
    }

    @GetMapping("/nome")
    public ModelAndView listarPorNome(@RequestParam(value = "nome") String nome, ModelMap model) {

        if (nome == null) {
            return new ModelAndView("redirect:/medicamentos");
        }

        model.addAttribute("medicamentos", service.buscarPorNome(nome));
        model.addAttribute("conteudo", "/medicamento/list");
        return new ModelAndView("home", model);
    }

    @GetMapping("/{id}")
    public ModelAndView listarPorId(@RequestParam(value = "id") Long id, ModelMap model) {

        if (id == null) {
            return new ModelAndView("redirect:/medicamentos");
        }

        model.addAttribute("medicamentos", service.buscarPorId(id));
        model.addAttribute("conteudo", "/medicamento/list");
        return new ModelAndView("home", model);
    }
}
