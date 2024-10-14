package practice.api.student.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import practice.api.course.model.CourseEntity;
import practice.api.course.model.CourseRes;
import practice.api.majors.model.MajorsEntity;
import practice.api.majors.repository.MajorsRepo;
import practice.api.student.model.StudentEntity;
import practice.api.student.model.StudentReq;
import practice.api.student.model.StudentRes;
import practice.api.student.repository.StudentRepo;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class StudentServiceImpl implements StudentService{
    private final StudentRepo studentRepo;
    private final MajorsRepo majorsRepo;

    @Override
    public List<StudentRes> get() {
        List<StudentEntity> result = this.studentRepo.findAll();
        if (result.isEmpty()) {
            return Collections.emptyList();
        }

        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<StudentRes> getById(String id) {
        StudentEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<StudentRes> save(StudentReq request) {
        StudentEntity result = convertReqToEntity(request);

        try {
            this.studentRepo.save(result);
            return Optional.of(this.convertEntityToRes(result));
        } catch (Exception e) {
            log.error("save failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<StudentRes> update(StudentReq request, String id) {
        StudentEntity result = this.getEntityById(id);
        convertReqToEntity(request, result);

        try {
            this.studentRepo.save(result);
            return Optional.of(this.convertEntityToRes(result));
        } catch (Exception e) {
            log.error("update failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public Optional<StudentRes> delete(String id) {
        StudentEntity result = this.getEntityById(id);

        try {
            this.studentRepo.delete(result);
            return Optional.of(this.convertEntityToRes(result));
        } catch (Exception e) {
            log.error("delete failed, error: {}", e.getMessage());
            return Optional.empty();
        }
    }

    private StudentRes convertEntityToRes(StudentEntity studentEntity) {
        StudentRes result = new StudentRes();
        BeanUtils.copyProperties(studentEntity, result);

        if (studentEntity.getMajors() != null) {
            result.setMajorsId(studentEntity.getMajors().getId());
            result.setMajorsName(studentEntity.getMajors().getName());
            result.setCourseList(convertCourseEntityToRes(studentEntity.getCourseList()));
        }

        return result;
    }

    private StudentEntity getEntityById(String id) {
        StudentEntity result = this.studentRepo.findById(id).orElse(null);
        if (result == null) {
            log.error("Student with id {} not found", id);
        }

        return result;
    }

    private StudentEntity convertReqToEntity(StudentReq request) {
        MajorsEntity majorsEntity = this.majorsRepo.findById(request.getMajorsId()).orElse(null);
        if (majorsEntity == null) {
            log.error("Majors with id {} not found", request.getMajorsId());
        }

        StudentEntity result = new StudentEntity();
        BeanUtils.copyProperties(request, result);
        result.setId(UUID.randomUUID().toString());
        result.setMajors(majorsEntity);
        return result;
    }

    private void convertReqToEntity(StudentReq request, StudentEntity result) {
        BeanUtils.copyProperties(request, result);

        MajorsEntity majorsEntity = this.majorsRepo.findById(request.getMajorsId()).orElse(null);
        if (majorsEntity == null) {
            log.error("Majors with id {} not found", request.getMajorsId());
        }
        result.setMajors(majorsEntity);
    }

    private List<CourseRes> convertCourseEntityToRes(List<CourseEntity> courseEntities) {
        List<CourseRes> result = new ArrayList<>();
        for (CourseEntity courseEntity : courseEntities) {
            CourseRes courseRes = new CourseRes();
            BeanUtils.copyProperties(courseEntity, courseRes);
            result.add(courseRes);
        }
        return result;
    }
}
