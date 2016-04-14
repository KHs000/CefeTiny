

package br.cefetmg.inf.expression;

/**
 *
 * @author Felipe Rabelo
 */
public class Nodo {
    private String data;
    protected Nodo next;
    protected Nodo previous;
    
    public Nodo (String data) {
        this.data = data;
        next = previous = null;
    }
    
    public Nodo (String data, Nodo next, Nodo previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }
    
    public String getData () {return data;}
}
