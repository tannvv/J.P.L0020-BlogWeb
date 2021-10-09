/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.account;

import java.sql.SQLException;
import javax.naming.NamingException;

/**
 *
 * @author TanNV
 */
public class AccountError {
    private String email, password, rePassword;

    public AccountError() {
    }

    public AccountError(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRePassword() {
        return rePassword;
    }

    public void setRePassword(String rePassword) {
        this.rePassword = rePassword;
    }
    

    @Override
    public String toString() {
        return "AccountError{" + "email=" + email + ", password=" + password + '}';
    }
    
    public static AccountError checkValidAccount(AccountDTO dto) throws NamingException, SQLException{
        AccountError error = new AccountError();
        if (!dto.getEmail().matches("[a-zA-Z0-9._]+@{1}+[a-zA-Z0-9.]{5,30}")) {
            error.setEmail("Email invalid, format email have to ***@***, don't have special character, 5-30 characters");
        }
        AccountDAO dao = new AccountDAO();
        boolean chechExist = dao.checkEmail(dto.getEmail());
        if (chechExist) {   // email is existed
            error.setEmail("Email is existed");
        }
        if (!dto.getPassword().matches("[a-zA-Z0-9]{5,30}")) {
            error.setPassword("Invalid password, password have to 5-30 character and don't have any special chracter");
        }
        
        return error;
    } 
}
