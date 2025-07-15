package br.com.pizzariadankcode.cursoevandro.usuario;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

    private Long id;

    @NotBlank
    private String login;

    @NotBlank
    private String password;
}
