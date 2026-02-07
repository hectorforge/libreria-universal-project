package com.microservice.cliente.domain.cliente;
import com.libreriauniversal.BaseEntity;
import com.microservice.cliente.domain.common.TipoCliente;

public class Cliente extends BaseEntity {
    private String keycloakId;
    private String apellidos;
    private String nombre;
    private String email;
    private TipoCliente tipoPersona;
    private String dni;
    private String ruc;
    private String rol;

    public Cliente() {
    }

    public String getKeycloakId() {
        return keycloakId;
    }

    public void setKeycloakId(String keycloakId) {
        this.keycloakId = keycloakId;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoCliente getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(TipoCliente tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}