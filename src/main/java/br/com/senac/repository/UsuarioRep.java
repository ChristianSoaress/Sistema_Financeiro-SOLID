package br.com.senac.repository;

import br.com.senac.model.Usuario;
import java.util.List;

public interface UsuarioRep {
    Usuario autenticar(String username, String senha);
    void cadastrarUsuario(Usuario usuario);
    void excluir(int id);
    List<Usuario> listar();
    boolean podeRemoverUsuario(int idUsuario, int idUsuarioAutenticado);
}
