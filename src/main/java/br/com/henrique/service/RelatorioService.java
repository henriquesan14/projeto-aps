package br.com.henrique.service;

import br.com.henrique.domain.Receita;
import com.itextpdf.text.Document;

public interface RelatorioService {
    Document gerarPdf(Receita receita);
}
