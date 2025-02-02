package UnitTesting;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import LibraryManager.Administrator;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import java.util.List;
import java.util.ArrayList;

public class AdministratorOperationsMockTest {

    @Test
    public void test_checker_isAdmin() {
        try (MockedStatic<Administrator> mockedStatic = mockStatic(Administrator.class)) {
            mockedStatic.when(() -> Administrator.checker("J0sh", "&zsX6QVZ")).thenReturn(true);
            assertTrue(Administrator.checker("J0sh", "&zsX6QVZ"));
        }
    }

    @Test
    public void test_checker_notAdmin_wrongPass() {
        try (MockedStatic<Administrator> mockedStatic = Mockito.mockStatic(Administrator.class)) {
            mockedStatic.when(() -> Administrator.checker("J0sh", "wrongPass")).thenReturn(false);
            assertFalse(Administrator.checker("J0sh", "wrongPass"));
        }
    }

    @Test
    public void test_checker_notAdmin_wrongUsername() {
        try (MockedStatic<Administrator> mockedStatic = Mockito.mockStatic(Administrator.class)) {
            mockedStatic.when(() -> Administrator.checker("wrongUsername", "&zsX6QVZ")).thenReturn(false);
            assertFalse(Administrator.checker("wrongUsername", "&zsX6QVZ"));
        }
    }

    @Test
    public void test_getAdmins() {
        List<Administrator> mockAdminList = new ArrayList<>();
        mockAdminList.add(new Administrator("J0sh", "&zsX6QVZ", "Josh", 1500, "(912) 561-2328", "josh@administrator.com"));
        mockAdminList.add(new Administrator("1", "3", "TestAdmin", 1500, "(912) 626-5353", "TestEmail@admin.com"));

        try (MockedStatic<Administrator> mockedStatic = Mockito.mockStatic(Administrator.class)) {
            mockedStatic.when(Administrator::getAdmins).thenReturn(mockAdminList);
            assertEquals(2, Administrator.getAdmins().size(), "Incorrect admin list size");
        }
    }
}