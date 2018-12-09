package model;

import java.io.IOException;
import java.util.Properties;

public class TestBot {
    private final String login;
    private final String password;

    public TestBot() throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getResourceAsStream("../ok.properties"));
        login = properties.getProperty("login");
        password = properties.getProperty("password");
    }

    public TestBot(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
