package practice.api.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.api.student.model.StudentEntity;

@Repository
public interface StudentRepo extends JpaRepository<StudentEntity, String> {
}
