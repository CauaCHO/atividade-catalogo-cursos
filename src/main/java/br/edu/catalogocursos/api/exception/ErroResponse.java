package br.edu.catalogocursos.api.exception;
import java.time.Instant;
import java.util.Map;
public record ErroResponse(Instant timestamp,int status,String message,String path,Map<String,String> fields) {}
