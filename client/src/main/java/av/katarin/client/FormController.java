package av.katarin.client;

import javafx.beans.property.ReadOnlyBooleanWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import org.springframework.stereotype.Component;

import javax.print.Doc;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.ToLongBiFunction;

@Component
public class FormController {

    @FXML
    public TableView<Document> tableView;
    @FXML
    public TableColumn<Document, Boolean> isActive;
    @FXML
    public TableColumn<Document, LocalDateTime> updateDate;
    @FXML
    public TableColumn<Document, String> documentNumber;
    @FXML
    public TableColumn<Document, LocalDateTime> date;

    private final ObservableList<Document> documents = FXCollections.observableArrayList();
    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private final FXWebClient fxWebClient;

    private final Function<TableColumn<Document, LocalDateTime>, TableCell<Document, LocalDateTime>> frmt = x ->
            new TableCell<Document, LocalDateTime>() {
                @Override
                protected void updateItem(LocalDateTime item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setText(null);
                    } else {
                        setText(dateTimeFormatter.format(item));
                    }
                }
            };

    public FormController(FXWebClient fxWebClient) {
        this.fxWebClient = fxWebClient;
    }

    @FXML
    public void initialize() {
        documents.addAll(fxWebClient.getDocuments());
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        date.setCellFactory(frmt::apply);
        updateDate.setCellValueFactory(new PropertyValueFactory<>("updateDate"));
        updateDate.setCellFactory(frmt::apply);
        documentNumber.setCellValueFactory(new PropertyValueFactory<>("documentNumber"));
        isActive.setCellValueFactory(param -> new ReadOnlyBooleanWrapper(
                param.getValue().getUpdateDate().isBefore(LocalDateTime.now().minusDays(60))
        ));
        isActive.setCellFactory(param -> new CheckBoxTableCell<>());
        tableView.setItems(documents);
    }
}
