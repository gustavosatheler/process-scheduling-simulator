/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerais;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Lucas
 */
public class LeitorArquivo {

    private static File arquivo;
    private static int quantidadeProcessos;

    public static File carregarArquivo() {

        JFileChooser chooser = new JFileChooser();
        FileFilter filter = new FileNameExtensionFilter("CSV file", "csv");
        chooser.addChoosableFileFilter(filter);
        chooser.setAcceptAllFileFilterUsed(false);
        int retorno = chooser.showOpenDialog(null);

        if (retorno == JFileChooser.APPROVE_OPTION) {

            arquivo = chooser.getSelectedFile();

            JOptionPane.showMessageDialog(null, "Arquivo importado com sucesso");
            return arquivo;

        }

        if (arquivo == null) {
            JOptionPane.showMessageDialog(null, "Arquivo Não Selecionado");
        }else{
            JOptionPane.showMessageDialog(null, "Nenhum arquivo foi selecionado será mantido o anterior");
        }
        

        return arquivo;
    }

    public static ArrayList montarLista(File arquivo) {

        int chegada;
        int tamanho;
        String[] processoStringArray;
        String processoString;
        String nome;
        ArrayList<Processo> listaProcessos = new ArrayList();
        FileReader reader;
        BufferedReader br;

        try {
            reader = new FileReader(arquivo);
            br = new BufferedReader(reader);
            while ((processoString = br.readLine()) != null) {
                processoStringArray = processoString.split(",");

                nome = processoStringArray[0];
                chegada = Integer.parseInt(processoStringArray[1]);
                tamanho = Integer.parseInt(processoStringArray[2]);

                listaProcessos.add(new Processo(nome, chegada, tamanho));

            }

        } catch (IOException ex) {
            Logger.getLogger(LeitorArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }

        Collections.sort(listaProcessos, new Comparator<Processo>() {
            @Override
            public int compare(Processo o1, Processo o2) {
                return o1.getChegada() - o2.getChegada();
            }
        });

        LeitorArquivo.quantidadeProcessos = listaProcessos.size();

        return listaProcessos;
    }

    public static int getQuantidadeProcessos() {
        return quantidadeProcessos;
    }

}
