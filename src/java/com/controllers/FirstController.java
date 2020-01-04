/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.controllers;

import au.com.bytecode.opencsv.CSVWriter;
import com.service.ReportService;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.*;
import com.service.IncidenceReportImpl;
import java.io.FileWriter;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.ServletContextAware;

/**
 *
 * @author Supersoft
 */
@Controller
@Scope("session")




public class FirstController implements ServletContextAware {
    
    private Logger logger = Logger.getLogger(MainController.class.getName());

    static final String SAVE_DIRECTORY = "uploads";
    String fullSavePath;
    static java.util.Date date = Calendar.getInstance().getTime();
    static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
    
    String today = sdf1.format(date);

    @Autowired
    private ReportService reportService;
    
    @Autowired
    IncidenceReportImpl incidence;
    
    
     private static ServletContext servletContext;

    @Override
    public void setServletContext(ServletContext servletCtx) {
        FirstController.servletContext = servletCtx;
    }
    
     @RequestMapping(value = "/downloadincidencereport", method = {RequestMethod.POST, RequestMethod.GET})
    public void downloadIncidenceReport(HttpServletRequest request,
            HttpServletResponse response, @RequestParam String reportType,
            @RequestParam(defaultValue = "") String fromDate,
            @RequestParam(defaultValue = "") String toDate, @RequestParam(defaultValue = "") String type) throws IOException {
        String subSql;
        String placeHolders[] = new String[2];
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();

         String username = auth.getName();
         System.out.println("username++++++++++++++++++++++++++++++++++ " + username);
        subSql = " WHERE tbl_incidences.sent_by=tbl_usertype_mapping.username AND DATE(datelogged) BETWEEN ? AND ? ";

        placeHolders[0] = fromDate;
        placeHolders[1] = toDate;
        System.out.println("sql: " + subSql);

        String sql = " SELECT tbl_incidences.id,tbl_incidences.sent_by,tbl_incidences.title,tbl_incidences.message_body,tbl_incidences.datelogged,tbl_incidences.institutionname,tbl_incidences.trackingnumber,tbl_incidences.products,tbl_usertype_mapping.username as username\n"
                + "FROM tbl_incidences,tbl_usertype_mapping " + subSql + " ORDER BY datelogged DESC ";

        System.out.println("fromDate: " + fromDate);
        System.out.println("toDate: " + toDate);
        System.out.println("sql: " + sql);
        String[] columns = {"S/N","sent_by", "title", "message_body", "institutionname", "datelogged", "trackingnumber", "products"};
        String reportTitle = "Report for Cases";
        List<Map<String, Object>> getTableFields = reportService.getRecords(sql, columns.length, placeHolders);
         System.out.println("getTableFields=============================================================== " + getTableFields);
        List<String> getColumnNames = Arrays.asList(columns);

        String filepath = null;
        String filepath2 = null;
         System.out.println("GOT HERE TO TEST!!!!!!!!!!!!!!!!!!!!!!!!");
//        String filename = null;
        if (!getTableFields.isEmpty()) {
            switch (reportType) {
                case "pdf":
                    filepath = getGenerateReport(getTableFields, getColumnNames, reportTitle, "pdf");
                    openPdf(response, request, filepath);
                    break;
                case "csv":
                    filepath = getGenerateReport(getTableFields, getColumnNames, reportTitle, "csv");
                    openCsv(response, request, filepath);
                    break;
                case "zip":
                    try {
                        filepath = getGenerateReport(getTableFields, getColumnNames, reportTitle, "pdf");

                        filepath2 = getGenerateReport(getTableFields, getColumnNames, reportTitle, "csv");

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    String location = getPathToFile("zip");
//                    filename = "incidence report" + today + ".zip";
                     System.out.println("GOT HERE TO TEST!!!!!!!!!!!!!!!!!!!!!!!!");
                    final int BUFFER = 2048;
                    try {
                        BufferedInputStream origin = null;
                        FileOutputStream dest = new FileOutputStream(location);
                        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
                        byte data[] = new byte[BUFFER];

                        String files[] = {filepath, filepath2};
                        for (String filee : files) {
                            FileInputStream fi = new FileInputStream(filee);
                            origin = new BufferedInputStream(fi, BUFFER);
                            ZipEntry entry = new ZipEntry(filee);
                            out.putNextEntry(entry);
                            int count;
                            while ((count = origin.read(data, 0, BUFFER)) != -1) {
                                out.write(data, 0, count);
                            }
                            origin.close();
                        }
                        out.close();

                    } catch (IOException e) {
                        System.out.println("error:" + e.getMessage());
                    }

                    openZip(response, request, location);
                    break;
                default:
                    break;
            }
        } else {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();

            response.sendRedirect("/IncidenceManagementReportSystem/downloaderror");

            pw.close();
        }

    }
    
    
    
     @RequestMapping(value = "/downloadincidencereport2", method = {RequestMethod.POST, RequestMethod.GET})
    public void downloadIncidenceReport2(HttpServletRequest request,
            HttpServletResponse response, @RequestParam String reportType,
            @RequestParam(defaultValue = "") String fromDate,
            @RequestParam(defaultValue = "") String toDate, @RequestParam(defaultValue = "") String type) throws IOException {
        String subSql;
        String placeHolders[] = new String[3];
             Authentication auth = SecurityContextHolder.getContext().getAuthentication();
             String username = auth.getName();
        subSql = " WHERE tbl_incidences.sent_by=tbl_usertype_mapping.username AND DATE(datelogged) BETWEEN ? AND ? ";
        
        placeHolders[0] = type;
        placeHolders[1] = fromDate;
        placeHolders[2] = toDate;
        System.out.println("sqb: " + subSql);

        String sql = " SELECT tbl_incidences.id,tbl_incidences.sent_by,tbl_incidences.title,tbl_incidences.message_body,tbl_incidences.datelogged,tbl_incidences.institutionname,tbl_incidences.trackingnumber,tbl_incidences.products,tbl_usertype_mapping.username as username\n"
                + "FROM tbl_incidences,tbl_usertype_mapping " + subSql + " ORDER BY datelogged DESC ";
        
        System.out.println("type: " + type);
        System.out.println("fromDate: " + fromDate);
        System.out.println("toDate: " + toDate);
        System.out.println("sql: " + sql);
        String[] columns = {"S/N","sent_by", "title", "message_body", "institutionname", "datelogged", "trackingnumber", "products"};

        String reportTitle = "Report for Cases";
        List<Map<String, Object>> getTableFields = reportService.getRecords(sql, columns.length, placeHolders);
        List<String> getColumnNames = Arrays.asList(columns);

        String filepath = null;
        String filepath2 = null;
        String filename = null;
        if (!getTableFields.isEmpty()) {
            switch (reportType) {
                case "pdf":
                    filepath = getGenerateReport(getTableFields, getColumnNames, reportTitle, "pdf");
                    openPdf(response, request, filepath);
                    break;
                case "csv":
                    filepath = getGenerateReport(getTableFields, getColumnNames, reportTitle, "csv");
                    openCsv(response, request, filepath);
                    break;
                case "zip":
                    try {
                        filepath = getGenerateReport(getTableFields, getColumnNames, reportTitle, "pdf");

                        filepath2 = getGenerateReport(getTableFields, getColumnNames, reportTitle, "csv");

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    String location = getPathToFile("zip");
                    filename = "incidence report" + today + ".zip";
                    final int BUFFER = 2048;
                    try {
                        BufferedInputStream origin = null;
                        FileOutputStream dest = new FileOutputStream(location);
                        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
                        byte data[] = new byte[BUFFER];

                        String files[] = {filepath, filepath2};
                        for (String filee : files) {
                            FileInputStream fi = new FileInputStream(filee);
                            origin = new BufferedInputStream(fi, BUFFER);
                            ZipEntry entry = new ZipEntry(filee);
                            out.putNextEntry(entry);
                            int count;
                            while ((count = origin.read(data, 0, BUFFER)) != -1) {
                                out.write(data, 0, count);
                            }
                            origin.close();
                        }
                        out.close();

                    } catch (IOException e) {
                        System.out.println("error:" + e.getMessage());
                    }

                    openZip(response, request, location);
                    break;
                default:
                    break;
            }
        } else {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();

            response.sendRedirect("/IncidenceManagementReportSystem/downloaderror");

            pw.close();
        }

    }
    
    
    
    
    
    
    
     
     @RequestMapping(value = "/downloadincidencereport3", method = {RequestMethod.POST, RequestMethod.GET})
    public void downloadIncidenceReportThree(HttpServletRequest request,
            HttpServletResponse response, @RequestParam String reportType,
            @RequestParam(defaultValue = "") String fromDate,
            @RequestParam(defaultValue = "") String toDate, @RequestParam(defaultValue = "") String type, @RequestParam(defaultValue = "") String institutionname) throws IOException {
        String subSql;
        String placeHolders[] = new String[3];
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();

         String username = auth.getName();
         System.out.println("username++++++++++++++++++++++++++++++++++ " + username);
//         String Institutionname = incidence.getIntitutionNameTwo(username);
        subSql = " WHERE tbl_incidences.institutionname = ? AND DATE(datelogged) BETWEEN ? AND ? ";

        placeHolders[0] = fromDate;
        placeHolders[1] = toDate;
        placeHolders[2] = institutionname;
        System.out.println("sql: " + subSql);
         System.out.println("JUST TO BE CLEAR########################");
        String sql = " SELECT tbl_incidences.id,tbl_incidences.sent_by,tbl_incidences.title,tbl_incidences.message_body, tbl_incidences.institutionname, tbl_incidences.datelogged,tbl_incidences.trackingnumber,tbl_incidences.products \n"
                + "FROM tbl_incidences " + subSql + " ORDER BY datelogged DESC ";

        System.out.println("fromDate: " + fromDate);
        System.out.println("toDate: " + toDate);
        System.out.println("sql: " + sql);
        String[] columns = {"S/N","sent_by", "title", "message_body", "institutionname", "datelogged", "trackingnumber", "products"};
        String reportTitle = "Report for Cases";
        List<Map<String, Object>> getTableFields = reportService.getRecords(sql, columns.length, placeHolders);
         System.out.println("getTableFields=============================================================== " + getTableFields);
        List<String> getColumnNames = Arrays.asList(columns);

        String filepath = null;
        String filepath2 = null;
         System.out.println("GOT HERE TO TEST!!!!!!!!!!!!!!!!!!!!!!!!");
//        String filename = null;
        if (!getTableFields.isEmpty()) {
            switch (reportType) {
                case "pdf":
                    filepath = getGenerateReport(getTableFields, getColumnNames, reportTitle, "pdf");
                    openPdf(response, request, filepath);
                    break;
                case "csv":
                    filepath = getGenerateReport(getTableFields, getColumnNames, reportTitle, "csv");
                    openCsv(response, request, filepath);
                    break;
                case "zip":
                    try {
                        filepath = getGenerateReport(getTableFields, getColumnNames, reportTitle, "pdf");

                        filepath2 = getGenerateReport(getTableFields, getColumnNames, reportTitle, "csv");

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    String location = getPathToFile("zip");
//                    filename = "incidence report" + today + ".zip";
                     System.out.println("GOT HERE TO TEST!!!!!!!!!!!!!!!!!!!!!!!!");
                    final int BUFFER = 2048;
                    try {
                        BufferedInputStream origin = null;
                        FileOutputStream dest = new FileOutputStream(location);
                        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
                        byte data[] = new byte[BUFFER];

                        String files[] = {filepath, filepath2};
                        for (String filee : files) {
                            FileInputStream fi = new FileInputStream(filee);
                            origin = new BufferedInputStream(fi, BUFFER);
                            ZipEntry entry = new ZipEntry(filee);
                            out.putNextEntry(entry);
                            int count;
                            while ((count = origin.read(data, 0, BUFFER)) != -1) {
                                out.write(data, 0, count);
                            }
                            origin.close();
                        }
                        out.close();

                    } catch (IOException e) {
                        System.out.println("error:" + e.getMessage());
                    }

                    openZip(response, request, location);
                    break;
                default:
                    break;
            }
        } else {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();

            response.sendRedirect("/IncidenceManagementReportSystem/downloaderror");

            pw.close();
        }

    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
      public static String getGenerateReport(List<Map<String, Object>> list, List<String> headings, String reportTitle, String type) throws FileNotFoundException, IOException {
        String FilePath = "";
        if (type.trim().equalsIgnoreCase("pdf")) {
            FilePath = getPathToFile(type.trim());
            getIncidentPdf(list, headings, reportTitle, FilePath);
        } else {
            FilePath = getPathToFile(type.trim());
            getCsv(list, headings, reportTitle, FilePath);
        }

        return FilePath;
    }
      
        public static String getPathToFile(String type) {

        String filename;
        StringBuilder sb = new StringBuilder();
        java.util.Date today = Calendar.getInstance().getTime();
        String appPath = servletContext.getRealPath("/");
        System.out.println("appPath:" + appPath);
        appPath = appPath.replace('\\', '/');
        String ServerDir = "";
        if (appPath.endsWith("/")) {
            ServerDir = appPath + SAVE_DIRECTORY;
        } else {
            ServerDir = (appPath + "/" + SAVE_DIRECTORY).trim();
        }

        File fileSaveDir = new File(ServerDir);
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }

        if (type.equalsIgnoreCase("pdf")) {
            filename = sb.append(ServerDir).append("/").append("Cases").append(sdf1.format(today)).append(".pdf").toString();
        } else if ((type.equalsIgnoreCase("csv"))) {
            filename = sb.append(ServerDir).append("/").append("Cases").append(sdf1.format(today)).append(".csv").toString();
        } else {
            filename = sb.append(ServerDir).append("/").append("Cases").append(sdf1.format(today)).append(".zip").toString();
        }
        return filename;
    }
        
        
        
          private static String getCsv(List<Map<String, Object>> list, List<String> headings, String reportTitle, String file) throws IOException {
        String path = file;
        String array[] = new String[headings.size()];
        try (CSVWriter writer = new CSVWriter(new FileWriter(path))) {
            for (int i = 0; i < headings.size(); i++) {
                array[i] = headings.get(i);
            }
            writer.writeNext(array);
            // write table row data
            for (Map<String, Object> row : list) {
                String array2[] = new String[headings.size()];
                for (int i = 0; i < headings.size(); i++) {
                    if (row.get(headings.get(i).trim()) != null) {
                        array2[i] = String.valueOf(row.get(headings.get(i).trim()));
//                        System.out.println("headings[" + row.get(headings.get(i).trim()).toString() + "]");
                    } else {
                        array2[i] = String.valueOf("");
                        System.out.println("headings[]");
                    }
                }
                writer.writeNext(array2);
            }
        }
        return path;
    }
    
          
          
        void openCsv(HttpServletResponse response, HttpServletRequest request, String filepath) throws FileNotFoundException, IOException {
        File file = new File(filepath);
        System.out.println("fff:" + file.getName());

        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());
        byte[] buffer = new byte[8096];
        int bytesRead = 0;
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        outStream.flush();
        inStream.close();
    }
        
        
        
        void openZip(HttpServletResponse response, HttpServletRequest request, String filepath) throws FileNotFoundException, IOException {
        File file = new File(filepath);
        System.out.println("fff:" + file.getName());
        
        response.setContentType("application/zip");
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());

        byte[] buffer = new byte[8096];
        int bytesRead = 0;
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        outStream.flush();
        inStream.close();
    }
        
        
        
        
        private static String getIncidentPdf(List<Map<String, Object>> list, List<String> headings, String reportTitle, String file) throws FileNotFoundException, MalformedURLException, IOException {
        String path = file;
        int countDispute = 0;

        Document my_pdf_report = new Document(PageSize.A4.rotate());
        my_pdf_report.setMargins(20, 20, 20, 20);

        try {
            PdfWriter.getInstance(my_pdf_report, new FileOutputStream(path));
            my_pdf_report.open();

            Font largeBold = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);
            Font headingFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

//            String imgpath = servletContext.getRealPath("/") + ("resources/imgs/naira_logo.png");

//            Image img = Image.getInstance(imgpath);
//            img.setAlignment(Element.ALIGN_CENTER);
//            my_pdf_report.add(img);

            Paragraph title = new Paragraph(reportTitle, largeBold);
            title.setAlignment(Element.ALIGN_CENTER);
            my_pdf_report.add(title);
            my_pdf_report.add(new Paragraph("\n"));

            String dateGenerated = "Report generated on:" + date;
            Paragraph dateGen = new Paragraph(dateGenerated, headingFont);
            dateGen.setAlignment(Element.ALIGN_CENTER);
            my_pdf_report.add(dateGen);
            my_pdf_report.add(new Paragraph("\n"));

            PdfPTable my_report_table = new PdfPTable(headings.size());

            PdfPCell table_cell;

            for (String str : headings) {
                String field = str;
                table_cell = new PdfPCell(new Phrase(field, FontFactory.getFont(FontFactory.TIMES_BOLD)));
                my_report_table.addCell(table_cell);
            }

//            HashMap<Object, Integer> getCounters = new HashMap();
            HashMap<Object, Integer> getIncrements = new HashMap();
            HashMap<Object, Integer> getIncrementFinancialInstitution = new HashMap();
//            int countMe = 0;

            for (Map<String, Object> row : list) {
                for (String str : headings) {
                    if (row.get(str.trim()) != null) {
                        if (str.equalsIgnoreCase("type")) {
                            int getC;
                            try {
                                getC = getIncrements.get(row.get(str.trim()));

                            } catch (Exception ex) {
                                getC = 0;
                                System.out.println("Got here....");
                            }

                            getIncrements.put(row.get(str.trim()), getC + 1);
                        }

                        if (str.equalsIgnoreCase("institutionname")) {
                            int getF;
                            try {
                                getF = getIncrementFinancialInstitution.get(row.get(str.trim()));

                            } catch (Exception ex) {
                                getF = 0;
                                System.out.println("Got here....");
                            }
                            getIncrementFinancialInstitution.put(row.get(str.trim()), getF + 1);
                        }

                        my_report_table.addCell((row.get(str.trim()).toString()));
                        my_report_table.setSpacingAfter(150f);
                    } else {
                        my_report_table.addCell((""));
                    }
                }
            }

            my_pdf_report.add(my_report_table);

//            for (Map.Entry<Object, Integer> entry : getIncrements.entrySet()) {
//                System.out.println("entries:" + entry.getKey() + "/" + entry.getValue().toString());
//                Paragraph valuesGen = new Paragraph("Number of incidence : " + entry.getKey() + "-" + entry.getValue(), largeBold);
//                my_pdf_report.add(valuesGen);
//                valuesGen.setAlignment(Element.ALIGN_CENTER);
//            }

//            for (Map.Entry<Object, Integer> entry : getIncrementFinancialInstitution.entrySet()) {
//                System.out.println("entries:" + entry.getKey() + "/" + entry.getValue().toString());
//                Paragraph valuesGen = new Paragraph("Number of financial institution : " + entry.getKey() + "-" + entry.getValue(), largeBold);
//                my_pdf_report.add(valuesGen);
//                valuesGen.setAlignment(Element.ALIGN_CENTER);
//
//            }

            my_pdf_report.add(new Paragraph("\n"));

            my_pdf_report.close();
        } catch (DocumentException ex) {

        }
        return path;
    }
        
