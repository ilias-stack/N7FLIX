package ma.enset.n7flix.dao.entities;

import java.time.LocalDate;
import java.util.Date;

public class User {
    private Integer id;
    private String username,email,password;
    private String birthDay;

    public User(Integer id, String username, String email, String password, String birthDay) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.birthDay = birthDay;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", birthDay=" + birthDay +
                '}';
    }

    public Integer getId() {
        return id;
    }


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }


    public String getBirthDay() {
        return birthDay;
    }

}
