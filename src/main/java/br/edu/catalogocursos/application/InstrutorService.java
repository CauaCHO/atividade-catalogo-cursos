package br.edu.catalogocursos.application;
import br.edu.catalogocursos.api.dto.*; import br.edu.catalogocursos.api.exception.*; import br.edu.catalogocursos.api.mapper.*; import br.edu.catalogocursos.domain.*; import br.edu.catalogocursos.repository.*;
import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional; import java.util.List;
@Service public class InstrutorService {
 private final InstrutorRepository instrutores; private final CursoRepository cursos; private final InstrutorMapper instrutorMapper; private final CursoMapper cursoMapper;
 public InstrutorService(InstrutorRepository i,CursoRepository c,InstrutorMapper im,CursoMapper cm){instrutores=i;cursos=c;instrutorMapper=im;cursoMapper=cm;}
 @Transactional public InstrutorResponse cadastrar(InstrutorRequest r){if(instrutores.existsByEmailIgnoreCase(r.email().trim()))throw new ConflitoException("E-mail já cadastrado");return instrutorMapper.toResponse(instrutores.save(instrutorMapper.toEntity(r)));}
 @Transactional(readOnly=true) public InstrutorResponse buscar(Long id){return instrutorMapper.toResponse(encontrar(id));}
 @Transactional(readOnly=true) public List<CursoResumoResponse> listarCursos(Long id){encontrar(id);return cursos.findByInstrutorIdOrderByTituloAsc(id).stream().map(cursoMapper::toResumo).toList();}
 Instrutor encontrar(Long id){return instrutores.findById(id).orElseThrow(()->new RecursoNaoEncontradoException("Instrutor não encontrado"));}
}
