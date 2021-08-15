package HostelPackage;

import javafx.beans.property.*;

public class adminModelTable {

    private final StringProperty prn;
    private final StringProperty FName, LName;
    private final IntegerProperty year;
    private final StringProperty roomNumber;
    private final StringProperty contactNumber;
    private final BooleanProperty paymentDue;
    private final FloatProperty paymentAmount;
    private final StringProperty departureDate;
    private final StringProperty arrivalDate;
    private final StringProperty leaveReason;
    private final BooleanProperty leaveApproved;
    private final StringProperty feedback;
//    private final BooleanProperty roomChange;


    public adminModelTable(String  prn, String FName, String LName, int year, String roomNumber, String contactNumber, boolean paymentDue,
                           float paymentAmount, String departureDate, String arrivalDate, String leaveReason,
                           Boolean leaveApproved, String feedback) {
        this.prn = new SimpleStringProperty(prn);
        this.FName = new SimpleStringProperty(FName);
        this.LName = new SimpleStringProperty(LName);
        this.year = new SimpleIntegerProperty(year);
        this.roomNumber = new SimpleStringProperty(roomNumber);
        this.contactNumber = new SimpleStringProperty(contactNumber);
        this.paymentDue = new SimpleBooleanProperty(paymentDue);
        this.paymentAmount = new SimpleFloatProperty(paymentAmount);
        this.departureDate = new SimpleStringProperty(departureDate);
        this.arrivalDate = new SimpleStringProperty(arrivalDate);
        this.leaveReason = new SimpleStringProperty(leaveReason);
        this.leaveApproved = new SimpleBooleanProperty(leaveApproved);
        this.feedback = new SimpleStringProperty(feedback);
//        this.roomChange = new SimpleBooleanProperty(roomChange);
    }

    public String getPrn() {
        return prn.get();
    }

    public StringProperty prnProperty() {
        return prn;
    }

    public void setPrn(String prn) {
        this.prn.set(prn);
    }

    public String getFName() {
        return FName.get();
    }

    public StringProperty FNameProperty() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName.set(FName);
    }

    public String getLName() {
        return LName.get();
    }

    public StringProperty LNameProperty() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName.set(LName);
    }

    public int getYear() {
        return year.get();
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public void setYear(int year) {
        this.year.set(year);
    }

    public String getRoomNumber() {
        return roomNumber.get();
    }

    public StringProperty roomNumberProperty() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber.set(roomNumber);
    }

    public String getContactNumber() {
        return contactNumber.get();
    }

    public StringProperty contactNumberProperty() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber.set(contactNumber);
    }

    public boolean isPaymentDue() {
        return paymentDue.get();
    }

    public BooleanProperty paymentDueProperty() {
        return paymentDue;
    }

    public void setPaymentDue(boolean paymentDue) {
        this.paymentDue.set(paymentDue);
    }

    public float getPaymentAmount() {
        return paymentAmount.get();
    }

    public FloatProperty paymentAmountProperty() {
        return paymentAmount;
    }

    public void setPaymentAmount(float paymentAmount) {
        this.paymentAmount.set(paymentAmount);
    }

    public String getDepartureDate() {
        return departureDate.get();
    }

    public StringProperty departureDateProperty() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate.set(departureDate);
    }

    public String getArrivalDate() {
        return arrivalDate.get();
    }

    public StringProperty arrivalDateProperty() {
        return arrivalDate;
    }

    public void setArrivalDate(String arrivalDate) {
        this.arrivalDate.set(arrivalDate);
    }

    public String getLeaveReason() {
        return leaveReason.get();
    }

    public StringProperty leaveReasonProperty() {
        return leaveReason;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason.set(leaveReason);
    }

    public boolean isLeaveApproved() {
        return leaveApproved.get();
    }

    public BooleanProperty leaveApprovedProperty() {
        return leaveApproved;
    }

    public void setLeaveApproved(boolean leaveApproved) {
        this.leaveApproved.set(leaveApproved);
    }

    public String getFeedback() {
        return feedback.get();
    }

    public StringProperty feedbackProperty() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback.set(feedback);
    }

//    public boolean isRoomChange() {
//        return roomChange.get();
//    }
//
//    public BooleanProperty roomChangeProperty() {
//        return roomChange;
//    }
//
//    public void setRoomChange(boolean roomChange) {
//        this.roomChange.set(roomChange);
//    }
}
