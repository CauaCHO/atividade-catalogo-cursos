package br.edu.catalogocursos.api.controller;
import br.edu.catalogocursos.api.dto.*; import br.edu.catalogocursos.application.CursoService; import jakarta.validation.Valid; import org.springframework.http.*; import org.springframework.web.bind.annotation.*; import org.springframework.web.servlet.support.ServletUriComponentsBuilder; import java.net.URI;
@RestController @RequestMapping("/api/cursos") public class CursoController {
 private final CursoService service; public CursoController(CursoService s){service=s;}
 @PostMapping public ResponseEntity<CursoResponse> cadastrar(@Valid @RequestBody CursoRequest r){CursoResponse x=service.cadastrar(r);URI u=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(x.id()).toUri();return ResponseEntity.created(u).body(x);}
 @GetMapping("/{id}") public CursoResponse buscar(@PathVariable Long id){return service.buscar(id);}
}
