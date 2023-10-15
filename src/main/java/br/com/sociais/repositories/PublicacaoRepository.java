package br.com.sociais.repositories;

import br.com.sociais.models.Publicacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicacaoRepository extends JpaRepository<Publicacao,Long> {
}
