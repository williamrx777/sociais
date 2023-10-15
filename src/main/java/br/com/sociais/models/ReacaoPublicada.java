package br.com.sociais.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class ReacaoPublicada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;
    @Column(nullable = false)
    private Date dataReacao;
    @Column(nullable = false, length = 100, columnDefinition = "Text")
    private String nome;


}
