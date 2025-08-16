package br.com.senac.service;

import org.junit.Test;
import static org.junit.Assert.*;

public class FidelidadeServTest {
    @Test
    public void testDescontoCarro(){
        FidelidadeServ servico = new FidelidadeServ();
        double valorDesconto = servico.calcularValor("CARRO", 1000.0, true);
        assertEquals(950.0, valorDesconto, 0.001);
    }
    
    @Test
    public void testDescontoCasa(){
        FidelidadeServ servico = new FidelidadeServ();
        double valorDesconto = servico.calcularValor("CASA", 1000.0, true);
        assertEquals(900.0, valorDesconto, 0.001);
    }
    
    @Test
    public void testDescontoVida(){
        FidelidadeServ servico = new FidelidadeServ();
        double valorDesconto = servico.calcularValor("VIDA", 1000.0, true);
        assertEquals(850.0, valorDesconto, 0.001);
    }
    
    @Test
    public void testSemDesconto(){
        FidelidadeServ servico = new FidelidadeServ();
        double valor = servico.calcularValor("CARRO", 1000.0, false);
        assertEquals(1000.0, valor, 0.001);
    }
}
