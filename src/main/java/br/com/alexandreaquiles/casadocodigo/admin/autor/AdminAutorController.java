package br.com.alexandreaquiles.casadocodigo.admin.autor;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/admin/autores")
public class AdminAutorController {

    private final AutorRepository autorRepository;

    public AdminAutorController(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    @Transactional
    @PostMapping
    public ResponseEntity<NovoAutorResponse> novoAutor(@RequestBody @Valid NovoAutorRequest novoAutorRequest) {
        Autor autor = novoAutorRequest.toEntity();
        autorRepository.save(autor);
        return ResponseEntity.ok(new NovoAutorResponse(autor));
    }

}
