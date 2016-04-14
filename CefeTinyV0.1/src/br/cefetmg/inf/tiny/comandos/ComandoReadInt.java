

package br.cefetmg.inf.tiny.comandos;

import br.cefetmg.inf.tiny.CefeTiny;
import br.cefetmg.inf.tiny.interpretador.Simbolo;
import br.cefetmg.inf.util.Console;

/**
 *
 * @author Felipe Rabelo
 */
public class ComandoReadInt extends Comando{
    
    Simbolo var;
    
    public ComandoReadInt (Simbolo var) {this.var = var;}

    @Override
    public void executa() {
        //obtem o valor da variável
        int varValue = Console.readInt();
        
        //salva a variável na memória
        if (!CefeTiny.memory.isEmpty()) {
            if (CefeTiny.memory.search(var.getLexema())) {CefeTiny.memory.update(var.getLexema(), varValue);}
            else {CefeTiny.memory.push(varValue, var.getLexema(), "int");}
        } else {CefeTiny.memory.push(varValue, var.getLexema(), "int");}
    }
    
}
