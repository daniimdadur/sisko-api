package practice.api.majors.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.api.base.BaseController;
import practice.api.base.Response;
import practice.api.majors.model.MajorsReq;
import practice.api.majors.model.MajorsRes;
import practice.api.majors.service.MajorsService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/majors")
public class MajorsController extends BaseController<MajorsRes> {
    private final MajorsService majorsService;

    @GetMapping
    public ResponseEntity<Response> get() {
        List<MajorsRes> result = majorsService.get();
        return getResponse(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable String id) {
        Optional<MajorsRes> result = majorsService.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> post(@RequestBody MajorsReq request) {
        Optional<MajorsRes> result = majorsService.save(request);
        return getResponse(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> patch(@PathVariable String id, @RequestBody MajorsReq request) {
        Optional<MajorsRes> result = majorsService.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable String id) {
        Optional<MajorsRes> result = majorsService.delete(id);
        return getResponse(result);
    }
}
