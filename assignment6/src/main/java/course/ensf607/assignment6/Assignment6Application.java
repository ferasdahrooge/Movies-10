package course.ensf607.assignment6;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import course.ensf607.assignment6.Movie.Movie;
import course.ensf607.assignment6.Screening.Screening;
import course.ensf607.assignment6.Seat.Seat;
import course.ensf607.assignment6.User.RegisteredUser;

@SpringBootApplication
public class Assignment6Application {

    public static void main(String[] args) {
        SpringApplication.run(Assignment6Application.class, args);
    }

    @Bean
    public Movie getMovie(){
        return new Movie();
    }

    @Bean
    public Seat getSeat(){
        return new Seat();
    }

    @Bean
    public Screening getScreening(){
        return new Screening();
    }

    @Bean
    public RegisteredUser getRegisteredUser(){
        return new RegisteredUser();
    }

    
    

}
 