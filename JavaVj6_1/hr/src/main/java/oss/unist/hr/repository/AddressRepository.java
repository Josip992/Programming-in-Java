package oss.unist.hr.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import oss.unist.hr.model.Address;


    public interface AddressRepository extends JpaRepository<Address, Long> {
    }

