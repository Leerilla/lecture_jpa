package com.ohgiraffers.mapping.section02.embedded;

import java.time.LocalDate;

public class BookRegistDTO {

    private String bookTitle;
    private String author;
    private String publisher;
    private LocalDate publishedDate;
    private int regularPrice;
    private double discountRate;

    public BookRegistDTO(String bookTitle, String author, String publisher, LocalDate publishedDate, int regularPrice, double discountRate) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.regularPrice = regularPrice;
        this.discountRate = discountRate;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public int getRegularPrice() {
        return regularPrice;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    @Override
    public String toString() {
        return "BookRegistDTO{" +
                "bookTitle=" + bookTitle +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishedDate=" + publishedDate +
                ", regularPrice=" + regularPrice +
                ", discountRate=" + discountRate +
                '}';
    }
}
