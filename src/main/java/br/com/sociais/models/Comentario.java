package br.com.sociais.models;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
public class Comentario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(columnDefinition = "TEXT")
    private String comentario;
    @CreationTimestamp
    private LocalDateTime dataComentario;
    private Boolean ativo;
    @ManyToOne
    private Publicacao publicacao;

}
