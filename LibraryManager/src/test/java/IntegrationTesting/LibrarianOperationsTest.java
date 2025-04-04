package IntegrationTesting;

import LibraryManager.Book;
import LibraryManager.Librarian;
import LibraryManager.Manager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
//
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class LibrarianOperationsTest {

    static ArrayList<Date> testDatesSold = new ArrayList<>();
    static ArrayList<Double> testMoneyMade = new ArrayList<>();

    @BeforeAll
    public static void setUp(){
        Manager.InstantiateLibrarians();

        testDatesSold.add(new Date());
        testMoneyMade.add(50.0);

        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Date previousDate = calendar.getTime();
        testDatesSold.add(previousDate);
        testMoneyMade.add(20.0);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date nextDate = calendar.getTime();
        testDatesSold.add(nextDate);
        testMoneyMade.add(10.0);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date previousMonth = calendar.getTime();
        testDatesSold.add(previousMonth);
        testMoneyMade.add(50.0);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 1);
        Date nextMonth = calendar.getTime();
        testDatesSold.add(nextMonth);
        testMoneyMade.add(40.0);

        calendar = Calendar.getInstance();
        calendar.add(Calendar.YEAR, - 1);
        Date previousYear = calendar.getTime();
        testDatesSold.add(previousYear);
        testMoneyMade.add(145.0);
    }


    // Testing Librarian.removeDuplicatesSoldBooks()
    @Test
    public void test_removeDuplicatesSoldBooks_noDupes(){
        ArrayList<Book> bookArrayList = new ArrayList<>();
        bookArrayList.add(new Book("0096184570112","In Search of Lost Time","Modernist","Ingram Content Group, Inc",65.00,73.96,"Marcel Proust",5));
        bookArrayList.add(new Book("4647500268094","Ulysses","Fiction","Baker & Taylor",15.00,18.00,"James Joyce",2));
        bookArrayList.add(new Book("0629778828041","Don Quixote","Novel","BCH Fulfillment & Distribution",5.00,6.59,"Miguel de Cervantes",10));

        ArrayList<Integer> quantitiesArrayList = new ArrayList<>();
        quantitiesArrayList.add(2);
        quantitiesArrayList.add(5);
        quantitiesArrayList.add(6);

        Librarian lib = new Librarian("TEST_USERNAME", "TEST_PASSWORD");
        lib.removeDuplicatesSoldBooks(bookArrayList, quantitiesArrayList);

        ArrayList<Book> expectedBookArrayList = new ArrayList<>();
        expectedBookArrayList.add(new Book("0629778828041","Don Quixote","Novel","BCH Fulfillment & Distribution",5.00,6.59,"Miguel de Cervantes",10));
        expectedBookArrayList.add(new Book("4647500268094","Ulysses","Fiction","Baker & Taylor",15.00,18.00,"James Joyce",2));
        expectedBookArrayList.add(new Book("0096184570112","In Search of Lost Time","Modernist","Ingram Content Group, Inc",65.00,73.96,"Marcel Proust",5));

        ArrayList<Integer> expectedQuantitiesArrayList = new ArrayList<>();
        expectedQuantitiesArrayList.add(6);
        expectedQuantitiesArrayList.add(5);
        expectedQuantitiesArrayList.add(2);

        assertEquals(expectedBookArrayList.size(), bookArrayList.size(), "Book List size problem");
        assertEquals(expectedQuantitiesArrayList.size(), quantitiesArrayList.size(), "Quantities List size problem");

        for (int i=0; i<expectedBookArrayList.size(); i++){
            assertEquals(expectedBookArrayList.get(i).getISBN(), bookArrayList.get(i).getISBN(), "Book ISBN has been changed");
            assertEquals(expectedQuantitiesArrayList.get(i), quantitiesArrayList.get(i), "Quantity has been changed");
        }
    }

    @Test
    public void test_removeDuplicatesSoldBooks_dupes(){
        ArrayList<Book> bookArrayList = new ArrayList<>();
        bookArrayList.add(new Book("0096184570112","In Search of Lost Time","Modernist","Ingram Content Group, Inc",65.00,73.96,"Marcel Proust",5));
        bookArrayList.add(new Book("4647500268094","Ulysses","Fiction","Baker & Taylor",15.00,18.00,"James Joyce",2));
        bookArrayList.add(new Book("0096184570112","In Search of Lost Time","Modernist","Ingram Content Group, Inc",65.00,73.96,"Marcel Proust",5));
        bookArrayList.add(new Book("0629778828041","Don Quixote","Novel","BCH Fulfillment & Distribution",5.00,6.59,"Miguel de Cervantes",10));

        ArrayList<Integer> quantitiesArrayList = new ArrayList<>();
        quantitiesArrayList.add(2);
        quantitiesArrayList.add(5);
        quantitiesArrayList.add(6);
        quantitiesArrayList.add(8);

        Librarian lib = new Librarian("TEST_USERNAME", "TEST_PASSWORD");
        lib.removeDuplicatesSoldBooks(bookArrayList, quantitiesArrayList);

        ArrayList<Book> expectedBookArrayList = new ArrayList<>();
        expectedBookArrayList.add(new Book("0629778828041","Don Quixote","Novel","BCH Fulfillment & Distribution",5.00,6.59,"Miguel de Cervantes",8));
        expectedBookArrayList.add(new Book("4647500268094","Ulysses","Fiction","Baker & Taylor",15.00,18.00,"James Joyce",2));
        expectedBookArrayList.add(new Book("0096184570112","In Search of Lost Time","Modernist","Ingram Content Group, Inc",65.00,73.96,"Marcel Proust",5));

        ArrayList<Integer> expectedQuantitiesArrayList = new ArrayList<>();
        expectedQuantitiesArrayList.add(8);
        expectedQuantitiesArrayList.add(5);
        expectedQuantitiesArrayList.add(8);

        assertEquals(expectedBookArrayList.size(), bookArrayList.size(), "Book List size problem");
        assertEquals(expectedQuantitiesArrayList.size(), quantitiesArrayList.size(), "Quantities List size problem");

        for (int i=0; i<expectedBookArrayList.size(); i++){
            assertEquals(expectedBookArrayList.get(i).getISBN(), bookArrayList.get(i).getISBN(), "Book ISBN problem");
            assertEquals(expectedQuantitiesArrayList.get(i), quantitiesArrayList.get(i), "Quantity problem");
        }
    }

    // Testing Librarian.MoneyMadeInDay()
    @Test
    public void testMoneyMadeInDay() {
        Librarian lib = new Librarian("1","1","TestLibrarian",500,"(912) 632-6353","TestEmail@librarian.com");
        lib.datesSold.addAll(testDatesSold);
        lib.moneyMadeDates.addAll(testMoneyMade);
        double moneyMadeInDay = lib.moneyMadeInDay();
        assertEquals(50.0, moneyMadeInDay);
    }

    // Testing Librarian.moneyMadeInYear()
    @Test
    public  void test_moneyMadeInYear() {
        Librarian lib = new Librarian("TEST_USERNAME", "TEST_PASSWORD");
        lib.datesSold.addAll(testDatesSold);
        lib.moneyMadeDates.addAll(testMoneyMade);
        double moneyMadeInYear = lib.moneyMadeInYear();
        assertEquals(120, moneyMadeInYear);
    }

    // Testing Librarian.CheckPassword()
    @Test
    public void test_checkPassword_Valid() {
        String validPass = "TyFzN8we";
        assertTrue(Librarian.checkPassword(validPass));
        validPass = "12345678";
        assertTrue(Librarian.checkPassword(validPass));
    }

    @Test
    public void test_checkPassword_InvalidShort() {
        String notValidPass = "1234567";
        assertFalse(Librarian.checkPassword(notValidPass));
    }

    // Testing CheckSalaryValid()
    @Test
    public void test_checkSalary_valid_doubleFormat() {
        assertTrue(Librarian.checkSalary("50000.50"));
    }

    @Test
    public void test_checkSalary_valid_leadingDigits(){
        assertTrue(Librarian.checkSalary("952."));
    }

    @Test
    public void test_checkSalary_valid_intFormat(){
        assertTrue(Librarian.checkSalary("52"));
    }

    @Test
    public void test_checkSalary_notValid_negative() {
        assertFalse(Librarian.checkSalary("-50"));
    }

    @Test
    public void test_checkSalary_notValid_multipleDots(){
        assertFalse(Librarian.checkSalary("40.523.52"));
    }

    @Test
    public void test_checkSalary_notValid_noLeadingDigits(){
        assertFalse(Librarian.checkSalary(".53"));
    }

    @Test
    public void test_checkSalary_notValid_numbersAndLetters(){
        assertFalse(Librarian.checkSalary("1c3"));
    }

    @Test
    public void test_checkSalary_notValid_allLetters(){
        assertFalse(Librarian.checkSalary("ABC"));
    }

    // Testing Librarian.checkEmail()
    @Test
    public void test_checkEmail_valid(){
        assertTrue(Librarian.checkEmail("john@example.com"));
    }

    @Test
    public void test_checkEmail_notValid(){
        assertFalse(Librarian.checkEmail("invalid-email"));
        assertFalse(Librarian.checkEmail("notAnEmail@domain"));
        assertFalse(Librarian.checkEmail("missingDomain@.com"));
        assertFalse(Librarian.checkEmail("user@123.com"));
    }

    // Testing Librarian.MoneyMadeInMonth()
    @Test
    public void testMoneyMadeInMonth_NoSales() {
        Librarian librarian = new Librarian("username", "password");
        double moneyMadeInMonth = librarian.moneyMadeInMonth();
        assertEquals(0, moneyMadeInMonth);
    }

    @Test
    public void testMoneyMadeInMonth_SalesInCurrentMonth() {
        Librarian librarian = new Librarian("username", "password");
        librarian.datesSold.addAll(testDatesSold);
        librarian.moneyMadeDates.addAll(testMoneyMade);
        assertEquals(80, librarian.moneyMadeInMonth());
    }

    // Testing Librarian.checkPhone()
    @Test
    public void test_checkPhone_valid() {
        assertTrue(Librarian.checkPhone("(912) 123-4567"));
    }

    @Test
    public void test_checkPhone_notValid_improperLength() {
        assertFalse(Librarian.checkPhone("(912) 123-456"), "String with improper length is passed");
    }

    @Test
    public void test_checkPhone_notValid_improperFormat() {
        assertFalse(Librarian.checkPhone("(912) 12-34567"), "String with improper format is passed");
    }

    @Test
    public void test_checkPhone_notValid_containsLetters(){
        assertFalse(Librarian.checkPhone("(912) ABC-FUCK"), "String with letters is passed");
    }

    // Testing Librarian.checkName()
    @Test
    public void testCheckName_ValidName() {
        assertTrue(Librarian.checkName("John"));
    }

    @Test
    public void testCheckName_InvalidName_EmptyString() {
        assertFalse(Librarian.checkName(""));
    }

    @Test
    public void testCheckName_InvalidName_ContainsSpecialCharacters() {
        assertFalse(Librarian.checkName("John@Doe"));
    }

    @Test
    public void testCheckName_InvalidName_ContainsNumbers() {
        assertFalse(Librarian.checkName("John123"));
    }

    // Testing Manager.partOfLibrarian()
    @Test
    public void test_partOfLibrarian_notPart() {
        Librarian lib = new Librarian("WRONG_USERNAME","SSU6aldo","Alfie",500,"(912) 921-2728","aflie@librarian.com");
        assertFalse(Manager.partOfLibrarian(lib), "Librarian object with incorrect username is passed");
    }

    @Test
    public void test_partOfLibrarian_isPart() {
        Librarian lib = new Librarian("Alfie123","SSU6aldo","Alfie",500,"(912) 921-2728","aflie@librarian.com");
        assertTrue(Manager.partOfLibrarian(lib), "Librarian object with correct username is not passed");
    }

    // Testing Manager.getBackLibrarian()
    @Test
    public void test_getBackLibrarian_isPart() {
        Librarian lib = new Librarian("Alfie123","SSU6aldo","Alfie",500,"(912) 921-2728","aflie@librarian.com");
        Librarian gottenBackLibrarian = Manager.getBackLibrarian(lib);
        assertNotNull(gottenBackLibrarian, "re-entered librarian is null");
        assertEquals(lib.getUsername(), gottenBackLibrarian.getUsername(), "The following attribute is incorrect: username");
        assertEquals(lib.getPassword(), gottenBackLibrarian.getPassword(), "The following attribute is incorrect: password");
        assertEquals(lib.getName(), gottenBackLibrarian.getName(), "The following attribute is incorrect: name");
        assertEquals(lib.getSalary(), gottenBackLibrarian.getSalary(), "The following attribute is incorrect: salary");
        assertEquals(lib.getPhone(), gottenBackLibrarian.getPhone(), "The following attribute is incorrect: phone");
        assertEquals(lib.getEmail(), gottenBackLibrarian.getEmail(), "The following attribute is incorrect: email");
    }

    @Test
    public void test_getBackLibrarian_notPart(){
        Librarian lib = new Librarian("WRONG_USERNAME","SSU6aldo","Alfie",500,"(912) 921-2728","aflie@librarian.com");
        Librarian gottenBackLibrarian = Manager.getBackLibrarian(lib);
        assertNull(gottenBackLibrarian, "re-entered librarian is not null");
    }

    // Testing Manager.LibrarianChecker()
    @Test
    public void testLibrarianChecker_isLibrarian() {
        Librarian librarian = new Librarian("Alfie123", "SSU6aldo", "Alfie", 500, "(912) 921-2728", "aflie@librarian.com");
        assertTrue(Manager.LibrarianChecker(librarian), "LibrarianChecker failed for an existing Librarian");
    }

    @Test
    public void test_LibrarianChecker_notLibrarian_wrongUsername() {
        Librarian librarian = new Librarian("NonExistingUser", "SSU6aldo", "NonExistingName", 0, "0000000000", "nonexisting@librarian.com");
        assertFalse(Manager.LibrarianChecker(librarian),
                "LibrarianChecker failed for a non-existing Librarian with a wrong username and a correct password");
    }

    @Test
    public void test_LibrarianChecker_notLibrarian_wrongPass() {
        Librarian librarian = new Librarian("Alfie123", "idkBro");
        assertFalse(Manager.LibrarianChecker(librarian),
                "LibrarianChecker failed for a non-existing Manager with a correct username and a wrong password");
    }

    // Testing Manager.deleteLibrarian()
    @Test
    public void test_deleteLibrarian_validLibrarian() {
        Librarian librarianToDelete = new Librarian("TO_BE_DELETED", "TO_BE_DELETED");
        Manager.AddLibrarian(librarianToDelete);
        int initialSize = Manager.getLibrarians().size();
        Manager.deleteLibrarian(librarianToDelete);
        int finalSize = Manager.getLibrarians().size();
        assertEquals(initialSize - 1, finalSize, "Size problem after deletion");
        assertNull(Manager.getBackLibrarian(librarianToDelete),
                "'librarianToDelete' is still part of librarian array");
    }

    @Test
    public void test_deleteLibrarian_nonExistingLibrarian() {
        Librarian librarianToDelete = new Librarian("TO_BE_DELETED", "TO_BE_DELETED");
        int initialSize = Manager.getLibrarians().size();
        Manager.deleteLibrarian(librarianToDelete);
        int finalSize = Manager.getLibrarians().size();
        assertEquals(initialSize, finalSize);
        assertNull(Manager.getBackLibrarian(librarianToDelete),
                "'librarianToDelete' is part of librarian array");
    }

    // Testing Manager.reEnter()
    @Test
    public void test_reEnter_ExistingLibrarian() {
        Librarian existingLibrarian = new Librarian("@Leo", "TyFzN8we", "Leo", 500, "(912) 152-7493", "leo@librarian.com");
        Librarian reEnteredLibrarian = Manager.reEnter(existingLibrarian);
        assertNotNull(reEnteredLibrarian, "re-entered Librarian is null");
        assertEquals(existingLibrarian.getUsername(), reEnteredLibrarian.getUsername(), "The following attribute is incorrect: username");
        assertEquals(existingLibrarian.getPassword(), reEnteredLibrarian.getPassword(), "The following attribute is incorrect: password");
        assertEquals(existingLibrarian.getName(), reEnteredLibrarian.getName(), "The following attribute is incorrect: name");
        assertEquals(existingLibrarian.getSalary(), reEnteredLibrarian.getSalary(), "The following attribute is incorrect: salary");
        assertEquals(existingLibrarian.getPhone(), reEnteredLibrarian.getPhone(), "The following attribute is incorrect: phone");
        assertEquals(existingLibrarian.getEmail(), reEnteredLibrarian.getEmail(), "The following attribute is incorrect: email");
    }

    @Test
    public void test_reEnter_nonExistingLibrarian() {
        Librarian nonExistingLibrarian = new Librarian("NonExistingUser", "NonExistingPassword");
        Librarian reEnteredLibrarian = Manager.reEnter(nonExistingLibrarian);
        assertNull(reEnteredLibrarian, "re-entered Librarian is not null");
    }

    // Testing Manager.updateLibrarians()
    @Test
    public void testUpdateExistingLibrarian() {
        Librarian existingLibrarian = new Librarian("Alfie123", "SSU6aldo", "Alfie", 500, "(912) 921-2728", "aflie@librarian.com");
        existingLibrarian.setEmail("new_email@librarian.com");
        existingLibrarian.setSalary(600);
        Manager.updateLibrarians(existingLibrarian);
        Librarian updatedLibrarian = Manager.getBackLibrarian(existingLibrarian);

        assertNotNull(updatedLibrarian,"updated Librarian object is null");

        assertEquals(existingLibrarian.getUsername(), updatedLibrarian.getUsername(), "The following attribute is incorrect: username");
        assertEquals(existingLibrarian.getPassword(), updatedLibrarian.getPassword(), "The following attribute is incorrect: password");
        assertEquals(existingLibrarian.getName(), updatedLibrarian.getName(), "The following attribute is incorrect: name");
        assertEquals(existingLibrarian.getSalary(), updatedLibrarian.getSalary(), "The following attribute is incorrect: salary");
        assertEquals(existingLibrarian.getPhone(), updatedLibrarian.getPhone(), "The following attribute is incorrect: phone");
        assertEquals(existingLibrarian.getEmail(), updatedLibrarian.getEmail(), "The following attribute is incorrect: email");

        Manager.getLibrarians().clear();
        Manager.InstantiateLibrarians();
    }

    @Test
    public void testUpdateNonExistingLibrarian() {
        Librarian nonExistingLibrarian = new Librarian("NonExistingUser", "NonExistingPassword");
        nonExistingLibrarian.setEmail("new_email@librarian.com");

        ArrayList<Librarian> preUpdateList = Manager.getLibrarians();
        Manager.updateLibrarians(nonExistingLibrarian);
        ArrayList<Librarian> postUpdateList = Manager.getLibrarians();

        Librarian retrievedLibrarian = Manager.getBackLibrarian(nonExistingLibrarian);
        assertNull(retrievedLibrarian, "retrieved Librarian object is not null");

        assertEquals(preUpdateList.size(), postUpdateList.size(),
                "Size problem after update");

        for (int i=0; i<preUpdateList.size(); i++){
            assertEquals(preUpdateList.get(i).getUsername(), postUpdateList.get(i).getUsername(), "The following attribute is changed: username");
            assertEquals(preUpdateList.get(i).getPassword(), postUpdateList.get(i).getPassword(), "The following attribute is changed: password");
            assertEquals(preUpdateList.get(i).getName(), postUpdateList.get(i).getName(), "The following attribute is changed: name");
            assertEquals(preUpdateList.get(i).getSalary(), postUpdateList.get(i).getSalary(), "The following attribute is changed: salary");
            assertEquals(preUpdateList.get(i).getPhone(), postUpdateList.get(i).getPhone(), "The following attribute is changed: phone");
            assertEquals(preUpdateList.get(i).getEmail(), postUpdateList.get(i).getEmail(), "The following attribute is changed: email");
        }
    }
}