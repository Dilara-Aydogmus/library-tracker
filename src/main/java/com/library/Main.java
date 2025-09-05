package com.library;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final Library library = new Library();

    public static void main(String[] args) {

        while (true) {
            printMenu();
            String choice = sc.nextLine().trim();

            try {
                switch (choice) {
                    case "1":
                        addBook();
                        break;
                    case "2":
                        list();
                        break;
                    case "3":
                        searchByTitle();
                        break;
                    case "4":
                        searchByAuthor();
                        break;
                    case "5":
                        borrow();
                        break;
                    case "6":
                        giveBack();
                        break;
                    case "7":
                        remove();
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Gecersiz secim.");
                }
            } catch (IllegalArgumentException | IllegalStateException | NoSuchElementException e) {
                System.out.println("Hata: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Beklenmeyen hata: " + e.getMessage());
            }

            System.out.println();
        }
    }
/*    Yanlış ID  IllegalArgumentException

    Kitap zaten ödünçte  IllegalStateException

    Listeden bulunamayan şey NoSuchElementException

    Diğer tüm hatalar için genel Exception


 */
    private static void printMenu() {
        System.out.println("=== Kutuphane Takip Sistemi ===");
        System.out.println("1) Kitap Ekle");
        System.out.println("2) Tum Kitaplari Listele");
        System.out.println("3) Basliga Gore Ara");
        System.out.println("4) Yazara Gore Ara");
        System.out.println("5) Odunc Al (ID)");
        System.out.println("6) Iade Et (ID)");
        System.out.println("7) Sil (ID)");
        System.out.println("0) Cikis");
        System.out.print("Secim: ");
    }

    private static void addBook() {
        System.out.print("ID: ");
        String idStr = sc.nextLine().trim();
        int id = Integer.parseInt(idStr);

        System.out.print("Baslik: ");
        String title = sc.nextLine().trim();
        title = null;

        System.out.print("Yazar: ");
        String author = sc.nextLine().trim();

        System.out.print("Yil: ");
        String yearStr = sc.nextLine().trim();
        int year = Integer.parseInt(yearStr);

        try{
            Book b = new Book(id, title, author, year);
            library.add(b);
            System.out.println("Kitap eklendi.");
        }
        catch(IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }



    }

    private static void list() {
        List<Book> all = library.listAll();
        if (all.isEmpty()) {
            System.out.println("Hic kitap yok.");
            return;
        }
        for (Book b : all) {
            System.out.println(b);
        }
    }

    private static void searchByTitle() {
        System.out.print("Baslik icin arama ifadesi: ");
        String title = sc.nextLine().trim();
        List<Book> result = library.searchByTitle(title);
        if (result.isEmpty()) {
            System.out.println("Eslesme bulunamadi.");
            return;
        }
        for (Book b : result) {
            System.out.println(b);
        }
    }

    private static void searchByAuthor() {
        System.out.print("Yazar icin arama ifadesi: ");
        String author = sc.nextLine().trim();
        List<Book> result = library.searchByAuthor(author);
        if (result.isEmpty()) {
            System.out.println("Eslesme bulunamadi.");
            return;
        }
        for (Book b : result) {
            System.out.println(b);
        }
    }
// "007" ---> parseInt("007") ---> 7 (int)
    //Çünkü Scanner.nextLine() her zaman String döner
    private static void borrow() {
        System.out.print("Odunc alinacak ID: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        library.borrowById(id);
        System.out.println("Odunc islemi basarili.");
    }

    private static void giveBack() {
        System.out.print("Iade edilecek ID: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        library.returnById(id);
        System.out.println("Iade islemi basarili.");
    }

    private static void remove() {
        System.out.print("Silinecek ID: ");
        int id = Integer.parseInt(sc.nextLine().trim());
        boolean removed = library.removeById(id);
        if (removed) {
            System.out.println("Kitap silindi.");
        } else {
            System.out.println("Kitap bulunamadi.");
        }
    }
}
