package br.com.pizzariadankcode.cursoevandro.infraestrutura;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorExceptios {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarExcecption404(){
        return ResponseEntity.notFound().build();
    }
}
