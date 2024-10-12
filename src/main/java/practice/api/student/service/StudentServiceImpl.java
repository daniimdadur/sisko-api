package practice.api.student.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import practice.api.student.model.StudentReq;
import practice.api.student.model.StudentRes;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService{
    @Override
    public List<StudentRes> get() {
        return List.of();
    }

    @Override
    public Optional<StudentRes> getById(String id) {
        return Optional.empty();
    }

    @Override
    public Optional<StudentRes> save(StudentReq request) {
        return Optional.empty();
    }

    @Override
    public Optional<StudentRes> update(StudentReq request, String id) {
        return Optional.empty();
    }

    @Override
    public Optional<StudentRes> delete(String id) {
        return Optional.empty();
    }
}
