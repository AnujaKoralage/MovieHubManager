package lk.ijse.dep.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardController {
    public Button btn_manageMovie;
    public Button btn_manageActor;
    public Button btn_registerActor;
    private Parent root = null;

    public void toMovieManage(ActionEvent actionEvent) {
        try {
            root = FXMLLoader.load(this.getClass().getResource("/Views/ManageActors.fxml"));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to load movie manage");
            alert.show();
            e.printStackTrace();
        }
        Scene mainScreen = new Scene(root);

        Stage stage = (Stage) btn_manageActor.getScene().getWindow();
        stage.setScene(mainScreen);
    }

    public void toManageActors(ActionEvent actionEvent) {
        try {
            root = FXMLLoader.load(this.getClass().getResource("/Views/ManageMovies.fxml"));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to load actor manage");
            alert.show();
            e.printStackTrace();
        }
        Scene mainScreen = new Scene(root);

        Stage stage = (Stage) btn_manageActor.getScene().getWindow();
        stage.setScene(mainScreen);
    }

    public void toRegistorActor(ActionEvent actionEvent) {
        try {
            root = FXMLLoader.load(this.getClass().getResource("/Views/RegisterActors.fxml"));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to load register");
            alert.show();
            e.printStackTrace();
        }
        Scene mainScreen = new Scene(root);

        Stage stage = (Stage) btn_manageActor.getScene().getWindow();
        stage.setScene(mainScreen);
    }
}
