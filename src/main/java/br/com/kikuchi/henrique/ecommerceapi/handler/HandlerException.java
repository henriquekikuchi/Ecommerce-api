package br.com.kikuchi.henrique.ecommerceapi.handler;

import br.com.kikuchi.henrique.ecommerceapi.exception.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class HandlerException {

    @ExceptionHandler({CarrinhoDeCompraNotFound.class})
    public ResponseEntity<ErrorResponse> handlerCarrinhoDeCompraNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("Carrinho de compras not found!"
                        ,"Carrinho de compras not found!"));
    }

    @ExceptionHandler({CategoriaNotFound.class})
    public ResponseEntity<ErrorResponse> handlerCategoriaNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("Categoria not found!"
                        ,"Categoria not found!"));
    }

    @ExceptionHandler({FornecedorNotFound.class})
    public ResponseEntity<ErrorResponse> handlerFornecedorNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("Fornecedor not found!"
                        ,"Fornecedor not found!"));
    }

    @ExceptionHandler({ProdutoNotFound.class})
    public ResponseEntity<ErrorResponse> handlerProdutoNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("Produto not found!"
                        ,"Produto not found!"));
    }

    @ExceptionHandler({UserNotFound.class})
    public ResponseEntity<ErrorResponse> handlerUserNotFound(){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ErrorResponse("User not found!"
                        ,"User not found!"));
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<ErrorResponse> handlerDataIntegrityViolationException(){
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(new ErrorResponse("Data integrity violation.",
                        "You are trying add an object that violates data integrity values."));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
