package practice.api.course.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import practice.api.course.model.CourseEntity;
import practice.api.course.model.CourseReq;
import practice.api.course.model.CourseRes;
import practice.api.course.repository.CourseRepo;
import practice.api.student.model.StudentEntity;
import practice.api.student.repository.StudentRepo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CourseServiceImpl implements CourseService{
    private final CourseRepo courseRepo;
    private final StudentRepo studentRepo;

    @Override
    public List<CourseRes> get() {
        List<CourseEntity> result = courseRepo.findAll();
        if (result.isEmpty()) {
            return Collections.emptyList();
        }

        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<CourseRes> getById(String id) {
        CourseEntity result = this.getEntityById(id);

        return Optional.of(convertEntityToRes(result));
    }

    @Override
    public Optional<CourseRes> save(CourseReq request) {
        CourseEntity result = this.convertReqToEntity(request);

        try {
            this.courseRepo.save(result);
            return Optional.of(convertEntityToRes(result));
        } catch (Exception e) {
            log.error("Error saving course {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<CourseRes> update(CourseReq request, String id) {
        CourseEntity result = this.getEntityById(id);
        convertReqToEntity(request, result);

        try {
            this.courseRepo.save(result);
            return Optional.of(convertEntityToRes(result));
        } catch (Exception e) {
            log.error("Error updating course {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<CourseRes> delete(String id) {
        CourseEntity result = this.getEntityById(id);

        try {
            this.courseRepo.delete(result);
            return Optional.of(convertEntityToRes(result));
        } catch (Exception e) {
            log.error("Error deleting course {}", e.getMessage());
            return Optional.empty();
        }
    }

    private CourseEntity getEntityById(String id) {
        CourseEntity result = this.courseRepo.findById(id).orElse(null);
        if (result == null) {
            log.error("Course with id {} not found", id);
        }
        return result;
    }

    private CourseRes convertEntityToRes(CourseEntity entity) {
        CourseRes result = new CourseRes();
        BeanUtils.copyProperties(entity, result);

        if (entity.getStudent() != null) {
            result.setStudentId(entity.getStudent().getId());
            result.setStudentName(entity.getStudent().getName());
        }

        return result;
    }

    private CourseEntity convertReqToEntity(CourseReq req) {
        StudentEntity studentEntity = this.studentRepo.findById(req.getStudentId()).orElse(null);
        if (studentEntity == null) {
            log.error("Student with id {} not found", req.getStudentId());
        }

        CourseEntity result = new CourseEntity();
        BeanUtils.copyProperties(req, result);
        result.setId(UUID.randomUUID().toString());
        result.setStudent(studentEntity);
        return result;
    }

    private void convertReqToEntity(CourseReq req, CourseEntity entity) {
        BeanUtils.copyProperties(req, entity);

        StudentEntity studentEntity = this.studentRepo.findById(req.getStudentId()).orElse(null);
        if (studentEntity == null) {
            log.error("Student with id {} not found", req.getStudentId());
        }
        entity.setStudent(studentEntity);
    }
}
