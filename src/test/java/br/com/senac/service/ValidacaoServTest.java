package br.com.senac.service;

import org.junit.Test;
import static org.junit.Assert.*;

public class ValidacaoServTest {
    
    @Test
    public void testCPFValido(){
        assertTrue(ValidacaoServ.validarCPF("12345678909"));
    }
    
    @Test
    public void testCPFInvalido(){
        assertFalse(ValidacaoServ.validarCPF("123"));
        assertFalse(ValidacaoServ.validarCPF("abc12345678"));
    }
}
