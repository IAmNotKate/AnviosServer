package restmodels.requests;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class LoginRequest {
    private String username;
    private String password;

    @JsonGetter("username")
    public String getUsername() {
        return username;
    }

    @JsonSetter("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonGetter("password")
    public String getPassword() {
        return password;
    }

    @JsonSetter("password")
    public void setPassword(String password) {
        this.password = new String(password);
    }
}

