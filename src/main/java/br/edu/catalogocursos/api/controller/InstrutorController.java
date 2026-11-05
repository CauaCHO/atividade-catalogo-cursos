package br.edu.catalogocursos.api.controller;
import br.edu.catalogocursos.api.dto.*; import br.edu.catalogocursos.application.InstrutorService; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import org.springframework.web.servlet.support.ServletUriComponentsBuilder; import java.net.URI; import java.util.List;
@RestController @RequestMapping("/api/instrutores") public class InstrutorController {
 private final InstrutorService service; public InstrutorController(InstrutorService s){service=s;}
 @PostMapping public ResponseEntity<InstrutorResponse> cadastrar(@Valid @RequestBody InstrutorRequest r){InstrutorResponse x=service.cadastrar(r);URI u=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(x.id()).toUri();return ResponseEntity.created(u).body(x);}
 @GetMapping("/{id}") public InstrutorResponse buscar(@PathVariable Long id){return service.buscar(id);}
 @GetMapping("/{id}/cursos") public List<CursoResumoResponse> cursos(@PathVariable Long id){return service.listarCursos(id);}
}
