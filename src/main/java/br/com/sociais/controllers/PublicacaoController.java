package br.com.sociais.controllers;

import br.com.sociais.models.Publicacao;
import br.com.sociais.models.Usuario;
import br.com.sociais.repositories.PublicacaoRepository;
import br.com.sociais.repositories.UsuarioRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/publicacoes")
@Tag(name = "publicacoes")
public class PublicacaoController {

    @Autowired
    private PublicacaoRepository publicacaoRepository;
    @GetMapping
    public ResponseEntity<List<Publicacao>> findAll(){
        return ResponseEntity.ok(publicacaoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Publicacao> post(@RequestBody Publicacao publicacao){
        return ResponseEntity.status(HttpStatus.CREATED).body(publicacaoRepository.save(publicacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Publicacao> getOne(@PathVariable Long id){
        Optional<Publicacao> publicacao = publicacaoRepository.findById(id);
        if (publicacao.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(publicacao.get());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Publicacao> update(@PathVariable Long id, @RequestBody Publicacao publicacao){
        Optional<Publicacao> publicacao0 = publicacaoRepository.findById(id);
        if (publicacao0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            var publicacaoNovo = publicacao0.get();
            BeanUtils.copyProperties(publicacao, publicacaoNovo);
            return ResponseEntity.status(HttpStatus.OK).body(publicacaoRepository.save(publicacaoNovo));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        publicacaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
