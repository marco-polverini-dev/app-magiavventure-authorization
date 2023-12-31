package app.magiavventure.authorization.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthorizationError {
    private String key;
    private Throwable throwable;
    private Object[] args;
}
