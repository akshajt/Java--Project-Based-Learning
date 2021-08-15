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

public class WardenDatabaseController implements Initializable {

    @FXML
    private Button btnWardenBack;
    @FXML
    private Button btnLogout;
    @FXML
    private Label lblLoadDatabase;
    @FXML
    private Button btnApproveLeave;
    @FXML
    private Label lblLeaveApproveError;

    @FXML
    private TableView<WardenModelTable> table;
    @FXML
    private TableColumn<WardenModelTable, String> col_prn;
    @FXML
    private TableColumn<WardenModelTable, String> col_FName;
    @FXML
    private TableColumn<WardenModelTable, String> col_LName;
    @FXML
    private TableColumn<WardenModelTable, DatePicker> col_departureDate;
    @FXML
    private TableColumn<WardenModelTable, DatePicker> col_arrivalDate;
    @FXML
    private TableColumn<WardenModelTable, String> col_leaveReason;
    @FXML
    private TableColumn<WardenModelTable, Boolean> col_leaveApproved;

    PreparedStatement preparedStatement = null;

    ObservableList<WardenModelTable> list = FXCollections.observableArrayList();

    @FXML
    public void handleWardenViewDatabase() {
        table.getItems().clear();
        final String sql = "select * from student where leaveApproved is not null;";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");
            ResultSet rs = con.createStatement().executeQuery(sql);

            while(rs.next()) {
                list.add(new WardenModelTable(rs.getString(1), rs.getString(2), rs.getString(3),
                        rs.getString(9), rs.getString(10), rs.getString(11), rs.getBoolean(12)));
            }
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
        }
        col_prn.setCellValueFactory(new PropertyValueFactory<>("prn"));
        col_FName.setCellValueFactory(new PropertyValueFactory<>("FName"));
        col_LName.setCellValueFactory(new PropertyValueFactory<>("LName"));
        col_departureDate.setCellValueFactory(new PropertyValueFactory<>("departureDate"));
        col_arrivalDate.setCellValueFactory(new PropertyValueFactory<>("arrivalDate"));
        col_leaveReason.setCellValueFactory(new PropertyValueFactory<>("leaveReason"));
        col_leaveApproved.setCellValueFactory(new PropertyValueFactory<>("leaveApproved"));

        table.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        table.getSelectionModel().setCellSelectionEnabled(true);
        table.setItems(list);
    }

    @FXML
    public void approveLeaveFromTable(ActionEvent event) {
        TablePosition tablePosition = table.getSelectionModel().getSelectedCells().get(0);
        int row = tablePosition.getRow();
        WardenModelTable wmt = table.getItems().get(row);
        TableColumn tableColumn = tablePosition.getTableColumn();
        String leavePRN = (String) tableColumn.getCellObservableValue(wmt).getValue();

        final String APPROVE_LEAVE_SQL = "UPDATE hostel.student SET leaveApproved = ? WHERE prn = " + leavePRN;
        {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");

                preparedStatement = con.prepareStatement(APPROVE_LEAVE_SQL);
                preparedStatement.setBoolean(1, true);

                int count = preparedStatement.executeUpdate();
                if (count > 0)
                {
                    lblLeaveApproveError.setTextFill(Color.GREEN);
                    lblLeaveApproveError.setText("Leave Has Been Approved For PRN " + leavePRN);
                    table.getItems().clear();
                    handleWardenViewDatabase();
                }
                else
                {
                    lblLeaveApproveError.setTextFill(Color.TOMATO);
                    lblLeaveApproveError.setText("Leave Failed To Approve");
                }

            } catch(SQLException | ClassNotFoundException e){
                System.out.println(e.getMessage());
                lblLeaveApproveError.setTextFill(Color.TOMATO);
                lblLeaveApproveError.setText(e.getMessage());
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
    public void initialize(URL location, ResourceBundle resources)
    {
        //TODO
        handleWardenViewDatabase();
    }
}
