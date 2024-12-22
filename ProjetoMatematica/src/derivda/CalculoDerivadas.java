/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package derivda;

/**
 *
 * @author natalle.santos
 */

import modelo.*;
import Enum.TipoFuncao;

public class CalculoDerivadas {
    public double calcularDerivada(Funcao funcao, double x) {
        TipoFuncao tipo = funcao.getTipo();
        String expressao = funcao.getExpressao();
        double h = 1e-5; // Valor pequeno para a aproximação da derivada

        switch (tipo) {
            case POLINOMIAL_COMPLEXO:
            case POLINOMIAL_SIMPLES:
                return calcularDerivadaPolinomial(x, h);
            case EXPONENCIAL_SIMPLES:
            case EXPONENCIAL_COMPLEXO:
                return calcularDerivadaExponencial(x, h);
            case TRIGONOMETRICA_SIMPLES:
            case TRIGONOMETRICA_COMPLEXA:
                return calcularDerivadaTrigonometrica(expressao, x, h);
            default:
                throw new IllegalArgumentException("Tipo de função não suportado.");
        }
    }

    private double calcularDerivadaPolinomial(double x, double h) {
        return (Math.pow(x + h, 2) - Math.pow(x, 2)) / h;
    }

    private double calcularDerivadaExponencial(double x, double h) {
        return (Math.exp(x + h) - Math.exp(x)) / h;
    }

    private double calcularDerivadaTrigonometrica(String funcao, double x, double h) {
        if (funcao.contains("sin")) {
            return (Math.sin(x + h) - Math.sin(x)) / h;
        } else if (funcao.contains("cos")) {
            return (Math.cos(x + h) - Math.cos(x)) / h;
        } else if (funcao.contains("tan")) {
            return (Math.tan(x + h) - Math.tan(x)) / h;
        }
        throw new IllegalArgumentException("Função trigonométrica desconhecida.");
    }
}
