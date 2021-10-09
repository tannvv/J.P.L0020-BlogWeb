/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.infor_user;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import tannv.util.DBHelper;

/**
 *
 * @author TanNV
 */
public class InforUserDAO {
    private Connection conn;
    private PreparedStatement ptsm;
    private ResultSet rs;
    final static Logger LOGGER = Logger.getLogger(InforUserDAO.class);

    public void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (ptsm != null) {
            ptsm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }
    
    public InforUserDTO getInforByID(String userID) throws SQLException{
        InforUserDTO infor = null;
        String sql = "SELECT userName, birthDay, roleID FROM InforUser \n"
                + "WHERE inforID = ?";
        try {
            conn = DBHelper.getConnection();
    
            ptsm = conn.prepareStatement(sql);
            ptsm.setString(1, userID);
            rs = ptsm.executeQuery();
            if (rs.next()) {
                Date birthDay = rs.getDate("birthDay");
                String userName = rs.getString("userName");
                int roleID = rs.getInt("roleID");
                infor = new InforUserDTO(userID, userName, birthDay, roleID);
            }
        } catch (Exception ex) {
            LOGGER.error("Get infor user by id is error" + ex.getMessage());
        }finally{
            closeConnection();
        }
        return infor;
    }
    
     public InforUserDTO createInfor(InforUserDTO infor) throws SQLException{
        String sql = "INSERT INTO InforUser(inforID, userName, birthDay, roleID) VALUES (?,?,?,?)";
        try {
            conn = DBHelper.getConnection();
            ptsm = conn.prepareStatement(sql);
            ptsm.setString(1, infor.getInforID());
            ptsm.setString(2, infor.getUserName());
            ptsm.setDate(3, infor.getBirthDay());
            ptsm.setInt(4, infor.getRole());
            if (ptsm.executeUpdate() == 1) {
                return infor;
            }
        } catch (Exception ex) {
           LOGGER.error("Get infor user by id is error" + ex.getMessage());
        }finally{
            closeConnection();
        }
        return null;
    }
}
