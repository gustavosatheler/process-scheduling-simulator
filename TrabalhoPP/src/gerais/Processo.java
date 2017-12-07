/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gerais;

/**
 *
 * @author Lucas
 */
public class Processo {

    private String nome;
    private int chegada;
    private int tempo;

    public Processo(String nome, int chegada, int tempo) {

        this.nome = nome;
        this.chegada = chegada;
        this.tempo = tempo;

    }

    public void executar() {
        if (this.tempo > 0) {
            this.tempo--;
        }
    }

    public String getNome() {
        return this.nome;
    }

    public int getChegada() {
        return this.chegada;
    }

    public int getTempo() {
        return this.tempo;
    }

}
