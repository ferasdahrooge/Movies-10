
package course.ensf607.assignment6.Seat;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import course.ensf607.assignment6.Auditorium.Auditorium;


@Entity
@Table(name = "seat")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatid;

    @Column(name = "seatrow")
    private String seatrow;

    @Column(name = "seatcol")
    private String seatcol;

    @ManyToOne
    @JoinColumn(name = "auditoriumid")
    private Auditorium auditorium;
    
    public Seat(){
        
    }

    public Seat(Auditorium auditorium){
        this.auditorium = auditorium;
    }
    

    public Long getSeatid() {
        return seatid;
    }

    public void setSeatid(Long seatid) {
        this.seatid = seatid;
    }

    public String getSeatrow() {
        return seatrow;
    }

    public void setSeatrow(String seatrow) {
        this.seatrow = seatrow;
    }

    public String getSeatcol() {
        return seatcol;
    }

    public void setSeatcol(String seatcol) {
        this.seatcol = seatcol;
    }

    public Auditorium getAuditorium(){
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium){
        this.auditorium = auditorium;
    }

  

    



    
}
