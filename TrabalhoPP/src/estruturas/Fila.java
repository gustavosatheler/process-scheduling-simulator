///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
package estruturas;
//
///**

import gerais.Processo;

// *
// * @author Lucas
// */
public class Fila {

    private Processo[] fila;
    int tamanhoAtual = 0;

    public Fila(int tamanho) {
        fila = new Processo[tamanho];
    }

    public boolean add(Processo proc) {
        for (int i = 0; i < fila.length; i++) {
            if (fila[i] == null) {
                fila[i] = proc;
                tamanhoAtual++;
                return true;
            }
        }
        return false;
    }

    public Processo remove() {
        Processo first = fila[0];
        fila[0] = null;

        for (int i = 0; i < tamanhoAtual-1; i++) {
            if (fila[i + 1] != null) {
                fila[i] = fila[i + 1];
                fila[i + 1] = null;
            }
        }

        tamanhoAtual--;
        return first;
    }

    public int getTamanho() {
        return tamanhoAtual;
    }

    public Processo get() {
        if (tamanhoAtual > 0) {
            return fila[0];
        }
        return null;
    }

//     public void enqueue(Object elemento)
//	// Post: An item is added to the back of the queue.
//	{
//		// Append the item to the end of our linked list.
//		lista.adicionar(elemento);
//	}
//
//	public Object dequeue()
//	// Pre: this.isEmpty() == false
//	// Post: The item at the front of the queue is returned and 
//	//         deleted from the queue. Returns null if precondition
//	//         not met.
//	{
//		// Store a reference to the item at the front of the queue
//		//   so that it does not get garbage collected when we 
//		//   remove it from the list.
//		// Note: list.get(...) returns null if item not found at
//		//   specified index. See postcondition.
//		Object elemento = lista.pegar(1);
//		// Remove the item from the list.
//		// My implementation of the linked list is based on the
//		//   J2SE API reference. In both, elements start at 1,
//		//   unlike arrays which start at 0.
//		lista.remover(1);
//		
//		// Return the item
//		return elemento;
//	}
//
//	public Object peek()
//	// Pre: this.isEmpty() == false
//	// Post: The item at the front of the queue is returned and 
//	//         deleted from the queue. Returns null if precondition
//	//         not met.
//	{
//		// This method is very similar to dequeue().
//		// See Queue.dequeue() for comments.
//		return lista.pegar(1);
//	}
}
