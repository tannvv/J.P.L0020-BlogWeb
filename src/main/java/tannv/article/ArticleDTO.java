/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.article;

import java.sql.Date;
import tannv.util.Util;

/**
 *
 * @author TanNV
 */
public class ArticleDTO {
    private int articleID, status;
    private String title,shortDescription,contentArticle, author, userID;
    private Date postDate;

    public ArticleDTO() {
    }

    public ArticleDTO(String title, String shortDescription, String contentArticle, String author, String userID) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.contentArticle = contentArticle;
        this.author = author;
        this.userID = userID;
        this.status = 1;
        this.postDate = Util.utilDateToSqlDate(new java.util.Date());
    }

    
    public ArticleDTO(int articleID, int status, String title, String shortDescription, String contentArticle, String author, String userID, Date postDate) {
        this.articleID = articleID;
        this.status = status;
        this.title = title;
        this.shortDescription = shortDescription;
        this.contentArticle = contentArticle;
        this.author = author;
        this.userID = userID;
        this.postDate = postDate;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getContentArticle() {
        return contentArticle;
    }

    public void setContentArticle(String contentArticle) {
        this.contentArticle = contentArticle;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    @Override
    public String toString() {
        return "ArticleDTO{" + "articleID=" + articleID + ", status=" + status + ", title=" + title + ", author=" + author + ", userID=" + userID + ", postDate=" + postDate + '}' + "\n";
    }
    
    
    
    
}
