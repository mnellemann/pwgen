package biz.nellemann.pwgen.views;

import com.gluonhq.charm.glisten.afterburner.GluonPresenter;
import com.gluonhq.charm.glisten.animation.BounceInRightTransition;
import com.gluonhq.charm.glisten.control.AppBar;
import com.gluonhq.charm.glisten.control.FloatingActionButton;
import com.gluonhq.charm.glisten.mvc.View;
import com.gluonhq.charm.glisten.visual.MaterialDesignIcon;
import biz.nellemann.pwgen.PasswordApplication;
import javafx.fxml.FXML;

public class SecondaryPresenter extends GluonPresenter<PasswordApplication> {

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
}
