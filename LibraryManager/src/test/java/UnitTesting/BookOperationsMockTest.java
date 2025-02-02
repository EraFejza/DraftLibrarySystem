package UnitTesting;

import LibraryManager.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookOperationsMockTest {

    @Mock
    private Book bookMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_getISBN() {
        when(bookMock.getISBN()).thenReturn("0096184570112");
        assertEquals("0096184570112", bookMock.getISBN());
    }

    @Test
    public void test_getTitle() {
        when(bookMock.getTitle()).thenReturn("In Search of Lost Time");
        assertEquals("In Search of Lost Time", bookMock.getTitle());
    }

    @Test
    public void test_getStock() {
        when(bookMock.getStock()).thenReturn(5);
        assertEquals(5, bookMock.getStock());
    }

    @Test
    public void test_AddStock() {
        doNothing().when(bookMock).AddStock(5);
        bookMock.AddStock(5);
        verify(bookMock, times(1)).AddStock(5);
    }

    @Test
    public void test_RemoveStock() {
        doNothing().when(bookMock).RemoveStock(3);
        bookMock.RemoveStock(3);
        verify(bookMock, times(1)).RemoveStock(3);
    }

    @Test
    public void test_getSoldDatesQuantitiesTotal() {
        when(bookMock.getSoldDatesQuantitiesTotal()).thenReturn("For \"In Search of Lost Time\" We have sold:\n5 at some_date");
        assertEquals("For \"In Search of Lost Time\" We have sold:\n5 at some_date", bookMock.getSoldDatesQuantitiesTotal());
    }

    @Test
    public void test_getTotalBooksSoldDay() {
        when(bookMock.getTotalBooksSoldDay()).thenReturn(2);
        assertEquals(2, bookMock.getTotalBooksSoldDay());
    }

    @Test
    public void test_getTotalBooksSoldMonth() {
        when(bookMock.getTotalBooksSoldMonth()).thenReturn(10);
        assertEquals(10, bookMock.getTotalBooksSoldMonth());
    }

    @Test
    public void test_getTotalBooksBoughtYear() {
        when(bookMock.getTotalBooksBoughtYear()).thenReturn(15);
        assertEquals(15, bookMock.getTotalBooksBoughtYear());
    }

    @Test
    public void test_getQuantitiesPurchased() {
        when(bookMock.getQuantitiesPurchased()).thenReturn(20);
        assertEquals(20, bookMock.getQuantitiesPurchased());
    }

    @Test
    public void test_getBoughtDatesQuantitiesTotal() {
        when(bookMock.getBoughtDatesQuantitiesTotal()).thenReturn("For \"In Search of Lost Time\" We have bought in a day:\n3 at some_date");
        assertEquals("For \"In Search of Lost Time\" We have bought in a day:\n3 at some_date", bookMock.getBoughtDatesQuantitiesTotal());
    }
}
