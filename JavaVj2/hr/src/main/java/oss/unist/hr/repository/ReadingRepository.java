package oss.unist.hr.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import oss.unist.hr.model.Reading;
    public interface ReadingRepository extends JpaRepository<Reading, Long> {
        List<Reading> findBySmartDevice_Client_Id(Long clientId);
    }

