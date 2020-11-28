package biz.nellemann.pwgen.views;

import biz.nellemann.libpw.PasswordGenerator;
import com.gluonhq.charm.glisten.afterburner.GluonPresenter;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import biz.nellemann.pwgen.PasswordApplication;
import java.util.ResourceBundle;

import javafx.beans.property.*;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public class PrimaryPresenter extends GluonPresenter<PasswordApplication> {

    @FXML
    public CheckBox checkCapitals;

    @FXML
    public CheckBox checkNumbers;

    @FXML
    public CheckBox checkSymbols;

    @FXML
    public Spinner<Integer> spinnerLength;

    @FXML
    private View primary;

    @FXML
    private TextField fieldResult;

    @FXML
    private ResourceBundle resources;


    private final StringProperty passwordValueProperty = new SimpleStringProperty();

    private final ObjectProperty<Integer> objectProp = new SimpleObjectProperty<>(12);
    private final IntegerProperty passwordLengthProperty = IntegerProperty.integerProperty(objectProp);

    private final BooleanProperty checkCapitalsProperty = new SimpleBooleanProperty(false);
    private final BooleanProperty checkNumbersProperty = new SimpleBooleanProperty(false);
    private final BooleanProperty checkSymbolsProperty = new SimpleBooleanProperty(false);

    final Clipboard clipboard = Clipboard.getSystemClipboard();
    final ClipboardContent content = new ClipboardContent();

    public void initialize() {
        checkCapitalsProperty.bindBidirectional(checkCapitals.selectedProperty());
        checkNumbersProperty.bindBidirectional(checkNumbers.selectedProperty());
        checkSymbolsProperty.bindBidirectional(checkSymbols.selectedProperty());
        passwordValueProperty.bindBidirectional(fieldResult.textProperty());
        spinnerLength.getValueFactory().valueProperty().bindBidirectional(objectProp);

        primary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = getApp().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        getApp().getDrawer().open()));
                appBar.setTitleText("Passwords");
                /*appBar.getActionItems().add(MaterialDesignIcon.SEARCH.button(e ->
                        System.out.println("Search")));*/
            }
        });

    }
    
    @FXML
    void buttonClick() {

        passwordValueProperty.setValue(
                PasswordGenerator.random(
                        passwordLengthProperty.getValue(),
                        checkCapitalsProperty.getValue(),
                        checkNumbersProperty.getValue(),
                        checkSymbolsProperty.getValue()
                )
        );

    }

    @FXML
    void buttonCopy() {
        content.putString(passwordValueProperty.getValue());
        clipboard.setContent(content);
    }

}
