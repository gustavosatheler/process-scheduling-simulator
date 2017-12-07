/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturas;

/**
 * @author Gustavo Bittencourt Satheler
 */
public class ListaEncadeada<T> {

    private Celula primeira;
    private Celula ultima;
    private int totalDeElementos;

    public void adicionar(T elemento) {
        if (this.totalDeElementos == 0) {
            this.adicionarNoComeco(elemento);
        } else {
            Celula nova = new Celula(elemento);
            this.ultima.setProxima(nova);
            nova.setAnterior(this.ultima);
            this.ultima = nova;
            this.totalDeElementos++;
        }
    }

    public void adicionar(int posicao, T elemento) {
        if (posicao == 0) { // No começo.
            this.adicionarNoComeco(elemento);
        } else if (posicao == this.totalDeElementos) { // No fim.
            this.adicionar(elemento);
        } else {
            Celula anterior = this.pegarCelula(posicao - 1);
            Celula proxima = anterior.getProxima();
            Celula nova = new Celula(anterior.getProxima(), elemento);
            nova.setAnterior(anterior);
            anterior.setProxima(nova);
            proxima.setAnterior(nova);
            this.totalDeElementos++;
        }
    }

    public T pegar(int posicao) {
        return (T)this.pegarCelula(posicao).getElemento();
    }

    public void remover(int posicao) {
        if (!this.posicaoOcupada(posicao)) {
            throw new IllegalArgumentException("Posição não existe");
        }

        if (posicao == 0) {
            this.removerDoComeco();
        } else if (posicao == this.totalDeElementos - 1) {
            this.removerDoFim();
        } else {
            Celula anterior = this.pegarCelula(posicao - 1);
            Celula atual = anterior.getProxima();
            Celula proxima = atual.getProxima();

            anterior.setProxima(proxima);
            proxima.setAnterior(anterior);

            this.totalDeElementos--;
        }
    }

    public int tamanho() {
        return this.totalDeElementos;
    }

    public boolean contem(T elemento) {
        Celula atual = this.primeira;

        while (atual != null) {
            if (atual.getElemento().equals(elemento)) {
                return true;
            }
            atual = atual.getProxima();
        }
        return false;
    }

    public void adicionarNoComeco(T elemento) {
        if (this.totalDeElementos == 0) {
            Celula nova = new Celula(elemento);
            this.primeira = nova;
            this.ultima = nova;
        } else {
            Celula nova = new Celula(this.primeira, elemento);
            this.primeira.setAnterior(nova);
            this.primeira = nova;
        }
        this.totalDeElementos++;
    }

    public void removerDoComeco() {
        if (!this.posicaoOcupada(0)) {
            throw new IllegalArgumentException("Posição não existe");
        }

        this.primeira = this.primeira.getProxima();
        this.totalDeElementos--;

        if (this.totalDeElementos == 0) {
            this.ultima = null;
        }
    }

    public void removerDoFim() {
        if (!this.posicaoOcupada(this.totalDeElementos - 1)) {
            throw new IllegalArgumentException("Posição não existe");
        }
        if (this.totalDeElementos == 1) {
            this.removerDoComeco();
        } else {
            Celula penultima = this.ultima.getAnterior();
            penultima.setProxima(null);
            this.ultima = penultima;
            this.totalDeElementos--;
        }
    }

    private boolean posicaoOcupada(int posicao) {
        return posicao >= 0 && posicao < this.totalDeElementos;
    }

    private Celula pegarCelula(int posicao) {
        if (!this.posicaoOcupada(posicao)) {
            throw new IllegalArgumentException("Posição não existe");
        }

        Celula atual = primeira;
        for (int i = 0; i < posicao; i++) {
            atual = atual.getProxima();
        }
        return atual;
    }

    public boolean vazio() {
        if (this.totalDeElementos == 0) {
            return true;
        }
        return false;
    }
}
