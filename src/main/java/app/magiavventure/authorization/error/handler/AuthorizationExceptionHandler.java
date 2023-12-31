package app.magiavventure.authorization.error.handler;

import app.magiavventure.authorization.configuration.AuthorizationProperties;
import app.magiavventure.authorization.configuration.AuthorizationProperties.ErrorProperties.ErrorMessage;
import app.magiavventure.authorization.error.AuthorizationException;
import app.magiavventure.authorization.mapper.AuthorizationErrorMapper;
import app.magiavventure.authorization.model.HttpError;
import app.magiavventure.authorization.model.AuthorizationError;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Objects;

@Slf4j
@ControllerAdvice
@AllArgsConstructor
public class AuthorizationExceptionHandler {
    private final AuthorizationProperties authorizationProperties;
    private final AuthorizationErrorMapper userErrorMapper;

    @ExceptionHandler({AuthorizationException.class})
    public ResponseEntity<HttpError> userExceptionHandler(AuthorizationException authorizationException) {

        AuthorizationError authorizationError = authorizationException.getAuthorizationError();
        ErrorMessage errorMessage = retrieveError(authorizationError.getKey());

        HttpError httpError = userErrorMapper.map(errorMessage);
        httpError.setMessage(formatMessage(errorMessage.getMessage(), authorizationError.getArgs()));

        return ResponseEntity
                .status(httpError.getStatus())
                .body(httpError);
    }

    private ErrorMessage retrieveError(@NotNull String key) {
        var errorMessage = authorizationProperties
                .getErrors()
                .getErrorMessages()
                .get(key);

        if(Objects.isNull(errorMessage)) return retrieveError(AuthorizationException.UNKNOWN_ERROR);


        return errorMessage;
    }

    private String formatMessage(String message, Object... args) {
        if(args.length > 0) {
            return String.format(message, args);
        }
        return message;
    }

}
