package course.ensf607.assignment6.Voucher;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import course.ensf607.assignment6.SeatsReserved.SeatsReserved;
import course.ensf607.assignment6.SeatsReserved.SeatsReservedRepository;
import course.ensf607.assignment6.User.RegisteredUser;


@Controller
public class VoucherController {
    private final VoucherRepository voucherRepository;
    private final SeatsReservedRepository seatsReservedRepository;
    //Calls the RegisteredUser bean (Singleton)
    @Autowired
    private RegisteredUser registeredUser;


    @Autowired
    public VoucherController(VoucherRepository voucherRepository, SeatsReservedRepository seatsReservedRepository) {
        this.voucherRepository = voucherRepository;
        this.seatsReservedRepository = seatsReservedRepository;
    }

    @GetMapping("/cancelPage")
    public ModelAndView showCancelPage() {
        ModelAndView mav = new ModelAndView("cancel-ticket-page");
        registeredUser = RegisteredUser.getMovieInstance();
        if(registeredUser != null){
            mav.addObject("registereduser", registeredUser);
        }
        return mav;
    }

    @PostMapping("/cancelPage")
    public ModelAndView cancel(@ModelAttribute(name="cancelForm") SeatsReserved seatsReserved, Model model){
        Long seatsReservedId = seatsReserved.getSeatsreservedid();
        Optional<SeatsReserved> seatsReserved2 = seatsReservedRepository.findById(seatsReservedId);
        ModelAndView mav = new ModelAndView("cancel-ticket-page");
        ModelAndView mav2 = new ModelAndView("voucher-page");

        registeredUser = RegisteredUser.getMovieInstance();
        if(registeredUser != null){
            mav.addObject("registereduser", registeredUser);
            mav2.addObject("registereduser", registeredUser);
        }

        if(seatsReserved2.isPresent()){
            Long time  = new Timestamp(System.currentTimeMillis()).getTime();
            Long reservedTime = seatsReserved2.get().getScreening().getTimestamp().getTime();
            Long subtractedTime = reservedTime - time;
  
            if(subtractedTime >= 259200000){
                
                seatsReservedRepository.delete(seatsReserved2.get()); 
                Timestamp currentDate = new Timestamp(System.currentTimeMillis());
                
                Calendar cal = Calendar.getInstance();

                cal.setTimeInMillis(currentDate.getTime());
                cal.add(Calendar.MONTH, 12);
                Timestamp yearAfter = new Timestamp(cal.getTime().getTime());

                double voucheramount = 0;
                if(registeredUser != null){
                    voucheramount = 10;
                }else{
                    voucheramount = 8.5;
                }
                Voucher voucher = new Voucher(voucheramount, currentDate, yearAfter);
                voucherRepository.save(voucher);
                mav2.addObject("voucher", voucher);
                return mav2;

            }else{
                
                model.addAttribute("error", "Invalid Ticket");
                return mav;
            }  
        }else{
            System.out.println("ID");
            model.addAttribute("error", "Invalid Ticket");
            return mav;
        }

        
        
    }


    
}
