package course.ensf607.assignment6.Auditorium;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "auditorium")
public class Auditorium {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long auditoriumid;

    public Long getAuditoriumid() {
        return auditoriumid;
    }

    public void setAuditoriumid(Long auditoriumid) {
        this.auditoriumid = auditoriumid;
    }

    

    
}
