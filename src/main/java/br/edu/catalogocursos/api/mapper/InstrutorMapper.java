package br.edu.catalogocursos.api.mapper;
import br.edu.catalogocursos.api.dto.*;
import br.edu.catalogocursos.domain.Instrutor;
import org.springframework.stereotype.Component;
@Component public class InstrutorMapper {
 public Instrutor toEntity(InstrutorRequest r){ return new Instrutor(r.nome().trim(), r.email().trim().toLowerCase()); }
 public InstrutorResponse toResponse(Instrutor i){ return new InstrutorResponse(i.getId(),i.getNome(),i.getEmail()); }
}
