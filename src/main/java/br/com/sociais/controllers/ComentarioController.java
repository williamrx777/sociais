package br.com.sociais.controllers;

import br.com.sociais.models.Comentario;
import br.com.sociais.models.ReacaoPublicada;
import br.com.sociais.repositories.ComentarioRepository;
import br.com.sociais.repositories.ReacaoPublicadaRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comentarios")
@Tag(name = "comentarios")
public class ComentarioController {

    @Autowired
    private ComentarioRepository comentarioRepository;
    @GetMapping
    public ResponseEntity<List<Comentario>> findAll(){
        return ResponseEntity.ok(comentarioRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Comentario> post(@RequestBody Comentario comentario){
        return ResponseEntity.status(HttpStatus.CREATED).body(comentarioRepository.save(comentario));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Comentario> getOne(@PathVariable Long id){
        Optional<Comentario> comentario = comentarioRepository.findById(id);
        if (comentario.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(comentario.get());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comentario> update(@PathVariable Long id, @RequestBody Comentario comentario){
        Optional<Comentario> comentario0 = comentarioRepository.findById(id);
        if (comentario0.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            var comentarioNovo = comentario0.get();
            BeanUtils.copyProperties(comentario, comentarioNovo);
            return ResponseEntity.status(HttpStatus.OK).body(comentarioRepository.save(comentarioNovo));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        comentarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
