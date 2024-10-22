package careconnect.ui;

import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;

import careconnect.commons.core.LogsCenter;
import careconnect.model.log.Log;

/**
 * Panel containing the list of persons.
 */
public class LogListPanel extends UiPart<Region> {
    private static final String FXML = "LogListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(LogListPanel.class);

    @javafx.fxml.FXML
    private ListView<Log> logListView;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public LogListPanel(ArrayList<Log> logs) {
        super(FXML);
        logListView.setItems(FXCollections.observableList(logs));
        logListView.setCellFactory(cell -> new LogListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    class LogListViewCell extends ListCell<Log> {

        public LogListViewCell() {
        }

        @Override
        protected void updateItem(Log log, boolean empty) {
            super.updateItem(log, empty);

            if (empty || log == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new LogCard(log, getIndex() + 1).getRoot());
            }
        }
    }
}
