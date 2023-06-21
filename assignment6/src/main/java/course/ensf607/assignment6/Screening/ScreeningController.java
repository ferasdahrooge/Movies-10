package course.ensf607.assignment6.Screening;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import course.ensf607.assignment6.Movie.Movie;
import course.ensf607.assignment6.Movie.MovieRepository;
import course.ensf607.assignment6.User.RegisteredUser;

import java.util.List;

@Controller
public class ScreeningController {
    private final ScreeningRepository screeningRepository;
    private final MovieRepository movieRepository;

    //Calls the Movie bean (Singleton)
    @Autowired
    private Movie movie;
    //Calls the RegisteredUser bean (Singleton)
    @Autowired
    private RegisteredUser registeredUser;
    

    @Autowired
    public ScreeningController(ScreeningRepository screeningRepository, MovieRepository movieRepository) {
        this.screeningRepository = screeningRepository;
        this.movieRepository = movieRepository;
    }
    

    @GetMapping("/showScreenings")
    public ModelAndView showScreenings(@RequestParam Long movieid){
        ModelAndView mav = new ModelAndView("list-screenings");
        //Movie movie = movieRepository.findById(movieid).get();
        movie = movieRepository.findById(movieid).get();
        Movie.setMovieInstance(movie);
        List<Screening> listOfScreenings = screeningRepository.findAllByMovie(movie);
        mav.addObject("screenings", listOfScreenings);
        registeredUser = RegisteredUser.getMovieInstance();
        if(registeredUser != null){
            mav.addObject("registereduser", registeredUser);
        }
        return mav;


    }
    
}
