/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.comment;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import tannv.infor_user.InforUserDAO;
import tannv.util.DBHelper;

/**
 *
 * @author TanNV
 */
public class CommentDAO {
    private Connection conn;
    private PreparedStatement ptsm;
    private ResultSet rs;
    final static Logger LOGGER = Logger.getLogger(CommentDAO.class);

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
    
    public ArrayList<CommentDTO> getCommentByArticle(int articleID) throws SQLException{
        ArrayList<CommentDTO> listComment = null;
        String sql = "SELECT commentID, commentDate, contentComment, articleID, userID  FROM Comment \n"
                + "WHERE articleID =  ?";
        try {
            conn = DBHelper.getConnection();

            ptsm = conn.prepareStatement(sql);
            ptsm.setInt(1, articleID);
            rs = ptsm.executeQuery();
            listComment = new ArrayList<>();
            while (rs.next()) {                
                int commentID = rs.getInt("commentID");
                Date commentDate = rs.getDate("commentDate");
                String contentComment = rs.getString("contentComment");
                String userID = rs.getString("userID");
                CommentDTO comment = new CommentDTO(commentID, articleID, contentComment, userID, commentDate);
                listComment.add(comment);
            }
        } catch (Exception ex) {
            LOGGER.error("Get comment by articleID is error" + ex.getMessage());
        }finally{
            closeConnection();
        }
        return listComment;
    }
    public CommentDTO createComment(CommentDTO comment) throws SQLException{
        String sql = "INSERT INTO Comment(commentDate, contentComment, articleID, userID) VALUES (?,?,?,?)";
        try {
            conn = DBHelper.getConnection();
            ptsm = conn.prepareStatement(sql);
            ptsm.setDate(1, comment.getCommentDate());
            ptsm.setString(2, comment.getContentComment());
            ptsm.setInt(3, comment.getArticleID());
            ptsm.setString(4, comment.getUserID());
            if (ptsm.executeUpdate() == 1) {
                return comment;
            }
        } catch (Exception ex) {
            LOGGER.error("Create comment is error" + ex.getMessage());
        }finally{
            closeConnection();
        }
        return null;
    }
    
}

