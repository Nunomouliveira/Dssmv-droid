package model;

import android.graphics.Bitmap;

import java.util.List;

public class Library {

    private String address;
    private String id;
    private String name;
    private Boolean open;
    private String openDays;
    private String openStatement;
    private String openTime;
    private String closeTime;



    public Library() {}

    public Library(String name, String id, String address, Boolean open, String openDays, String openStatement, String openTime, String closeTime) {
        this.address = address;
        this.id = id;
        this.name = name;
        this.open = open;
        this.openDays = openDays;
        this.openStatement = openStatement;
        this.openTime = openTime;
        this.closeTime = closeTime;
    }


    public String getAddress() {return address;}

    public void setAddress(String address) {this.address = address;}

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public Boolean getOpen() {return open;}

    public void setOpen(Boolean open) {this.open = open;}

    public String getOpenDays() {return openDays;}

    public void setOpenDays(String openDays) {this.openDays = openDays;}

    public String getOpenStatement() {return openStatement;}

    public void setOpenStatement(String openStatement) {this.openStatement = openStatement;}

    public String getOpenTime() {return openTime;}

    public void setOpenTime(String openTime) {this.openTime = openTime;}

    public String getCloseTime() {return closeTime;}

    public void setCloseTime(String closeTime) {this.closeTime = closeTime;}

}
