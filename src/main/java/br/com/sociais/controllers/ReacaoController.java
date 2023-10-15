package br.com.sociais.controllers;


import br.com.sociais.models.Reacao;

import br.com.sociais.repositories.ReacaoRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/reacoes")
@Tag(name = "reacoes")
public class ReacaoController {

    @Autowired
    private ReacaoRepository reacaoRepository;
    @GetMapping
    public ResponseEntity<List<Reacao>> findAll(){
        return ResponseEntity.ok(reacaoRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Reacao> post(@RequestBody Reacao reacao){
        return ResponseEntity.status(HttpStatus.CREATED).body(reacaoRepository.save(reacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reacao> getOne(@PathVariable Long id){
        Optional<Reacao> reacao = reacaoRepository.findById(id);
        if (reacao.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(reacao.get());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reacao> update(@PathVariable Long id, @RequestBody Reacao reacao){
        Optional<Reacao> reacao0 = reacaoRepository.findById(id);
        if (reacao0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            var reacaoNovo = reacao0.get();
            BeanUtils.copyProperties(reacao, reacaoNovo);
            return ResponseEntity.status(HttpStatus.OK).body(reacaoRepository.save(reacaoNovo));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        reacaoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
