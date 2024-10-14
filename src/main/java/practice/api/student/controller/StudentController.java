package practice.api.student.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.api.base.BaseController;
import practice.api.base.Response;
import practice.api.student.model.StudentReq;
import practice.api.student.model.StudentRes;
import practice.api.student.service.StudentService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/student")
public class StudentController extends BaseController<StudentRes> {
    private final StudentService studentService;

    @GetMapping
    public ResponseEntity<Response> get() {
        List<StudentRes> result = this.studentService.get();
        return getResponse(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable String id) {
        Optional<StudentRes> result = this.studentService.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> post(@RequestBody StudentReq request) {
        Optional<StudentRes> result = this.studentService.save(request);
        return getResponse(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> patch(@PathVariable String id, @RequestBody StudentReq request) {
        Optional<StudentRes> result = this.studentService.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable String id) {
        Optional<StudentRes> result = this.studentService.delete(id);
        return getResponse(result);
    }
}
