package br.com.senac.service.desconto;

public class DescontoCasa implements DescontoStrategy {
    @Override
    public double aplicarDesconto(double valorOriginal){
        return valorOriginal * 0.90;
    }
}
