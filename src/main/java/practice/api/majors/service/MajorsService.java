package practice.api.majors.service;

import practice.api.majors.model.MajorsReq;
import practice.api.majors.model.MajorsRes;

import java.util.List;
import java.util.Optional;

public interface MajorsService {
    List<MajorsRes> get();
    Optional<MajorsRes> getById(String id);
    Optional<MajorsRes> save(MajorsReq request);
    Optional<MajorsRes> update(MajorsReq request, String id);
    Optional<MajorsRes> delete(String id);
}
