package br.edu.ifmg.produto.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class RequestTokenDTO {

    @Email(message = "e-mail invalido")
    @NotBlank(message = "campo requerido")
    public String email;

    public RequestTokenDTO(String email) {
        this.email = email;
    }

    public RequestTokenDTO() {}

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
