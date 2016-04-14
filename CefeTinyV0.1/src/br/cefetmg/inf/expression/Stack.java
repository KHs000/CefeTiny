

package br.cefetmg.inf.expression;

/**
 *
 * @author Felipe Rabelo
 */
public class Stack {
    private Nodo base;
    private Nodo top;
    
    public Stack () {}
    
    public void push (String data) {
        if (!isEmpty()) {top = top.next = new Nodo (data, null, top);} 
        else {base = top = new Nodo (data, null, null);}
    }
    
    public Nodo pop () {
        if (!isEmpty()) {
            Nodo aux = top;
            top = top.previous;
            top.next = null;
            return aux;
        } return null;
    }
    
    public boolean isEmpty () {
        if (base == null) {
            return true;
        } return false;
    }
}
