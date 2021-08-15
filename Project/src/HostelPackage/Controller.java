package HostelPackage;

import java.net.URL;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import javax.xml.transform.Result;

public class Controller implements Initializable {
    public String loginFlag;

    @FXML
    private Button btnBack;
    @FXML
    private Button btnLogout;
    @FXML
    private Button btnApplyForLeave;
    @FXML
    private TextField txtUsername;
    @FXML
    private TextField txtPassword;
    @FXML
    private Label lblLoginError;
    @FXML
    private Button btnSignIn;
    @FXML
    private Label label;
    @FXML
    private Label lblAddStudentError;
    @FXML
    private TextField txtFName;
    @FXML
    private TextField txtLName;
    @FXML
    private TextField txtYear;
    @FXML
    private TextField txtContactNumber;
    @FXML
    private TextArea txtFeedback;
    @FXML
    private TextField txtRoomNumber;
    @FXML
    public TextField txtPrn;
    @FXML
    private Button btnAdminBack;
    @FXML
    private Button btnAddNewUser;
    @FXML
    private Button btnCreateNewStudentRecord;
    @FXML
    private Button btnEditUserSearch;
    @FXML
    private Button btnEditUser;
    @FXML
    private Button btnOpenEditSearch;
    @FXML
    private Label lblEditUserSearchError;
    @FXML
    private Label lblEditUserError;
    @FXML
    private Button btnOpenDelete;
    @FXML
    private Button btnDeleteUser;
    @FXML
    private Label lblDeleteUserError;
    @FXML
    private Button btnViewDatabase;
    @FXML
    private DatePicker dateDeparture;
    @FXML
    private DatePicker dateArrival;
    @FXML
    private TextArea leaveReason;
    @FXML
    private Button btnSubmitLeave;
    @FXML
    private Label lblLeaveError;
    @FXML
    private Label lblPaymentError;
    @FXML
    private Label lblPaymentSearchError;
    @FXML
    private Button btnOpenPaymentSearch;
    @FXML
    private Button btnOpenPaymentPage;
    @FXML
    private TextField txtPaymentAmount;
    @FXML
    private TextField txtPaymentDue;
    @FXML
    private CheckBox checkBoxPaymentDue;
    @FXML
    private Label lblFeedback;
    @FXML
    private Button btnHandlePayment;
    @FXML
    private Button btnWardenViewDatabase;
    @FXML
    private Label lblPendingLeave;
    @FXML
    private Label lblApprove;
    @FXML
    private Button btnUserLoadHomeDetails;
    @FXML
    private Label lblAnnouncements;
    @FXML
    private Label lblPendingPayments;
    @FXML
    private Label lblUserWelcome;
    @FXML
    private Label lblHomeAnnouncement;
    @FXML
    private Button btnOpenAnnouncement;
    @FXML
    private Button btnSetAnnouncement;
    @FXML
    private TextArea txtAnnouncements;
    @FXML
    private Label lblAnnouncementError;
    @FXML
    private Button btnOpenPayment;
    @FXML
    private Button btnRequestHouseKeeping;
    @FXML
    private Button btnWardenOpenHKRequests;
    @FXML
    private Label lblLeaveCount;
    @FXML
    private Label lblHKCount;
    @FXML
    private Label lblFBCount;
    @FXML
    private ChoiceBox paymentBox;
    @FXML
    private Button btnWardenOpenRC;
    @FXML
    private Button btnOpenFeedback;
    @FXML
    private Button btnOpenRoomChange;
    @FXML
    private Button btnConfirmRoomChange;
    @FXML
    private Label lblRCCount;
    @FXML
    private Button btnOpenChangePW;
    @FXML
    private Button btnChangePW;
    @FXML
    private TextField txtPasswordChange;
    @FXML
    private TextField txtPWConfirm1;
    @FXML
    private TextField txtPWConfirm2;
    @FXML
    private Label lblChangePW;
    @FXML
    private Label lblUserHome;
    @FXML
    private ImageView imgUser;
    @FXML
    private javafx.scene.control.Button closeButton;

