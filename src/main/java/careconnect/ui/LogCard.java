package careconnect.ui;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.function.Consumer;

import careconnect.model.log.Log;
import careconnect.model.person.Person;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class LogCard extends UiPart<Region> {

    private static final String FXML = "LogCard.fxml";
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy HH:mm");

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Log log;

    @FXML
    private HBox cardPane;
    @FXML
    private Label date;
    @FXML
    private Label id;
    @FXML
    private Label remark;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public LogCard(Log log, int displayedIndex) {
        super(FXML);
        this.log = log;
        id.setText(displayedIndex + ". ");
        date.setText(simpleDateFormat.format(log.getDate()));
        remark.setText(log.getRemark());
    }
}
