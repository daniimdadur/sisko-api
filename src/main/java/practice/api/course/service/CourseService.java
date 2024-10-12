package practice.api.course.service;

import practice.api.course.model.CourseReq;
import practice.api.course.model.CourseRes;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<CourseRes> get();
    Optional<CourseRes> getById(String id);
    Optional<CourseRes> save(CourseReq request);
    Optional<CourseRes> update(CourseReq request, String id);
    Optional<CourseRes> delete(String id);
}