    // This method is responsible for opening the pdf file on the browser using httpservlet response
    void openPdf(HttpServletResponse response, HttpServletRequest request, String filepath) throws FileNotFoundException, IOException {
        File file = new File(filepath);
        System.out.println("fff:" + file.getName());

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
        BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
        BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());

        byte[] buffer = new byte[8096];
        int bytesRead = 0;
        while ((bytesRead = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
        outStream.flush();
        inStream.close();

    }
    
    
    
     
     @RequestMapping(value = "/incidentreport/downloadincidencereport", method = {RequestMethod.POST, RequestMethod.GET})
    public void downloadIncidenceReportNewPage(HttpServletRequest request,
            HttpServletResponse response, @RequestParam String reportType,
            @RequestParam(defaultValue = "") String fromDate,
            @RequestParam(defaultValue = "") String toDate, @RequestParam(defaultValue = "") String type) throws IOException {
        String subSql;
        String placeHolders[] = new String[2];
         Authentication auth = SecurityContextHolder.getContext().getAuthentication();

         String username = auth.getName();
         System.out.println("username++++++++++++++++++++++++++++++++++ " + username);
        subSql = " WHERE tbl_incidences.sent_by=tbl_usertype_mapping.username AND DATE(datelogged) BETWEEN ? AND ? ";

        placeHolders[0] = fromDate;
        placeHolders[1] = toDate;
        System.out.println("sql: " + subSql);

        String sql = " SELECT tbl_incidences.id,tbl_incidences.sent_by,tbl_incidences.title,tbl_incidences.message_body,tbl_incidences.datelogged,tbl_incidences.institutionname,tbl_incidences.trackingnumber,tbl_incidences.products,tbl_usertype_mapping.username as username\n"
                + "FROM tbl_incidences,tbl_usertype_mapping " + subSql + " ORDER BY datelogged DESC ";

        System.out.println("fromDate: " + fromDate);
        System.out.println("toDate: " + toDate);
        System.out.println("sql: " + sql);
        String[] columns = {"S/N","sent_by", "title", "message_body", "institutionname", "datelogged", "trackingnumber","products"};
        String reportTitle = "Report for Cases";
        List<Map<String, Object>> getTableFields = reportService.getRecords(sql, columns.length, placeHolders);
         System.out.println("getTableFields=============================================================== " + getTableFields);
        List<String> getColumnNames = Arrays.asList(columns);

        String filepath = null;
        String filepath2 = null;
         System.out.println("GOT HERE TO TEST!!!!!!!!!!!!!!!!!!!!!!!!");
//        String filename = null;
        if (!getTableFields.isEmpty()) {
            switch (reportType) {
                case "pdf":
                    filepath = getGenerateReport(getTableFields, getColumnNames, reportTitle, "pdf");
                    openPdf(response, request, filepath);
                    break;
                case "csv":
                    filepath = getGenerateReport(getTableFields, getColumnNames, reportTitle, "csv");
                    openCsv(response, request, filepath);
                    break;
                case "zip":
                    try {
                        filepath = getGenerateReport(getTableFields, getColumnNames, reportTitle, "pdf");

                        filepath2 = getGenerateReport(getTableFields, getColumnNames, reportTitle, "csv");

                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    String location = getPathToFile("zip");
//                    filename = "incidence report" + today + ".zip";
                     System.out.println("GOT HERE TO TEST!!!!!!!!!!!!!!!!!!!!!!!!");
                    final int BUFFER = 2048;
                    try {
                        BufferedInputStream origin = null;
                        FileOutputStream dest = new FileOutputStream(location);
                        ZipOutputStream out = new ZipOutputStream(new BufferedOutputStream(dest));
                        byte data[] = new byte[BUFFER];

                        String files[] = {filepath, filepath2};
                        for (String filee : files) {
                            FileInputStream fi = new FileInputStream(filee);
                            origin = new BufferedInputStream(fi, BUFFER);
                            ZipEntry entry = new ZipEntry(filee);
                            out.putNextEntry(entry);
                            int count;
                            while ((count = origin.read(data, 0, BUFFER)) != -1) {
                                out.write(data, 0, count);
                            }
                            origin.close();
                        }
                        out.close();

                    } catch (IOException e) {
                        System.out.println("error:" + e.getMessage());
                    }

                    openZip(response, request, location);
                    break;
                default:
                    break;
            }
        } else {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();

            response.sendRedirect("/IncidenceManagementReportSystem/downloaderror");

            pw.close();
        }

    }
    
          
          
}
