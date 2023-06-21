package course.ensf607.assignment6.User;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredUserRepository extends JpaRepository<RegisteredUser, Long>{

    // Optional<Screening> findByMovieid(Long movieid);
    Optional<RegisteredUser> findByEmailAndPassword(String email, String password);
    Optional<RegisteredUser> findByEmail(String email);
    
}
