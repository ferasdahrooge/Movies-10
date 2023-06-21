package course.ensf607.assignment6.Seat;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import course.ensf607.assignment6.Screening.Screening;
import course.ensf607.assignment6.Screening.ScreeningRepository;
import course.ensf607.assignment6.User.RegisteredUser;

@Controller
public class SeatController {
    private final SeatRepository seatRepository;
    private final ScreeningRepository screeningRepository;
    //Calls the Screening bean (Singleton)
    @Autowired
    private Screening screening;
    //Calls the RegisteredUser bean (Singleton)
    @Autowired
    private RegisteredUser registeredUser;
    



    @Autowired
    public SeatController(SeatRepository seatRepository,ScreeningRepository screeningRepository) {
        this.seatRepository = seatRepository;
        this.screeningRepository = screeningRepository;
    }

    @GetMapping("/showOpenSeats")
    public ModelAndView showScreenings(@RequestParam Long screeningid){
        // ModelAndView mav = new ModelAndView("list-openseats");
        ModelAndView mav = new ModelAndView("list-openseats");
        //Movie movie = movieRepository.findById(movieid).get();
        List<Seat> listOfOpenSeats = seatRepository.findAllOpenSeats(screeningid);
        //names will be like 0_0 or 1_1, row_col etc.
        List<Long> listOfOpenSeatIds = new ArrayList<>();
        for(int i = 0; i < listOfOpenSeats.size(); i++){
            Long id = listOfOpenSeats.get(i).getSeatid();
            listOfOpenSeatIds.add(id);
        }

        List<Seat> listOfAllSeats = seatRepository.findAllByScreeningId(screeningid);
        //List<ArrayList<Seat>> matrixOfAllSeats = new ArrayList<ArrayList<Seat>>();
        Seat[][] matrixOfAllSeats = new Seat[5][5];
        // List<String> listOfAllSeatIds = new ArrayList<>();
        int index = 0;
        while(index < listOfAllSeats.size()){
            for(int row = 0; row < 5; row ++){

                for(int col = 0; col < 5; col ++){
                    matrixOfAllSeats[row][col] =listOfAllSeats.get(index); 
                    index++;
                }
            }
        }

        //Optional<Screening> screening = screeningRepository.findById(screeningid);
        screening = screeningRepository.findById(screeningid).get();
        //screening.setScreeningid(screeningid);
        mav.addObject("openSeats", listOfOpenSeats);
        mav.addObject("openSeatIds", listOfOpenSeatIds);
        mav.addObject("screening", screening);
        mav.addObject("matrixOfAllSeats", matrixOfAllSeats);


        registeredUser = RegisteredUser.getMovieInstance();
        if(registeredUser != null){
            mav.addObject("registereduser", registeredUser);
        }
        return mav;

    }




    
}
