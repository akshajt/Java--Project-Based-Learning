package HostelPackage;

import javafx.beans.property.*;


public class WardenModelHKTable {

    private final StringProperty prn, FName, LName, roomNumber;
    private final BooleanProperty requestHousekeeping;
    private final StringProperty feedback;

    public WardenModelHKTable(String prn, String FName, String LName, String roomNumber, Boolean requestHousekeeping, String feedback) {
        this.prn = new SimpleStringProperty(prn);
        this.FName = new SimpleStringProperty(FName);
        this.LName = new SimpleStringProperty(LName);
        this.roomNumber = new SimpleStringProperty(roomNumber);
        this.requestHousekeeping = new SimpleBooleanProperty(requestHousekeeping);
        this.feedback = new SimpleStringProperty(feedback);
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

    public String getRoomNumber() {
        return roomNumber.get();
    }

    public StringProperty roomNumberProperty() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber.set(roomNumber);
    }

    public boolean isRequestHousekeeping() {
        return requestHousekeeping.get();
    }

    public BooleanProperty requestHousekeepingProperty() {
        return requestHousekeeping;
    }

    public void setRequestHousekeeping(boolean requestHousekeeping) {
        this.requestHousekeeping.set(requestHousekeeping);
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
}
