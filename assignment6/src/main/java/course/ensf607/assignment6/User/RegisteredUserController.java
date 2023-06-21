package course.ensf607.assignment6.User;


import java.sql.Timestamp;
import java.util.Calendar;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import course.ensf607.assignment6.Payment.Payment;
import course.ensf607.assignment6.Payment.PaymentRepository;



@Controller
public class RegisteredUserController {
    private final RegisteredUserRepository registeredUserRepository;
    private final PaymentRepository paymentRepository;

    @Autowired
    public RegisteredUserController(RegisteredUserRepository registeredUserRepository, PaymentRepository paymentRepository) {
        this.registeredUserRepository = registeredUserRepository;
        this.paymentRepository = paymentRepository;
    }


    @GetMapping("/registerPage")
    public String registerPage() {
        return "register-user";
    }

    @PostMapping("/registerUser")
    public ModelAndView reserveSeat(@ModelAttribute(name="registeruser") RegisteredUser registeredUser, Model model){
     
        

        //Check to see if username already exists in database
        Optional<RegisteredUser> userCheck = registeredUserRepository.findByEmail(registeredUser.getEmail());
        if(userCheck.isPresent()){
            ModelAndView mav = new ModelAndView("register-user");
            model.addAttribute("userexists", "userexists");
            return mav;

        }
        //Create mav
        ModelAndView mav = new ModelAndView("login-page");

        model.addAttribute("registercomplete", "registration complete");
        Payment payment = new Payment(registeredUser.getFname(), registeredUser.getLname(), registeredUser.getCreditcardnumber(), registeredUser.getExpirydate(), registeredUser.getCvv(), 20);

        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
                
        Calendar cal = Calendar.getInstance();

        cal.setTimeInMillis(currentDate.getTime());
        cal.add(Calendar.MONTH, 12);
        Timestamp yearAfter = new Timestamp(cal.getTime().getTime());

        registeredUser.setDateregistered(currentDate);
        registeredUser.setNextpaymentdate(yearAfter);

        paymentRepository.save(payment);
        registeredUserRepository.save(registeredUser);


        //I want to pass mav a SeatsReserved object
  
        return mav;

    }

    @PostMapping("/renew")
    public ModelAndView renew(@RequestParam Long registereduserid, Model model){
        ModelAndView mav = new ModelAndView("login-page");
        RegisteredUser registeredUser = registeredUserRepository.findById(registereduserid).get();
        Payment payment = new Payment(registeredUser.getFname(), registeredUser.getLname(), registeredUser.getCreditcardnumber(), registeredUser.getExpirydate(), registeredUser.getCvv(), 20);
        
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(currentDate.getTime());
        cal.add(Calendar.MONTH, 12);
        Timestamp yearAfter = new Timestamp(cal.getTime().getTime());
        
        registeredUser.setNextpaymentdate(yearAfter);
        registeredUserRepository.save(registeredUser);
        paymentRepository.save(payment);

        model.addAttribute("renewsuccess", "renewsuccess");
        return mav;
    }


    
}
