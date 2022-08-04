/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user;

/**
 *
 * @author ADMIN
 */
public class UserDTO {
    private String userID;
    private String password;
    private String fullName;
    private boolean roleID;
    private String address;
    private String birthday;
    private String phone;
    private boolean status;
    private String email;

    public UserDTO() {
        this.userID = "";
        this.password = "";
        this.fullName = "";
        this.roleID = false;
        this.address = "";
        this.birthday = "";
        this.phone = "";
        this.status = false;
        this.email = "";
    }
    
    public UserDTO(String userID, String password, String fullName, boolean roleID, String address, String birthday, String phone, boolean status) {
        this.userID = userID;
        this.password = password;
        this.fullName = fullName;
        this.roleID = roleID;
        this.address = address;
        this.birthday = birthday;
        this.phone = phone;
        this.status = status;
    }

    public UserDTO(String fullName, boolean roleID, boolean status) {
        this.fullName = fullName;
        this.roleID = roleID;
        this.status = status;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isAdmin() {
        return roleID;
    }

    public void setRoleID(boolean roleID) {
        this.roleID = roleID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActive() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
     
}