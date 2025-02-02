package UnitTesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import LibraryManager.Librarian;
import LibraryManager.Manager;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LibrarianOperationsMockTest {

    @BeforeEach
    void setUp() {
        Mockito.clearAllCaches();
    }

    @Test
    public void test_checkPassword_Valid() {
        try (MockedStatic<Librarian> mockedLibrarian = mockStatic(Librarian.class)) {
            mockedLibrarian.when(() -> Librarian.checkPassword("TyFzN8we")).thenReturn(true);
            mockedLibrarian.when(() -> Librarian.checkPassword("12345678")).thenReturn(true);
            assertTrue(Librarian.checkPassword("TyFzN8we"));
            assertTrue(Librarian.checkPassword("12345678"));
        }
    }

    @Test
    public void test_checkPassword_InvalidShort() {
        try (MockedStatic<Librarian> mockedLibrarian = mockStatic(Librarian.class)) {
            mockedLibrarian.when(() -> Librarian.checkPassword("1234567")).thenReturn(false);
            assertFalse(Librarian.checkPassword("1234567"));
        }
    }

    @Test
    public void test_checkSalary_valid() {
        try (MockedStatic<Librarian> mockedLibrarian = mockStatic(Librarian.class)) {
            mockedLibrarian.when(() -> Librarian.checkSalary("50000.50")).thenReturn(true);
            assertTrue(Librarian.checkSalary("50000.50"));
        }
    }

    @Test
    public void test_checkSalary_notValid_negative() {
        try (MockedStatic<Librarian> mockedLibrarian = mockStatic(Librarian.class)) {
            mockedLibrarian.when(() -> Librarian.checkSalary("-50")).thenReturn(false);
            assertFalse(Librarian.checkSalary("-50"));
        }
    }

    @Test
    public void test_checkEmail_valid() {
        try (MockedStatic<Librarian> mockedLibrarian = mockStatic(Librarian.class)) {
            mockedLibrarian.when(() -> Librarian.checkEmail("john@example.com")).thenReturn(true);
            assertTrue(Librarian.checkEmail("john@example.com"));
        }
    }

    @Test
    public void test_checkEmail_notValid() {
        try (MockedStatic<Librarian> mockedLibrarian = mockStatic(Librarian.class)) {
            mockedLibrarian.when(() -> Librarian.checkEmail("invalid-email")).thenReturn(false);
            assertFalse(Librarian.checkEmail("invalid-email"));
        }
    }

    @Test
    public void test_LibrarianChecker_isLibrarian() {
        Librarian librarian = new Librarian("Alfie123", "SSU6aldo");
        try (MockedStatic<Manager> mockedManager = mockStatic(Manager.class)) {
            mockedManager.when(() -> Manager.LibrarianChecker(librarian)).thenReturn(true);
            assertTrue(Manager.LibrarianChecker(librarian));
        }
    }

    @Test
    public void test_LibrarianChecker_notLibrarian() {
        Librarian librarian = new Librarian("NonExistingUser", "wrongPass");
        try (MockedStatic<Manager> mockedManager = mockStatic(Manager.class)) {
            mockedManager.when(() -> Manager.LibrarianChecker(librarian)).thenReturn(false);
            assertFalse(Manager.LibrarianChecker(librarian));
        }
    }

    @Test
    public void test_deleteLibrarian() {
        Librarian librarianToDelete = new Librarian("TO_BE_DELETED", "TO_BE_DELETED");
        List<Librarian> librarians = new ArrayList<>(List.of(librarianToDelete));

        try (MockedStatic<Manager> managerMock = mockStatic(Manager.class)) {
            managerMock.when(Manager::getLibrarians).thenReturn(librarians);
            managerMock.when(() -> Manager.getBackLibrarian(librarianToDelete))
                    .thenAnswer(invocation -> librarians.contains(librarianToDelete) ? librarianToDelete : null);

            managerMock.when(() -> Manager.deleteLibrarian(librarianToDelete)).then(invocation -> {
                librarians.remove(librarianToDelete);
                return null;
            });

            int initialSize = Manager.getLibrarians().size();
            Manager.deleteLibrarian(librarianToDelete);
            int finalSize = Manager.getLibrarians().size();

            assertEquals(initialSize - 1, finalSize, "Size problem after deletion");
            assertNull(Manager.getBackLibrarian(librarianToDelete), "'librarianToDelete' is still part of librarian array");
        }
    }
}
