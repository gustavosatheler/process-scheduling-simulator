/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escalonadores;

import estruturas.ListaCircular;
import gerais.Processo;
import java.util.ArrayList;

/**
 *
 * @author Lucas
 */
public class RR {

    private ListaCircular<Processo> listaProcessos;
    private ArrayList<Processo> listaEntrada;
    private int tamanhoQuantum;
    private int tempoClock;
    private int quantum;
    private ArrayList<String> listaPlot;

    public RR(ArrayList listaEntrada, int tamanhoQuantum) {

        int quantidadeProcessos;

        this.listaEntrada = listaEntrada;
        this.tamanhoQuantum = tamanhoQuantum;
        quantidadeProcessos = listaEntrada.size();

        this.listaProcessos = new ListaCircular(quantidadeProcessos);
        this.listaPlot = new ArrayList();

        tempoClock = 0;
        quantum = 0;

    }

    public ArrayList executar() {

        while (listaProcessos.getTamanho() > 0 || listaEntrada.size() > 0) {

            for (int i = listaEntrada.size() - 1; i >= 0; i--) {
                if (listaEntrada.get(i).getChegada() == tempoClock) {
                    listaProcessos.inserir(listaEntrada.remove(i));
                }
            }

            if (listaProcessos.getTamanho() > 0) {
                listaProcessos.obter().executar();
                listaPlot.add(listaProcessos.obter().getNome());
                quantum++;
                if (listaProcessos.obter().getTempo() == 0) {
                    listaProcessos.remover();
                    quantum = 0;
                }
                

            } else {
                listaPlot.add("-");
                quantum = 0;
            }

            tempoClock++;

            if (quantum == this.tamanhoQuantum) {
                quantum = 0;
            }

            if (quantum == 0 && listaProcessos.getTamanho() > 0) {
                listaProcessos.proximo();
            }

        }

        return listaPlot;

    }

}
