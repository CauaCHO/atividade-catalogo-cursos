package br.edu.catalogocursos.application;
import br.edu.catalogocursos.api.dto.*; import br.edu.catalogocursos.api.exception.RecursoNaoEncontradoException; import br.edu.catalogocursos.api.mapper.CursoMapper; import br.edu.catalogocursos.domain.NivelCurso; import br.edu.catalogocursos.repository.*; import org.junit.jupiter.api.Test; import java.lang.reflect.Proxy; import java.util.Optional; import static org.junit.jupiter.api.Assertions.*;
class CursoServiceTest {
 @Test void rejeitaInstrutorInexistente(){
  CursoRepository c=(CursoRepository)Proxy.newProxyInstance(getClass().getClassLoader(),new Class[]{CursoRepository.class},(p,m,a)->null);
  InstrutorRepository i=(InstrutorRepository)Proxy.newProxyInstance(getClass().getClassLoader(),new Class[]{InstrutorRepository.class},(p,m,a)->m.getName().equals("findById")?Optional.empty():null);
  CursoService s=new CursoService(c,i,new CursoMapper());CursoRequest r=new CursoRequest("Poções",20,NivelCurso.INICIANTE,999L);
  assertThrows(RecursoNaoEncontradoException.class,()->s.cadastrar(r));
 }
}
