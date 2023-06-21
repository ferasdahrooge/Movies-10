package course.ensf607.assignment6.Seat;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface SeatRepository extends JpaRepository<Seat, Long>{

    @Query(
    value = "SELECT * FROM seat WHERE seatId not in (SELECT seatId FROM seatsreserved where screeningId = :screeningid) AND auditoriumId IN (Select auditoriumId FROM screening WHERE screeningId = :screeningid);", nativeQuery = true)
    List<Seat>  findAllOpenSeats(@Param("screeningid") Long screeningid);
    //gives screeningId 

    @Query(
        value = "SELECT * FROM seat WHERE auditoriumId IN (Select auditoriumId FROM screening WHERE screeningId = :screeningid);", nativeQuery = true
    )
    List<Seat> findAllByScreeningId(@Param("screeningid") Long screeningid);
    
}
