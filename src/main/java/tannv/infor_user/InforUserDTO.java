/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.infor_user;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author TanNV
 */
public class InforUserDTO implements Serializable{
    private String inforID, userName;
    private Date birthDay;
    private int role;

    public InforUserDTO() {
    }

    public InforUserDTO(String inforID, String userName, Date birthDay, int role) {
        this.inforID = inforID;
        this.userName = userName;
        this.birthDay = birthDay;
        this.role = role;
    }

    public String getInforID() {
        return inforID;
    }

    public void setInforID(String inforID) {
        this.inforID = inforID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }
    
}
