package br.com.senac.service;

import java.util.Map;
import br.com.senac.service.desconto.*;
import java.util.HashMap;

public class FidelidadeServ {
    private final Map<String, DescontoStrategy> estrategiasDesconto;
    
    public FidelidadeServ(){
        this.estrategiasDesconto = new HashMap<>();
        registrarEstrategias();
    }
    
    public void registrarEstrategias(){
        estrategiasDesconto.put("CARRO", new DescontoCarro());
        estrategiasDesconto.put("CASA", new DescontoCasa());
        estrategiasDesconto.put("VIDA", new DescontoVida());
    }
    
    public double calcularValor(String tipoSeguro, double valorOriginal, boolean fiel){
        if(!fiel){
            return valorOriginal;
        }
        
        DescontoStrategy estrategia = estrategiasDesconto.getOrDefault(
                tipoSeguro.toUpperCase(), 
                new SemDesconto());
        
        return estrategia.aplicarDesconto(valorOriginal);
    }
}
