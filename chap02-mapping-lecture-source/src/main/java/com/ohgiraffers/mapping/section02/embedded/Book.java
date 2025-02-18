package com.ohgiraffers.mapping.section02.embedded;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tbl_book")
public class Book {

    @Id
    @Column(name = "BOOK_NO")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookNo;

    @Column(name = "BOOK_TITLE")
    private String bookTitle;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "PUBLISHER")
    private String publisher;

    @Column(name = "PUBLISHED_DATE")
    private LocalDate publishedDate;

//    @Column(name = "REGULAR_PRICE")
//    private int regularPrice;
//
//    @Column(name = "DISCOUNT_RATE")
//    private double discountRate;
//
//    @Column(name = "SELL_PRICE")
//    private int sellPrice;

    @Embedded
    private Price price;

    protected Book() {}

    public Book(String bookTitle, String author, String publisher, LocalDate publishedDate, Price price) {
        this.bookTitle = bookTitle;
        this.author = author;
        this.publisher = publisher;
        this.publishedDate = publishedDate;
        this.price = price;
    }

    public int getBookNo() {
        return bookNo;
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

    public Price getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookNo=" + bookNo +
                ", bookTitle=" + bookTitle +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", publishedDate=" + publishedDate +
                ", price=" + price +
                '}';
    }
}
