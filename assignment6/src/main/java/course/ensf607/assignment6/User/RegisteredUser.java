package course.ensf607.assignment6.User;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;






@Entity
@Table(name = "registereduser")
public class RegisteredUser {

    private static RegisteredUser instance;
    public static void setMovieInstance(RegisteredUser registeredUser){
        instance = registeredUser;
    }
    public static RegisteredUser getMovieInstance(){
        if(instance == null){
            return null;
        }
        return instance;
    }  

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long registereduserid;

    @Column(name="email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "address")
    private String address;

    @Column(name = "fname")
    private String fname;

    @Column(name = "lname")
    private String lname;

    @Column(name = "creditcardnumber")
    private String creditcardnumber;

    @Column(name = "expirydate")
    private String expirydate;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "dateregistered")
    private Timestamp dateregistered;

    @Column(name = "nextpaymentdate")
    private Timestamp nextpaymentdate;

    public Long getRegistereduserid() {
        return registereduserid;
    }

    public void setRegistereduserid(Long registereduserid) {
        this.registereduserid = registereduserid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getCreditcardnumber() {
        return creditcardnumber;
    }

    public void setCreditcardnumber(String creditcardnumber) {
        this.creditcardnumber = creditcardnumber;
    }

    public String getExpirydate() {
        return expirydate;
    }

    public void setExpirydate(String expirydate) {
        this.expirydate = expirydate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public Timestamp getDateregistered() {
        return dateregistered;
    }
    public void setDateregistered(Timestamp dateregistered) {
        this.dateregistered = dateregistered;
    }
    public Timestamp getNextpaymentdate() {
        return nextpaymentdate;
    }
    public void setNextpaymentdate(Timestamp nextpaymentdate) {
        this.nextpaymentdate = nextpaymentdate;
    }

    






    
}


