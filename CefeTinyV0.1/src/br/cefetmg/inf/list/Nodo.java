

package br.cefetmg.inf.list;

/**
 *
 * @author Felipe Rabelo
 */
public class Nodo {
    protected Object data;
    protected String type;
    protected String name;
    protected Nodo next;
    protected Nodo previous;
    
    public Nodo (Object data, String name, String type, Nodo previous, Nodo next) {
        this.data = data;
        this.name = name;
        this.type = type;
        this.previous = previous;
        this.next = next;
    }
    
    public Nodo (Object data, String name, String type) {
        this.data = data;
        this.name = name;
        this.type = type;
        previous = next = null;
    }
    
    public String getName () {return name;}
    
    public String getType () {return type;}
    
    public Object getData () {return data;}
}
