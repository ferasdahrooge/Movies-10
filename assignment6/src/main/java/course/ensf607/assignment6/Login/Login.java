package course.ensf607.assignment6.Login;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Login {

    @Id
    private Long loginid;

    private String email;
    private String password;
    
    public Long getLoginid() {
        return loginid;
    }
    public void setLoginid(Long loginid) {
        this.loginid = loginid;
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

    


    
    
}