    PreparedStatement preparedStatement = null;
    PreparedStatement preparedStatement2 = null;
    ResultSet resultSet = null;

//    ADMIN

    @FXML
    public void handleOpenAddNewUser() {
        try {
            btnAddNewUser.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminAddStudent.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't Load Your Window!");
        }
    }

    @FXML
    public void handleAddNewUser() {
        String prn = txtPrn.getText();
        String fName = txtFName.getText();
        String lName = txtLName.getText();
        int year = Integer.parseInt(txtYear.getText());
        String roomNumber = txtRoomNumber.getText();
        String contactNumber = txtContactNumber.getText();

        final String INSERT_STUDENTS_SQL = "INSERT INTO student (prn, FName, LName, Year, roomNumber, contactNumber, paymentDue, paymentAmount) VALUES (?, ?, ?, ?, ?, ?, 0, 0)";
        final String CREATE_LOGIN_INFO_SQL = "INSERT INTO LOGIN (prn, password) VALUES (?,?)";
        // try-with-resource statement will auto close the connection.
        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");

                preparedStatement = con.prepareStatement(INSERT_STUDENTS_SQL);
                preparedStatement.setString(1, prn);
                preparedStatement.setString(2, fName);
                preparedStatement.setString(3, lName);
                preparedStatement.setInt(4, year);
                preparedStatement.setString(5, roomNumber);
                preparedStatement.setString(6, contactNumber);

                preparedStatement2 = con.prepareStatement(CREATE_LOGIN_INFO_SQL);
                preparedStatement2.setString(1, prn);
                preparedStatement2.setString(2, fName);
                preparedStatement2.executeUpdate();

                int count = preparedStatement.executeUpdate();
                if (count > 0)
                {
                    lblAddStudentError.setTextFill(Color.GREEN);
                    lblAddStudentError.setText("Student Details Added Successfully.");
                    txtPrn.clear();
                    txtFName.clear();
                    txtLName.clear();
                    txtYear.clear();
                    txtContactNumber.clear();
                    txtRoomNumber.clear();

                }
                else
                {
                    lblAddStudentError.setTextFill(Color.TOMATO);
                    lblAddStudentError.setText("Student Details Failed To Add To Database");
                }

            } catch(SQLException | ClassNotFoundException e){
                System.out.println(e.getMessage());
                lblAddStudentError.setTextFill(Color.TOMATO);
                lblAddStudentError.setText(e.getMessage());
            }
    }

    @FXML
    public void handleOpenEditUser() {
        User.prn = txtPrn.getText();
        try {
            final String sql = "select * from student where prn = " + User.prn;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");

            preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next())
            {
                lblEditUserSearchError.setTextFill(Color.TOMATO);
                lblEditUserSearchError.setText("PRN not found in database. Try again.");
                txtPrn.clear();
            }
            else
            {
                btnEditUserSearch.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminEditStudent.fxml"));
                Parent root1 = fxmlLoader.load();
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root1));
                stage.show();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleOpenEditUserSearch() {
        try {
            btnOpenEditSearch.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminEditStudentSearch.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't Load Your Window!");
        }
    }

    @FXML
    public void handleEditUser() {

        String fName = txtFName.getText();
        String lName = txtLName.getText();
        int year = Integer.parseInt(txtYear.getText());
        String roomNumber = txtRoomNumber.getText();
        String contactNumber = txtContactNumber.getText();

        try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");
                final String UPDATE_STUDENTS_SQL = "UPDATE hostel.student SET FName = ?, LName = ?, Year = ?, roomNumber = ?, contactNumber = ? WHERE prn = " + User.prn;

                preparedStatement = con.prepareStatement(UPDATE_STUDENTS_SQL);
                preparedStatement.setString(1, fName);
                preparedStatement.setString(2,lName);
                preparedStatement.setInt(3, year);
                preparedStatement.setString(4, roomNumber);
                preparedStatement.setString(5, contactNumber);
                preparedStatement.executeUpdate();

                int count = preparedStatement.executeUpdate();
                if (count > 0)
                {
                    lblEditUserError.setTextFill(Color.GREEN);
                    lblEditUserError.setText("Student Details Updated Successfully.");
                    txtFName.clear();
                    txtLName.clear();
                    txtYear.clear();
                    txtContactNumber.clear();
                    txtRoomNumber.clear();
                }
                else
                {
                    lblEditUserError.setTextFill(Color.TOMATO);
                    lblEditUserError.setText("Student Details Failed To Update In Database");
                }
           }
        catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleOpenDeleteUser() {
        try {
            btnAddNewUser.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminDeleteStudent.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't Load Your Window!");
        }
    }

    @FXML
    public void handleDeleteUser() {

        String prn = txtPrn.getText();
        final String DELETE_STUDENTS_SQL = "delete from student where prn = " + prn;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");

            preparedStatement = con.prepareStatement(DELETE_STUDENTS_SQL);

            int count = preparedStatement.executeUpdate();
            if (count > 0)
            {
                lblDeleteUserError.setTextFill(Color.GREEN);
                lblDeleteUserError.setText("Student Details Deleted Successfully.");
                txtPrn.clear();
            }
            else
            {
                lblDeleteUserError.setTextFill(Color.TOMATO);
                lblDeleteUserError.setText("Student Details Not Present in Database");
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleOpenPaymentSearch() {
        try {
            btnOpenPaymentSearch.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminPaymentSearch.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't Load Your Window!");
        }
    }

    @FXML
    public void handlePaymentSearch() {
        User.prn = txtPrn.getText();

        try {
            final String sql = "select * from student where prn = " + User.prn;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");

            preparedStatement = con.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            if (!resultSet.next())
            {
                lblPaymentSearchError.setTextFill(Color.TOMATO);
                lblPaymentSearchError.setText("PRN not found in database. Try again.");
                txtPrn.clear();
            }
            else {
                btnOpenPaymentPage.getScene().getWindow().hide();
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminPaymentHandle.fxml"));
                Parent root1 = fxmlLoader.load();
                Stage stage = new Stage();
                stage.initStyle(StageStyle.UNDECORATED);
                stage.setScene(new Scene(root1));
                stage.show();
            }
        } catch (Exception e) {
            System.out.println("Can't Load Your Window!");
        }
    }

    @FXML
    public void handlePaymentDetails() {

        float paymentAmount = Float.parseFloat(txtPaymentAmount.getText());
        boolean paymentDue;
        paymentDue = checkBoxPaymentDue.isSelected();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");
            final String UPDATE_STUDENT_PAYMENT_DETAILS_SQL = "UPDATE hostel.student SET paymentAmount = ?, paymentDue = ? WHERE prn = " + User.prn;

            preparedStatement = con.prepareStatement(UPDATE_STUDENT_PAYMENT_DETAILS_SQL);
            preparedStatement.setFloat(1, paymentAmount);
            preparedStatement.setBoolean(2, paymentDue);

            int count = preparedStatement.executeUpdate();
            if (count > 0)
            {
                lblPaymentError.setTextFill(Color.GREEN);
                lblPaymentError.setText("Payment Details Updated");
                txtPaymentAmount.clear();
                checkBoxPaymentDue.setSelected(false);
            }
            else
            {
                lblPaymentError.setTextFill(Color.TOMATO);
                lblPaymentError.setText("Payment Details Failed To Update");
            }
        }
        catch(SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleRequestHouseKeeping(){
        try {
            final String sql_1 = "SET SQL_SAFE_UPDATES = 0;";
            final String sql_2 = "update student set requestHousekeeping = 1 where prn = " + User.prn;
            final String sql_3 = "SET SQL_SAFE_UPDATES = 1;";
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");

            preparedStatement = con.prepareStatement(sql_1);
            preparedStatement.executeUpdate();
            preparedStatement = con.prepareStatement(sql_2);
            preparedStatement.executeUpdate();
            preparedStatement = con.prepareStatement(sql_3);
            preparedStatement.executeUpdate();

            btnRequestHouseKeeping.setText("House Keeping Requested");

        } catch (Exception e) {
            System.out.println("Can't Load Your Window!");
        }
    }

    @FXML
    public void handleOpenAnnouncement() {
        try {
            btnOpenAnnouncement.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminSetAnnouncement.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't Load Your Window!");
        }
    }

    @FXML
    public void handleSetAnnouncement() {
        txtAnnouncements.setWrapText(true);
        String announcement = txtAnnouncements.getText();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");
            final String UPDATE_ANNOUNCEMENT_SQL = "UPDATE hostel.admin SET announcement = ?";

            preparedStatement = con.prepareStatement(UPDATE_ANNOUNCEMENT_SQL);
            preparedStatement.setString(1, announcement);
            preparedStatement.executeUpdate();

            int count = preparedStatement.executeUpdate();
            if (count > 0)
            {
                lblAnnouncementError.setTextFill(Color.GREEN);
                lblAnnouncementError.setText("Announcement Has Been Updated");
                txtAnnouncements.clear();
            }
            else
            {
                lblAnnouncementError.setTextFill(Color.TOMATO);
                lblAnnouncementError.setText("Announcement Failed To Update");
            }
        }
        catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleAdminLoggingIn() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminHome.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't Load Your Window!");
        }
    }

    @FXML
    public void handleOpenViewDatabase() {
        try {
            btnViewDatabase.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminViewDatabase.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();
//            DatabaseController.handleViewDatabase();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleAdminBack() {
        try {
            btnAdminBack.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adminHome.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    USER

    @FXML
    public void handleOpenApplyForLeave() {
        try {
            btnApplyForLeave.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userApplyForLeave.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleApplyForLeave() {
        String dDeparture = dateDeparture.getValue().toString();
        String dArrival = dateArrival.getValue().toString();
        leaveReason.setWrapText(true);
        String lReason = leaveReason.getText();

        final String student_leave = "UPDATE hostel.student SET departureDate = ?, arrivalDate = ?, leaveReason = ?, leaveApproved = 0 WHERE prn = " + User.prn;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");
            preparedStatement = con.prepareStatement(student_leave);
            preparedStatement.setString(1, dDeparture);
            preparedStatement.setString(2, dArrival);
            preparedStatement.setString(3, lReason);

            int count = preparedStatement.executeUpdate();
            if (count > 0)
            {
                lblLeaveError.setTextFill(Color.GREEN);
                lblLeaveError.setText("Leave Application Has Been Submitted");
                dateDeparture.getEditor().clear();
                dateArrival.getEditor().clear();
                leaveReason.clear();
            }
            else
            {
                lblLeaveError.setTextFill(Color.TOMATO);
                lblLeaveError.setText("Leave Application Failed To Submit");
            }

        }
        catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }

        final String getLeaveReason = "select leaveReason, departureDate, arrivalDate from student where prn = " + User.prn;
        String stringLeaveReason;
        Date depart;
        Date arrive;
        String leaveArray[] = new String[]{ "medical", "emergency", "family", "doctor" , "health", "wedding", "event",
        "parents", "visiting", "visit"};
        List<String> stringList = new ArrayList<>(Arrays.asList(leaveArray));
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");

            preparedStatement = con.prepareStatement(getLeaveReason);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next())
            {
                stringLeaveReason = resultSet.getString(1);
                String leaveSplit[] = stringLeaveReason.split(" ");
                depart = resultSet.getDate(2);
                arrive = resultSet.getDate(3);
                int days = (int) (arrive.getTime() - depart.getTime() / (1000 * 60 * 60 * 24));
                final String autoLeaveApprove = "UPDATE hostel.student SET leaveApproved = 1 WHERE prn = " + User.prn;

                for(String st : leaveSplit)
                    if(stringList.contains(st) && days < 3)
                    {
                        preparedStatement = con.prepareStatement(autoLeaveApprove);
                        int count = preparedStatement.executeUpdate();
                        if(count > 0) {
                            lblLeaveError.setTextFill(Color.GREEN);
                            lblLeaveError.setText("Your Leave Has Been Approved");
                        }
                        break;
                    }
            }
        }
        catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void dateCheck() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/mm/dd");
        LocalDateTime now = LocalDateTime.now();

        final String date_handle_sql_1 = "SET SQL_SAFE_UPDATES = 0;";
        final String date_handle_sql_2 = "update student set departureDate = null, arrivalDate = null, leaveReason = null, " +
                "leaveApproved = null where (arrivalDate <= NOW());";
        final String date_handle_sql_3 = "SET SQL_SAFE_UPDATES = 1;";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");
            preparedStatement = con.prepareStatement(date_handle_sql_1);
            preparedStatement.executeUpdate();
            preparedStatement = con.prepareStatement(date_handle_sql_2);
            preparedStatement.executeUpdate();
            preparedStatement = con.prepareStatement(date_handle_sql_3);
            preparedStatement.executeUpdate();
        }
        catch(SQLException | ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleOpenPayment() {
        try {
            btnOpenPayment.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userPayment.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();
        }  catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleViewAmount() {

        float paymentAmount;
        final String getAmount = "select paymentAmount from student where prn = " + User.prn;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");

            preparedStatement = con.prepareStatement(getAmount);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                paymentAmount = resultSet.getFloat(1);
                lblPendingPayments.setTextFill(Color.WHITE);
                lblPendingPayments.setText("Your Pending Payment Amount -- " + paymentAmount);
            }
            btnHandlePayment.setVisible(true);
            lblPaymentError.setVisible(true);
            btnOpenPayment.setVisible(false);
            paymentBox.setVisible(true);
            paymentBox.setValue("Net Banking");
            paymentBox.setItems(paymentList);
        }
        catch(SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
    ObservableList<String> paymentList = FXCollections.
            observableArrayList("Net Banking", "Credit Card", "UPI", "Other");

    @FXML
    public void handleClearPayment() {
        final String MAKE_PAYMENT_SQL = "UPDATE hostel.student SET paymentDue = 0, paymentAmount = 0 where prn = " + User.prn;
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");

            preparedStatement = con.prepareStatement(MAKE_PAYMENT_SQL);
            int count = preparedStatement.executeUpdate();

            if (count > 0) {
                lblPaymentSearchError.setTextFill(Color.GREEN);
                lblPaymentSearchError.setText("Payment Successful");
            }
            else {
                lblPaymentSearchError.setTextFill(Color.TOMATO);
                lblPaymentSearchError.setText("Payment Failed");
            }
        }
        catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleRoomChangeRequest() {
        boolean roomChange;

        final String MAKE_ROOMCHANGE_REQUEST_SQL = "UPDATE hostel.student SET roomChange = 1 where prn = " + User.prn;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");

            preparedStatement = con.prepareStatement(MAKE_ROOMCHANGE_REQUEST_SQL);
            int count = preparedStatement.executeUpdate();
            if (count > 0) {
                lblFeedback.setTextFill(Color.GREEN);
                lblFeedback.setText("Room Change Requested Successfully");
            }
            else {
                lblFeedback.setTextFill(Color.TOMATO);
                lblFeedback.setText("Room Change Request Failed");
            }

        }  catch(SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleOpenRoomChangeRequest() {
        try {
            btnOpenRoomChange.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userRoomChange.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleOpenFeedback() {
        try {
            btnOpenFeedback.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userFeedback.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleFeedback() {
        String feedback = txtFeedback.getText();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");
            final String UPDATE_STUDENT_FEEDBACK_SQL = "UPDATE hostel.student SET feedback = ? WHERE prn = " + User.prn;

            preparedStatement = con.prepareStatement(UPDATE_STUDENT_FEEDBACK_SQL);
            preparedStatement.setString(1, feedback);
            preparedStatement.executeUpdate();

            int count = preparedStatement.executeUpdate();
            if (count > 0)
            {
                lblFeedback.setTextFill(Color.GREEN);
                lblFeedback.setText("Feedback Submitted Successfully.");
                txtFeedback.clear();
            }
            else {
                lblFeedback.setTextFill(Color.TOMATO);
                lblFeedback.setText("Feedback Submission Failed");
            }
        }
        catch(SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleOpenChangePassword() {
        try {
            btnOpenChangePW.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userChangePassword.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleChangePassword() {
        String current;
        String currentInput = txtPasswordChange.getText();
        String confirm1 = txtPWConfirm1.getText();
        String confirm2 = txtPWConfirm2.getText();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");

            final String GET_PASSWORD = "select * from login WHERE prn = ? and password = ?;";

            preparedStatement = con.prepareStatement(GET_PASSWORD);
            preparedStatement.setString(1,User.prn);
            preparedStatement.setString(2,currentInput);
            resultSet = preparedStatement.executeQuery();

            if(!resultSet.next()) {
                lblChangePW.setTextFill(Color.TOMATO);
                lblChangePW.setText("Invalid Password");
            }
            else {
                current = resultSet.getString(2);
                if (confirm1.equals(confirm2) && currentInput.equals(current)) {
                    final String UPDATE_PASSWORD_1 = "SET SQL_SAFE_UPDATES = 0;";
                    final String UPDATE_PASSWORD_2 = "update login set password = '" + confirm1 + "' where prn = '" + User.prn + "';";
                    final String UPDATE_PASSWORD_3 = "SET SQL_SAFE_UPDATES = 1;";

                    preparedStatement = con.prepareStatement(UPDATE_PASSWORD_1);
                    preparedStatement.executeQuery();

                    preparedStatement = con.prepareStatement(UPDATE_PASSWORD_2);
                    int count = preparedStatement.executeUpdate();
                    if (count > 0) {
                        lblChangePW.setTextFill(Color.GREEN);
                        lblChangePW.setText("Password Changed Successfully");
                        txtPasswordChange.clear();
                        txtPWConfirm1.clear();
                        txtPWConfirm2.clear();
                    }
                    preparedStatement = con.prepareStatement(UPDATE_PASSWORD_3);
                    preparedStatement.executeUpdate();
                }
                else {
                    lblChangePW.setTextFill(Color.TOMATO);
                    lblChangePW.setText("Passwords Do Not Match");
                }
            }
        }
        catch(SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleLoggingIn() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userHome.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void userHomeDetails() {
        Boolean approved;
        Date depart, arrive;
        Boolean paymentDue;
        float paymentAmount;
        String fName;
        String announcements;

        final String getDates = "select departureDate, arrivalDate, leaveApproved, paymentDue, paymentAmount, FName from student where prn = " + User.prn;
        final String getAnnouncement = "select announcement from admin";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");

            preparedStatement = con.prepareStatement(getAnnouncement);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                announcements = resultSet.getString(1);
                lblHomeAnnouncement.setTextFill(Color.WHITE);
                lblHomeAnnouncement.setText("Announcement -- " + announcements);
            }

            preparedStatement = con.prepareStatement(getDates);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                depart = resultSet.getDate(1);
                arrive = resultSet.getDate(2);
                approved = resultSet.getBoolean(3);
                paymentDue = resultSet.getBoolean(4);
                paymentAmount = resultSet.getFloat(5);
                fName = resultSet.getString(6);

                lblUserWelcome.setTextFill(Color.WHITE);
                lblUserWelcome.setText("Hello, " + fName);
                lblUserWelcome.setTextAlignment(TextAlignment.CENTER);

                lblApprove.setTextFill(Color.WHITE);
                lblApprove.setTextAlignment(TextAlignment.CENTER);
                lblPendingLeave.setTextFill(Color.WHITE);
                lblPendingLeave.setTextAlignment(TextAlignment.CENTER);
                if(depart == null && arrive == null) {
                    lblPendingLeave.setText("You Have No Pending Leaves");
                    lblApprove.setText(" ");
                }
                else if(depart.toString().length() != 0 && arrive.toString().length() != 0)
                {
                    lblPendingLeave.setText("Your Applied Leave -- " + depart + " to " + arrive);
                    if(approved)
                        lblApprove.setText("Your Leave Has Been Approved");
                    else
                        lblApprove.setText("Your Leave Is Awaiting Approval");
                }

                lblPendingPayments.setTextFill(Color.WHITE);
                if(paymentDue)
                    lblPendingPayments.setText("Your Pending Payment Amount -- " + paymentAmount);
                else
                    lblPendingPayments.setText("All Payments Up To Date");

                btnUserLoadHomeDetails.setVisible(false);
                btnOpenChangePW.setVisible(true);
                lblUserHome.setVisible(false);
                imgUser.setVisible(false);
            }
        }
        catch(SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleUserBack() {
        try {
            btnBack.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("userHome.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    WARDEN

    @FXML
    public void handleWardenLoggingIn() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("wardenHome.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println("Can't Load Your Window!");
        }
    }

    @FXML
    public void wardenHomeDetails(){
        final String getLeaveCount = "select count(FName) from student where leaveApproved=0;";
        final String getHKCount = "select count(FName) from student where requestHousekeeping=1;";
        final String getRCCount = "select count(FName) from student where roomChange=1;";
        final String getFBCount = "select count(FName) from student where feedback IS NOT NULL;";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");

            preparedStatement = con.prepareStatement(getLeaveCount);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                lblLeaveCount.setTextFill(Color.WHITE);
                lblLeaveCount.setText("Pending Leave Requests -- " + resultSet.getString(1));
            }

            preparedStatement = con.prepareStatement(getHKCount);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                lblHKCount.setTextFill(Color.WHITE);
                lblHKCount.setText("Pending House Keeping Requests -- " + resultSet.getString(1));
            }

            preparedStatement = con.prepareStatement(getRCCount);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                lblRCCount.setTextFill(Color.WHITE);
                lblRCCount.setText("Pending Room Change Requests -- " + resultSet.getString(1));
            }

            preparedStatement = con.prepareStatement(getFBCount);
            resultSet = preparedStatement.executeQuery();

            if(resultSet.next()){
                lblFBCount.setTextFill(Color.WHITE);
                lblFBCount.setText(resultSet.getString(1) + " students have feedback for you");
            }
            btnUserLoadHomeDetails.setVisible(false);

        }
        catch(SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleWardenViewDatabase() {
        try {
            btnWardenViewDatabase.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("wardenViewDatabase.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleWardenOpenHKRequest(){
        try {
            btnWardenOpenHKRequests.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("wardenViewHouseKeepingRequest.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void wardenOpenRoomChangeRequest() {
        try {
            btnWardenOpenRC.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("wardenRoomChangeDatabase.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

//    COMMON

    @FXML
    public void handleLogin() {
        String prn = txtUsername.getText();
        String password = txtPassword.getText();
        User.prn = prn;

        final String sql = "SELECT * FROM hostel.login WHERE prn = ? and password = ?";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hostel", "root", "root");

            preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, prn);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                lblLoginError.setTextFill(Color.TOMATO);
                lblLoginError.setText("Enter correct Username/Password");
            }
            else {
                lblLoginError.setTextFill(Color.GREEN);
                lblLoginError.setText("Login Successful");
                loginFlag = "Success";

                // Hiding the current login window
                btnSignIn.getScene().getWindow().hide();

                if(prn.equals("admin") && password.equals("admin"))
                    handleAdminLoggingIn();
                else if(prn.equals("warden") && password.equals("warden"))
                    handleWardenLoggingIn();
                else
                    handleLoggingIn();
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    public void handleClose() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void handleLogout() {
        // Hide the main window
        try {
            btnLogout.getScene().getWindow().hide();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root1 = fxmlLoader.load();
            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(new Scene(root1));
            stage.show();
            dateCheck();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //TODO
    }
}