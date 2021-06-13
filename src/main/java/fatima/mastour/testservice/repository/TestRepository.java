package fatima.mastour.testservice.repository;

import fatima.mastour.testservice.entities.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface TestRepository extends JpaRepository<Test, Long> {
}
