package practice.api.fakultas.service;

import practice.api.fakultas.model.FakultasReq;
import practice.api.fakultas.model.FakultasRes;

import java.util.List;
import java.util.Optional;

public interface FakultasService {
    List<FakultasRes> get();
    Optional<FakultasRes> getById(String id);
    Optional<FakultasRes> save(FakultasReq request);
    Optional<FakultasRes> update(FakultasReq request, String id);
    Optional<FakultasRes> delete(String id);
}
