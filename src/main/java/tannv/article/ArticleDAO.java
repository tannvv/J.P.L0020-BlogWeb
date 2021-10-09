/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.article;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.log4j.Logger;
import tannv.util.DBHelper;
import tannv.util.Util;

/**
 *
 * @author TanNV
 */
public class ArticleDAO {

    private Connection conn;
    private PreparedStatement ptsm;
    private ResultSet rs;
    final static Logger LOGGER = Logger.getLogger(ArticleDAO.class);

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

    public ArrayList<ArticleDTO> getPageArticleAdmin(int pageNumber) throws SQLException {
        ArrayList<ArticleDTO> list = null;
        try {
            String sql = "SELECT articleID, title, shortDescription, contentArticle,postDate, author, status, userID \n"
                    + "FROM Article \n"
                    + "ORDER BY postDate DESC \n"
                    + "OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY";
               conn = DBHelper.getConnection();
            ptsm = conn.prepareStatement(sql);
            ptsm.setInt(1, ((pageNumber - 1) * 20));
            rs = ptsm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                int articleID = rs.getInt("articleID");
                String title = rs.getString("title");
                String shorDescription = rs.getString("shortDescription");
                String contentArticle = rs.getString("contentArticle");
                Date postDate = rs.getDate("postDate");
                String author = rs.getString("author");
                int status = rs.getInt("status");
                String userID = rs.getString("userID");
                ArticleDTO dto = new ArticleDTO(articleID, status, title, shorDescription, contentArticle, author, userID, postDate);
                list.add(dto);
            }
        } catch (Exception e) {
            LOGGER.error("Get page with role admin is error" + e.getMessage());
        } finally {
            closeConnection();
        }

