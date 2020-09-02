/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package restaurantes;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import java.lang.Integer;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class MainMeanController implements Initializable {

    @FXML
    TextField numMeals;
    @FXML
    TextField numDranks;
    /*----------------------------------------*/
    @FXML
    Pane Darnks;
    @FXML
    Pane Meals;
    /*----------------------------------------*/
    @FXML
    ComboBox typeD;
    @FXML
    ComboBox typeM;
    /*----------------------------------------*/
    @FXML
    TextField numM;
    @FXML
    TextField nameM;
    @FXML
    TextField costM;
    @FXML
    Label doneM;
    /*----------------------------------------*/
    @FXML
    TableView<Meals> tableM;
    @FXML
    TableColumn<Meals, Integer> numTM;
    @FXML
    TableColumn<Meals, String> nameTM;
    @FXML
    TableColumn<Meals, String> typeTM;
    @FXML
    TableColumn<Meals, Integer> costTM;
    /*----------------------------------------*/
    @FXML
    TableView<Dranks> tableD;
    @FXML
    TableColumn<Dranks, Integer> numTD;
    @FXML
    TableColumn<Dranks, String> nameTD;
    @FXML
    TableColumn<Dranks, String> typeTD;
    @FXML
    TableColumn<Dranks, Integer> costTD;
    /*----------------------------------------*/
    @FXML
    TextField numD;
    @FXML
    TextField nameD;
    @FXML
    TextField costD;
    @FXML
    Label doneD;
    @FXML
    TextField searchM;
    TextField searchD;

    ObservableList<Meals> listM;
    ObservableList<Dranks> listD;
    int indexM = -1;
    int indexD = -1;

    public void entred(Event e) {
        ((Button) e.getSource()).setScaleX(1.1);
        ((Button) e.getSource()).setScaleY(1.1);
        ((Button) e.getSource()).setTextFill(Color.BLUE);

    }

    public void exited(Event e) {
        ((Button) e.getSource()).setScaleX(1);
        ((Button) e.getSource()).setScaleY(1);
//((Button)e.getSource()).setTextFill(Color.BLACK);
        if (((Button) e.getSource()).getText().equals("تسجيل الخروج")) {
            ((Button) e.getSource()).setTextFill(Color.RED);

        } else {
            ((Button) e.getSource()).setTextFill(Color.BLACK);

        }
    }

    public void Dranks() {
        Darnks.setVisible(true);
        Meals.setVisible(false);
    }

    public void Meals() {
        Meals.setVisible(true);
        Darnks.setVisible(false);

    }

    public void InsertMeals() {
        try {
            int num = Integer.parseInt(numM.getText());
            String name = nameM.getText();
            String type = typeM.getSelectionModel().getSelectedItem().toString();
            int cost = Integer.parseInt(costM.getText());
            if (!DB.insertM(num, name, type, cost)) {
                listM.add(new Meals(num, name, type, cost));
                doneM.setText("تمت عمليةالاضافة");
                doneM.setVisible(true);
                numMeals.setText(Integer.parseInt(numMeals.getText()) + 1 + "");
            }
        } catch (Exception e) {
        }

    }

    public void InsertDrank() {
        try {
            int num = Integer.parseInt(numD.getText());
            String name = nameD.getText();
            String type = typeD.getSelectionModel().getSelectedItem().toString();
            int cost = Integer.parseInt(costD.getText());
            if (!DB.insertD(num, name, type, cost)) {
                listD.add(new Dranks(num, name, type, cost));
                doneD.setText("تمت عمليةالاضافة");
                doneD.setVisible(true);
                numDranks.setText(Integer.parseInt(numDranks.getText()) + 1 + "");
            }
        } catch (Exception e) {
            
        }

    }

    public void getSelectedMeals() {
        indexM = tableM.getSelectionModel().getSelectedIndex();
        if (indexM <= -1) {
            return;
        }
        numM.setText(numTM.getCellData(indexM).toString());
        nameM.setText(nameTM.getCellData(indexM).toString());
        typeM.getSelectionModel().select(typeTM.getCellData(indexM));
        costM.setText(costTM.getCellData(indexM).toString());
    }

    public void getSelectedDrank() {
        indexD = tableD.getSelectionModel().getSelectedIndex();
        if (indexD <= -1) {
            return;
        }
        numD.setText(numTD.getCellData(indexD).toString());
        nameD.setText(nameTD.getCellData(indexD).toString());
        typeD.getSelectionModel().select(typeTD.getCellData(indexD));
        costD.setText(costTD.getCellData(indexD).toString());
    }

    public void updataMeals() {
        try {
            int num = Integer.parseInt(numM.getText());
            String name = nameM.getText();
            String type = typeM.getSelectionModel().getSelectedItem().toString();
            int cost = Integer.parseInt(costM.getText());

            if (DB.updateM("where numM=" + num, name, type, cost)) {
                listM.set(indexM, new Meals(num, name, type, cost));
                doneM.setText("تمت عمليةالتعديل");
                doneM.setVisible(true);
                clearM();

            }
        } catch (Exception e) {
        }

    }

    public void updataDrank() {
        try {
            int num = Integer.parseInt(numD.getText());
            String name = nameD.getText();
            String type = typeD.getSelectionModel().getSelectedItem().toString();
            int cost = Integer.parseInt(costD.getText());
            if (DB.updateD("where numD=" + num, name, type, cost)) {
                listD.set(indexD, new Dranks(num, name, type, cost));
                doneD.setText("تمت عمليةالتعديل");
                doneD.setVisible(true);
                clearD();

            }

        } catch (Exception e) {
        }

    }

    public void clearM() {
        numM.clear();
        typeM.getSelectionModel().select(-1);
        nameM.clear();
        costM.clear();
    }

    public void clearD() {
        numD.clear();
        typeD.getSelectionModel().select(-1);
        nameD.clear();
        costD.clear();
    }

    public void deleteMeals() {
        if (indexM == -1) {
            return;
        }
        if (DB.deleteM("numM=" + numTM.getCellData(indexM))) {

            doneM.setText("تمت الحذف");
            doneM.setVisible(true);
            numMeals.setText(Integer.parseInt(numMeals.getText()) + -1 + "");
            listM.remove(indexM);
            indexM = -1;
            clearM();
        }
    }

    public void deleteDarks() {
        if (indexD == -1) {
            return;
        }
        if (DB.deleteD("numD=" + numTD.getCellData(indexD))) {

            doneD.setText("تمت الحذف");
            doneD.setVisible(true);
            numDranks.setText(Integer.parseInt(numDranks.getText()) + -1 + "");
            listD.remove(indexD);
            indexD = -1;
            clearD();
        }
    }

    public void searchMeals() {
        searchM.textProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable o) {
                if (searchM.textProperty().get().isEmpty()) {
                    tableM.setItems(listM);
                    return;
                }

                ObservableList<Meals> tableitems = FXCollections.observableArrayList();
                ObservableList<TableColumn<Meals, ?>> cols = tableM.getColumns();
                for (int i = 0; i < listM.size(); i++) {
                    for (int j = 0; j < cols.size(); j++) {
                        TableColumn col = cols.get(j);
                        String cellValue = col.getCellData(listM.get(i)).toString();
                        cellValue = cellValue.toLowerCase();
                        if (cellValue.contains(searchM.getText().toLowerCase()) && cellValue.startsWith(searchM.getText().toLowerCase())) {
                            tableitems.add(listM.get(i));
                            break;
                        }
                    }

                }
                tableM.setItems(tableitems);
            }
        });

    }

    public void searchDranks() {
        try {
            searchD.textProperty().addListener(new InvalidationListener() {
                @Override
                public void invalidated(Observable o) {
                    if (searchD.textProperty().get().isEmpty()) {
                        tableD.setItems(listD);
                        return;
                    }

                    ObservableList<Dranks> tableitems = FXCollections.observableArrayList();
                    ObservableList<TableColumn<Dranks, ?>> col = tableD.getColumns();
                    for (int i = 0; i < listD.size(); i++) {
                        for (int j = 0; j < col.size(); j++) {
                            TableColumn coll = col.get(j);
                            String cellValue = coll.getCellData(listD.get(i)).toString();
                            cellValue = cellValue.toLowerCase();
                            if (cellValue.contains(searchD.getText().toLowerCase()) && cellValue.startsWith(searchD.getText().toLowerCase())) {
                                tableitems.add(listD.get(i));
                                break;
                            }
                        }

                    }
                    tableD.setItems(tableitems);
                }
            });

        } catch (Exception e) {

        }
    }

    public void logout(Event e) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("logined.fxml"));
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("تسجيل الدخول");
            Rectangle2D rd = Screen.getPrimary().getVisualBounds();
            stage.setX((rd.getWidth() - stage.getWidth()) / 2);
            stage.setY((rd.getHeight() - stage.getHeight()) / 2);
        } catch (Exception ee) {
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        numTM.setCellValueFactory(new PropertyValueFactory<Meals, Integer>("id"));
        nameTM.setCellValueFactory(new PropertyValueFactory<Meals, String>("name"));
        typeTM.setCellValueFactory(new PropertyValueFactory<Meals, String>("type"));
        costTM.setCellValueFactory(new PropertyValueFactory<Meals, Integer>("cost"));

        listM = DB.getMeals();
        tableM.setItems(listM);

        numTD.setCellValueFactory(new PropertyValueFactory<Dranks, Integer>("id"));
        nameTD.setCellValueFactory(new PropertyValueFactory<Dranks, String>("name"));
        typeTD.setCellValueFactory(new PropertyValueFactory<Dranks, String>("type"));
        costTD.setCellValueFactory(new PropertyValueFactory<Dranks, Integer>("cost"));

        listD = DB.getDranks();
        tableD.setItems(listD);

        numDranks.setText(DB.countD() + "");
        numMeals.setText(DB.countM() + "");
        ObservableList listM = FXCollections.observableArrayList("أسمك", "مشويات", "لحوم", "وجبات سريعة");
        typeM.setItems(listM);

        ObservableList listD = FXCollections.observableArrayList("بارد", "دافئ");
        typeD.setItems(listD);

    }

}
