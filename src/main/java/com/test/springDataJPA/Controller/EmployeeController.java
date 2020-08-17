package com.test.springDataJPA.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.test.springDataJPA.Service.EmployeeService;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/report", method = RequestMethod.GET)
    public void report(HttpServletResponse response,@RequestParam("id") int id) throws Exception {
//        response.setContentType("text/html");
        response.setContentType("application/x-download");
        response.setHeader("Content-Disposition", "attachment; filename=\"users.pdf\"");

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(employeeService.report(id));
        JRBeanCollectionDataSource dataSource2 = new JRBeanCollectionDataSource(employeeService.report2(id));
        InputStream inputStream = this.getClass().getResourceAsStream("/report.jrxml");
        InputStream inputStream2 = this.getClass().getResourceAsStream("/report2.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(inputStream);
        JasperReport jasperReport2 = JasperCompileManager.compileReport(inputStream2);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, null, dataSource);
        JasperPrint jasperPrint2 = JasperFillManager.fillReport(jasperReport2, null, dataSource2);
        List<JasperPrint> jasperPrints = new ArrayList<JasperPrint>();
        jasperPrints.add(jasperPrint);
        jasperPrints.add(jasperPrint2);
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(SimpleExporterInput.getInstance(jasperPrints)); //Set as export input my list with JasperPrint s
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream())); //or any other out streaam
        SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
        configuration.setCreatingBatchModeBookmarks(true); //add this so your bookmarks work, you may set other parameters
        exporter.setConfiguration(configuration);
        exporter.exportReport();



//        HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
//        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
//        exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
//        exporter.exportReport();
//        JasperExportManager.exportReportToPdfStream(jasperPrint,response.getOutputStream());
    }
}