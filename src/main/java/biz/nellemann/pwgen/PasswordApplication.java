package biz.nellemann.pwgen;

import biz.nellemann.pwgen.views.AppViewManager;
import com.gluonhq.charm.glisten.application.MobileApplication;
import com.gluonhq.charm.glisten.visual.Swatch;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class PasswordApplication extends MobileApplication {

    @Override
    public void init() {
        AppViewManager.registerViewsAndDrawer(this);
    }

    @Override
    public void postInit(Scene scene) {
        Swatch.BLUE.assignTo(scene);

        scene.getStylesheets().add(PasswordApplication.class.getResource("style.css").toExternalForm());
        ((Stage) scene.getWindow()).getIcons().add(new Image(PasswordApplication.class.getResourceAsStream("/icon.png")));
    }

    public static void main(String args[]) {
        launch(args);
    }
}