        return list;
    }

    public ArrayList<ArticleDTO> getPageArticleUser(int pageNumber) throws SQLException {
        ArrayList<ArticleDTO> list = null;
        try {
            String sql = "SELECT articleID, title, shortDescription, contentArticle,postDate, author, status, userID \n"
                    + "FROM Article WHERE status = 2\n"
                    + "ORDER BY postDate DESC \n"
                    + "OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY";

               conn = DBHelper.getConnection();
            ptsm = conn.prepareStatement(sql);
            ptsm.setInt(1, ((pageNumber - 1) * 20));
            rs = ptsm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                int articleID = rs.getInt("articleID");
                String title = rs.getString("title");
                String shorDescription = rs.getString("shortDescription");
                String contentArticle = rs.getString("contentArticle");
                Date postDate = rs.getDate("postDate");
                String author = rs.getString("author");
                int status = rs.getInt("status");
                String userID = rs.getString("userID");
                ArticleDTO dto = new ArticleDTO(articleID, status, title, shorDescription, contentArticle, author, userID, postDate);
                list.add(dto);
            }
        } catch (Exception e) {
             LOGGER.error("Get page with role user is error" + e.getMessage());
        } finally {
            closeConnection();
        }

        return list;
    }

    public ArticleDTO getArticleByID(int id) throws SQLException {
        ArticleDTO articleDTO = null;
        try {
            String sql = "SELECT title, shortDescription, contentArticle,postDate, author, status, userID \n"
                    + "FROM Article WHERE articleID = ?";

               conn = DBHelper.getConnection();
            ptsm = conn.prepareStatement(sql);
            ptsm.setInt(1, id);
            rs = ptsm.executeQuery();
            if (rs.next()) {
                String title = rs.getString("title");
                String shorDescription = rs.getString("shortDescription");
                String contentArticle = rs.getString("contentArticle");
                Date postDate = rs.getDate("postDate");
                String author = rs.getString("author");
                int status = rs.getInt("status");
                String userID = rs.getString("userID");
                articleDTO = new ArticleDTO(id, status, title, shorDescription, contentArticle, author, userID, postDate);
            }
        } catch (Exception e) {
             LOGGER.error("Get article by ID is error" + e.getMessage());
        } finally {
            closeConnection();
        }

        return articleDTO;
    }

    public ArrayList<ArticleDTO> getArticleByContentUser(String content, int page) throws SQLException {
        ArrayList<ArticleDTO> list = null;
        try {
            String sql = "SELECT articleID, title, shortDescription, contentArticle,postDate, author, status, userID \n"
                    + "FROM Article \n"
                    + "WHERE contentArticle LIKE ? OR title LIKE ? AND status = 2"
                    + "ORDER BY postDate DESC \n"
                    + "OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY";
        
               conn = DBHelper.getConnection();
            ptsm = conn.prepareStatement(sql);
            ptsm.setString(1, "%" + content + "%");
            ptsm.setString(2, "%" + content + "%");
            ptsm.setInt(3, ((page - 1) * 20));
            rs = ptsm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                int articleID = rs.getInt("articleID");
                String title = rs.getString("title");
                String shorDescription = rs.getString("shortDescription");
                String contentArticle = rs.getString("contentArticle");
                Date postDate = rs.getDate("postDate");
                String author = rs.getString("author");
                int status = rs.getInt("status");
                String userID = rs.getString("userID");
                ArticleDTO dto = new ArticleDTO(articleID, status, title, shorDescription, contentArticle, author, userID, postDate);
                list.add(dto);
            }
        } catch (Exception e) {
             LOGGER.error("Get content article" + e.getMessage());
        } finally {
            closeConnection();
        }

        return list;
    }
    
    public ArrayList<ArticleDTO> getArticleByContentAdmin(String content, int page) throws SQLException {
        ArrayList<ArticleDTO> list = null;
        try {
            String sql = "SELECT articleID, title, shortDescription, contentArticle,postDate, author, status, userID \n"
                    + "FROM Article \n"
                    + "WHERE contentArticle LIKE ?";

            
               conn = DBHelper.getConnection();
            ptsm = conn.prepareStatement(sql);
            ptsm.setString(1, "%" + content + "%");
            rs = ptsm.executeQuery();
            list = new ArrayList<>();
            while (rs.next()) {
                int articleID = rs.getInt("articleID");
                String title = rs.getString("title");
                String shorDescription = rs.getString("shortDescription");
                String contentArticle = rs.getString("contentArticle");
                Date postDate = rs.getDate("postDate");
                String author = rs.getString("author");
                int status = rs.getInt("status");
                String userID = rs.getString("userID");
                ArticleDTO dto = new ArticleDTO(articleID, status, title, shorDescription, contentArticle, author, userID, postDate);
                list.add(dto);
            }
        } catch (Exception e) {
             LOGGER.error("Get content article for admin" + e.getMessage());
        } finally {
            closeConnection();
        }

        return list;
    }

    public ArrayList<ArticleDTO> getArticleByContentAndStatus(String content, int statusSelect, int page) throws SQLException {
        ArrayList<ArticleDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT articleID, title, shortDescription, contentArticle,postDate, author, status, userID \n"
                    + "FROM Article \n"
                    + "WHERE (contentArticle LIKE ? OR title LIKE ?) AND status = ? \n"
                    + "ORDER BY postDate DESC \n"
                    + "OFFSET ? ROWS FETCH NEXT 20 ROWS ONLY";
           
               //conn = DBHelper.getConnection();
               conn = DBHelper.getConnection_2();
            ptsm = conn.prepareStatement(sql);
            ptsm.setString(1, "%" + content + "%");
            ptsm.setString(2, "%" + content + "%");
            ptsm.setInt(3, statusSelect);
            ptsm.setInt(4, ((page - 1) * 20));
            rs = ptsm.executeQuery();
            while (rs.next()) {
                int articleID = rs.getInt("articleID");
                String title = rs.getString("title");
                String shorDescription = rs.getString("shortDescription");
                String contentArticle = rs.getString("contentArticle");
                Date postDate = rs.getDate("postDate");
                String author = rs.getString("author");
                int status = rs.getInt("status");
                String userID = rs.getString("userID");
                ArticleDTO dto = new ArticleDTO(articleID, status, title, shorDescription, contentArticle, author, userID, postDate);
                list.add(dto);
            }
        } catch (Exception e) {
             LOGGER.error("Get article by content and static is error" + e.getMessage());
        } finally {
            closeConnection();
        }

        return list;
    }

    public boolean deleteArticle(int id) throws SQLException {
        try {
            String sql = "UPDATE Article SET status = 0 WHERE articleID = ?";
            conn = DBHelper.getConnection_2();
            ptsm = conn.prepareStatement(sql);
            ptsm.setInt(1, id);
            if (ptsm.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception e) {
             LOGGER.error("Delete article is error" + e.getMessage());
        } finally {
            closeConnection();
        }

        return false;
    }

    public int getPageNumberUser() throws SQLException {
        int result = -1;
        try {
            String sql = "SELECT count(articleID) AS numberPage\n"
                    + "FROM Article WHERE status = 2";
            
               conn = DBHelper.getConnection();
            ptsm = conn.prepareStatement(sql);
            rs = ptsm.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("numberPage");
                result = Util.numberPage(count, 20);
            }
        } catch (Exception e) {
             LOGGER.error("Get number page of article is error" + e.getMessage());
        } finally {
            closeConnection();
        }
        return result;
    }

    public int getPageNumberAdmin() throws SQLException {
        int result = -1;
        try {
            String sql = "SELECT count(articleID) AS numberPage\n"
                    + "FROM Article";
           
               conn = DBHelper.getConnection();
            ptsm = conn.prepareStatement(sql);
            rs = ptsm.executeQuery();
            if (rs.next()) {
                int count = rs.getInt("numberPage");
                result = Util.numberPage(count, 20);
            }
        } catch (Exception e) {
             LOGGER.error("Get number page with role admin is error" + e.getMessage());
        } finally {
            closeConnection();
        }
        return result;
    }

    public ArticleDTO createArticle(ArticleDTO article) throws SQLException {
        try {
            String sql = "INSERT INTO Article(title,shortDescription,contentArticle,postDate,author,status,userID) \n"
                    + "VALUES (?,?,?,?,?,?,?)";
            
//               conn = DBHelper.getConnection();
            conn = DBHelper.getConnection_2();
            ptsm = conn.prepareStatement(sql);
            ptsm.setString(1, article.getTitle());
            ptsm.setString(2, article.getShortDescription());
            ptsm.setString(3, article.getContentArticle());
            ptsm.setDate(4, article.getPostDate());
            ptsm.setString(5, article.getAuthor());
            ptsm.setInt(6, article.getStatus());
            ptsm.setString(7, article.getUserID());
            if (ptsm.executeUpdate() == 1) {
                return article;
            }
        } catch (Exception e) {
             LOGGER.error("Create article is error" + e.getMessage());
        } finally {
            closeConnection();
        }
        return null;
    }
     public boolean updateStatus(int id, int status) throws SQLException {
        try {
            String sql = "UPDATE Article SET status = ? WHERE articleID = ?";
            
               conn = DBHelper.getConnection();
            ptsm = conn.prepareStatement(sql);
            ptsm.setInt(1, status);
            ptsm.setInt(2, id);
            if (ptsm.executeUpdate() == 1) {
                return true;
            }
        } catch (Exception e) {
             LOGGER.error("Update article is error" + e.getMessage());
        } finally {
            closeConnection();
        }

        return false;
    }
}
