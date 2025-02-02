package UnitTesting;

import LibraryManager.BillNumber;
import LibraryManager.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookFileOperationsMockTest {

    private static final String TEST_FILE_PATH = "BooksTesting.txt";

    @Mock
    private BillNumber billNumberMock;


    @Mock
    private Book bookMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_showStringStock() {
        when(billNumberMock.showStringStock(TEST_FILE_PATH)).thenReturn("Mocked Stock Output");
        assertEquals("Mocked Stock Output", billNumberMock.showStringStock(TEST_FILE_PATH));
    }

    @Test
    public void test_setInitialStock() throws IOException {
        doNothing().when(billNumberMock).setInitialStock(TEST_FILE_PATH);
        billNumberMock.setInitialStock(TEST_FILE_PATH);
        verify(billNumberMock, times(1)).setInitialStock(TEST_FILE_PATH);
    }

    @Test
    public void test_addBookToStock() throws IOException {
        doNothing().when(billNumberMock).addBookToStock(TEST_FILE_PATH, bookMock);
        billNumberMock.addBookToStock(TEST_FILE_PATH, bookMock);
        verify(billNumberMock, times(1)).addBookToStock(TEST_FILE_PATH, bookMock);
    }

    @Test
    public void test_getBooksSoldDay() {
        when(billNumberMock.getBooksSoldDay(TEST_FILE_PATH)).thenReturn("Mocked Sold Books Output");
        assertEquals("Mocked Sold Books Output", billNumberMock.getBooksSoldDay(TEST_FILE_PATH));
    }

    @Test
    public void test_getBooksBoughtDay() {
        when(billNumberMock.getBooksBoughtDay(TEST_FILE_PATH)).thenReturn("Mocked Bought Books Output");
        assertEquals("Mocked Bought Books Output", billNumberMock.getBooksBoughtDay(TEST_FILE_PATH));
    }

    @Test
    public void test_getIntBooksSoldDay() {
        when(billNumberMock.getIntBooksSoldDay(TEST_FILE_PATH)).thenReturn(5);
        assertEquals(5, billNumberMock.getIntBooksSoldDay(TEST_FILE_PATH));
    }

    @Test
    public void test_getIncomeDay() {
        when(billNumberMock.getIncomeDay(TEST_FILE_PATH)).thenReturn(167.69);
        assertEquals(167.69, billNumberMock.getIncomeDay(TEST_FILE_PATH));
    }

    @Test
    public void test_isPartOfBooks() {
        when(billNumberMock.isPartOfBooks(TEST_FILE_PATH, "0096184570112")).thenReturn(true);
        assertTrue(billNumberMock.isPartOfBooks(TEST_FILE_PATH, "0096184570112"));
    }

    @Test
    public void test_getISBNName() {
        ArrayList<String> mockISBNList = new ArrayList<>();
        mockISBNList.add("0096184570112 - In Search of Lost Time");
        mockISBNList.add("0629778828041 - Don Quixote");
        when(billNumberMock.getISBNName(TEST_FILE_PATH)).thenReturn(mockISBNList);
        assertEquals(mockISBNList, billNumberMock.getISBNName(TEST_FILE_PATH));
    }

    @Test
    public void test_getBooksSoldTotal() {
        when(billNumberMock.getBooksSoldTotal(TEST_FILE_PATH)).thenReturn("Mocked Total Sold Books Output");
        assertEquals("Mocked Total Sold Books Output", billNumberMock.getBooksSoldTotal(TEST_FILE_PATH));
    }

    @Test
    public void testUpdateBooks() throws IOException {
        ArrayList<Book> mockBookList = new ArrayList<>();
        mockBookList.add(bookMock);
        doNothing().when(billNumberMock).updateBooks(TEST_FILE_PATH, mockBookList);
        billNumberMock.updateBooks(TEST_FILE_PATH, mockBookList);
        verify(billNumberMock, times(1)).updateBooks(TEST_FILE_PATH, mockBookList);
    }
}
