package com.library;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Library {
    private final List<Book> books = new ArrayList<>();

    public void add(Book book) {
        if (findById(book.getId()) != null) {
            throw new IllegalArgumentException("Ayni ID ile kitap zaten var: " + book.getId());
        }
        books.add(book);
    }

    public List<Book> listAll() {
        return new ArrayList<>(books);
    }

    public List<Book> searchByTitle(String query) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().contains(query)) {
                result.add(book);
            }
        }
        return result;
    }

    public List<Book> searchByAuthor(String query) {
        List<Book> result = new ArrayList<>();
        for (Book book : books) {
            if (book.getAuthor().contains(query)) {
                result.add(book);
            }
        }
        return result;
    }

    public void borrowById(int id) {
        Book book = requireById(id);
        if (book.isBorrowed()) {
            throw new IllegalStateException("Kitap zaten odunc alindi: " + id);
        }
        book.setBorrowed(true);
    }

    public void returnById(int id) {
        Book book = requireById(id);
        if (!book.isBorrowed()) {
            throw new IllegalStateException("Kitap oduncta degil: " + id);
        }
        book.setBorrowed(false);
    }

    public boolean removeById(int id) {
        Iterator<Book> iterator = books.iterator();
        while (iterator.hasNext()) {
            Book book = iterator.next();
            if (book.getId() == id) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    private Book findById(int id) {
        for (Book book : books) {
            if (book.getId() == id) return book;
        }
        return null;
    }

    private Book requireById(int id) {
        Book book = findById(id);
        if (book == null) {
            throw new IllegalArgumentException("ID bulunamadi: " + id);
        }
        return book;
    }
}
