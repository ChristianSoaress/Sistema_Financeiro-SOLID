package br.com.senac.service;

public class ValidacaoServ {
    public static boolean validarCPF(String cpf){
        if(cpf == null || cpf.length() != 11 || !cpf.matches("\\d+")){
            return false;
        }
        
        return true;
    }
}
