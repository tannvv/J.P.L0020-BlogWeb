/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tannv.article;

/**
 *
 * @author TanNV
 */
public class ArticleError {
    private String title,shortDescription,contentArticle, author;

    public ArticleError() {
    }

    public ArticleError(String title, String shortDescription, String contentArticle, String author) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.contentArticle = contentArticle;
        this.author = author;
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

    @Override
    public String toString() {
        return "ArticleError{" + "title=" + title + ", shortDescription=" + shortDescription + ", contentArticle=" + contentArticle + ", author=" + author + '}';
    }
    
    public static ArticleError validArticle(ArticleDTO dto){
        ArticleError error = new ArticleError();
        if (dto.getTitle().length() < 5 ||dto.getTitle().length() > 100 ) {
            error.setTitle("Title must 5-100 character");
        }
        if (dto.getAuthor().length() < 5 || dto.getAuthor().length() > 30 ) {
            error.setAuthor("Author must 5-30 character");
        }
        if (dto.getShortDescription().length() < 10 || dto.getShortDescription().length() < 10 ) {
            error.setShortDescription("Short description must be 10-200 characters");
        }
        if (dto.getContentArticle().length() <= 20 ) {
            error.setContentArticle("Content must more 20 character");
        }
        return error;
    }
}
