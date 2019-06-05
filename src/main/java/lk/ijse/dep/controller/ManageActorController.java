package lk.ijse.dep.controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import lk.ijse.dep.AppInitializer;
import lk.ijse.dep.bo.ActorBO;
import lk.ijse.dep.dto.ActorDTO;
import lk.ijse.dep.entities.Actor;
import lk.ijse.dep.util.ActorTM;

import java.io.IOException;

public class ManageActorController {
    public TextField txt_actorId;
    public TextField txt_actorName;
    public Button btn_save;
    public Button btn_delete;
    public TableView<ActorTM> tbl_actor;
    public Button btn_update;
    public Button btn_back;
    public TextField txt_actorAge;
    private Parent root = null;

    ActorBO actorBO = AppInitializer.ctx.getBean(ActorBO.class);

    public void initialize(){
        txt_actorId.setDisable(true);

        tbl_actor.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tbl_actor.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));
        tbl_actor.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("age"));

        ObservableList<ActorTM> items = tbl_actor.getItems();

        try {
            for (ActorDTO actorDTO:actorBO.getAllActors()) {
                items.add(new ActorTM(actorDTO.getId(),actorDTO.getName(),actorDTO.getAge()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to load Actor, check your connection");
            alert.show();
        }

        btn_update.setVisible(false);
        btn_save.setVisible(true);
        btn_delete.setDisable(true);
        txt_actorId.setText(String.valueOf(actorBO.newId()));

        tbl_actor.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                btn_delete.setDisable(false);
                ActorTM selectedItem = tbl_actor.getSelectionModel().getSelectedItem();

                txt_actorId.setText(String.valueOf(selectedItem.getId()));
                txt_actorName.setText(selectedItem.getName());
                txt_actorAge.setText(String.valueOf(selectedItem.getAge()));

                btn_update.setVisible(true);
                btn_save.setVisible(false);
            }
        });

    }

    public void saveActor(ActionEvent actionEvent) {
        try {
            actorBO.saveActor(new ActorDTO(
                    Integer.parseInt(txt_actorId.getText()),
                    txt_actorName.getText(),
                    Integer.parseInt(txt_actorAge.getText())
            ));

            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Actor saved successfully");
            alert.show();

            tbl_actor.getItems().add(new ActorTM(
                    Integer.parseInt(txt_actorId.getText()),
                    txt_actorName.getText(),
                    Integer.parseInt(txt_actorAge.getText())
            ));

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to save actor");
            alert.show();
        }
    }

    public void deleteActor(ActionEvent actionEvent) {
        ActorTM selectedItem = tbl_actor.getSelectionModel().getSelectedItem();
        try {
            actorBO.deleteActor(new ActorDTO(selectedItem.getId(),selectedItem.getName(),selectedItem.getAge()));

            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Actor deleted successfully");
            alert.show();

            tbl_actor.getItems().remove(selectedItem);

            btn_save.setVisible(true);
            btn_update.setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to delete actor");
            alert.show();
        }
        tbl_actor.refresh();
    }

    public void updateActor(ActionEvent actionEvent) {

        int selectedIndex = tbl_actor.getSelectionModel().getSelectedIndex();
        try {
            actorBO.updateActor(new ActorDTO(
                    Integer.parseInt(txt_actorId.getText()),
                    txt_actorName.getText(),
                    Integer.parseInt(txt_actorAge.getText())
            ));

            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Actor updated successfully");
            alert.show();

            tbl_actor.getItems().get(selectedIndex).setName(txt_actorName.getText());
            tbl_actor.getItems().get(selectedIndex).setAge(Integer.parseInt(txt_actorAge.getText()));
            tbl_actor.refresh();
            btn_update.setVisible(false);
            btn_save.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to update actor");
            alert.show();
        }

    }

    public void toDashboard(ActionEvent actionEvent) {

        try {
            root = FXMLLoader.load(this.getClass().getResource("/Views/Dashboard.fxml"));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to load main dashboard");
            alert.show();
            e.printStackTrace();
        }
        Scene mainScreen = new Scene(root);

        Stage stage = (Stage) btn_back.getScene().getWindow();
        stage.setScene(mainScreen);

    }
}
