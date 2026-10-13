package br.com.fiap.spring_backend_esg.model;

public enum UsuarioRole {
    ADMIN("admin"),
    OPERADOR("operador"),
    USER("user");

    private String role;

    UsuarioRole(String role){
        this.role = role;
    }

    public String getRole(){
        return  this.role;
    }
}
