/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.allcoware.actiontaximockup.printing;

import com.allcoware.actiontaximockup.main.Main;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static jdk.nashorn.internal.codegen.Compiler.LOG;
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

    final StringBuilder sb = new StringBuilder();

    /**
     * This method/constructor designates the type of form the user wants to
     * print and creates a print.
     *
     */
    public ReportPrinter(int type, String pageTitle, String driverNum, String cabID, String firstName,
            String middleName, String lastName, String phoneNum, JTable jTable1) {

        try {
            if (type == -1) {
                find_Resouce("exampleReport.jrxml");
            } else if (type == 0) {
                find_Resouce("driverReport.jrxml");
            } else if (type == 1) {
                find_Resouce("cabReport.jrxml");
            } else if (type == 2) {
                find_Resouce("transactionReport.jrxml");
            } else if (type == 3) {
                find_Resouce("reocurringTransactionReport.jrxml");
            }
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("pageTitle", pageTitle);
            params.put("driverNum", driverNum);
            params.put("cabID", cabID);
            params.put("firstName", firstName);
            params.put("middleName", middleName);
            params.put("lastName", lastName);
            params.put("phoneNum", phoneNum);

            String temp = sb.toString();

            JasperReport jasperReport = JasperCompileManager.compileReport(temp);

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

    private void find_Resouce(String location) {
        try (Reader r = new InputStreamReader(Main.class.getResource(location).openStream())) {
            CharBuffer cb = CharBuffer.allocate(0x10);
            while (r.ready()) {
                r.read(cb);
                cb.rewind();
                sb.append(cb);
                cb.rewind();
            }

        } catch (IOException ex) {
            LOG.log(Level.WARNING, null, ex);
        }
    }
}
