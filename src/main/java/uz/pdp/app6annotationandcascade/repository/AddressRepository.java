package uz.pdp.app6annotationandcascade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.app6annotationandcascade.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
}
