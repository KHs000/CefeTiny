package br.cefetmg.inf.tiny;					// pacote ao qual este objeto pertence

import br.cefetmg.inf.tiny.comandos.Comando;
import br.cefetmg.inf.tiny.comandos.ComandoEnd;
import br.cefetmg.inf.tiny.comandos.ComandoPrint;
import br.cefetmg.inf.tiny.comandos.ComandoPrintStr;
import br.cefetmg.inf.tiny.comandos.ComandoPrintln;
import br.cefetmg.inf.tiny.comandos.ComandoReadFloat;
import br.cefetmg.inf.tiny.comandos.ComandoReadInt;
import br.cefetmg.inf.tiny.excecoes.ErroSintatico;
import br.cefetmg.inf.tiny.interpretador.Constantes;
import br.cefetmg.inf.tiny.interpretador.Interpretador;
import br.cefetmg.inf.tiny.interpretador.Simbolo;
import br.cefetmg.inf.list.linkedList;
import java.util.ArrayList;

public class CefeTiny {

    private Interpretador parser;           // para leitura dos simbolos do arquivo
    private ArrayList<Comando> comandos;    // para armazenamento dos comandos
    private Simbolo simboloAtual;           // para leitura do arquivo de entrada
    
    public static linkedList memory = new linkedList ();

    // construtor da classe CefeTiny --------------------------------------
    public CefeTiny(String fileName) throws java.io.FileNotFoundException {
        this.parser = new Interpretador(fileName);      // crie o analisador lexico do arquivo
        this.comandos = new ArrayList<>();              // crie a lista de comandos
    }

    // inicia interpretador de comandos
    public void run() {

        // obtenha o primeiro simbolo
        this.simboloAtual = this.parser.obterSimbolo();        

        if(this.simboloAtual.getToken() != Constantes.BEGIN)
            throw new ErroSintatico("Erro: bloco de comando invalido na linha "
                        + this.parser.getLineNumber() + ": era esperado begin");

        do {
            // obtenha o proximo simbolo
            this.simboloAtual = this.parser.obterSimbolo();
            
            // auxiliar para referenciar comando
            Comando cmd = null;

            // selecione o comando
            switch (this.simboloAtual.getToken()) {
               
                case Constantes.PRINTSTR:
                    cmd = trataComandoPrintStr();
                    break;
                case Constantes.PRINTLN:
                    cmd = trataComandoPrintln();
                    break;
                case Constantes.PRINT:
                    cmd = trataComandoPrint();
                    break;
                case Constantes.END:
                    cmd = trataComandoEnd();
                    break;
                case Constantes.READINT:
                    cmd = trataComandoReadInt();
                    break;
                case Constantes.READFLOAT:
                    cmd = trataComandoReadFloat();
                    break;
                case Constantes.T_IDENTIFIER:
                    linha.add(simboloAtual);
                    this.simboloAtual = this.parser.obterSimbolo();
                    if (this.simboloAtual.getToken() == Constantes.ATRIBOP) {cmd = trataComandoAtribOp();}
                    break;
                default:
                    // exiba mensagem de erro sintatico
                    throw new ErroSintatico("Erro: bloco de comando invalido na linha "
                            + this.parser.getLineNumber() + ": foi encontrado '"
                            + this.simboloAtual.getLexema() + "' ");
            }

            // adicione o comando identificado na lista de comandos
            this.comandos.add(cmd);

            // enquanto n�o for identificado o fim do arquivo
        } while (this.simboloAtual.getToken() != Constantes.END);

        // depois que todos os comandos (até primeiro comando end) tiverem sido identificados
        // comece a execucao deles
        this.executa();
    }

    // tratamento do comando End
    private Comando trataComandoEnd() {
        
        return new ComandoEnd();	// cria o comando End
    }

