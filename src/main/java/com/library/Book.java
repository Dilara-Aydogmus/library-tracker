package com.library;

import org.apache.commons.lang3.StringUtils;

/**
 * Kitap bilgileri tutulur
 */
public class Book {

    private final int id;
    private final String title;
    private final String author;
    private final int year;
    private boolean borrowed;

    /**
     * Kitap yapılandırıcı
     * @param id kitap kimlik bilgisi
     * @param title kitap adı
     * @param author kitap yazarı
     * @param year kitap basım yılı
     */
    public Book(int id, String title, String author, int year) {

        if (StringUtils.isBlank(title)) {
            throw new IllegalArgumentException("Baslik bos olamaz");
        }
        if (author == null || author.isEmpty()) {
            throw new IllegalArgumentException("Yazar bos olamaz");
        }
        if (year <= 0) {
            throw new IllegalArgumentException("Yil pozitif olmali");
        }

        this.id = id;
        this.title = title.trim();
        this.author = author.trim();
        this.year = year;
        this.borrowed = false;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getYear() { return year; }
    public boolean isBorrowed() { return borrowed; }

    void setBorrowed(boolean borrowed) { this.borrowed = borrowed; }

    @Override
    public String toString() {
        return "id: " + id + " title: " + title + "author: " + author + " yil: " + year + (borrowed ? " ODUNC ALINDI " : " ODUNC ALINABILIR ");
    }

}
