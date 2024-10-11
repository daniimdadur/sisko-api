package practice.api.fakultas.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import practice.api.fakultas.model.FakultasEntity;
import practice.api.fakultas.model.FakultasReq;
import practice.api.fakultas.model.FakultasRes;
import practice.api.fakultas.repository.FakultasRepo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

        return result.stream().map(FakultasRes::new).collect(Collectors.toList());
    }

    @Override
    public Optional<FakultasRes> getById(String id) {
        FakultasEntity result = fakultasRepo.findById(id).orElse(null);
        if (result == null) {
            return Optional.empty();
        }

        return Optional.of(new FakultasRes(result));
    }

    @Override
    public Optional<FakultasRes> save(FakultasReq request) {
        FakultasEntity result = new FakultasEntity();

        BeanUtils.copyProperties(request, result);
        result.setId(UUID.randomUUID().toString());

        try {
            this.fakultasRepo.save(result);
            return Optional.of(new FakultasRes(result));
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
            return Optional.of(new FakultasRes(result));
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
            return Optional.of(new FakultasRes(result));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
