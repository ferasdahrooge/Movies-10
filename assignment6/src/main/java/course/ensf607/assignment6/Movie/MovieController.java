package course.ensf607.assignment6.Movie;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

import course.ensf607.assignment6.User.RegisteredUser;

@Controller
public class MovieController {
    private final MovieRepository movieRepository;
    //Calls the RegisteredUser bean (Singleton)
    @Autowired
    private RegisteredUser registeredUser;

    @Autowired
    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    // @GetMapping("/index")
    // public String getMovies(Model model) {
    //     List<Movie> listOfMovies = movieRepository.findAll();
    //     model.addAttribute("movies", listOfMovies);
    //     return "index";
       
    // }

    // @GetMapping({"/showMovies", "/" , "/list"})
    @GetMapping("/showMovies")
    public ModelAndView getMovies(@ModelAttribute(name="movie") Movie movie) {
        //this is the html file name
        ModelAndView mav = new ModelAndView("list-movies");

        if(movie != null){
            List<Movie> listOfMovies = movieRepository.findAllOrdinaryMoviesByMovieName(movie.getMoviename());
            List<Movie> listOfAdvancedMovies = movieRepository.findAllAdvancedMoviesByMovieName(movie.getMoviename());
            registeredUser = RegisteredUser.getMovieInstance();
            if(registeredUser != null){
                mav.addObject("registereduser", registeredUser);
            }
            mav.addObject("movies", listOfMovies);
            mav.addObject("advancedMovies", listOfAdvancedMovies);
            return mav;

        }
        
        List<Movie> listOfMovies = movieRepository.findAllOrdinaryScreeningMovies();
        List<Movie> listOfAdvancedMovies = movieRepository.findAllAdvancedScreeningMovies();
        //addObject(key,value)
        //this key will be accessed in the html
        registeredUser = RegisteredUser.getMovieInstance();
        if(registeredUser != null){
            mav.addObject("registereduser", registeredUser);
        }
        mav.addObject("movies", listOfMovies);
        mav.addObject("advancedMovies", listOfAdvancedMovies);
        return mav;
       
    }


    
}
