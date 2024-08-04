package dio.Santander.Dev.Week._4.adapters.in.exceptionHandler;

import dio.Santander.Dev.Week._4.domain.exceptions.ChampionNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ChampionNotFoundException.class)
    public ResponseEntity<ApiError> handleDomainException(ChampionNotFoundException domainError){

        return ResponseEntity.unprocessableEntity().body(new ApiError(domainError.getMessage()));

    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleDomainException(Exception domainError){

        String message = "Unexpected error";
        logger.error(message, domainError);

        return ResponseEntity.internalServerError().body(new ApiError(message));

    }

    public record ApiError(String message){ }
}
