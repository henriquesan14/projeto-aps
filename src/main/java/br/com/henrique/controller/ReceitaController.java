package br.com.henrique.controller;


import br.com.henrique.domain.Medicamento;
import br.com.henrique.domain.MedicamentoPorReceita;
import br.com.henrique.domain.Receita;
import br.com.henrique.service.*;
import com.itextpdf.text.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.awt.*;
import java.io.*;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("consultas/{idConsulta}/receitas")
public class ReceitaController {

    @Autowired
    ReceitaService receitaService;

    @Autowired
    ConsultaService consultaService;

    @Autowired
    MedicamentoReceitaService medicamentoService;

    @Autowired
    MedicamentoService medicamento2Service;

    @Autowired
    RelatorioService relatorioService;

    @GetMapping
    public ModelAndView receita(@PathVariable("idConsulta") Long id, ModelMap model){
        model.addAttribute("receitas",receitaService.receitasPorConsulta(id));
        model.addAttribute("consulta",consultaService.buscarPorId(id));
        model.addAttribute("conteudo","receita/list");
        return new ModelAndView("home");
    }

    @GetMapping("/nova")
    public ModelAndView novaReceita(@PathVariable("idConsulta") Long idConsulta,@ModelAttribute("receita") Receita receita,ModelMap model){
        model.addAttribute("consulta",consultaService.buscarPorId(idConsulta));
        receita.setConsulta(consultaService.buscarPorId(idConsulta));
        receita.setDataReceita(LocalDate.now());
        model.addAttribute("conteudo","receita/add");
        return new ModelAndView("home");
    }

    @PostMapping("/salvar")
    public ModelAndView salvar(@Valid @ModelAttribute("receita") Receita receita, BindingResult result, RedirectAttributes attr){

        receitaService.salvar(receita);
        attr.addFlashAttribute("mensagem","Receita cadastrada com sucesso.");
        return new ModelAndView("redirect:/consultas/{idConsulta}/receitas");
    }

    @GetMapping("/{idReceita}")
    public ModelAndView listarPorId(@PathVariable("idConsulta") Long idConsulta,@PathVariable("idReceita") Long idReceita, ModelMap model, @ModelAttribute("medicamentoPorReceita")MedicamentoPorReceita medicamentoPorReceita) {
        if (idReceita == null) {
            return new ModelAndView("redirect:/consultas/{idConsulta}/receitas");
        }
        medicamentoPorReceita.setReceita(receitaService.buscarPorId(idReceita));
        model.addAttribute("consulta",consultaService.buscarPorId(idConsulta));
        model.addAttribute("medicamentos",medicamentoService.medicamentosPorReceita(idReceita));
        model.addAttribute("receita", receitaService.buscarPorId(idReceita));
        model.addAttribute("conteudo", "/receita/detalhe");
        return new ModelAndView("home", model);
    }

    @GetMapping("/{idReceita}/remover")
    public ModelAndView excluirReceita(@PathVariable("idReceita") Long id,RedirectAttributes attr){
        receitaService.excluir(id);
        medicamentoService.apagaMedicamentosPorReceita(id);
        attr.addFlashAttribute("mensagem","Receita excluida com sucesso");
        return new ModelAndView("redirect:/consultas/{idConsulta}/receitas");
    }

    @GetMapping("/{idReceita}/remover/{idMedicamento}")
    public ModelAndView excluirMedicamento(@PathVariable("idMedicamento") Long idMedicamento, RedirectAttributes attr){
        medicamentoService.excluir(idMedicamento);
        attr.addFlashAttribute("mensagem","Medicamento excluido com sucesso.");
        return new ModelAndView("redirect:/consultas/{idConsulta}/receitas/{idReceita}");
    }




    @PostMapping("/{idReceita}/incluir")
    public ModelAndView incluir(@Valid @ModelAttribute("medicamentoPorReceita") MedicamentoPorReceita medicamentoPorReceita,BindingResult result,RedirectAttributes attr){
        if(result.hasErrors()){
            attr.addFlashAttribute("mensagem","Error ao incluir medicamento.");
            return new ModelAndView ("redirect:/consultas/{idConsulta}/receitas/{idReceita}");
        }
        medicamentoService.salvar(medicamentoPorReceita);
        attr.addFlashAttribute("mensagem", "Medicamento incluido com sucesso.");
        return new ModelAndView("redirect:/consultas/{idConsulta}/receitas/{idReceita}");

    }

    @GetMapping("{idReceita}/imprimir")
    public ModelAndView imprimir(@PathVariable("idReceita") Long id){
        Receita receita=receitaService.buscarPorId(id);
        Document document=relatorioService.gerarPdf(receita);
        try {
            Runtime.getRuntime().exec(new String[]{"cmd.exe", "/c", "start", "relatorios//receita"+id+".pdf"});
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ModelAndView("redirect:/consultas/{idConsulta}/receitas/{idReceita}");
    }



    @ModelAttribute("nomesMedicamentos")
    public List<Medicamento> nomesMedicamentos(){
        return medicamento2Service.buscar();
    }



}
