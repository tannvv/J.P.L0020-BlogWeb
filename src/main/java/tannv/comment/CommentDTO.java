/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.comment;

import java.io.Serializable;
import java.sql.Date;
import tannv.util.Util;

/**
 *
 * @author TanNV
 */
public class CommentDTO implements Serializable{
    private int commentID, articleID;
    private String contentComment, userID;
    private Date commentDate;

    public CommentDTO() {
    }

    public CommentDTO(int commentID, int articleID, String contentComment, String userID, Date commentDate) {
        this.commentID = commentID;
        this.articleID = articleID;
        this.contentComment = contentComment;
        this.userID = userID;
        this.commentDate = commentDate;
    }

    public CommentDTO(int articleID, String contentComment, String userID) {
        this.articleID = articleID;
        this.contentComment = contentComment;
        this.userID = userID;
        java.util.Date today = new java.util.Date();
        this.commentDate = Util.utilDateToSqlDate(today);
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public String getContentComment() {
        return contentComment;
    }

    public void setContentComment(String contentComment) {
        this.contentComment = contentComment;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
    
    
    
}
