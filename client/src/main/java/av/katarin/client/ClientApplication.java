package av.katarin.client;

import javafx.application.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        Application.launch(FXClient.class, args);
    }

}
