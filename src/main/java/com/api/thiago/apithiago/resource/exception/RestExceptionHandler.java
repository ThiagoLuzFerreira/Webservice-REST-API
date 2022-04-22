package com.api.thiago.apithiago.resource.exception;

import com.api.thiago.apithiago.domain.DetalheErro;
import com.api.thiago.apithiago.exception.IdInvalidoException;
import com.api.thiago.apithiago.exception.NaoExisteDaoException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({IdInvalidoException.class})
    public ResponseEntity<Object> idInvalido(IdInvalidoException ex, WebRequest request){
        return handleExceptionInternal(
                ex, DetalheErro.builder()
                        .addErro(ex.getMessage())
                        .addStatus(HttpStatus.BAD_REQUEST)
                        .addHttpMethod(getHttpMethod(request))
                        .addPath(getPath(request))
                        .build(),
                new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({NullPointerException.class, IllegalArgumentException.class})
    public ResponseEntity<Object> serverException(RuntimeException ex, WebRequest request){

        return handleExceptionInternal(
                ex, DetalheErro.builder()
                        .addDetalhe("Uma exceção foi lançada.")
                        .addErro(ex.getMessage())
                        .addStatus(HttpStatus.INTERNAL_SERVER_ERROR)
                        .addHttpMethod(getHttpMethod(request))
                        .addPath(getPath(request))
                        .build(),
                new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @ExceptionHandler({NaoExisteDaoException.class})
    public ResponseEntity<Object> entidadeNaoEncontrada(NaoExisteDaoException ex, WebRequest request){

        return handleExceptionInternal(
          ex, DetalheErro.builder()
                        .addDetalhe("Recurso não encontrado na base de dados.")
                        .addErro(ex.getMessage())
                        .addStatus(HttpStatus.NOT_FOUND)
                        .addHttpMethod(getHttpMethod(request))
                        .addPath(getPath(request))
                        .build(),
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    private String getHttpMethod(WebRequest request) {
        return ((ServletWebRequest) request).getRequest().getMethod();
    }

    private String getPath(WebRequest request){
        return ((ServletWebRequest) request).getRequest().getRequestURI();
    }
}
