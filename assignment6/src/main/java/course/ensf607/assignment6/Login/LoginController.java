package course.ensf607.assignment6.Login;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.servlet.ModelAndView;

import course.ensf607.assignment6.Movie.Movie;
import course.ensf607.assignment6.Movie.MovieRepository;
import course.ensf607.assignment6.User.RegisteredUser;
import course.ensf607.assignment6.User.RegisteredUserRepository;

@Controller
public class LoginController {
    private final RegisteredUserRepository registeredUserRepository;
    private final MovieRepository movieRepository;

    //Calls the RegisteredUser bean (Singleton)
    @Autowired
    private RegisteredUser registeredUser;

    @Autowired
    public LoginController(RegisteredUserRepository registeredUserRepository, MovieRepository movieRepository) {
        this.registeredUserRepository = registeredUserRepository;
        this.movieRepository = movieRepository;
    }

    @GetMapping("/welcome")
    public ModelAndView showWelcomePage(Model model) {
        ModelAndView mav = new ModelAndView("welcome");
        registeredUser = RegisteredUser.getMovieInstance();
        if(registeredUser != null){
            mav.addObject("registereduser", registeredUser);
        }
        return mav;
    }

    @GetMapping("/loginPage")
    public String showLogin() {
        return "login-page";
    }

    @GetMapping("/logout")
    public String logout(){
        RegisteredUser.setMovieInstance(null);
        return "redirect:/showMovies";
    }


    @PostMapping("/loginPage")
    public ModelAndView login(@ModelAttribute(name="loginForm") Login login, Model model){
        String email = login.getEmail();
        String password = login.getPassword();
        Optional<RegisteredUser> registeredUserOptional = registeredUserRepository.findByEmailAndPassword(email, password);
    
        if(registeredUserOptional.isPresent()){
            registeredUser = registeredUserOptional.get();
            
            //If Registered user needs to pay their yearly fee of $20
            Timestamp currentDate = new Timestamp(System.currentTimeMillis());

            //If nextpaymentdate is already past, show payment page
            if(registeredUser.getNextpaymentdate().compareTo(currentDate) < 0){
                ModelAndView mav = new ModelAndView("annual-payment-page");
                mav.addObject("registereduser", registeredUser);
                return mav;
            }

           
       
            ModelAndView mav = new ModelAndView("list-movies");
            RegisteredUser.setMovieInstance(registeredUser);
            mav.addObject("registereduser", registeredUser);
            List<Movie> listOfMovies = movieRepository.findAllOrdinaryScreeningMovies();
            List<Movie> listOfAdvancedMovies = movieRepository.findAllAdvancedScreeningMovies();
            mav.addObject("movies", listOfMovies);
            mav.addObject("advancedMovies", listOfAdvancedMovies);

            return mav;

        }
        else{
            model.addAttribute("error", "Incorrect email and password");
            ModelAndView mav = new ModelAndView("login-page");
            return mav;
        }
        
        
    }

    
}
