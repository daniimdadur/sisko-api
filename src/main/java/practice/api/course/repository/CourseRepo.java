package practice.api.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.api.course.model.CourseEntity;

@Repository
public interface CourseRepo extends JpaRepository<CourseEntity, String> {
}
