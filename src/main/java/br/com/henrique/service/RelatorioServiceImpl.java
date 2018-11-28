package br.com.henrique.service;

import br.com.henrique.domain.MedicamentoPorReceita;
import br.com.henrique.domain.Receita;
import com.itextpdf.text.*;
import com.itextpdf.text.Image;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
@Transactional
public class RelatorioServiceImpl implements RelatorioService {


    @Transactional(readOnly = true)
    @Override
    public Document gerarPdf(Receita r) {

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("relatorios/receita"+r.getId()+".pdf"));
            document.open();
            document.addTitle("Receita");
            Image image = Image.getInstance("relatorios/logo.jpg");
            image.setAlignment(Element.ALIGN_CENTER);
            document.add(image);
            Paragraph title= new Paragraph("RECEITUÁRIO ",FontFactory.getFont(FontFactory.TIMES_BOLD,18));
            title.setAlignment(Element.ALIGN_CENTER);
            Paragraph p1=new Paragraph("Médico:  "+r.getConsulta().getMedico().getNome()+"               "+"Paciente: "+r.getConsulta().getPaciente().getNome(),FontFactory.getFont(FontFactory.TIMES_BOLD,12));
            p1.setAlignment(Element.ALIGN_CENTER);
            Paragraph p2=new Paragraph("Receita nº :"+r.getId()+"                                    "+"Data da Receita:  "+r.getDataReceita(),FontFactory.getFont(FontFactory.TIMES_BOLD,12));
            p2.setAlignment(Element.ALIGN_CENTER);
            Paragraph p3=new Paragraph("PRESCRIÇÃO: ",FontFactory.getFont(FontFactory.TIMES_BOLD,12));
            p3.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(new Paragraph(" "));
            document.add(p1);
            document.add(p2);
            document.add(new Paragraph(" "));
            document.add(p3);
            document.add(new Paragraph(" "));
            PdfPTable table=new PdfPTable(2);
            PdfPCell cell=new PdfPCell(new Paragraph("Medicamentos"));
            cell.setColspan(3);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
            table.addCell("Nome");
            table.addCell("Dosagem");
            for(MedicamentoPorReceita m: r.getMedicamentos()){
                table.addCell(m.getMedicamento().getNome());
                table.addCell(m.getDosagem());
            }
            document.add(table);
            Paragraph linha = new Paragraph("_________________________________");
            linha.setAlignment(Element.ALIGN_CENTER);
            Paragraph assinatura = new Paragraph("Assinatura Médico");
            assinatura.setAlignment(Element.ALIGN_CENTER);
            assinatura.setFont(FontFactory.getFont(FontFactory.TIMES,10));
            document.add(new Paragraph(" "));
            document.add(new Paragraph(" "));
            document.add(linha);
            document.add(assinatura);
        } catch (FileNotFoundException | DocumentException ex) {
            System.out.println("Error");
            System.out.println(ex.getMessage());
        } catch (IOException ex) {
            ex.getMessage();
        }finally{
            document.close();
        }

        return document;


    }
}
