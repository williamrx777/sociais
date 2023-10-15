package br.com.sociais.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Entity
@Data
public class Reacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(nullable = false)
    private Integer tipo;
    @Column(nullable = false, length = 20)
    private String descricao;

    @ManyToMany(mappedBy = "reacoes")
    private Set<Publicacao> publicacoes;


}
