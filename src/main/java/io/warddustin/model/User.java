package io.warddustin.model;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by dward on 3/25/17.
 */
public class User {
    @NotNull
    @Size(min = 5, max = 15, message = "Your username must be between 5 and 15 characters or numbers")
    private String username;

    @Email
    private String email;

    @NotNull
    @Size(min = 8, message = "Your password must be at least 8 characters, numbers or symbols!")
    private String password;

    @NotNull(message = "Passwords must match!")
    private String verify;

    public User(){

    }

    public User(String username, String email, String password){
        this.email = email;
        this.password = password;
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
        this.verifyPassword(this.verify, this.password);
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
        this.verifyPassword(this.verify, this.password);
    }

    public void verifyPassword(String password, String verify){
        if (password==null || verify==null || !password.equals(verify)){
            this.verify = null;
        }
    }
}
