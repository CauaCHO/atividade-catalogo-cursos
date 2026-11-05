package br.edu.catalogocursos.api.exception;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import java.time.Instant;
import java.util.*;
@RestControllerAdvice public class ApiExceptionHandler {
 @ExceptionHandler(RecursoNaoEncontradoException.class) ResponseEntity<ErroResponse> notFound(RuntimeException e,HttpServletRequest r){return resposta(404,e.getMessage(),r,Map.of());}
 @ExceptionHandler(ConflitoException.class) ResponseEntity<ErroResponse> conflict(RuntimeException e,HttpServletRequest r){return resposta(409,e.getMessage(),r,Map.of());}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<ErroResponse> invalid(MethodArgumentNotValidException e,HttpServletRequest r){Map<String,String> f=new LinkedHashMap<>();e.getBindingResult().getFieldErrors().forEach(x->f.putIfAbsent(x.getField(),x.getDefaultMessage()));return resposta(400,"Dados inválidos",r,f);}
 private ResponseEntity<ErroResponse> resposta(int s,String m,HttpServletRequest r,Map<String,String> f){return ResponseEntity.status(s).body(new ErroResponse(Instant.now(),s,m,r.getRequestURI(),f));}
}
