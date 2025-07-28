package br.com.senac.service.desconto;

public class DescontoCarro implements DescontoStrategy{
    @Override
    public double aplicarDesconto(double valorOriginal){
        return valorOriginal * 0.95;
    }
}
