package com.library;

import org.junit.Assert;
import org.junit.Test;

public class BookTest {

    @Test
    public void getTitle() {
        Book book = new Book(1, "baslik", "xyz", 2025);
        Assert.assertNotNull(book.getTitle());
    }

    @Test(expected = IllegalArgumentException.class)
    public void getAuthor() {
        Book book = new Book(1, "baslik", "", 2025);

        }
}