package av.katarin.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;


public class WebClient {

    public ObservableList<Document> getDocuments(){
        String url = "http://localhost:8080/documents";
        RestTemplate restTemplate = new RestTemplate();
        return FXCollections.observableArrayList();
    }
}
