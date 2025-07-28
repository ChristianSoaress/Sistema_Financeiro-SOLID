package br.com.senac.controller;

import br.com.senac.model.Usuario;
import br.com.senac.persistence.UsuarioDAO;
import javax.swing.JOptionPane;

public class LoginCtrl {
    private final UsuarioDAO usuarioDAO;
    
    public LoginCtrl(){
        this.usuarioDAO = new UsuarioDAO();
    }
    
    public Usuario autenticar(String nomeUser, String senha){
        if(nomeUser == null || nomeUser.trim().isEmpty()){
            throw new IllegalArgumentException("Username não pode ser vazio");
        }
        
        if(senha == null || senha.trim().isEmpty()){
            throw new IllegalArgumentException("Senha não pode ser vazia");
        }
        
        Usuario usuario = usuarioDAO.autenticar(nomeUser, senha);
        
        if(usuario == null){
            throw new SecurityException("Credenciais inválidas.");
        }
        
        JOptionPane.showMessageDialog(
                null, 
                "Login realizado com sucesso.\n Bem-vindo, " + usuario.getUsername() + "!", 
                "Sucesso", 
                JOptionPane.INFORMATION_MESSAGE);
        
        return usuario;
    }
}
