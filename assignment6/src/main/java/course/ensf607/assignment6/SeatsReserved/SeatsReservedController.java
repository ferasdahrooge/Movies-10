package course.ensf607.assignment6.SeatsReserved;


import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import course.ensf607.assignment6.Movie.MovieRepository;
import course.ensf607.assignment6.Payment.Payment;
import course.ensf607.assignment6.Payment.PaymentRepository;
import course.ensf607.assignment6.Screening.Screening;
import course.ensf607.assignment6.Screening.ScreeningRepository;
import course.ensf607.assignment6.Seat.Seat;
import course.ensf607.assignment6.Seat.SeatRepository;
import course.ensf607.assignment6.User.RegisteredUser;
import course.ensf607.assignment6.Voucher.Voucher;
import course.ensf607.assignment6.Voucher.VoucherRepository;


@Controller
public class SeatsReservedController {

    private final SeatsReservedRepository seatsReservedRepository;

    private final SeatRepository seatRepository;
    private final ScreeningRepository screeningRepository;
    private final MovieRepository movieRepository;
    private final PaymentRepository paymentRepository;
    private final VoucherRepository voucherRepository;

    //Calls the Seat bean (Singleton)
    @Autowired
    private Seat seat;
    
    //Calls the Screening bean (Singleton)
    @Autowired
    private Screening screening;
    
    //Calls the RegisteredUser bean (Singleton)
    @Autowired
    private RegisteredUser registeredUser;
    

    @Autowired
    public SeatsReservedController(SeatsReservedRepository seatsReservedRepository, SeatRepository seatRepository, ScreeningRepository screeningRepository,MovieRepository movieRepository, PaymentRepository paymentRepository, VoucherRepository voucherRepository) {
        this.seatsReservedRepository = seatsReservedRepository;
        this.seatRepository = seatRepository;
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
        this.paymentRepository = paymentRepository;
        this.voucherRepository = voucherRepository;
    }



    @PostMapping("/reserveSeat")
    public ModelAndView reserveSeat( @RequestParam Long screeningid, @RequestParam Long seatid, @RequestParam double price, @RequestParam(required = false) Long voucherid, @ModelAttribute(name="payment") Payment payment, Model model){
        //Create mav
        ModelAndView mav = new ModelAndView("ticket-page");

        //Voucher cancel
        if(voucherid != null){
            Voucher voucher = voucherRepository.findById(voucherid).get();
            voucherRepository.delete(voucher);
    
        }

        Payment payment2;
        //Check if signed in
        registeredUser = RegisteredUser.getMovieInstance();
        if(registeredUser != null){
            payment2 = new Payment(registeredUser.getFname(),registeredUser.getLname(),registeredUser.getCreditcardnumber(),registeredUser.getExpirydate(),registeredUser.getCvv(),price);
            mav.addObject("registereduser", registeredUser);
        }
        else{
            //If not signed in
            payment2 = new Payment(payment.getFname(),payment.getLname(),payment.getCreditcardnumber(),payment.getExpirydate(),payment.getCvv(),price);
        }

        //save payment to db
        payment2.setPrice(payment.getPrice());
        paymentRepository.save(payment2);
        seat = seatRepository.findById(seatid).get();
        screening = screeningRepository.findById(screeningid).get();
        SeatsReserved newSeatReserved = new SeatsReserved(seat, screening,new Timestamp(System.currentTimeMillis()));
        //save seatreserved(ticket) to db
        seatsReservedRepository.save(newSeatReserved);

        //I want to pass mav a SeatsReserved object
        mav.addObject("seatsReserved", newSeatReserved);
        mav.addObject("payment", payment2);

        return mav;

    }



    
}
