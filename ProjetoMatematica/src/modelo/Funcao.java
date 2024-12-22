/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import Enum.TipoFuncao;

/**
 *
 * @author natalle.santos
 */
public class Funcao {
    private String expressao;
    private TipoFuncao tipo;

    public Funcao(String expressao, TipoFuncao tipo) {
        this.expressao = expressao;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Função: " + expressao + " (" + tipo + ")";
    }

    public TipoFuncao getTipo() {
        return tipo;
    }

    public String getExpressao() {
        return expressao;
    }
}
