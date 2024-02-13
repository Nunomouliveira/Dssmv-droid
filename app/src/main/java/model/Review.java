package model;

public class Review {

    private String createdDate;
    private String id;
    private String isbn;
    private Boolean recommended;
    private String review;
    private String reviewer;

    public Review(String createdDate, String id, String isbn, Boolean recommended, String review, String reviewer) {
        this.createdDate = createdDate;
        this.id = id;
        this.isbn = isbn;
        this.recommended = recommended;
        this.review = review;
        this.reviewer = reviewer;
    }

    public String getCreatedDate() {return createdDate;}

    public void setCreatedDate(String createdDate) {this.createdDate = createdDate;}

    public String getId() {return id;}

    public void setId(String id) {this.id = id;}

    public String getIsbn() {return isbn;}

    public void setIsbn(String isbn) {this.isbn = isbn;}

    public Boolean getRecommended() {return recommended;}

    public void setRecommended(Boolean recommended) {this.recommended = recommended;}

    public String getReview() {return review;}

    public void setReview(String review) {this.review = review;}

    public String getReviewer() {return reviewer;}

    public void setReviewer(String reviewer) {this.reviewer = reviewer;}
}
