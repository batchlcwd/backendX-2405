package com.substring.smartresult.controllers;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/visit")
public class VisitController {

    @GetMapping
    public ResponseEntity<byte[]> downloadVisitingCard() {

        System.out.println("Printing visiting card");


//        create visiting card

        var visitingCard1 = new VisitingCard("Aman", "45141412", "aman@substringtechnologies.com", "New Delhi,1242134", "Software Engineer");


        var visitingCard2 = new VisitingCard("Akshat", "45141413", "akshat@substringtechnologies.com", "New Delhi,1242135", "Software Engineer");
        var visitingCard3 = new VisitingCard("Shivam", "45141414", "shivam@substringtechnologies.com", "New Delhi,1242136", "Software Engineer");
        var visitingCard4 = new VisitingCard("Rohan", "45141415", "rohan@substringtechnologies.com", "New Delhi,1242137", "Software Engineer");

        var list= List.of(visitingCard1,visitingCard2,visitingCard3,visitingCard4);


        JRBeanCollectionDataSource source = new JRBeanCollectionDataSource(list);

        try(var inputStream= new ClassPathResource("reports/visit.jrxml").getInputStream()){


            //compile the report
            var report= JasperCompileManager.compileReport(inputStream);

            //fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(report, null, source);

            // export to pdf
            byte[] bytes = JasperExportManager.exportReportToPdf(jasperPrint);


            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(bytes);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }



    }

}
