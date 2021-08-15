package HostelPackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class wardenRCController implements Initializable {

    @FXML
    private Button btnWardenBack;
    @FXML
    private Button btnLogout;
    @FXML
    private Label lblLoadDatabase;
    @FXML
    private Button btnApproveHK;
    @FXML
    private Label lblHKError;
    @FXML
    private TextField txtNewRoomNumber;

    @FXML
    private TableView<wardenModelRCTable> table;
    @FXML
    private TableColumn<wardenModelRCTable, String> col_prn;
    @FXML
    private TableColumn<wardenModelRCTable, String> col_FName;
    @FXML
    private TableColumn<wardenModelRCTable, String> col_LName;
    @FXML
    private TableColumn<wardenModelRCTable, String> col_roomNumber;
    @FXML
    private TableColumn<wardenModelRCTable, Boolean> col_roomChange;

    PreparedStatement preparedStatement = null;

    ObservableList<wardenModelRCTable> list = FXCollections.observableArrayList();

    @FXML
    public void handleViewRCRequest(){
        table.getItems().clear();
        final String sql = "select prn, FName, LName, roomNumber, roomChange from student where roomChange = true";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");
            ResultSet rs = con.createStatement().executeQuery(sql);

            while(rs.next()) {
                list.add(new wardenModelRCTable(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getBoolean(5)));
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        col_prn.setCellValueFactory(new PropertyValueFactory<>("prn"));
        col_FName.setCellValueFactory(new PropertyValueFactory<>("FName"));
        col_LName.setCellValueFactory(new PropertyValueFactory<>("LName"));
        col_roomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        col_roomChange.setCellValueFactory(new PropertyValueFactory<>("roomChange"));

        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.getSelectionModel().setCellSelectionEnabled(true);
        table.setItems(list);
    }

    @FXML
    public void changeRoomNumber(ActionEvent event) {
        TablePosition tablePosition = table.getSelectionModel().getSelectedCells().get(0);
        int row = tablePosition.getRow();
        wardenModelRCTable wmt = table.getItems().get(row);
        TableColumn tableColumn = tablePosition.getTableColumn();
        String roomChangePRN = (String) tableColumn.getCellObservableValue(wmt).getValue();

        String newRoomNumber = txtNewRoomNumber.getText();
        final String sql_1 = "SET SQL_SAFE_UPDATES = 0;";
        final String CHANGE_ROOM_NUMBER_SQL = "UPDATE student SET roomChange = null, roomNumber = ? WHERE prn = " + roomChangePRN;
        final String sql_3 = "SET SQL_SAFE_UPDATES = 1;";
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");

                preparedStatement = con.prepareStatement(sql_1);
                preparedStatement.executeUpdate();
                preparedStatement = con.prepareStatement(CHANGE_ROOM_NUMBER_SQL);
                preparedStatement.setString(1, newRoomNumber);
                int count = preparedStatement.executeUpdate();
                if (count > 0)
                {
                    lblHKError.setTextFill(Color.GREEN);
                    lblHKError.setText("Room Number Change Confirmed For " + roomChangePRN + " to " + newRoomNumber);
                    table.getItems().clear();
                    handleViewRCRequest();
                }
                else
                {
                    lblHKError.setTextFill(Color.TOMATO);
                    lblHKError.setText("Room Change Failed");
                }
                preparedStatement = con.prepareStatement(sql_3);
                preparedStatement.executeUpdate();

            } catch(SQLException | ClassNotFoundException e){
                System.out.println(e.getMessage());
                lblHKError.setTextFill(Color.TOMATO);
                lblHKError.setText(e.getMessage());
            }
        }
    }

    @FXML
    public void handleWardenBack() throws IOException {
        btnWardenBack.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("wardenHome.fxml"));
        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    public void handleLogout() throws IOException {
        // Hide the main window
        btnLogout.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));

        Stage primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    public void handleClose() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //TODO
        handleViewRCRequest();
    }
}
