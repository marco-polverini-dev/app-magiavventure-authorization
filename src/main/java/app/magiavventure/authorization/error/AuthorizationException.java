package app.magiavventure.authorization.error;

import app.magiavventure.authorization.model.AuthorizationError;
import lombok.Getter;

@Getter
public class AuthorizationException extends RuntimeException {

    public static final String UNKNOWN_ERROR = "unknown-error";
    public static final String USER_EXISTS = "user-exists";
    public static final String USER_NOT_FOUND = "user-not-found";

    private final transient AuthorizationError authorizationError;

    private AuthorizationException(AuthorizationError authorizationError) {
        super(authorizationError.getKey(), authorizationError.getThrowable());
        this.authorizationError = authorizationError;
    }

    public static AuthorizationException of(String key, String... args) {
        final var userError = AuthorizationError
                .builder()
                .key(key)
                .args(args)
                .build();
        return new AuthorizationException(userError);
    }

}
