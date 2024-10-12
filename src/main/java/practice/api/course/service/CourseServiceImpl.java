package practice.api.course.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.api.course.model.CourseReq;
import practice.api.course.model.CourseRes;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService{
    @Override
    public List<CourseRes> get() {
        return List.of();
    }

    @Override
    public Optional<CourseRes> getById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<CourseRes> save(CourseReq request) {
        return Optional.empty();
    }

    @Override
    public Optional<CourseRes> update(CourseReq request, String id) {
        return Optional.empty();
    }

    @Override
    public Optional<CourseRes> delete(String id) {
        return Optional.empty();
    }
}
