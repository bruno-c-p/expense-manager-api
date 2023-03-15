package me.brunocardozo.meusgastos.handler;

import me.brunocardozo.meusgastos.common.ConversorData;
import me.brunocardozo.meusgastos.domain.exception.ResourceBadRequestException;
import me.brunocardozo.meusgastos.domain.exception.ResourceNotFoundException;
import me.brunocardozo.meusgastos.domain.model.ErroResposta;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErroResposta> handleResourceNotFoundException(ResourceNotFoundException exception) {
        ErroResposta erro = new ErroResposta(
                ConversorData.converterDateParaString(new Date()),
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND.value()).body(erro);
    }
    @ExceptionHandler(ResourceBadRequestException.class)
    public ResponseEntity<ErroResposta> handleResourceBadRequestException(ResourceBadRequestException exception) {
        ErroResposta erro = new ErroResposta(
                ConversorData.converterDateParaString(new Date()),
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body(erro);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErroResposta> handleRequestException(Exception exception) {
        ErroResposta erro = new ErroResposta(
                ConversorData.converterDateParaString(new Date()),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Internal Server Error",
                exception.getMessage()
        );
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value()).body(erro);
    }
}
