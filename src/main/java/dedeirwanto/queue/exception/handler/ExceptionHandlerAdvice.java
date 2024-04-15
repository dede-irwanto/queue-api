package dedeirwanto.queue.exception.handler;

import dedeirwanto.queue.dto.ErrorResponseDTO;
import dedeirwanto.queue.enums.ErrorCode;
import dedeirwanto.queue.exception.BadRequestException;
import dedeirwanto.queue.exception.ResourceNotFoundException;
import dedeirwanto.queue.exception.UnAuthorizedException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class ExceptionHandlerAdvice extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> details = new ArrayList<>();
        for (ObjectError error : ex.getAllErrors()) {
            details.add(error.getDefaultMessage());
        }
        ErrorResponseDTO errorResponse = ErrorResponseDTO.of("Bad Request!", details, ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponseDTO> handleForbiddenException(Exception ex, WebRequest request) {
        List<String> details = new ArrayList<String>();
        ErrorResponseDTO errorResponse = null;
        HttpStatus httpStatus = null;

        if (ex instanceof AccessDeniedException) {
            details.add(ex.getMessage());
            errorResponse = ErrorResponseDTO.of("FORBIDDEN!", details, ErrorCode.FORBIDDEN, HttpStatus.FORBIDDEN);
            httpStatus = HttpStatus.FORBIDDEN;
        }

        if (ex instanceof UnAuthorizedException) {
            details.add(ex.getMessage());
            errorResponse = ErrorResponseDTO.of("UNAUTHORIZED!", details, ErrorCode.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
            httpStatus = HttpStatus.UNAUTHORIZED;
        }

        if (ex instanceof SignatureException) {
            details.add(ex.getMessage());
            errorResponse = ErrorResponseDTO.of("FORBIDDEN!", details, ErrorCode.FORBIDDEN, HttpStatus.FORBIDDEN);
            httpStatus = HttpStatus.FORBIDDEN;
        }

        if (ex instanceof ExpiredJwtException) {
            details.add(ex.getMessage());
            errorResponse = ErrorResponseDTO.of("FORBIDDEN!", details, ErrorCode.FORBIDDEN, HttpStatus.FORBIDDEN);
            httpStatus = HttpStatus.FORBIDDEN;
        }

        if (ex instanceof BadRequestException) {
            details.add(ex.getMessage());
            errorResponse = ErrorResponseDTO.of("BAD REQUEST!", details, ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        if (ex instanceof ResourceNotFoundException) {
            details.add(ex.getMessage());
            errorResponse = ErrorResponseDTO.of("NOT FOUND!", details, ErrorCode.NOT_FOUND, HttpStatus.NOT_FOUND);
            httpStatus = HttpStatus.NOT_FOUND;
        }

        if (ex instanceof NullPointerException) {
            details.add(ex.getMessage());
            errorResponse = ErrorResponseDTO.of("BAD REQUEST!", details, ErrorCode.BAD_REQUEST, HttpStatus.BAD_REQUEST);
            httpStatus = HttpStatus.NOT_FOUND;
        }

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

}
