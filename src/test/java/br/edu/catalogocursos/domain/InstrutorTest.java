package br.edu.catalogocursos.domain;
import org.junit.jupiter.api.Test; import static org.junit.jupiter.api.Assertions.*;
class InstrutorTest {
 @Test void associaCursoNosDoisLados(){Instrutor i=new Instrutor("Minerva McGonagall","minerva@magia.edu");Curso c=new Curso("Transfiguração",40,NivelCurso.AVANCADO);i.adicionarCurso(c);assertSame(i,c.getInstrutor());assertEquals(1,i.getCursos().size());}
 @Test void rejeitaCursoNulo(){Instrutor i=new Instrutor("Pomona Sprout","pomona@magia.edu");assertThrows(NullPointerException.class,()->i.adicionarCurso(null));}
 @Test void evitaMesmaInstanciaDuplicada(){Instrutor i=new Instrutor("Fílio Flitwick","filio@magia.edu");Curso c=new Curso("Feitiços",30,NivelCurso.INICIANTE);i.adicionarCurso(c);i.adicionarCurso(c);assertEquals(1,i.getCursos().size());}
}
