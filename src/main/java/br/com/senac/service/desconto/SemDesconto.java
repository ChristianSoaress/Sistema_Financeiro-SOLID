package br.com.senac.service.desconto;

public class SemDesconto implements DescontoStrategy {
    @Override
    public double aplicarDesconto(double valorOriginal){
        return valorOriginal;
    }
}
