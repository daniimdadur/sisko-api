package practice.api.base;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public class BaseController<T> {
    public ResponseEntity<Response> getResponse(List<T> result) {
        return ResponseEntity.ok(
                Response.builder()
                        .code(HttpStatus.OK.value())
                        .statusCode(HttpStatus.OK.name())
                        .message("Success")
                        .data(result)
                        .build()
        );
    }

    public ResponseEntity<Response> getResponse(Optional<T> result) {
        if (result.isPresent()) {
            T res = result.get();
            return ResponseEntity.ok(
                    Response.builder()
                            .code(HttpStatus.OK.value())
                            .statusCode(HttpStatus.OK.name())
                            .message("Success")
                            .data(res)
                            .build()
            );
        } else {
            return ResponseEntity.badRequest().body(
                    Response.builder()
                            .code(HttpStatus.BAD_REQUEST.value())
                            .statusCode(HttpStatus.BAD_REQUEST.name())
                            .message("Bad Request")
                            .data(null)
                            .build()
            );
        }
    }
}
