package br.com.sociais.models;

import jakarta.persistence.*;
import lombok.Data;


import java.util.Set;


@Entity
@Data
public class Publicacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(nullable = false, length = 200)
    private String texto;
    @Column(nullable = false, length = 500)
    private String fotoUrl;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToMany
    @JoinTable(name = "publicacao_reacao",
            joinColumns = @JoinColumn(name = "publicacao_id"),
            inverseJoinColumns = @JoinColumn(name = "reacao_id"))
    private Set<Reacao> reacoes;

    @OneToMany(mappedBy = "publicacao", cascade = CascadeType.ALL)
    private Set<Comentario> comentarios;

}
