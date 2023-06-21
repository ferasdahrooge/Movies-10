package course.ensf607.assignment6.Payment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import course.ensf607.assignment6.Movie.Movie;
import course.ensf607.assignment6.Screening.Screening;
import course.ensf607.assignment6.Screening.ScreeningRepository;
import course.ensf607.assignment6.Seat.Seat;
import course.ensf607.assignment6.Seat.SeatRepository;
import course.ensf607.assignment6.User.RegisteredUser;
import course.ensf607.assignment6.Voucher.Voucher;
import course.ensf607.assignment6.Voucher.VoucherRepository;

import java.sql.Timestamp;
import java.util.Optional;

@Controller
public class PaymentController {
    
    private final PaymentRepository paymentRepository;
    private final ScreeningRepository screeningRepository;
    private final SeatRepository seatRepository;
    private final VoucherRepository voucherRepository;
    //Calls the Movie bean (Singleton)
    @Autowired
    private Movie movie;
    //Calls the RegisteredUser bean (Singleton)
    @Autowired
    private RegisteredUser registeredUser;
    

    @Autowired
    public PaymentController(PaymentRepository paymentRepository,ScreeningRepository screeningRepository,SeatRepository seatRepository, VoucherRepository voucherRepository) {
        this.paymentRepository = paymentRepository;
        this.screeningRepository = screeningRepository;
        this.seatRepository = seatRepository;
        this.voucherRepository = voucherRepository;
    }

    @GetMapping("/inputPaymentInfo")
    public ModelAndView inputPaymentInfo(@RequestParam Long screeningid, @RequestParam Long seatid){
        ModelAndView mav = new ModelAndView("payment-page");
        // if(voucher.getVoucherid() != null){
        //     Optional<Voucher> voucherCheck = voucherRepository.findById(voucher.getVoucherid());
        //     if(voucherCheck.isPresent()){
        //         mav.addObject("voucher", voucherCheck);
        //         model.addAttribute("vouchervalid", "vouchervalid");
        //     }else{
        //         model.addAttribute("vouchererror", "vouchererror");
        //     }

        // }

        Screening screening = screeningRepository.findById(screeningid).get();
        //screening.setScreeningid(screeningid);
        Seat seat = new Seat();
        seat.setSeatid(seatid);
        Payment payment = new Payment();
        movie = Movie.getMovieInstance();
        mav.addObject("payment", payment);
        mav.addObject("screening", screening);
        mav.addObject("seat", seat);
        mav.addObject("movie", movie);

        registeredUser = RegisteredUser.getMovieInstance();
        if(registeredUser != null){
            mav.addObject("registereduser", registeredUser);
        }

        return mav;

    }


    @PostMapping("/inputPaymentInfo")
    public ModelAndView inputPaymentInfoVoucher(@RequestParam Long screeningid, @RequestParam Long seatid, @ModelAttribute(name="voucher") Voucher voucher, Model model){
        ModelAndView mav = new ModelAndView("payment-page");
        

        Screening screening = screeningRepository.findById(screeningid).get();
        //screening.setScreeningid(screeningid);
        Seat seat = new Seat();
        seat.setSeatid(seatid);
        Payment payment = new Payment();
        //payment.setPrice(10.0);
        movie = Movie.getMovieInstance();

        if(voucher.getVoucherid() != null){
            Optional<Voucher> voucherCheck = voucherRepository.findById(voucher.getVoucherid());
            if(voucherCheck.isPresent()){
                //if present, check if currentdate is passed expiry date of voucher
                Timestamp today = new Timestamp(System.currentTimeMillis());
                Timestamp expiry = voucherCheck.get().getExpirydate();
                int compare = expiry.compareTo(today); //if compare > 0 expiry is greater
                if(compare > 0){
                    mav.addObject("voucher", voucherCheck.get());
                    model.addAttribute("vouchervalid", "vouchervalid");
                    payment.setPrice(payment.getPrice() - voucherCheck.get().getRefundamount());
                }else{
                    //Passed expiry date
                    model.addAttribute("vouchererror", "vouchererror");
                }

            }else{
                model.addAttribute("vouchererror", "vouchererror");
            }

        }

        mav.addObject("payment", payment);
        mav.addObject("screening", screening);
        mav.addObject("seat", seat);
        mav.addObject("movie", movie);

        registeredUser = RegisteredUser.getMovieInstance();
        if(registeredUser != null){
            mav.addObject("registereduser", registeredUser);
        }

        return mav;

    }




    
}
