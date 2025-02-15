package com.groceryStore.groceryStore.handler;

import com.groceryStore.groceryStore.exception.GroceryNotFoundException;
import com.groceryStore.groceryStore.exception.InsufficientInventoryException;
import com.groceryStore.groceryStore.exception.OrderProcessingException;
import com.groceryStore.groceryStore.exception.UserNotFoundException;
import com.groceryStore.groceryStore.model.error.Errordto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class UserExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Errordto> handleUserNotFound(UserNotFoundException ex) {

        Errordto errordto = Errordto.builder()
                .message(ex.getLocalizedMessage())
                .status(HttpStatus.NOT_FOUND.name())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build();

        return new ResponseEntity<>(errordto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Errordto> handleIllegalArgumentException(IllegalArgumentException ex) {

        Errordto errordto = Errordto.builder()
                .message(ex.getLocalizedMessage())
                .status(HttpStatus.BAD_REQUEST.name())
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build();

        return new ResponseEntity<>(errordto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(GroceryNotFoundException.class)
    public ResponseEntity<Errordto> handleGroceryNotFoundException(GroceryNotFoundException ex){

        Errordto errordto = Errordto.builder()
                .message(ex.getLocalizedMessage())
                .status(HttpStatus.NOT_FOUND.name())
                .statusCode(HttpStatus.NOT_FOUND.value())
                .build();
        return new ResponseEntity<>(errordto, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(InsufficientInventoryException.class)
    public ResponseEntity<Errordto> InsufficientInventoryException(InsufficientInventoryException ex){

        Errordto errordto = Errordto.builder()
                .message(ex.getLocalizedMessage())
                .status(HttpStatus.INSUFFICIENT_STORAGE.name())
                .statusCode(HttpStatus.INSUFFICIENT_STORAGE.value())
                .build();

        return new ResponseEntity<>(errordto, HttpStatus.INSUFFICIENT_STORAGE);
    }

    @ExceptionHandler(OrderProcessingException.class)
    public ResponseEntity<Errordto> OrderProcessingExceptionhadler(OrderProcessingException ex){
        Errordto errordto = Errordto.builder()
                .message(ex.getLocalizedMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR.name())
                .statusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();

        return new ResponseEntity<>(errordto, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
