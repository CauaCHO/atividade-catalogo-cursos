package br.edu.catalogocursos.application;
import br.edu.catalogocursos.api.dto.*; import br.edu.catalogocursos.api.exception.*; import br.edu.catalogocursos.api.mapper.*; import br.edu.catalogocursos.domain.*; import br.edu.catalogocursos.repository.*;
import org.springframework.stereotype.Service; import org.springframework.transaction.annotation.Transactional;
@Service public class CursoService {
 private final CursoRepository cursos; private final InstrutorRepository instrutores; private final CursoMapper mapper;
 public CursoService(CursoRepository c,InstrutorRepository i,CursoMapper m){cursos=c;instrutores=i;mapper=m;}
 @Transactional public CursoResponse cadastrar(CursoRequest r){Instrutor i=instrutores.findById(r.instrutorId()).orElseThrow(()->new RecursoNaoEncontradoException("Instrutor não encontrado"));Curso c=mapper.toEntity(r);i.adicionarCurso(c);return mapper.toResponse(cursos.save(c));}
 @Transactional(readOnly=true) public CursoResponse buscar(Long id){Curso c=cursos.findById(id).orElseThrow(()->new RecursoNaoEncontradoException("Curso não encontrado"));return mapper.toResponse(c);}
}
