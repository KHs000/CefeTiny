

package br.cefetmg.inf.tiny.comandos;

import br.cefetmg.inf.tiny.CefeTiny;
import br.cefetmg.inf.tiny.excecoes.ErroSemantico;
import br.cefetmg.inf.tiny.interpretador.Simbolo;

/**
 *
 * @author Felipe Rabelo
 */
public class ComandoPrint extends Comando {
    
    Simbolo var;
    
    public ComandoPrint (Simbolo var) {this.var = var;}

    @Override
    public void executa() {
        if (!CefeTiny.memory.isEmpty()) {
            if (CefeTiny.memory.search(var.getLexema())) {System.out.print(CefeTiny.memory.getAt(var.getLexema()));}
            else {throw new ErroSemantico ("Não existe esta variável");}
        } else {throw new ErroSemantico ("Não existe esta variável");}
    }
    
}
