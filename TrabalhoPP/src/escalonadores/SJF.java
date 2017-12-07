/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escalonadores;

import estruturas.ListaEncadeada;
import gerais.Processo;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Gustavo Bittencourt Satheler
 * <gustavo.satheler@alunos.unipampa.edu.br>
 */
public class SJF {

    private ArrayList<String> listaPlot;
    private ArrayList<Processo> listaEntrada;
    private ListaEncadeada<Processo> listaEspera;
    private int tempoClock;

    public SJF(ArrayList<Processo> listaEntrada) {

        this.listaPlot = new ArrayList();
        this.listaEspera = new ListaEncadeada();
        this.listaEntrada = listaEntrada;
        this.tempoClock = 0;

    }

    public ArrayList<String> executar() {

        while (listaEspera.tamanho() > 0 || listaEntrada.size() > 0) {

            for (int i = listaEntrada.size() - 1; i >= 0; i--) {
                if (listaEntrada.get(i).getChegada() == tempoClock) {
                    listaEspera.adicionar(listaEntrada.remove(i));
                }
            }

            if (listaEspera.tamanho() > 0) {
                this.ordenarDuracao(listaEspera);
                listaEspera.pegar(0).executar();
                listaPlot.add(listaEspera.pegar(0).getNome());
                if (listaEspera.pegar(0).getTempo() == 0) {
                    listaEspera.remover(0);
                }
            }else{
                listaPlot.add("-");
            }

            tempoClock++;
        }

        return listaPlot;

    }

    private void ordenarDuracao(ListaEncadeada lista1) {
        ArrayList<Processo> lista2 = new ArrayList();
        while (!lista1.vazio()) {
            lista2.add((Processo) lista1.pegar(0));
            lista1.remover(0);
        }

        Collections.sort(lista2, (o1, o2) -> {
            return ((Processo) o1).getTempo() - ((Processo) o2).getTempo();
        });

        while (!lista2.isEmpty()) {
            lista1.adicionar(lista2.get(0));
            lista2.remove(0);
        }
    }

}
