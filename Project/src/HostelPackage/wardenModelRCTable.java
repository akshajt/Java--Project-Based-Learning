package HostelPackage;

import javafx.beans.property.*;

public class wardenModelRCTable {

    private final StringProperty prn;
    private final StringProperty FName;
    private final StringProperty LName;
    private final StringProperty roomNumber;
    private final BooleanProperty roomChange;

    public wardenModelRCTable(String prn, String FName, String LName, String roomNumber, Boolean roomChange) {
        this.prn = new SimpleStringProperty(prn);
        this.FName = new SimpleStringProperty(FName);
        this.LName = new SimpleStringProperty(LName);
        this.roomNumber = new SimpleStringProperty(roomNumber);
        this.roomChange = new SimpleBooleanProperty(roomChange);
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

    public boolean isRoomChange() {
        return roomChange.get();
    }

    public BooleanProperty roomChangeProperty() {
        return roomChange;
    }

    public void setRoomChange(boolean roomChange) {
        this.roomChange.set(roomChange);
    }
}
