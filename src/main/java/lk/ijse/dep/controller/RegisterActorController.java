package lk.ijse.dep.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import lk.ijse.dep.AppInitializer;
import lk.ijse.dep.bo.ActorBO;
import lk.ijse.dep.bo.ActorRegisterBO;
import lk.ijse.dep.bo.MovieBO;
import lk.ijse.dep.dto.ActorDTO;
import lk.ijse.dep.dto.ActorRegistrationDTO;
import lk.ijse.dep.dto.MovieDTO;
import lk.ijse.dep.entities.ActorRegistration;

import java.io.IOException;

public class RegisterActorController {
    public ComboBox cmb_movieId;
    public TextField txt_movieName;
    public ComboBox cmb_actorId;
    public TextField txt_actorName;
    public TextField txt_actorRole;
    public Button btn_registerActor;
    public Button btn_back;
    private Parent root =null;

    ActorBO actorBO = AppInitializer.ctx.getBean(ActorBO.class);
    MovieBO movieBO = AppInitializer.ctx.getBean(MovieBO.class);
    ActorRegisterBO actorRegisterBO = AppInitializer.ctx.getBean(ActorRegisterBO.class);
    int id=0;

    public void initialize(){
        txt_actorName.setDisable(true);
        txt_movieName.setDisable(true);
        id = actorRegisterBO.getID();

        try {
            for (ActorDTO actorDTO:actorBO.getAllActors()) {
                cmb_actorId.getItems().add(actorDTO.getId());
            }

            for (MovieDTO actorDTO:movieBO.getAllMovie()) {
                cmb_movieId.getItems().add(actorDTO.getId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to load actors");
            alert.show();
        }

        cmb_movieId.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                txt_movieName.setText(movieBO.getMoviebyId(Integer.parseInt(cmb_movieId.getSelectionModel().getSelectedItem().toString())));
            }
        });

        cmb_actorId.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                txt_actorName.setText(actorBO.getActorName(Integer.parseInt(cmb_actorId.getSelectionModel().getSelectedItem().toString())));
            }
        });
    }

    public void registerActor(ActionEvent actionEvent) {

        boolean k = true;
        for (ActorRegistrationDTO actorRegistration:actorRegisterBO.getAllRegs()) {
            if (actorRegistration.getRole().equals(txt_actorName.getText()) && actorRegistration.getActorid()==Integer.parseInt(String.valueOf(cmb_actorId.getSelectionModel().getSelectedItem())) && actorRegistration.getMovieID()==Integer.parseInt(cmb_movieId.getSelectionModel().getSelectedItem().toString())){
                k=false;
            }
        }

        if (k){
            try {
                actorRegisterBO.makeRegistration(new ActorRegistrationDTO(id,
                        txt_actorRole.getText(),
                        Integer.parseInt(cmb_movieId.getSelectionModel().getSelectedItem().toString()),
                        Integer.parseInt(cmb_actorId.getSelectionModel().getSelectedItem().toString())));

                Alert alert =new Alert(Alert.AlertType.INFORMATION,"Registration completed");
                alert.show();

            } catch (Exception e) {
                e.printStackTrace();
                Alert alert =new Alert(Alert.AlertType.ERROR,"Unable to register");
                alert.show();
            }
        }
        else {
            Alert alert =new Alert(Alert.AlertType.ERROR,"Unable to register");
            alert.show();
        }

    }

    public void toDashboard(ActionEvent actionEvent) {

        try {
            root = FXMLLoader.load(this.getClass().getResource("/Views/Dashboard.fxml"));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to load main dashboard!!");
            alert.show();
            e.printStackTrace();
        }
        Scene mainScreen = new Scene(root);

        Stage stage = (Stage) btn_back.getScene().getWindow();
        stage.setScene(mainScreen);

    }
}
