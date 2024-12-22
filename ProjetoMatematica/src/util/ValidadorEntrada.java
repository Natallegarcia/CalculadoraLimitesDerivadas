/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

/**
 *
 * @author natalle.santos
 */
import modelo.FuncaoParser;

public class ValidadorEntrada {
    private final FuncaoParser parser;

    public ValidadorEntrada(FuncaoParser parser) {
        this.parser = parser;
    }

    public boolean validarEntrada(String entrada) {
        try {
            parser.parse(entrada);  // Tenta identificar o tipo de função
            return true;  // Se a função é válida, retorna true
        } catch (Exception e) {
            return false; // Caso contrário, retorna false
        }
    }

    public String obterMensagemErro() {
        return "A expressão fornecida é inválida. Verifique e tente novamente.";
    }
}
