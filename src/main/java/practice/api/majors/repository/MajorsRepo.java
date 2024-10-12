package practice.api.majors.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.api.majors.model.MajorsEntity;

@Repository
public interface MajorsRepo extends JpaRepository<MajorsEntity, String> {
}
