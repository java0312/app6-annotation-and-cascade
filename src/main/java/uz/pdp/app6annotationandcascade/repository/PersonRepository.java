package uz.pdp.app6annotationandcascade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.app6annotationandcascade.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

}
