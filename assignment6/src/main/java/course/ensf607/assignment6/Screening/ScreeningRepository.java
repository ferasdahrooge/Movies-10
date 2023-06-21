package course.ensf607.assignment6.Screening;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import course.ensf607.assignment6.Movie.Movie;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long>{
    
    // Optional<Screening> findByMovieid(Long movieid);
    // List<Screening> findAllByMovieid(Long movieid); 
    List<Screening> findAllByMovie(Movie movie);       
}
