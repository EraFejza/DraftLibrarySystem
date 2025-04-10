package IntegrationTesting;

import LibraryManager.Book;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

public class BookOperationsTest {

    static Book bookWithoutDates;
    static Book bookWithDates;

    static Date today = new Date();
    static ArrayList<Date> soldDates = new ArrayList<>();
    static ArrayList<Integer> soldQuantities = new ArrayList<>();
    static ArrayList<Date> purchasedDates = new ArrayList<>();
    static ArrayList<Integer> purchasedQuantities = new ArrayList<>();

    @BeforeAll
    public static void setUp(){
        bookWithoutDates = new Book("0096184570112","Book_Without_Dates","Modernist","Ingram Content Group, Inc",65.00,73.96,"Marcel Proust",5);

        bookWithDates = new Book("4647500268094","Book_With_Dates","Fiction","Baker & Taylor",15.00,18.00,"James Joyce",2);
        bookWithDates.addDate(today);
        bookWithDates.addQuantity(2);
        bookWithDates.addPurchasedDate(today);
        bookWithDates.addQuantitiesPurchased(1);

        soldDates.add(today);
        soldQuantities.add(2);
        purchasedDates.add(today);
        purchasedQuantities.add(1);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        bookWithDates.addDate(calendar.getTime());
        bookWithDates.addQuantity(6);
        bookWithDates.addPurchasedDate(calendar.getTime());
        bookWithDates.addQuantitiesPurchased(6);

        soldDates.add(calendar.getTime());
        soldQuantities.add(6);
        purchasedDates.add(calendar.getTime());
        purchasedQuantities.add(6);

        calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        bookWithDates.addDate(calendar.getTime());
        bookWithDates.addQuantity(2);
        bookWithDates.addPurchasedDate(calendar.getTime());
        bookWithDates.addQuantitiesPurchased(6);

        soldDates.add(calendar.getTime());
        soldQuantities.add(2);
        purchasedDates.add(calendar.getTime());
        purchasedQuantities.add(6);

        calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.MONTH, -1);
        bookWithDates.addDate(calendar.getTime());
        bookWithDates.addQuantity(4);
        bookWithDates.addPurchasedDate(calendar.getTime());
        bookWithDates.addQuantitiesPurchased(3);

        soldDates.add(calendar.getTime());
        soldQuantities.add(4);
        purchasedDates.add(calendar.getTime());
        purchasedQuantities.add(3);

        calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.MONTH, 1);
        bookWithDates.addDate(calendar.getTime());
        bookWithDates.addQuantity(7);
        bookWithDates.addPurchasedDate(calendar.getTime());
        bookWithDates.addQuantitiesPurchased(4);

        soldDates.add(calendar.getTime());
        soldQuantities.add(7);
        purchasedDates.add(calendar.getTime());
        purchasedQuantities.add(4);

        calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.YEAR, 1);
        bookWithDates.addDate(calendar.getTime());
        bookWithDates.addQuantity(5);
        bookWithDates.addPurchasedDate(calendar.getTime());
        bookWithDates.addQuantitiesPurchased(1);

        soldDates.add(calendar.getTime());
        soldQuantities.add(5);
        purchasedDates.add(calendar.getTime());
        purchasedQuantities.add(1);
    }

    // Testing Book.RemoveStock()
    @Test
    public void test_removeStock(){
        Book book = new Book("testISBN");
        book.AddStock(5);
        int stock_to_remove = 5;
        book.RemoveStock(stock_to_remove);
        assertEquals(0, book.getStock());
    }

    // Testing Book.AddStock()
    @Test
    public void test_addStock(){
        Book book = new Book("testISBN");
        book.AddStock(5);
        int stock_to_add = 5;
        book.AddStock(stock_to_add);
        assertEquals(10, book.getStock());
    }

    // Testing Book.getPurchasedAmount()
    @Test
    public void test_getPurchasedAmount_noPurchases(){
        int expected = 0;
        int result = bookWithoutDates.getPurchasedAmount();
        assertEquals(expected, result);
    }

    @Test
    public void test_getPurchasedAmount_withPurchases(){
        int expected = 26;
        int result = bookWithDates.getPurchasedAmount();
        assertEquals(expected, result);
    }

    // Testing Book.getSoldDatesQuantitiesTotal()
    @Test
    public void test_getSoldDatesQuantitiesTotal_noDates() {
        String expectedResult = "Book_Without_Dates has had no purchases\n";
        String result = bookWithoutDates.getSoldDatesQuantitiesTotal();
        assertEquals(expectedResult, result);
    }

    @Test
    public void test_getSoldDatesQuantitiesTotal_withDates() {
        StringBuilder expectedTitle = new StringBuilder("For \"Book_With_Dates\" We have sold:\n");
        for (int i=0;i<soldDates.size();i++){
            expectedTitle.append(soldQuantities.get(i)).append(" at ").append(soldDates.get(i)).append("\n");
        }
        assertEquals(expectedTitle.toString(), bookWithDates.getSoldDatesQuantitiesTotal());
    }

    // Testing Book.getSoldDatesQuantitiesMonth()
    @Test
    public void test_getSoldDatesQuantitiesMonth_noDates() {
        String expectedResult = "Book_Without_Dates has had no purchases\n";
        String result = bookWithoutDates.getSoldDatesQuantitiesMonth();
        assertEquals(expectedResult, result);
    }

    @Test
    public void test_getSoldDatesQuantitiesMonth_withDates() {
        String expectedTitle = "For \"Book_With_Dates\" We have sold in a month:\n";
        String expectedResult = expectedTitle +
                soldQuantities.get(0) + " at " + soldDates.get(0) + "\n" +
                soldQuantities.get(1) + " at " + soldDates.get(1) + "\n" +
                soldQuantities.get(2) + " at " + soldDates.get(2) + "\n";
        String result = bookWithDates.getSoldDatesQuantitiesMonth();
        assertEquals(expectedResult, result);
    }

    // Testing Book.getSoldDatesQuantitiesDay()
    @Test
    public void test_getSoldDatesQuantitiesDay_noSales() {
        String expected = bookWithoutDates.getTitle() + " has had no purchases\n";
        String result = bookWithoutDates.getSoldDatesQuantitiesDay();
        assertEquals(expected, result);
    }

    @Test
    public void test_getSoldDatesQuantitiesDay_withSales(){
        String expected = "For \"" + bookWithDates.getTitle() + "\" We have sold in a day:\n" +
                soldQuantities.get(0) + " at " + soldDates.get(0) + "\n";
        String result = bookWithDates.getSoldDatesQuantitiesDay();
        assertEquals(expected, result);
    }

    // Testing Book.getBoughtDatesQuantitiesTotal()
    @Test
    public void test_getBoughtDatesQuantitiesTotal_noDates() {
        String expectedResult = "We have made no purchases on \"Book_Without_Dates\"\n";
        String result = bookWithoutDates.getBoughtDatesQuantitiesTotal();
        assertEquals(expectedResult, result);
    }

    @Test
    public void test_getBoughtDatesQuantitiesTotal_withDates() {
        StringBuilder expectedTitle = new StringBuilder("For \"Book_With_Dates\" We have bought in a day:\n");
        for (int i=0;i<purchasedQuantities.size();i++){
            expectedTitle.append(purchasedQuantities.get(i)).append(" at ").append(purchasedDates.get(i)).append("\n");
        }
        assertEquals(expectedTitle.toString(), bookWithDates.getBoughtDatesQuantitiesTotal());
    }

    // Testing Book.getBoughtDatesQuantitiesYear()
    @Test
    public void test_getBoughtDatesQuantitiesYear_noDates() {
        String expectedResult = "We have made no purchases on \"Book_Without_Dates\"\n";
        String result = bookWithoutDates.getBoughtDatesQuantitiesYear();
        assertEquals(expectedResult, result);
    }

    @Test
    public void test_getBoughtDatesQuantitiesYear_withDates() {
        String expectedTitle = "For \"Book_With_Dates\" We have bought in a year:\n";
        StringBuilder expectedResult = new StringBuilder(expectedTitle);
        for (int i = 0; i < purchasedQuantities.size() - 1; i++) {
            if (i == 3)
                continue;
            expectedResult.append(purchasedQuantities.get(i)).append(" at ").append(purchasedDates.get(i)).append("\n");
        }
        String result = bookWithDates.getBoughtDatesQuantitiesYear();
        assertEquals(expectedResult.toString(), result);
    }

    // Testing Book.getBoughtDatesQuantitiesDay()
    @Test
    public void test_getBoughtDatesQuantitiesDay_noPurchases(){
        String expected = "We have made no purchases on \""+ bookWithoutDates.getTitle() +"\"\n";
        String result = bookWithoutDates.getBoughtDatesQuantitiesDay();
        assertEquals(expected, result);
    }

    @Test
    public void test_getBoughtDatesQuantitiesDay_withPurchases(){
        String expected = "For \"" + bookWithDates.getTitle() +"\" We have bought in a day:\n" +
                purchasedQuantities.get(0) + " at " + purchasedDates.get(0) + "\n";
        String result = bookWithDates.getBoughtDatesQuantitiesDay();
        assertEquals(expected, result);
    }

    // Testing Book.getNumberDatesMonth()
    @Test
    public void test_getNumberDatesMonth_noDates() {
        int result = bookWithoutDates.getNumberDatesMonth(new ArrayList<>(), new ArrayList<>());
        assertEquals(0, result);
    }

    @Test
    public void test_getNumberDatesMonth_withDates() {
        int result = bookWithoutDates.getNumberDatesMonth(soldDates, soldQuantities);
        assertEquals(10, result);
    }

    // Testing Book.getTotalBooksBoughtDay()
    @Test
    public void test_getTotalBooksBoughtDay_noDates(){
        assertEquals(0, bookWithoutDates.getTotalBooksBoughtDay());
    }

    @Test
    public void test_getTotalBooksBoughtDay_withDates() {
        assertEquals(1, bookWithDates.getTotalBooksBoughtDay());
    }

    // Testing Book.getTotalBooksBoughtYear()
    @Test
    public void test_getTotalBooksBoughtYear() {
        int expected = 0;
        for (int i = 0; i < purchasedDates.size(); i++) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(purchasedDates.get(i));
            if (cal.get(Calendar.YEAR) == Calendar.getInstance().get(Calendar.YEAR)) {
                expected += purchasedQuantities.get(i);
            }
        }
        int actual = bookWithDates.getTotalBooksBoughtYear();
        assertEquals(expected, actual);
    }

    // Testing Book.getQuantitiesPurchased()
    @Test
    public void test_getQuantitiesPurchased_noPurchases() {
        int result = bookWithoutDates.getQuantitiesPurchased();
        assertEquals(0, result);
    }

    @Test
    public void test_getQuantitiesPurchased_withPurchases() {
        int result = bookWithDates.getQuantitiesPurchased();
        assertEquals(21, result);
    }

    // Testing Book.getSoldDatesQuantitiesYear()
    @Test
    public void test_getSoldDatesQuantitiesYear_noSales() {
        String result = bookWithoutDates.getSoldDatesQuantitiesYear();
        assertEquals(bookWithoutDates.getTitle() + " has had no purchases\n", result);
    }

    @Test
    public void test_getSoldDatesQuantitiesYear_withSales() {
        String result = bookWithDates.getSoldDatesQuantitiesYear();
        StringBuilder expected = new StringBuilder("For \"" + bookWithDates.getTitle()  +"\" We have sold in a year:\n");
        for (int i=0; i<soldDates.size()-1; i++){
            if (i==3)
                continue;
            expected.append(soldQuantities.get(i)).append(" at ").append(soldDates.get(i)).append("\n");
        }
        assertEquals(expected.toString(), result);
    }

    // Testing Book.getSoldBoughtQuantitiesMonth()
    @Test
    public void test_getBoughtDatesQuantitiesMonth_noPurchases() {
        String result = bookWithoutDates.getBoughtDatesQuantitiesMonth();
        String expectedMessage = "We have made no purchases on \"" + bookWithoutDates.getTitle()+"\"\n" ;
        assertEquals(expectedMessage, result);
    }

    @Test
    public void test_getBoughtDatesQuantitiesMonth_withPurchases() {
        String result = bookWithDates.getBoughtDatesQuantitiesMonth();
        String expected = "For \"" + bookWithDates.getTitle() + "\" We have bought in a month:\n" +
                purchasedQuantities.get(0) + " at " + purchasedDates.get(0) + "\n" +
                purchasedQuantities.get(1) + " at " + purchasedDates.get(1) + "\n" +
                purchasedQuantities.get(2) + " at " + purchasedDates.get(2) + "\n";
        assertEquals(expected, result);
    }

    // Testing Book.getTotalBooksSoldMonth()
    @Test
    public void test_getTotalBooksSoldMonth_noSales() {
        int result = bookWithoutDates.getTotalBooksSoldMonth();
        assertEquals(0, result);
    }

    @Test
    public void test_getTotalBooksSoldMonth_withSales() {
        int result = bookWithDates.getTotalBooksSoldMonth();
        int expected = 10;
        assertEquals(expected, result);
    }

    // Testing Book.getTotalBooksSoldDay()
    @Test
    public void test_getTotalBooksSoldDay_noSales(){
        int expected = 0;
        int result = bookWithoutDates.getTotalBooksSoldDay();
        assertEquals(expected, result);
    }

    @Test
    public void test_getTotalBooksSoldDay_withSales(){
        int expected = 2;
        int result = bookWithDates.getTotalBooksSoldDay();
        assertEquals(expected, result);
    }

    // Testing Book.getTotalBooksSoldYear()
    @Test
    public void test_getTotalBooksSoldYear_noSales(){
        int expected = 0;
        int result = bookWithoutDates.getTotalBooksSoldYear();
        assertEquals(expected, result);
    }

    @Test
    public void test_getTotalBooksSoldYear_withSales(){
        int expected = 17;
        int result = bookWithDates.getTotalBooksSoldYear();
        assertEquals(expected, result);
    }

    // Testing Book.getNumberDatesYear()
    @Test
    public void test_getNumberDatesYear_NoPurchases() {
        int result = bookWithoutDates.getNumberDatesYear(new ArrayList<>(), new ArrayList<>());
        assertEquals(0, result);
    }
    @Test
    public void test_getNumberDatesYear_withPurchases() {
        int result = bookWithDates.getNumberDatesYear(purchasedDates, purchasedQuantities);
        assertEquals(17, result);
    }

    // Testing Book.getTotalBooksBoughtMonth()
    @Test
    public void test_getTotalBooksBoughtMonth_withPurchases() {
        int result = bookWithDates.getTotalBooksBoughtMonth();
        assertEquals(13, result);
    }

    @Test
    public void test_getTotalBooksBoughtMonth_noPurchases() {
        int result = bookWithoutDates.getTotalBooksBoughtMonth();
        assertEquals(0, result);
    }

    // Testing Book.getTotalBooksBought()
    @Test
    public void test_getTotalBooksBought_withPurchases() {
        int result = bookWithDates.getTotalBooksBought();
        assertEquals(21, result);
    }

    @Test
    public void test_getTotalBooksBought_noPurchases() {
        int result = bookWithoutDates.getTotalBooksBought();
        assertEquals(0, result);
    }
}
