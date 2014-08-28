/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allcoware.actiontaximockup.printing;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Reaper
 */
public class ReportPrinter {

    /**
     * This method/constructor designates the type of form the user wants to
     * print and creates a print.
     *
     */
    public ReportPrinter(int type, String pageTitle, String driverNum, String cabID, String firstName,
            String middleName, String lastName, String phoneNum, JTable jTable1) {

        try {
            URL reportSource = null;
            if (type == -1) {
                reportSource = ReportPrinter.class.getResource("exampleReport.jrxml");
            } else if (type == 0) {
                reportSource = ReportPrinter.class.getResource("driverReport.jrxml");
            } else if (type == 1) {
                reportSource = ReportPrinter.class.getResource("cabReport.jrxml");
            } else if (type == 2) {
                reportSource = ReportPrinter.class.getResource("transactionReport.jrxml");
            } else if (type == 3) {
                reportSource = ReportPrinter.class.getResource("reocurringTransactionReport.jrxml");
            }
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("pageTitle", pageTitle);
            params.put("driverNum", driverNum);
            params.put("cabID", cabID);
            params.put("firstName", firstName);
            params.put("middleName", middleName);
            params.put("lastName", lastName);
            params.put("phoneNum", phoneNum);

            JasperReport jasperReport = JasperCompileManager.compileReport(reportSource.getPath());

            if (jTable1 != null) {
                for (int i = 0; i < jTable1.getColumnCount(); i++) {
                    params.put("title_" + i, jTable1.getColumnName(i));
                }
                DefaultTableModel dModel = (DefaultTableModel) jTable1.getModel();
                JRTableModelDataSource dataSource = new JRTableModelDataSource(dModel);

                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource);
                JasperViewer.viewReport(jasperPrint, false);
            } else {
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
                JasperViewer.viewReport(jasperPrint, false);
            }
        } catch (Exception ex) {
            System.out.println("Cause: " + ex.getCause());
            System.out.println("Message: " + ex.getMessage());
            System.out.println("Local Message: " + ex.getLocalizedMessage());
            //ex.printStackTrace();
        }
    }
}