    //tratamento do comando Printstr
    private Comando trataComandoPrintStr() {
        //obtem o proximo simbolo
        this.simboloAtual = this.parser.obterSimbolo();

        //analisa se e abre parentese
        if (this.simboloAtual.getToken() != Constantes.ABREPAR) {
            throw new ErroSintatico("Era esperado um ( depois de printstr");
        }

        //obtem o proximo simbolo
        this.simboloAtual = this.parser.obterSimbolo();

        //analisa se e uma string
        if (this.simboloAtual.getTipo() != Constantes.T_STRING) {
            throw new ErroSintatico("O comando printstr so aceita String");
        }

        //obtem a string
        String str = this.simboloAtual.getLexema();

        //obtem o proximo simbolo
        this.simboloAtual = this.parser.obterSimbolo();

        //analisa se e fecha parentese
        if (this.simboloAtual.getToken() != Constantes.FECHAPAR) {
            throw new ErroSintatico("Era esperado um ) depois de \"" + str + "\"");
        }

        //cria o comando Printstr
        return new ComandoPrintStr(str);
    }

    //tratamento do comando Println
    private Comando trataComandoPrintln() {
        //cria o comando Println
        return new ComandoPrintln();
    }
    
    //tratamento do comando Print()
    private Comando trataComandoPrint() {
        //obtem o proximo simbolo
        this.simboloAtual = this.parser.obterSimbolo();

        //analisa se e abre parentese
        if (this.simboloAtual.getToken() != Constantes.ABREPAR) {
            throw new ErroSintatico("Era esperado um ( depois de print");
        }

        //obtem o proximo simbolo
        this.simboloAtual = this.parser.obterSimbolo();
        
        Simbolo var = simboloAtual;
        
        //obtem o proximo simbolo
        this.simboloAtual = this.parser.obterSimbolo();

        //analisa se e fecha parentese
        if (this.simboloAtual.getToken() != Constantes.FECHAPAR) {
            throw new ErroSintatico("Era esperado um ) depois de \"" + var.getLexema() + "\"");
        }
        
        //return new ComandoPrint (varName, memory);
        return new ComandoPrint (var);
    }
    
    //tratamento do comando ReadInt()
    private Comando trataComandoReadInt() {
        //obtem o proximo simbolo
        this.simboloAtual = this.parser.obterSimbolo();

        //analisa se e abre parentese
        if (this.simboloAtual.getToken() != Constantes.ABREPAR) {
            throw new ErroSintatico("Era esperado um ( depois de readint");
        }
        
        //obtem o proximo simbolo
        this.simboloAtual = this.parser.obterSimbolo();
        
        //obtem o nome da variável
        Simbolo var = simboloAtual;
        
        //obtem o proximo simbolo
        this.simboloAtual = this.parser.obterSimbolo();

        //analisa se e fecha parentese
        if (this.simboloAtual.getToken() != Constantes.FECHAPAR) {
            throw new ErroSintatico("Era esperado um ) depois de \"" + var + "\"");
        }
        
        return new ComandoReadInt(var);
    }
    
    //tratamento do comando ReadFloat()
    private Comando trataComandoReadFloat() {
        //obtem o proximo simbolo
        this.simboloAtual = this.parser.obterSimbolo();

        //analisa se e abre parentese
        if (this.simboloAtual.getToken() != Constantes.ABREPAR) {
            throw new ErroSintatico("Era esperado um ( depois de readint");
        }
        
        //obtem o proximo simbolo
        this.simboloAtual = this.parser.obterSimbolo();
        
        //obtem o nome da variável
        Simbolo var = simboloAtual;
        
        //obtem o proximo simbolo
        this.simboloAtual = this.parser.obterSimbolo();

        //analisa se e fecha parentese
        if (this.simboloAtual.getToken() != Constantes.FECHAPAR) {
            throw new ErroSintatico("Era esperado um ) depois de \"" + var + "\"");
        }
        
        return new ComandoReadFloat(var);
    }
    
    //tratamento do comando :=
    private Comando trataComandoAtribOp() {
        this.simboloAtual = linha.get(0);
        System.out.println(linha.get(0));
        
        return null;
    }
    
    // Execucao dos comandos lidos do arquivo
    private void executa() {
        for(Comando comando : this.comandos)
            comando.executa();
    }
}

