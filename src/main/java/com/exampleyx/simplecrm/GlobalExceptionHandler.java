package com.exampleyx.simplecrm;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;



@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{




@Override
protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
    HttpHeaders headers, HttpStatus status, WebRequest request) {
        //get list of all validation erors from the exception object
        List<ObjectError> validationErrors = ex.getBindingResult().getAllErrors();
        //create a stringBuilder to store all error messages
         StringBuilder sb = new StringBuilder();

         for(ObjectError error: validationErrors){
            sb.append(error.getDefaultMessage() + " ");
         }
         //create custom error response
         ErrorResponse errorResponse = new ErrorResponse(sb.toString());

         return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

//Handle all other exception
//General exception handler - handle uncaught exception
@ExceptionHandler(Exception.class)
public ResponseEntity<Object> handleAllExceptions(Exception ex){
    ErrorResponse errorResponse = new ErrorResponse("Something went wrong. Please try again later.");
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
}

@ExceptionHandler({CustomerNotFoundException.class, InteractionNotFoundException.class})
public ResponseEntity<Object> handleResourceNotFoundException(RuntimeException ex){

    ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
} 

//(ResourceNotFoundException ex){
// @ExceptionHandler(CustomerNotFoundException.class)
// public ResponseEntity<Object> handleCustomerNotFoundException
// (CustomerNotFoundException ex){
//     ErrorResponse errorResponse = new ErrorResponse(ex.getMessage());
//     return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
// }
//}


//handle unsuccesful deletion
@ExceptionHandler(EmptyResultDataAccessException.class)
public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex){
    ErrorResponse errorResponse = new ErrorResponse("Cannot delete item that doesn't exist");
    return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
}
    
}
