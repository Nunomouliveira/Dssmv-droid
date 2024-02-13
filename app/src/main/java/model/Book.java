package model;

import android.graphics.Bitmap;

import java.util.List;

public class Book {

    private List<Author> authors;
    private String byStatement;
    private CoverUrls cover;
    private String description;
    private String isbn;
    private String numberOfPages;
    private String publishDate;
    private String title;
    private String subjectPeople;
    private String subjectPlaces;
    private String subjectTimes;
    private String subjects;



    public Book(List<Author> authors, String byStatement, CoverUrls cover, String description, String isbn, String numberOfPages, String publishDate, String title, String subjectPeople, String subjectPlaces, String subjectTimes, String subjects) {
        this.authors = authors;
        this.byStatement = byStatement;
        this.cover = cover;
        this.description = description;
        this.isbn = isbn;
        this.numberOfPages = numberOfPages;
        this.publishDate = publishDate;
        this.title = title;
        this.subjectPeople = subjectPeople;
        this.subjectPlaces = subjectPlaces;
        this.subjectTimes = subjectTimes;
        this.subjects = subjects;
    }

    public Book() {
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getByStatement() {
        return byStatement;
    }

    public void setByStatement(String byStatement) {
        this.byStatement = byStatement;
    }

    public CoverUrls getCover() {
        return cover;
    }

    public void setCover(CoverUrls cover) {
        this.cover = cover;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(String numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(String publishDate) {
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubjectPeople() {
        return subjectPeople;
    }

    public void setSubjectPeople(String subjectPeople) {
        this.subjectPeople = subjectPeople;
    }

    public String getSubjectPlaces() {
        return subjectPlaces;
    }

    public void setSubjectPlaces(String subjectPlaces) {
        this.subjectPlaces = subjectPlaces;
    }

    public String getSubjectTimes() {
        return subjectTimes;
    }

    public void setSubjectTimes(String subjectTimes) {
        this.subjectTimes = subjectTimes;
    }

    public String getSubjects() {
        return subjects;
    }

    public void setSubjects(String subjects) {
        this.subjects = subjects;
    }
}

