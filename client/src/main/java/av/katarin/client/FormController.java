package av.katarin.client;

import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import org.springframework.stereotype.Component;

@Component
public class FormController {

    @FXML
    TableView<Document> tableView;

}
