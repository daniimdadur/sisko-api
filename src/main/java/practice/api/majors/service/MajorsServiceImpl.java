package practice.api.majors.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import practice.api.course.repository.CourseRepo;
import practice.api.majors.model.MajorsEntity;
import practice.api.majors.model.MajorsReq;
import practice.api.majors.model.MajorsRes;
import practice.api.student.model.StudentEntity;
import practice.api.student.model.StudentRes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MajorsServiceImpl implements MajorsService{
    private final CourseRepo courseRepo;

    @Override
    public List<MajorsRes> get() {
        return List.of();
    }

    @Override
    public Optional<MajorsRes> getById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<MajorsRes> save(MajorsReq request) {
        return Optional.empty();
    }

    @Override
    public Optional<MajorsRes> update(MajorsReq request, String id) {
        return Optional.empty();
    }

    @Override
    public Optional<MajorsRes> delete(String id) {
        return Optional.empty();
    }

    private MajorsRes convertEntityToRes(MajorsEntity entity) {
        MajorsRes result = new MajorsRes();

        BeanUtils.copyProperties(entity, result);
        if (entity.getFakultas() != null) {
            List<StudentRes> studentResult = new ArrayList<>();
            for (StudentEntity studentEntity : entity.getStudentList()) {
                StudentRes studentRes = new StudentRes();

                BeanUtils.copyProperties(studentEntity, studentRes);
                studentRes.setMajorsId(result.getId());
                studentRes.setMajorsName(result.getName());
                studentResult.add(studentRes);
            }

            result.setStudentList(studentResult);
        }

        return result;
    }
}
