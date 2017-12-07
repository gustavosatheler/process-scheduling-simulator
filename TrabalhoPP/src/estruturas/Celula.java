/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package estruturas;

/**
 *
 * @author Gustavo Bittencourt Satheler
 * <gustavo.satheler@alunos.unipampa.edu.br>
 */
public class Celula {

  private Celula proxima;
  private Celula anterior;
  private Object elemento;

  public Celula(Celula proxima, Object elemento) {
    this.proxima = proxima;
    this.elemento = elemento;
  }

  public Celula(Object elemento) {
    this.elemento = elemento;
  }

  public void setProxima(Celula proxima) {
    this.proxima = proxima;
  }

  public Celula getProxima() {
    return proxima;
  }
  
  public Object getElemento() {
    return elemento;
  }
  
  public Celula getAnterior() {
    return anterior;
  }
  
  public void setAnterior(Celula anterior) {
    this.anterior = anterior;
  }
}
