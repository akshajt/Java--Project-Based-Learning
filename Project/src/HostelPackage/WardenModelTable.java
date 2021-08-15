package HostelPackage;

import javafx.beans.property.*;

public class WardenModelTable {

    private final StringProperty prn;
    private final StringProperty FName, LName;
    private final StringProperty departureDate;
    private final StringProperty arrivalDate;
    private final StringProperty leaveReason;
    private final BooleanProperty leaveApproved;

    public WardenModelTable(String  prn, String FName, String LName,
                      String departureDate, String arrivalDate, String leaveReason, Boolean leaveApproved) {
        this.prn = new SimpleStringProperty(prn);
        this.FName = new SimpleStringProperty(FName);
        this.LName = new SimpleStringProperty(LName);
        this.departureDate = new SimpleStringProperty(departureDate);
        this.arrivalDate = new SimpleStringProperty(arrivalDate);
        this.leaveReason = new SimpleStringProperty(leaveReason);
        this.leaveApproved = new SimpleBooleanProperty(leaveApproved);
    }

    public String getPrn() {
        return prn.get();
    }

    public void setPrn(String value) {
        prn.set(value);
    }

    public String getFName() {
        return FName.get();
    }

    public void setFName(String value) {
        FName.set(value);
    }

    public String getLName() {
        return LName.get();
    }

    public void setLName(String value) {
        LName.set(value);
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
}
