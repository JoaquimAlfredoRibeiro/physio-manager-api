package pt.home.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pt.home.domain.Pathology;

public interface PathologyRepository extends JpaRepository<Pathology, Long> {

    Pathology findByName(String name);
}
