package practice.api.course.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import practice.api.base.BaseController;
import practice.api.base.Response;
import practice.api.course.model.CourseReq;
import practice.api.course.model.CourseRes;
import practice.api.course.service.CourseService;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/course")
public class CourseController extends BaseController<CourseRes> {
    private final CourseService courseService;

    @GetMapping
    public ResponseEntity<Response> get() {
        List<CourseRes> result = this.courseService.get();
        return getResponse(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> get(@PathVariable String id) {
        Optional<CourseRes> result = this.courseService.getById(id);
        return getResponse(result);
    }

    @PostMapping
    public ResponseEntity<Response> post(@RequestBody CourseReq request) {
        Optional<CourseRes> result = this.courseService.save(request);
        return getResponse(result);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Response> patch(@PathVariable String id, @RequestBody CourseReq request) {
        Optional<CourseRes> result = this.courseService.update(request, id);
        return getResponse(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable String id) {
        Optional<CourseRes> result = this.courseService.delete(id);
        return getResponse(result);
    }
}
