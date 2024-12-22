/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package integral;

/**
 *
 * @author Willi
 */

import static Enum.TipoFuncao.*;
import modelo.Funcao;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IntegralFuncao {

    public Funcao calcularIntegralIndefinida(Funcao funcao){
    // Um switch-case para calcular de acordo com o tipo
    switch(funcao.getTipo()){
        case POLINOMIAL_SIMPLES:{
            //Uso o pattern para separar a expressão em grupos
            Pattern pattern = Pattern.compile("^([+-]?\\d*)(x)(?:\\^(\\d+))?$");
            Matcher matcher = pattern.matcher(funcao.getExpressao());
            
            //matcher.find() vai achar os grupos caso não for de acordo com o regex que eu forneci no pattern
            if(matcher.find()){
                //Separo a expressão em termos para facilitar a manipulação
                String coeficiente = matcher.group(1);
                String variavel = matcher.group(2);
                String expoente = matcher.group(3);
                    
                //Caso o coeficiente seja nulo ou vazio, na matematica significa que é 1, por isso aloca o 1 na string
                if(coeficiente == null || coeficiente.isEmpty()) coeficiente = "1";
                else if(coeficiente.equals("-")) coeficiente = "-1";
                    
                //Segue a mesma logica
                if(expoente == null || expoente.isEmpty()) expoente = "1";
                    
                //Transforma as String em double para poder calcular
                double coeficienteDou = Double.parseDouble(coeficiente);
                double expoenteDou = Double.parseDouble(expoente);
                double fracao = 0;
                    
                //Seguindo a formula (x^(n+1))/n+1, sendo n o expoente, por isso somei + 1
                expoenteDou += 1;
                    
                //Se o expoente for igual ao coeficiente quer dizer que a divisão entre eles da zero
                if(expoenteDou == coeficienteDou) coeficienteDou = 0;
                //Nesse if eu comparo se o expoente é divisivel com o coeficiente ou vice-versa
                else if(expoenteDou  > coeficienteDou && expoenteDou % coeficienteDou == 0){
                    fracao = expoenteDou / coeficienteDou;
                    coeficienteDou = 0;
                } else if(expoenteDou  < coeficienteDou && coeficienteDou % expoenteDou == 0){
                    fracao = coeficienteDou / expoenteDou;
                    coeficienteDou = 0;
                    // Nesse if verifico se o expoente não é divisivel com o coeficiente, caso não seja mantenho o termo e acrescento atras da variavel
                } else if(expoenteDou / coeficienteDou != 0){
                    fracao = expoenteDou;
                    variavel = coeficienteDou + variavel;
                } else if(expoenteDou % 2 == 0 || expoenteDou % 3 == 0){
                    fracao = expoenteDou;
                    //Verifica se a função pode ser simplicado, coloco dentro de while para caso seja um numero que necessite de mais simplificações
                    while(fracao % 2 == 0 && coeficienteDou % 2 == 0 ||
                        fracao % 3 == 0 && coeficienteDou % 3 == 0){
                        //Se for divisivel por 2
                        if(fracao % 2 == 0 && coeficienteDou % 2 == 0){
                            coeficienteDou /= 2;
                            fracao /= 2;
                        }
                        //Se for divisivel por 3
                        if(fracao % 3 == 0 && coeficienteDou % 3 == 0){
                            coeficienteDou /= 3;
                            fracao /= 3;
                        }
                   }
                }
                    
                String resultado = "";
                //Nesse if verifico em qual saida devo formar
                if(fracao == 0) resultado = variavel + "^" + expoenteDou;
                else resultado = "(" + variavel + "^" + expoenteDou + ")/" + fracao + " + C";
                    
                //Retorno o resultado com a String formatado com 2 casas decimais
                return new Funcao(String.format(resultado, "%.2f"), funcao.getTipo());
                    
                }
                
            }
            
            case POLINOMIAL_COMPLEXO:{
                //Separar a expressão em termos para pode manipular melhor a expressão
                String termo[] = funcao.getExpressao().split("(?=[+-])|(?<=[+-])");
                String resultado = "";
                for(int pos = 0; pos < termo.length; pos++){
                    // Uma verificação da posição, como o split separou os operadores dos termos, caso a pos for impar quer dizer que há um decimal
                    if(pos % 2 != 0){
                        //Aqui eu incremento o operador ao resultado
                        resultado += " " + termo[pos] + " ";
                    } else {
                        //Todos os patterns seguiram a mesma logica de separar em grupos
                        Pattern pattern = Pattern.compile("^([+-]?\\d*)(x)?(?:\\^(\\d+))?$");
                        Matcher matcher = pattern.matcher(termo[pos]);
                
                        if(matcher.find()){
                            //Aqui igualmente seguira a mesma logica de armazenar os grupos para manipular com mais facilidade
                            String coeficiente = matcher.group(1);
                            String variavel = matcher.group(2);
                            String expoente = matcher.group(3);
                    
                        //Caso a variavel e o expoente forem nulos quer dizer que é apenas um numeral, nesse caso apenas incremento um "x" nele
                        if(variavel == null && expoente == null){
                            resultado += coeficiente + "x";
                            //Caso contrario sigo com o resto do codigo
                        } else {
                            //Usarei muito isso de nulo ou vazio é igual a 1
                            if(coeficiente == null || coeficiente.isEmpty()){
                                coeficiente = "1";
                            } else if(coeficiente.equals("-")) coeficiente = "-1";
                    
                            if(expoente == null || expoente.isEmpty()){
                                expoente = "1";
                            }
                    
                            //Mesma logica
                            double coeficienteDou = Double.parseDouble(coeficiente);
                            double expoenteDou = Double.parseDouble(expoente);
                            double fracao = 0;
                            
                            expoenteDou += 1;
                        
                            if(expoenteDou == coeficienteDou){
                                coeficienteDou = 0;
                            } else if(expoenteDou  > coeficienteDou && expoenteDou % coeficienteDou == 0){
                                fracao = expoenteDou / coeficienteDou;
                                coeficienteDou = 0;
                            } else if(expoenteDou  < coeficienteDou && coeficienteDou % expoenteDou == 0){
                                fracao = 0;
                                coeficienteDou = coeficienteDou / expoenteDou;
                            } else if(expoenteDou % 2 == 0 || expoenteDou % 3 == 0){
                                fracao = expoenteDou;
                                //Verifica se a função pode ser simplicado, coloco dentro de while para caso seja um numero que necessite de mais simplificações
                                while(fracao % 2 == 0 && coeficienteDou % 2 == 0 ||
                                        fracao % 3 == 0 && coeficienteDou % 3 == 0){
                                    //Se for divisivel por 2
                                    if(fracao % 2 == 0 && coeficienteDou % 2 == 0){
                                        coeficienteDou /= 2;
                                        fracao /= 2;
                                    }
                                    //Se for divisivel por 3
                                    if(fracao % 3 == 0 && coeficienteDou % 3 == 0){
                                        coeficienteDou /= 3;
                                        fracao /= 3;
                                    }
                                }
                            //Caso não posso ser simplicado, dividido e todas as outras verificações, significa que nada pode ser feito com a fracao e o coeficiente
                            } else if(expoenteDou / coeficienteDou != 0 || coeficienteDou / expoenteDou != 0){
                                fracao = expoenteDou;
                            }
                        
                            //O coeficiente em um double para manipulações retorna ao lado da variavel
                            if(coeficienteDou != 0) variavel = coeficienteDou + variavel;
                        
                            //Caso não tenha fracao, formato o resultado para sair de uma forma que seria diferente se tivesse uma fracao
                            if(fracao == 0) resultado += variavel + "^" + expoenteDou;
                            else resultado += "((" + variavel + "^" + expoenteDou + ")/" + fracao + ")";
                            }
                        }
                    }
                }
                resultado += " + C";
                return new Funcao(String.format(resultado, "%.2f"), funcao.getTipo());
            }
            
            case EXPONENCIAL_SIMPLES:{
                Pattern pattern = Pattern.compile("e(?:\\^([+-]?\\d*|x))?(x)?$");
                Matcher matcher = pattern.matcher(funcao.getExpressao());
                
                if(matcher.find()){
                    String base = "e";
                    String expoente = matcher.group(1);
                    String variavelExpoente = matcher.group(2);
                    
                    String fracao = expoente;
                    
                    String resultado = "";
                    
                    if(expoente.isEmpty() && variavelExpoente.isEmpty()) resultado +=  base + " * x + C";
                    else if(expoente.equals("-")) resultado += "-" + base + "^" + expoente + variavelExpoente + " + C";
                    else if(expoente.contains("-")) resultado += "-((" + base + "^" + expoente + variavelExpoente + ")/" + expoente.replace("-", "") + ") + C";
                    else if(expoente.isEmpty()) resultado += base + "^" + variavelExpoente + " + C";
                    else if(variavelExpoente.isEmpty()) resultado +=  base + "^" + expoente + " * x + C";
                    else resultado += "(" + base + "^" + expoente + variavelExpoente + ")/" + fracao + " + C";
                    
                    return new Funcao(resultado, funcao.getTipo());
                }
            } 
            
            case EXPONENCIAL_COMPLEXO:{
                String expressaoOriginal = funcao.getExpressao().replaceAll("e\\^\\(", "").replaceAll("\\)$", "");
                String resultado = "";
                
                String expressao[] = expressaoOriginal.split("(?=[+-])|(?<=\\+)");
                for(int pos = 0; pos < expressao.length; pos++){
                    if(!expressao[pos].equals("+") && !expressao[pos].equals("-")){
                        Pattern pattern = Pattern.compile("^([+-]?\\d*)(x)?(?:\\^(\\d+))?$");
                        Matcher matcher = pattern.matcher(expressao[pos]);
                        
                        if(matcher.find()){
                            String base = "e";
                            String expoente = matcher.group(1);
                            String variavelExpoente = matcher.group(2);
                            
                            String fracao = expoente;
                            
                            if(expoente.isEmpty()) {
                                resultado += base + "^" + "(" + expressaoOriginal + ") + C";
                                break;
                            } else if(expoente.equals("1")) resultado = "(" + base + resultado + " + C";
                            else if(variavelExpoente == null) resultado = "(" + base + "^" + expoente + resultado + " + C";
                            else resultado += base + "^" + expoente + variavelExpoente + ")/" + fracao;
                        }
                    }
                }
                return new Funcao(resultado, funcao.getTipo());
            }
        }
        return funcao;
    }
    
    public Funcao calcularIntegralDefinida(Funcao funcao, double limiteInferior, double limiteSuperior){
        
        switch(funcao.getTipo()){
            case POLINOMIAL_SIMPLES:{
                //Para a integral definida, preciso primeiramente da indefinida por isso crio um objeto da classe funcao para receber o resultado da integral indefinida
                Funcao integralIndefinida = calcularIntegralIndefinida(funcao);
                //Mesma logica
                Pattern pattern = Pattern.compile("^([+-]?\\d*)(x)(?:\\^(\\d+))?(?:/(\\d+))?(?:([+-])C)?$");
                //Removi os (), pois por algum motivo não tava conseguindo de jeito nenhum passar eles no pattern então os removi no matcher, não precisaria deles mesmo
                Matcher matcher = pattern.matcher(integralIndefinida.getExpressao().replace("(", "").replace(")", ""));
                
                if(matcher.find()){
                    String coeficiente = matcher.group(1);
                    String variavel = matcher.group(2);
                    String expoente = matcher.group(3);
                    
                    if(coeficiente == null || coeficiente.isEmpty()){
                        coeficiente = "1";
                    } else if(coeficiente.equals("-")) coeficiente = "-1";
                    
                    if(expoente == null || expoente.isEmpty()){
                        expoente = "1";
                    }
                    
                    //Mesma logica so que com o limite superior e inferior se tra
                    double coeficienteLimite = Double.parseDouble(coeficiente);
                    double expoenteLimite = Double.parseDouble(expoente);
                    
                    double soma = 0;
                    //A integral definida é basicamente a substituição das variaveis na integral indefinida e subtrair os resultados, lembrando que a substituição deve ser feito tanto com limite inferior quanto superior
                    soma += ((coeficienteLimite)*(Math.pow(limiteSuperior, expoenteLimite)))/expoenteLimite;
                    
                    soma -= (((coeficienteLimite)*(Math.pow(limiteInferior, expoenteLimite)))/expoenteLimite);
                    
                    return new Funcao(String.valueOf(soma), funcao.getTipo());
                }
            }
            
            case POLINOMIAL_COMPLEXO:{
                Funcao integralIndefinida = calcularIntegralIndefinida(funcao);
                String termo[] = integralIndefinida.getExpressao().split("(?=[+-])|(?<=[+-])");
                double soma = 0;
                for(int pos = 0; pos < termo.length; pos++){
                    if(pos % 2 == 0){
                        Pattern pattern = Pattern.compile("^([+-]?\\d*\\.?\\d+)?(x)(?:\\^)?(\\d*\\.?\\d+)?(?:\\/)?(\\d*\\.?\\d+)?$");
                        Matcher matcher = pattern.matcher(termo[pos].replace("(", "").replace(")", "").replace(" ", ""));
                
                        if(matcher.find()){
                            String coeficiente = matcher.group(1);
                            String variavel = matcher.group(2);
                            String expoente = matcher.group(3);
                            String divisor = matcher.group(4);
                    
                            if(coeficiente == null || coeficiente.isEmpty()){
                                coeficiente = "1";
                            } else if(coeficiente.equals("-")) coeficiente = "-1";
                    
                            if(expoente == null || expoente.isEmpty()){
                                expoente = "1";
                            }
                            
                            if(divisor == null || expoente.isEmpty()){
                                divisor = "1";
                            }
                    
                            double coeficienteLimite = Double.parseDouble(coeficiente);
                            double expoenteLimite = Double.parseDouble(expoente);
                            double divisorLimite = Double.parseDouble(divisor);
                    
                            if(pos > 0 && termo[pos-1].equals("-")) 
                                soma -= ((coeficienteLimite)*(Math.pow(limiteSuperior, expoenteLimite)))/divisorLimite;
                            else soma += ((coeficienteLimite)*(Math.pow(limiteSuperior, expoenteLimite)))/divisorLimite;
                    
                            soma -= (((coeficienteLimite)*(Math.pow(limiteInferior, expoenteLimite)))/divisorLimite);
                            
                        }
                    }
                }
                return new Funcao(String.valueOf(soma), funcao.getTipo());
            }
            
            case EXPONENCIAL_SIMPLES:{
                Funcao integralIndefinida = calcularIntegralIndefinida(funcao);
                Pattern pattern = Pattern.compile("(?:\\(\\e\\^)?(\\d+)?(x)(?:\\)\\/)?(\\d+)?");
                Matcher matcher = pattern.matcher(integralIndefinida.getExpressao());
                
                if(matcher.find()){
                    String base = "e";
                    String expoente = matcher.group(1);
                    String variavelExpoente = matcher.group(2);
                    String divisor = matcher.group(3);
                    
                    String resultado = "";
                    
                    int expoenteLimite = 0;
                    
                    if(expoente != null) expoenteLimite = Integer.parseInt(expoente);
                    
                    if(expoente == null && limiteInferior == 0)
                        resultado += base + "^" + limiteSuperior + " - 1";
                    else if(expoente == null)
                        resultado += base + "^" + limiteSuperior + " - " + base + "^" + limiteInferior;
                    else if(divisor != null && limiteInferior > 0) 
                        resultado += "(" + base + "^" + (expoenteLimite * limiteSuperior) + " - " +
                                base + "^" + (expoenteLimite * limiteInferior) + ") / " + divisor;
                    else if(limiteInferior == 0)
                        resultado += "(" + base + "^" + (expoenteLimite * limiteSuperior) + 
                                " - 1) / " + divisor;
                    
                    return new Funcao(resultado, funcao.getTipo());
                }
            }
            
            case EXPONENCIAL_COMPLEXO:{
                Funcao integralIndefinida = calcularIntegralIndefinida(funcao);
                String resultado = "";
                    Pattern pattern = Pattern.compile("\\(e\\^(\\d*)(x)?e\\^(\\d*)(x)?\\)\\/?(\\d*)?");
                    Matcher matcher = pattern.matcher(integralIndefinida.getExpressao());
                    if(matcher.find()){
                        String base = "e";
                        String expoentePrimeiro = matcher.group(1);
                        String variavelExpoentePrimeiro = matcher.group(2);
                        String expoenteSegundo = matcher.group(3);
                        String variavelExpoenteSegundo = matcher.group(4);
                        String divisor = matcher.group(5);
                    
                        int expoenteLimitePri = 0;
                        int expoenteLimiteSeg = 0;
                        if(expoentePrimeiro != null) expoenteLimitePri = Integer.parseInt(expoentePrimeiro);
                        if(expoenteSegundo != null) expoenteLimiteSeg = Integer.parseInt(expoenteSegundo);
                    
                        if(divisor != null && variavelExpoentePrimeiro != null &&  variavelExpoenteSegundo != null)
                            resultado += "(" + base + "^" + ((expoenteLimitePri * limiteSuperior) - (expoenteLimitePri * limiteInferior)) + " - " +
                                    base + "^" + ((expoenteLimiteSeg * limiteSuperior) - (expoenteLimiteSeg * limiteInferior)) + ") / " + divisor;
                        if(divisor != null && variavelExpoentePrimeiro != null &&  variavelExpoenteSegundo != null)
                            resultado += "(" + base + "^" + ((expoenteLimitePri * limiteSuperior) - (expoenteLimitePri * limiteInferior)) + " - " +
                                    base + "^" + ((expoenteLimiteSeg * limiteSuperior) - (expoenteLimiteSeg * limiteInferior)) + ") / " + divisor;
                        else if(divisor != null && variavelExpoentePrimeiro != null &&  variavelExpoenteSegundo == null) 
                            resultado += "(" + base + "^" + ((expoenteLimitePri * limiteSuperior) - (expoenteLimitePri * limiteInferior)) + " - " +
                                    base + "^" + expoenteLimiteSeg + ") / " + divisor;
                        else if(divisor != null && variavelExpoentePrimeiro == null &&  variavelExpoenteSegundo != null) 
                            resultado += "(" + base + "^" + expoenteLimitePri + " - " + base + "^" + 
                                    ((expoenteLimiteSeg * limiteSuperior) - (expoenteLimiteSeg * limiteInferior)) + ") / " + divisor;
                        else if(divisor != null && variavelExpoentePrimeiro != null &&  variavelExpoenteSegundo == null && limiteInferior == 0) 
                            resultado += "(" + base + "^" + (expoenteLimitePri * limiteSuperior) + " - 1 " +
                                    base + "^" + expoenteLimiteSeg + ") / " + divisor;
                        else if(divisor != null && variavelExpoentePrimeiro == null &&  variavelExpoenteSegundo != null && limiteInferior == 0) 
                            resultado += "(" + base + "^" + expoenteLimitePri + " - " + base + "^" + 
                                    (expoenteLimiteSeg * limiteSuperior) + " - 1) / " + divisor;
                    
                            return new Funcao(resultado, funcao.getTipo());
                        }
                    }
                    default:
                        throw new UnsupportedOperationException("Tipo de função não suportado para cálculo de integral.");
        }
    }
}
