/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author natalle.santos
 */

import java.util.regex.*;
import Enum.TipoFuncao;
import modelo.Funcao;


public class FuncaoParser {
    public Funcao parse(String input) {
        input = input.replaceAll("\\s+", ""); // Remove espaços em branco
        TipoFuncao tipo = identificarTipoFuncao(input);
        return new Funcao(input, tipo);
    }

    // Identifica o tipo de função com base na expressão
    private TipoFuncao identificarTipoFuncao(String expressao) {
        // Funções polinomiais simples e complexas
        String polinomialSimples = "^[+-]?\\d*x(\\^\\d+)?$";
        String polinomialComplexo = "^[+-]?\\d*x(\\^\\d+)?([+-]\\d*x?(\\^\\d+)?)*([+-]\\d+)?$";
        
        // Funções exponenciais simples e complexas
        String exponencialSimples = "^e\\^([+-]?\\d*x)$";
        String exponencialComplexo = "^e\\^\\(([^\\)]+)\\)$";
        
        // Funções trigonométricas simples e complexas
        String trigonometricaSimples = "^(sin|cos|tan)\\(x\\)$";
        String trigonometricaComplexa = "^(sin|cos|tan)\\(([^\\)]+)\\)$";

        if (expressao.matches(polinomialSimples)) {
            return TipoFuncao.POLINOMIAL_SIMPLES;
        } else if (expressao.matches(polinomialComplexo)) {
            return TipoFuncao.POLINOMIAL_COMPLEXO;
        } else if (expressao.matches(exponencialSimples)) {
            return TipoFuncao.EXPONENCIAL_SIMPLES;
        } else if (expressao.matches(exponencialComplexo)) {
            return TipoFuncao.EXPONENCIAL_COMPLEXO;
        } else if (expressao.matches(trigonometricaSimples)) {
            return TipoFuncao.TRIGONOMETRICA_SIMPLES;
        } else if (expressao.matches(trigonometricaComplexa)) {
            return TipoFuncao.TRIGONOMETRICA_COMPLEXA;
        } else {
            return TipoFuncao.DESCONHECIDO;
        }
    }
}
