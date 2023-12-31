package app.magiavventure.authorization.operation;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/authorization")
@Tag(name = "Authorization Operation", description = "Do login/logout")
public class AuthorizationOperation {
    
    @GetMapping()
    public String hello() {
        return "Hello";
    }

}
