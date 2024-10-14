package practice.api.majors.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import practice.api.fakultas.model.FakultasEntity;
import practice.api.fakultas.repository.FakultasRepo;
import practice.api.majors.model.MajorsEntity;
import practice.api.majors.model.MajorsReq;
import practice.api.majors.model.MajorsRes;
import practice.api.majors.repository.MajorsRepo;
import practice.api.student.model.StudentEntity;
import practice.api.student.model.StudentRes;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class MajorsServiceImpl implements MajorsService{
    private final MajorsRepo majorsRepo;
    private final FakultasRepo fakultasRepo;

    @Override
    public List<MajorsRes> get() {
        List<MajorsEntity> result = this.majorsRepo.findAll();
        if (result.isEmpty()) {
            return Collections.emptyList();
        }

        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<MajorsRes> getById(String id) {
        MajorsEntity result = this.getEntityById(id);

        return Optional.of(this.convertEntityToRes(result));
    }

    @Override
    public Optional<MajorsRes> save(MajorsReq request) {
        MajorsEntity result = this.convertReqToEntity(request);

        try {
            majorsRepo.save(result);
            return Optional.of(this.convertEntityToRes(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<MajorsRes> update(MajorsReq request, String id) {
        MajorsEntity result = this.getEntityById(id);
        convertReqToEntity(request, result);

        try {
            majorsRepo.save(result);
            return Optional.of(this.convertEntityToRes(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<MajorsRes> delete(String id) {
        MajorsEntity result = this.getEntityById(id);

        try {
            majorsRepo.delete(result);
            return Optional.of(this.convertEntityToRes(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private MajorsRes convertEntityToRes(MajorsEntity entity) {
        MajorsRes result = new MajorsRes();
        BeanUtils.copyProperties(entity, result);

        if (entity.getFakultas() != null) {
            result.setFakultasId(entity.getFakultas().getId());
            result.setFakultasName(entity.getFakultas().getName());
            result.setStudentList(convertStudentEntityToRes(entity.getStudentList()));
        }

        return result;
    }

    private MajorsEntity convertReqToEntity(MajorsReq req) {
        FakultasEntity fakultasEntity = fakultasRepo.findById(req.getFakultasId()).orElse(null);
        if (fakultasEntity == null) {
            log.info("fakultas with id {} not found", req.getFakultasId());
        }

        MajorsEntity result = new MajorsEntity();
        BeanUtils.copyProperties(req, result);
        result.setId(UUID.randomUUID().toString());
        result.setFakultas(fakultasEntity);
        return result;
    }

    private MajorsEntity getEntityById(String id) {
        MajorsEntity result = this.majorsRepo.findById(id).orElse(null);
        if (result == null) {
            log.info("majors with id {} not found", id);
        }

        return result;
    }

    private void convertReqToEntity(MajorsReq req, MajorsEntity majorsEntity) {
        BeanUtils.copyProperties(req, majorsEntity);

        FakultasEntity fakultasEntity = this.fakultasRepo.findById(req.getFakultasId()).orElse(null);
        if (fakultasEntity == null) {
            log.info("fakultas with id {} not found", req.getFakultasId());
        }
        majorsEntity.setFakultas(fakultasEntity);
    }

    private List<StudentRes> convertStudentEntityToRes(List<StudentEntity> studentEntities) {
        List<StudentRes> result = new ArrayList<>();
        for (StudentEntity studentEntity : studentEntities) {
            StudentRes studentRes = new StudentRes();
            BeanUtils.copyProperties(studentEntity, studentRes);
            result.add(studentRes);
        }
        return result;
    }
}
