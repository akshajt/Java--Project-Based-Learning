package UIPackage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
//import javafx.scene.control.Alert;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.stage.StageStyle;

public class Controller implements Initializable{
    public String loginFlag;

    @FXML
    private Label label;

    @FXML
    private Label lblError;

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private Button btnSignin;

    @FXML
    public void handleLoggingIn()
    {
        try
        {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("homescreen.fxml"));
            Parent root1 = (Parent)fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();
        }
        catch(Exception e)
        {
            System.out.println("Can't Load Your Window!");
        }
    }

    @FXML
    private javafx.scene.control.Button closeButton;

    @FXML
    private void handleClose()
    {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        //TODO
    }

//    public void LoginController()
//    {
//        con = ConnectionUtil.conDB();
//    }

    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;

    public void handleLogin(ActionEvent event)
    {
        String prn = txtUsername.getText();
        String password = txtPassword.getText();

        String sql = "SELECT * FROM hostel.admin WHERE username = ? and password = ?";
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");

            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1,prn);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            if(!resultSet.next())
            {
                lblError.setTextFill(Color.TOMATO);
                lblError.setText("Enter correct Username/Password");
            }
            else
            {
                lblError.setTextFill(Color.GREEN);
                lblError.setText("Login Successful");
                loginFlag = "Success";
//                Hiding the current login window
                ((Node)event.getSource()).getScene().getWindow().hide();
                handleLoggingIn();
            }
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

//    private void showDialogue(String info, String header, String title)
//    {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setContentText(info);
//        alert.setHeaderText(header);
//        alert.showAndWait();
//    }
}
