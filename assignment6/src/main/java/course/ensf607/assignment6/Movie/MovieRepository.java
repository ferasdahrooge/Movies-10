package course.ensf607.assignment6.Movie;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
    @Query(
        value = "SELECT * FROM movie WHERE advancedScreening = true", nativeQuery = true
    )
    List<Movie> findAllAdvancedScreeningMovies();    

    @Query(
        value = "SELECT * FROM movie WHERE advancedScreening = false", nativeQuery = true
    )
    List<Movie> findAllOrdinaryScreeningMovies();  

    @Query(
        value = "SELECT * FROM movie WHERE advancedScreening = false AND moviename LIKE :moviename% ", nativeQuery = true
    )
    List<Movie> findAllOrdinaryMoviesByMovieName(@Param("moviename") String moviename);   
    
    @Query(
        value = "SELECT * FROM movie WHERE advancedScreening = true AND moviename LIKE :moviename% ", nativeQuery = true
    )
    List<Movie> findAllAdvancedMoviesByMovieName(@Param("moviename") String moviename);   

}
