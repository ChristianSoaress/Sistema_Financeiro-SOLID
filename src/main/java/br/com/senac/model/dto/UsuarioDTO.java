package br.com.senac.model.dto;

public class UsuarioDTO {
    private Integer id;
    private String username;
    private String tipoUsuario;

    public UsuarioDTO(Integer id, String username, String tipoUsuario) {
        this.id = id;
        this.username = username;
        this.tipoUsuario = tipoUsuario;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
