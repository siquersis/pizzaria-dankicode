package br.com.pizzariadankcode.cursoevandro.pizza;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Pizza")
@Data /*ENGLOBA O GETTERS E SETTERS*/
@AllArgsConstructor
@NoArgsConstructor
public class Pizza {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private boolean disponivel;

    private double preco;

    @Enumerated(EnumType.STRING)
    private Tamanho tamanho;

    @Enumerated(EnumType.STRING)
    private Sabor sabor;

    @Enumerated(EnumType.STRING)
    private Categoria categoria;
}
