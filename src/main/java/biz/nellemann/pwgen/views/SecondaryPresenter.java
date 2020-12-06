package biz.nellemann.pwgen.views;

import com.gluonhq.attach.browser.BrowserService;
import com.gluonhq.attach.util.Services;
import com.gluonhq.charm.glisten.afterburner.GluonPresenter;
import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import biz.nellemann.pwgen.PasswordApplication;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.URISyntaxException;

public class SecondaryPresenter extends GluonPresenter<PasswordApplication> {

    final String projectUrl = "https://github.com/mnellemann/pwgen";

    @FXML
    private View secondary;

    public void initialize() {
        secondary.setShowTransitionFactory(BounceInRightTransition::new);
        
        /*FloatingActionButton fab = new FloatingActionButton(MaterialDesignIcon.INFO.text,
                e -> System.out.println("Info"));
        fab.showOn(secondary);*/
        
        secondary.showingProperty().addListener((obs, oldValue, newValue) -> {
            if (newValue) {
                AppBar appBar = getApp().getAppBar();
                appBar.setNavIcon(MaterialDesignIcon.MENU.button(e -> 
                        getApp().getDrawer().open()));
                appBar.setTitleText("About");
                /*appBar.getActionItems().add(MaterialDesignIcon.FAVORITE.button(e ->
                        System.out.println("Favorite")));*/
            }
        });
    }


    @FXML
    void openLink() {
        Services.get(BrowserService.class).ifPresent(service -> {
            try {
                service.launchExternalBrowser(projectUrl);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        });
    }

}
