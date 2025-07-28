package br.com.senac.controller;

import br.com.senac.model.Usuario;
import br.com.senac.model.dto.UsuarioDTO;
import br.com.senac.persistence.UsuarioDAO;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioCtrl {
    private final UsuarioDAO usuarioDAO;
    
    public UsuarioCtrl(){
        this.usuarioDAO = new UsuarioDAO();
    }
    
    public List<UsuarioDTO> listarUsuarios(){
        return ListParaDTO(usuarioDAO.listar());
    }
    
    public void excluirUsuario(Integer id, Integer idUsuarioAutenticado){
        if(id.equals(idUsuarioAutenticado)){
            throw new SecurityException("Você não pode remover a si mesmo.");
        }
        usuarioDAO.excluir(id);
    }
    
    private List<UsuarioDTO> ListParaDTO(List<Usuario> usuarios){
        return usuarios.stream().map(usuario -> new UsuarioDTO(
            usuario.getId(),
            usuario.getUsername(),
            usuario.getTipoUsuario()
        )).collect(Collectors.toList());
    }
}
