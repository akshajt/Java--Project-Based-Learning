package HostelPackage;

public class User {
    static public String prn;
    public String FName;
    public String LName;
    public int Year;
    public int roomNumber;
    public String contactNumber;
    static public float amount;

    public User() {}

    public User(String FName, String LName, int Year, int roomNumber, String contactNumber) {
        super();
        this.FName=FName;
        this.LName=LName;
        this.Year=Year;
        this.roomNumber=roomNumber;
        this.contactNumber=contactNumber;
    }

    public User(String prn,String FName, String LName, int Year, int roomNumber, String contactNumber, float amount) {
        super();
        this.prn=prn;
        this.FName=FName;
        this.LName=LName;
        this.Year=Year;
        this.roomNumber=roomNumber;
        this.contactNumber=contactNumber;
        this.amount=amount;
    }

    public String getPrn() {
        return prn;
    }

    public void setPrn(String prn) {
        this.prn = prn;
    }

    public String getFName() {
        return FName;
    }

    public void setFName(String FName) {
        this.FName = FName;
    }

    public String getLName() {
        return LName;
    }

    public void setLName(String LName) {
        this.LName = LName;
    }

    public int getYear() {
        return Year;
    }

    public void setYear(int year) {
        Year = year;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public static float getAmount() {
        return amount;
    }

    public static void setAmount(float amount) {
        User.amount = amount;
    }
}