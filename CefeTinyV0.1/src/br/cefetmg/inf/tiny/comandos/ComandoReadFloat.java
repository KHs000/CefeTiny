
package br.cefetmg.inf.tiny.comandos;

import br.cefetmg.inf.tiny.CefeTiny;
import br.cefetmg.inf.tiny.interpretador.Simbolo;
import br.cefetmg.inf.util.Console;

/**
 *
 * @author Felipe Rabelo
 */
public class ComandoReadFloat extends Comando{
    
    Simbolo var;
    
    public ComandoReadFloat (Simbolo var) {this.var = var;}

    @Override
    public void executa() {
        //obtem o valor da variável
        float varValue = Float.valueOf(Console.readLine());
        
        //salva a variável na memória
        if (!CefeTiny.memory.isEmpty()) {
            if (CefeTiny.memory.search(var.getLexema())) {CefeTiny.memory.update(var.getLexema(), varValue);}
            else {CefeTiny.memory.push(varValue, var.getLexema(), "float");}
        } else {CefeTiny.memory.push(varValue, var.getLexema(), "float");}
    }
   
}
