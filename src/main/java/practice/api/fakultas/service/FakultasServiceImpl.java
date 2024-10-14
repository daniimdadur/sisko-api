package practice.api.fakultas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import practice.api.fakultas.model.FakultasEntity;
import practice.api.fakultas.model.FakultasReq;
import practice.api.fakultas.model.FakultasRes;
import practice.api.fakultas.repository.FakultasRepo;
import practice.api.majors.model.MajorsEntity;
import practice.api.majors.model.MajorsRes;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FakultasServiceImpl implements FakultasService {
    private final FakultasRepo fakultasRepo;

    @Override
    public List<FakultasRes> get() {
        List<FakultasEntity> result = fakultasRepo.findAll();
        if (result.isEmpty()) {
            return Collections.emptyList();
        }

        return result.stream().map(this::convertEntityToRes).collect(Collectors.toList());
    }

    @Override
    public Optional<FakultasRes> getById(String id) {
        FakultasEntity result = fakultasRepo.findById(id).orElse(null);
        if (result == null) {
            return Optional.empty();
        }

        return Optional.of(convertEntityToRes(result));
    }

    @Override
    public Optional<FakultasRes> save(FakultasReq request) {
        FakultasEntity result = new FakultasEntity();

        BeanUtils.copyProperties(request, result);
        result.setId(UUID.randomUUID().toString());

        try {
            this.fakultasRepo.save(result);
            return Optional.of(convertEntityToRes(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<FakultasRes> update(FakultasReq request, String id) {
        FakultasEntity result = fakultasRepo.findById(id).orElse(null);
        if (result == null) {
            return Optional.empty();
        }

        BeanUtils.copyProperties(request, result);
        try {
            fakultasRepo.save(result);
            return Optional.of(convertEntityToRes(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<FakultasRes> delete(String id) {
        FakultasEntity result = fakultasRepo.findById(id).orElse(null);
        if (result == null) {
            return Optional.empty();
        }

        try {
            fakultasRepo.delete(result);
            return Optional.of(convertEntityToRes(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private FakultasRes convertEntityToRes(FakultasEntity entity) {
        FakultasRes result = new FakultasRes();

        BeanUtils.copyProperties(entity, result);
        if (!entity.getMajorsList().isEmpty()) {
            List<MajorsRes> majorsResList = new ArrayList<>();
            for (MajorsEntity majorsEntity : entity.getMajorsList()) {
                MajorsRes majorsRes = new MajorsRes();

                BeanUtils.copyProperties(majorsEntity, majorsRes);
                majorsRes.setFakultasName(result.getName());
                majorsResList.add(majorsRes);
            }

            result.setMajorsList(majorsResList);
        }

        return result;
    }
}
