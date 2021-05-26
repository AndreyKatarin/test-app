package av.katarin.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Component;

@Component
public class FormController {

    @FXML
    public TableView<Document> tableView;
    ObservableList<Document> documents = FXCollections.observableArrayList();
    private FXWebClient fxWebClient;

    public FormController(FXWebClient fxWebClient) {
        this.fxWebClient = fxWebClient;
    }

    @FXML
    public void initialize(){

    }
}
