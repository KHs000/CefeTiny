

package br.cefetmg.inf.list;

/**
 *
 * @author Felipe Rabelo
 */
public class linkedList {
    private Nodo root;
    private Nodo last;
    
    public linkedList () {root = last = null;}
    
    public String getAt (String name) {
        Nodo atual = root;
        while (atual != null) {
            if (atual.name.equals(name)) {return atual.data.toString();}
            atual = atual.next;
        }
        return null;
    }
    
    public boolean isEmpty () {
        boolean flag = true;
        
        if (root != null) {flag = false;}
        
        return flag;
    }
    
    public Nodo pop () {
        Nodo poped;
        if (root == null) {/*exception*/}
        else {
            poped = last;
            last.previous = last;
            last.next = null;
            return poped;
        }
        return null;
    }
    
    public void push (Object data, String name, String type) {
        if (root == null) {root = last = new Nodo (data, name, type);}
        else {last.next = last = new Nodo (data, name, type, last, null);}
    }
    
    public boolean search (String name) {
        Nodo atual = root;
        while (atual != null) {
            if (atual.name.equals(name)) {return true;}
            atual = atual.next;
        }
        
        return false;
    }
    
    public void update (String name, Object data) {
        Nodo atual = root;
        while (atual != null) {
            if (atual.name.equals(name)) {atual.data = data;}
            atual = atual.next;
        }
    }
}
