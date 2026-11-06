package br.edu.catalogocursos.application;

import br.edu.catalogocursos.api.mapper.CursoMapper;
import br.edu.catalogocursos.api.mapper.InstrutorMapper;
import br.edu.catalogocursos.domain.Curso;
import br.edu.catalogocursos.domain.Instrutor;
import br.edu.catalogocursos.domain.NivelCurso;
import br.edu.catalogocursos.repository.CursoRepository;
import br.edu.catalogocursos.repository.InstrutorRepository;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InstrutorServiceTest {

    @Test
    void listagemRetornaSomenteCursosDoInstrutorOrdenadosPorTitulo() {
        Instrutor instrutorSolicitado = new Instrutor("Minerva McGonagall", "minerva@magia.edu");
        Instrutor outroInstrutor = new Instrutor("Severo Snape", "snape@magia.edu");

        Curso pocoes = new Curso("Poções", 40, NivelCurso.INTERMEDIARIO);
        Curso defesa = new Curso("Defesa Contra as Artes das Trevas", 60, NivelCurso.AVANCADO);
        Curso herbologia = new Curso("Herbologia", 30, NivelCurso.INICIANTE);
        instrutorSolicitado.adicionarCurso(pocoes);
        instrutorSolicitado.adicionarCurso(defesa);
        outroInstrutor.adicionarCurso(herbologia);

        InstrutorRepository instrutores = (InstrutorRepository) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{InstrutorRepository.class},
                (proxy, method, args) -> method.getName().equals("findById")
                        ? Optional.of(instrutorSolicitado)
                        : null);

        CursoRepository cursos = (CursoRepository) Proxy.newProxyInstance(
                getClass().getClassLoader(),
                new Class[]{CursoRepository.class},
                (proxy, method, args) -> method.getName().equals("findByInstrutorIdOrderByTituloAsc")
                        ? List.of(defesa, pocoes)
                        : null);

        InstrutorService service = new InstrutorService(
                instrutores, cursos, new InstrutorMapper(), new CursoMapper());

        var resultado = service.listarCursos(1L);

        assertEquals(2, resultado.size());
        assertEquals("Defesa Contra as Artes das Trevas", resultado.get(0).titulo());
        assertEquals("Poções", resultado.get(1).titulo());
    }
}
