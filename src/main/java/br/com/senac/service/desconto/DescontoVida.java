package br.com.senac.service.desconto;

public class DescontoVida implements DescontoStrategy {
    @Override
    public double aplicarDesconto(double valorOriginal){
        return valorOriginal * 0.85;
    }
}
