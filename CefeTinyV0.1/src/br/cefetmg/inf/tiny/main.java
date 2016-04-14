package br.cefetmg.inf.tiny;

import br.cefetmg.inf.tiny.excecoes.ErroSintatico;

import java.io.IOException;

public class main {

    public static void main(String[] args) {

        try {
            String fileName = "./src/exemplos/teste1_3.ita";

            CefeTiny cefeTiny = new CefeTiny(fileName);
            // interpreta e executa arquivo de entrada
            cefeTiny.run();

        } catch (ErroSintatico | IOException e) {
            e.printStackTrace();
        }
    }
}