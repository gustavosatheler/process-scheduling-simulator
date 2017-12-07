/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

/**
 *
 * @author Lucas
 */
public class PlotTable {

    private static TableModel model;
    private static int step = 0;
    private static ArrayList<String> ordemProcessos;

    static void plotAll(JTable table, ArrayList<String> listaPlot, int quantidadeProcessos) {

        model = new DefaultTableModel(quantidadeProcessos + 1, listaPlot.size());
        ordemProcessos = new ArrayList();

        for (int i = 0; i < listaPlot.size(); i++) {
            if (ordemProcessos.isEmpty()) {
                ordemProcessos.add(listaPlot.get(i));
            }
            if (!ordemProcessos.contains(listaPlot.get(i)) && !listaPlot.get(i).equals("-")) {
                ordemProcessos.add(listaPlot.get(i));
            }
        }

        for (int i = 0; i < listaPlot.size(); i++) {
            if (listaPlot.get(i).equals("-")) {
                model.setValueAt(null, 0, i);
            } else {
                int j = ordemProcessos.size() - ordemProcessos.indexOf(listaPlot.get(i)) - 1;
                model.setValueAt(listaPlot.get(i), j, i);
            }
            model.setValueAt(step, quantidadeProcessos, i);
            step++;
        }

        table.setModel(model);
        layoutHeader(table, listaPlot);

    }

    static void plotStep(JTable table, ArrayList<String> listaPlot, int quantidadeProcessos) {

        if (model == null) {
            model = new DefaultTableModel(quantidadeProcessos + 1, listaPlot.size());
            table.setModel(model);
            layoutHeader(table, listaPlot);

        }
        if (ordemProcessos == null) {
            ordemProcessos = new ArrayList();
        }
        if (ordemProcessos.isEmpty()) {
            ordemProcessos.add(listaPlot.get(step));
        }
        if (!ordemProcessos.contains(listaPlot.get(step)) && !listaPlot.get(step).equals("-")) {
            ordemProcessos.add(listaPlot.get(step));
        }

        if (listaPlot.get(step).equals("-")) {
            model.setValueAt(null, 0, step);
        } else {
            int j = quantidadeProcessos - ordemProcessos.indexOf(listaPlot.get(step)) - 1;
            model.setValueAt(listaPlot.get(step), j, step);
        }
        model.setValueAt(step, quantidadeProcessos, step);
        table.setModel(model);

        step++;
    }

    static void stepBack(JTable table, ArrayList<String> listaPlot, int quantidadeProcessos) {
        step--;
        if (step >= 0) {
            if (listaPlot.get(step).equals("-")) {
                model.setValueAt(null, 0, step);
            } else {
                int j = quantidadeProcessos - ordemProcessos.indexOf(listaPlot.get(step)) - 1;
                model.setValueAt(null, j, step);
            }
            model.setValueAt(null, quantidadeProcessos, step);
        }
    }

    static void reset() {
        model = null;
        ordemProcessos = null;
        step = 0;
    }

    public static int getStep() {
        return step;
    }

    private static void layoutHeader(JTable table, ArrayList<String> listaPlot) {

        int tamanho1;
        int tamanho2;

        for (int i = 0; i < listaPlot.size(); i++) {

            tamanho1 = String.valueOf(i).length() * 20;
            tamanho2 = listaPlot.get(i).length() * 20;
            if (tamanho1 > tamanho2) {
                table.getColumnModel().getColumn(i).setPreferredWidth(tamanho1);
            }else{
                table.getColumnModel().getColumn(i).setPreferredWidth(tamanho2);
            }

            JTableHeader header = table.getTableHeader();
            TableColumnModel colMod = header.getColumnModel();
            TableColumn tabCol = colMod.getColumn(i);
            tabCol.setHeaderValue(" ");
        }
    }

}
