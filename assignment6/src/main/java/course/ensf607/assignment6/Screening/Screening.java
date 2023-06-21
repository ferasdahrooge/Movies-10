
package course.ensf607.assignment6.Screening;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import course.ensf607.assignment6.Auditorium.Auditorium;
import course.ensf607.assignment6.Movie.Movie;




@Entity
@Table(name = "screening")
public class Screening {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long screeningid;

    @Column(name = "timestamp")
    private Timestamp timestamp;

    @ManyToOne
    @JoinColumn(name = "movieid")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "auditoriumid")
    private Auditorium auditorium;

    public Screening(Movie movie, Auditorium auditorium){
        this.movie = movie;
        this.auditorium = auditorium;
    }

    public Screening(){
        
    }




    public Long getScreeningid() {
        return screeningid;
    }

    public void setScreeningid(Long screeningid) {
        this.screeningid = screeningid;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    // public Long getMovieid() {
    //     return movieid;
    // }

    // public void setMovieid(Long movieid) {
    //     this.movieid = movieid;
    // }
    public Movie getMovie(){
        return movie;
    }
    public void setMovie(Movie movie){
        this.movie = movie;
    }

    public Auditorium getAuditorium(){
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium){
        this.auditorium = auditorium;
    }

  


    
    
}
