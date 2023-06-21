package course.ensf607.assignment6.SeatsReserved;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatsReservedRepository extends JpaRepository<SeatsReserved, Long>{
    
}
