package HostelPackage;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ResourceBundle;

public class adminDatabaseController implements Initializable {

    @FXML
    private Button btnAdminBack;
    @FXML
    private Button btnLogout;
    @FXML
    private Label lblLoadDatabase;

    @FXML
    private TableView<adminModelTable> table;
    @FXML
    private TableColumn<adminModelTable, String> col_prn;
    @FXML
    private TableColumn<adminModelTable, String> col_FName;
    @FXML
    private TableColumn<adminModelTable, String> col_LName;
    @FXML
    private TableColumn<adminModelTable, Integer> col_year;
    @FXML
    private TableColumn<adminModelTable, String> col_roomNumber;
    @FXML
    private TableColumn<adminModelTable, String> col_contact;
    @FXML
    private TableColumn<adminModelTable, Boolean> col_paymentDue;
    @FXML
    private TableColumn<adminModelTable, Float> col_paymentAmount;
    @FXML
    private TableColumn<adminModelTable, DatePicker> col_departureDate;
    @FXML
    private TableColumn<adminModelTable, DatePicker> col_arrivalDate;
    @FXML
    private TableColumn<adminModelTable, String> col_leaveReason;
    @FXML
    private TableColumn<adminModelTable, Boolean> col_leaveApproved;
    @FXML
    private TableColumn<adminModelTable, String> col_feedback;
//    @FXML
//    private TableColumn<adminModelTable, Boolean> col_roomChange;

    ObservableList<adminModelTable> list = FXCollections.observableArrayList();

    @FXML
    public void handleViewDatabase() {
        table.getItems().clear();
        try {
            final String sql = "select * from student;";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");
            ResultSet rs = con.createStatement().executeQuery(sql);

            while(rs.next()) {
                list.add(new adminModelTable(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getInt(4), rs.getString(5), rs.getString(6), rs.getBoolean(7),
                        rs.getFloat(8), rs.getString(9), rs.getString(10), rs.getString(11),
                        rs.getBoolean(12), rs.getString(13)));
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }

        col_prn.setCellValueFactory(new PropertyValueFactory<>("prn"));
        col_FName.setCellValueFactory(new PropertyValueFactory<>("FName"));
        col_LName.setCellValueFactory(new PropertyValueFactory<>("LName"));
        col_year.setCellValueFactory(new PropertyValueFactory<>("year"));
        col_roomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        col_contact.setCellValueFactory(new PropertyValueFactory<>("contactNumber"));
        col_paymentDue.setCellValueFactory(new PropertyValueFactory<>("paymentDue"));
        col_paymentAmount.setCellValueFactory(new PropertyValueFactory<>("paymentAmount"));
        col_departureDate.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        col_arrivalDate.setCellValueFactory(new PropertyValueFactory<>("arrivalDate"));
        col_leaveReason.setCellValueFactory(new PropertyValueFactory<>("leaveReason"));
        col_leaveApproved.setCellValueFactory(new PropertyValueFactory<>("leaveApproved"));
        col_feedback.setCellValueFactory(new PropertyValueFactory<>("feedback"));
//        col_roomChange.setCellValueFactory(new PropertyValueFactory<>("roomChange"));

        table.setItems(list);
    }

    @FXML
    public void handleAdminBack() throws IOException {
        btnAdminBack.getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("adminHome.fxml"));

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
        handleViewDatabase();
    }
}
