package course.ensf607.assignment6.Movie;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "movie")
public class Movie {
    private static Movie instance;
    public static void setMovieInstance(Movie movie){
        instance = movie;
    }
    public static Movie getMovieInstance(){
        return instance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieid;

    @Column(name = "moviename")
    private String moviename;

    public Long getMovieid() {
        return movieid;
    }

    public void setMovieid(Long movieid) {
        this.movieid = movieid;
    }

    public String getMoviename() {
        return moviename;
    }

    public void setMoviename(String moviename) {
        this.moviename = moviename;
    }

 
    
    
}
