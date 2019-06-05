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
import lk.ijse.dep.bo.MovieBO;
import lk.ijse.dep.dto.MovieDTO;
import lk.ijse.dep.util.MovieTM;

import java.io.IOException;

public class ManageMovieController {
    public TextField txt_movieId;
    public TextField txt_movieName;
    public Button btn_save;
    public Button btn_delete;
    public Button btn_update;
    public TableView<MovieTM> tbl_movie;
    public Button btn_back;
    private Parent root=null;

    MovieBO movieBO = AppInitializer.ctx.getBean(MovieBO.class);

    public void initialize(){

        txt_movieId.setText(String.valueOf(movieBO.getNewId()));

        txt_movieId.setDisable(true);

        tbl_movie.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("id"));
        tbl_movie.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("name"));

        ObservableList<MovieTM> items = tbl_movie.getItems();

        try {
            for (MovieDTO movieDTO:movieBO.getAllMovie()) {
                items.add(new MovieTM(movieDTO.getId(),movieDTO.getName()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to load movies");
            alert.show();
        }

        btn_save.setVisible(true);
        btn_update.setVisible(false);
        btn_delete.setDisable(true);

        tbl_movie.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                btn_save.setVisible(false);
                btn_update.setVisible(true);
                btn_delete.setDisable(false);

                MovieTM selectedItem = tbl_movie.getSelectionModel().getSelectedItem();
                txt_movieId.setText(String.valueOf(selectedItem.getId()));
                txt_movieName.setText(selectedItem.getName());
            }
        });
    }

    public void saveMovie(ActionEvent actionEvent) {

        try {
            movieBO.saveMovie(new MovieDTO(
                    Integer.parseInt(txt_movieId.getText()),
                    txt_movieName.getText()
            ));
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Unable to save movie");
            alert.show();

            tbl_movie.getItems().add(new MovieTM(
                    Integer.parseInt(txt_movieId.getText()),
                    txt_movieName.getText()
            ));

        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to save movie");
            alert.show();
        }

    }

    public void deleteMovie(ActionEvent actionEvent) {

        try {
            movieBO.deleteMovie(new MovieDTO(
                    tbl_movie.getSelectionModel().getSelectedItem().getId(),
                    tbl_movie.getSelectionModel().getSelectedItem().getName()
            ));

            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Movie successfully deleted");
            alert.show();

            tbl_movie.getItems().remove(new MovieTM(
                    tbl_movie.getSelectionModel().getSelectedItem().getId(),
                    tbl_movie.getSelectionModel().getSelectedItem().getName()
            ));
            btn_delete.setDisable(true);
            btn_update.setVisible(false);
            btn_save.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to delete movie");
            alert.show();
        }

    }

    public void updateMovie(ActionEvent actionEvent) {

        try {
            movieBO.saveMovie(new MovieDTO(
                    Integer.parseInt(txt_movieId.getText()),
                    txt_movieName.getText()
            ));
            Alert alert = new Alert(Alert.AlertType.ERROR,"Movie successfully updated");
            alert.show();

            tbl_movie.getSelectionModel().getSelectedItem().setId(Integer.parseInt(txt_movieId.getText()));
            tbl_movie.getSelectionModel().getSelectedItem().setName(txt_movieName.getText());

            tbl_movie.refresh();
        } catch (Exception e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to update movie");
            alert.show();
        }

    }

    public void toDashboard(ActionEvent actionEvent) {

        try {
            root = FXMLLoader.load(this.getClass().getResource("/Views/Dashboard.fxml"));
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR,"Unable to load maindashboard");
            alert.show();
            e.printStackTrace();
        }
        Scene mainScreen = new Scene(root);

        Stage stage = (Stage) btn_back.getScene().getWindow();
        stage.setScene(mainScreen);

    }
}
