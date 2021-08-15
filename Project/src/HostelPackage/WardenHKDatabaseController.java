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

public class WardenHKDatabaseController implements Initializable {

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
    private Button btnAcknowledge;
    @FXML
    private Label lblFB;

    @FXML
    private TableView<WardenModelHKTable> table;
    @FXML
    private TableColumn<WardenModelHKTable, String> col_prn;
    @FXML
    private TableColumn<WardenModelHKTable, String> col_FName;
    @FXML
    private TableColumn<WardenModelHKTable, String> col_LName;
    @FXML
    private TableColumn<WardenModelHKTable, String> col_roomNumber;
    @FXML
    private TableColumn<WardenModelHKTable, Boolean> col_requestHK;
    @FXML
    private TableColumn<WardenModelHKTable, String> col_feedback;

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    ObservableList<WardenModelHKTable> list = FXCollections.observableArrayList();

    @FXML
    public void handleViewHKRequest(){
        table.getItems().clear();
        final String sql = "select prn, FName, LName, roomNumber, requestHousekeeping, feedback from student where requestHousekeeping = true OR feedback IS NOT NULL";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");
            ResultSet rs = con.createStatement().executeQuery(sql);

            while(rs.next())
            {
                list.add(new WardenModelHKTable(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(4), rs.getBoolean(5), rs.getString(6)));
            }
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
        col_prn.setCellValueFactory(new PropertyValueFactory<>("prn"));
        col_FName.setCellValueFactory(new PropertyValueFactory<>("FName"));
        col_LName.setCellValueFactory(new PropertyValueFactory<>("LName"));
        col_roomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        col_requestHK.setCellValueFactory(new PropertyValueFactory<>("requestHousekeeping"));
        col_feedback.setCellValueFactory(new PropertyValueFactory<>("feedback"));

        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.getSelectionModel().setCellSelectionEnabled(true);
        table.setItems(list);
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
    public void approveHK(ActionEvent event) {
        TablePosition tablePosition = table.getSelectionModel().getSelectedCells().get(0);
        int row = tablePosition.getRow();
        WardenModelHKTable wmt = table.getItems().get(row);
        TableColumn tableColumn = tablePosition.getTableColumn();
        String HKprn = (String) tableColumn.getCellObservableValue(wmt).getValue();

        final String APPROVE_HK = "UPDATE student SET requestHousekeeping = null WHERE prn = " + HKprn;
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");

                preparedStatement = con.prepareStatement(APPROVE_HK);
                int count = preparedStatement.executeUpdate();
                if (count > 0)
                {
                    lblHKError.setTextFill(Color.GREEN);
                    lblHKError.setText("House Keeping Has Been Approved For " + HKprn);
                    table.getItems().clear();
                    handleViewHKRequest();
                }
                else
                {
                    lblHKError.setTextFill(Color.TOMATO);
                    lblHKError.setText("House Keeping Failed To Approve");
                }

            } catch(SQLException | ClassNotFoundException e){
                System.out.println(e.getMessage());
                lblHKError.setTextFill(Color.TOMATO);
                lblHKError.setText(e.getMessage());
            }
        }
    }

    @FXML
    public void ackFeedback(ActionEvent event) {
        TablePosition tablePosition = table.getSelectionModel().getSelectedCells().get(0);
        int row = tablePosition.getRow();
        WardenModelHKTable wmt = table.getItems().get(row);
        TableColumn tableColumn = tablePosition.getTableColumn();
        String fbPRN = (String) tableColumn.getCellObservableValue(wmt).getValue();

        final String APPROVE_HK = "UPDATE student SET feedback = null WHERE prn = " + fbPRN;
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");

                preparedStatement = con.prepareStatement(APPROVE_HK);
                int count = preparedStatement.executeUpdate();
                if (count > 0)
                {
                    lblFB.setTextFill(Color.GREEN);
                    lblFB.setText("Feedback confirmed for " + fbPRN);
                    table.getItems().clear();
                    handleViewHKRequest();
                }
                else
                {
                    lblFB.setTextFill(Color.TOMATO);
                    lblFB.setText("Feedback confirmation failed");
                }

            } catch(SQLException | ClassNotFoundException e){
                System.out.println(e.getMessage());
                lblFB.setTextFill(Color.TOMATO);
                lblFB.setText(e.getMessage());
            }
        }
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
    public void initialize(URL location, ResourceBundle resources)
    {
        //TODO
        handleViewHKRequest();
    }
}
