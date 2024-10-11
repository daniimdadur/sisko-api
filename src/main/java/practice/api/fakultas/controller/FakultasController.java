package practice.api.fakultas.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.api.base.BaseController;
import practice.api.base.Response;
import practice.api.fakultas.model.FakultasReq;
import practice.api.fakultas.model.FakultasRes;
import practice.api.fakultas.service.FakultasService;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/fakultas")
public class FakultasController extends BaseController<FakultasRes> {
    private final FakultasService fakultasService;

    public FakultasController(FakultasService fakultasService) {
        this.fakultasService = fakultasService;
    }

    @GetMapping
    public ResponseEntity<Response> get() {
        return ResponseEntity.ok(
                Response.builder()
                        .code(HttpStatus.OK.value())
                        .statusCode(HttpStatus.OK.name())
                        .message("Success")
                        .data(fakultasService.get())
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable String id) {
        Optional<FakultasRes> result = fakultasService.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> save(@RequestBody FakultasReq request) {
        Optional<FakultasRes> result = fakultasService.save(request);
        return getResponse(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable String id, @RequestBody FakultasReq request) {
        Optional<FakultasRes> result = fakultasService.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable String id) {
        Optional<FakultasRes> result = fakultasService.delete(id);
        return getResponse(result);
    }
}
