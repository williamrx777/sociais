package br.com.sociais.controllers;

import br.com.sociais.models.Reacao;
import br.com.sociais.models.ReacaoPublicada;
import br.com.sociais.repositories.ReacaoPublicadaRepository;
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
@RequestMapping("/reacoespublicadas")
@Tag(name = "reacoespublicadas")
public class ReacaopublicadaController {

    @Autowired
    private ReacaoPublicadaRepository reacaoPublicadaRepository;
    @GetMapping
    public ResponseEntity<List<ReacaoPublicada>> findAll(){
        return ResponseEntity.ok(reacaoPublicadaRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<ReacaoPublicada> post(@RequestBody ReacaoPublicada reacaoPublicada){
        return ResponseEntity.status(HttpStatus.CREATED).body(reacaoPublicadaRepository.save(reacaoPublicada));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReacaoPublicada> getOne(@PathVariable Long id){
        Optional<ReacaoPublicada> reacaoPublicada = reacaoPublicadaRepository.findById(id);
        if (reacaoPublicada.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(reacaoPublicada.get());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReacaoPublicada> update(@PathVariable Long id, @RequestBody ReacaoPublicada reacaoPublicada){
        Optional<ReacaoPublicada> reacaoPublicada0 = reacaoPublicadaRepository.findById(id);
        if (reacaoPublicada0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            var reacaoPublicadaNovo = reacaoPublicada0.get();
            BeanUtils.copyProperties(reacaoPublicada, reacaoPublicadaNovo);
            return ResponseEntity.status(HttpStatus.OK).body(reacaoPublicadaRepository.save(reacaoPublicadaNovo));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        reacaoPublicadaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
