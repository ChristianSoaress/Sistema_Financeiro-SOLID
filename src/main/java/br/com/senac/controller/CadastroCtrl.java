package br.com.senac.controller;

import br.com.senac.model.Usuario;
import br.com.senac.persistence.UsuarioDAO;

public class CadastroCtrl {
    private final UsuarioDAO usuarioDAO;
    
    public CadastroCtrl(){
        this.usuarioDAO = new UsuarioDAO();
    }
    
    public void cadastrarUsuario(String nomeUser, String senha, String tipoUsuario){
        Usuario novoUsuario = new Usuario();
        novoUsuario.setUsername(nomeUser);
        novoUsuario.setSenha(senha);
        novoUsuario.setTipoUsuario(tipoUsuario);
        
        usuarioDAO.cadastrarUsuario(novoUsuario);
    }
}
