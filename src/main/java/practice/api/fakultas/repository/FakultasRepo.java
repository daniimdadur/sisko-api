package practice.api.fakultas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.api.fakultas.model.FakultasEntity;

@Repository
public interface FakultasRepo extends JpaRepository<FakultasEntity, String> {
}
