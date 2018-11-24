package br.com.henrique.controller;

import br.com.henrique.domain.Consulta;
import br.com.henrique.domain.Medico;
import br.com.henrique.domain.Paciente;
import br.com.henrique.service.ConsultaService;
import br.com.henrique.service.MedicoService;
import br.com.henrique.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    ConsultaService consultaService;

    @Autowired
    PacienteService pacienteService;

    @Autowired
    MedicoService medicoService;

    @GetMapping("/cadastro")
    public ModelAndView preSalvar(@ModelAttribute("consulta") Consulta consulta){
        return new ModelAndView("home","conteudo","consulta/add");
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@Valid @ModelAttribute("consulta") Consulta consulta, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("home","conteudo","consulta/add");
        }

        consultaService.salvar(consulta);
        attr.addFlashAttribute("mensagem", "Consulta cadastrada com sucesso.");
        return new ModelAndView("redirect:/consultas");
    }

    @GetMapping
    public ModelAndView listar(ModelMap model){
        model.addAttribute("consultas",consultaService.buscar());
        model.addAttribute("conteudo","consulta/list");
        return new ModelAndView("home",model);
    }

    @GetMapping("/{id}/editar")
    public ModelAndView preAtualizar(@PathVariable("id") Long id, ModelMap model) {
        Consulta consulta = consultaService.buscarPorId(id);
        model.addAttribute("consulta", consulta);
        model.addAttribute("conteudo","/consulta/add");
        return new ModelAndView("home", model);
    }

    @PutMapping("/salvar")
    public ModelAndView atualizar(@Valid @ModelAttribute("consulta") Consulta consulta, BindingResult result, RedirectAttributes attr) {
        if (result.hasErrors()) {
            return new ModelAndView("/consulta/add");
        }

        consultaService.editar(consulta);
        attr.addFlashAttribute("mensagem", "Consulta atualizada com sucesso.");
        return new ModelAndView("redirect:/consultas");
    }

    @GetMapping("/{id}/remover")
    public ModelAndView excluir(@PathVariable("id") Long id, ModelMap model){
        Consulta consulta = consultaService.buscarPorId(id);
        model.addAttribute("consulta",consulta);
        model.addAttribute("conteudo","/consulta/confirm");
        return new ModelAndView("home",model);
    }

    @GetMapping("/{id}/remover/confirm")
    public ModelAndView confirmar(@PathVariable Long id, RedirectAttributes attr ){
        consultaService.excluir(id);
        attr.addFlashAttribute("mensagem", "Consulta excluida com sucesso");
        return new ModelAndView("redirect:/consultas");
    }



    @GetMapping("/{id}")
    public ModelAndView listarPorId(@RequestParam(value = "id") Long id, ModelMap model) {

        if (id == null) {
            return new ModelAndView("redirect:/consultas");
        }

        model.addAttribute("consultas", consultaService.buscarPorId(id));
        model.addAttribute("conteudo", "/consulta/list");
        return new ModelAndView("home", model);
    }

    @GetMapping("/{id}/detalhes")
    public ModelAndView detalhesConsulta(@PathVariable Long id,ModelMap model){
        Consulta consulta= consultaService.buscarPorId(id);
        Long idPaciente = consulta.getPaciente().getId();
        Long idMedico = consulta.getMedico().getId();
        LocalDate date=LocalDate.now();
        model.addAttribute("consulta",consulta);
        model.addAttribute("paciente",pacienteService.buscarPorId(idPaciente));
        model.addAttribute("medico",medicoService.buscarPorId(idMedico));
        model.addAttribute("dataAtual",date);
        model.addAttribute("conteudo","consulta/detalhe");
        return new ModelAndView("home",model);
    }

    @GetMapping("/{id}/atender")
    public ModelAndView atender(@PathVariable Long id, RedirectAttributes attr){
        Consulta consulta=consultaService.buscarPorId(id);
        consulta.setTipo("andamento");
        consultaService.salvar(consulta);
        attr.addFlashAttribute("mensagem", "Consulta movida para atendimento");
        return new ModelAndView("redirect:/consultas");
    }

    @GetMapping("/hoje")
    public ModelAndView consultasHoje(ModelMap model){
        LocalDate date= LocalDate.now();
        model.addAttribute("consultasDia",consultaService.consultasDoDia(date));
        model.addAttribute("conteudo","consulta/consultahoje");
        return new ModelAndView("home",model);
    }

    @GetMapping("/andamento")
    public ModelAndView consultasHojeAndamento(ModelMap model){
        LocalDate date= LocalDate.now();
        model.addAttribute("consultasDiaAndamento",consultaService.consultasDoDiaAndamento(date));
        model.addAttribute("conteudo","consulta/andamento");
        return new ModelAndView("home",model);
    }

    @GetMapping("/paciente")
    public ModelAndView consultasPorPaciente(@RequestParam(value="nome") String nome,ModelMap model){
        if (nome == null) {
            return new ModelAndView("redirect:/consultas");
        }

        model.addAttribute("consultas", consultaService.consultasPorPaciente(nome));
        model.addAttribute("conteudo", "/consulta/list");
        return new ModelAndView("home", model);
    }

    @GetMapping("/medico")
    public ModelAndView consultasPorMedico(@RequestParam(value="nome") String nome,ModelMap model){
        if (nome == null) {
            return new ModelAndView("redirect:/consultas");
        }

        model.addAttribute("consultas", consultaService.consultasPorMedico(nome));
        model.addAttribute("conteudo", "/consulta/list");
        return new ModelAndView("home", model);
    }




    @ModelAttribute("pacientes")
    public List<Paciente> pacientes() {
        return pacienteService.buscar();
    }

    @ModelAttribute("medicos")
    public List<Medico> medicos() {
        return medicoService.buscar();
    }
}
