package course.ensf607.assignment6.SeatsReserved;


import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import course.ensf607.assignment6.Screening.Screening;
import course.ensf607.assignment6.Seat.Seat;


@Entity
@Table(name = "seatsreserved")
public class SeatsReserved {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long seatsreservedid;

    @ManyToOne
    @JoinColumn(name = "seatid")
    private Seat seat;

    
    @ManyToOne
    @JoinColumn(name = "screeningid")
    private Screening screening;

    @Column(name ="datepurchased")
    private Timestamp datepurchased;


    public SeatsReserved(){
        
    }

    public SeatsReserved(Seat seat, Screening screening){
        this.seat = seat;
        this.screening = screening;
    }
    public SeatsReserved(Seat seat, Screening screening, Timestamp datepurchased){

        this.seat = seat;
        this.screening = screening;
        this.datepurchased = datepurchased;
    }

    public Long getSeatsreservedid() {
        return seatsreservedid;
    }

    public void setSeatsreservedid(Long seatsreservedid) {
        this.seatsreservedid = seatsreservedid;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

    public Timestamp getDatepurchased() {
        return datepurchased;
    }

    public void setDatepurchased(Timestamp datepurchased) {
        this.datepurchased = datepurchased;
    }
    


    //GETTERS AND SETTERS
    

    
}
