package UnitTesting;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import LibraryManager.Administrator;
import LibraryManager.Manager;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ManagerOperationsMockTest {

    private List<Manager> managerList;

    @BeforeEach
    public void setUp() {
        managerList = new ArrayList<>();
        managerList.add(new Manager("Calv1n", "PQ532Abba", "Calvin", 900, "(912) 561-2628", "calvl@manager.com"));
        managerList.add(new Manager("Lui54", "y@.3FYrn", "Lui", 900, "(912) 218-2594", "lu@manager.com"));
        managerList.add(new Manager("1", "2", "TestManager", 900, "(912) 623-5353", "TestEmail@librarian.com"));
    }

    @Test
    public void test_instantiateManagers() {
        try (MockedStatic<Administrator> adminMock = mockStatic(Administrator.class)) {
            adminMock.when(Administrator::getManagers).thenReturn(managerList);
            assertEquals(managerList.size(), Administrator.getManagers().size(), "Manager list size mismatch");
        }
    }

    @Test
    public void test_partOfManager() {
        Manager existingManager = new Manager("Calv1n", "PQ532Abba", "Calvin", 900, "(912) 561-2628", "calvl@manager.com");
        Manager nonExistingManager = new Manager("NonExisting", "12345", "Fake", 500, "(999) 999-9999", "fake@manager.com");

        try (MockedStatic<Administrator> adminMock = mockStatic(Administrator.class)) {
            adminMock.when(() -> Administrator.partOfManager(existingManager)).thenReturn(true);
            adminMock.when(() -> Administrator.partOfManager(nonExistingManager)).thenReturn(false);

            assertTrue(Administrator.partOfManager(existingManager));
            assertFalse(Administrator.partOfManager(nonExistingManager));
        }
    }

    @Test
    public void test_reEnterManager() {
        Manager existingManager = new Manager("Calv1n", "PQ532Abba", "Calvin", 900, "(912) 561-2628", "calvl@manager.com");

        try (MockedStatic<Administrator> mockedAdmin = mockStatic(Administrator.class)) {
            mockedAdmin.when(() -> Administrator.reEnter(existingManager)).thenReturn(existingManager);

            Manager reEnteredManager = Administrator.reEnter(existingManager);
            assertNotNull(reEnteredManager, "Re-entered Manager is null");
            assertEquals(existingManager.getUsername(), reEnteredManager.getUsername(), "Username mismatch");
            assertEquals(existingManager.getPassword(), reEnteredManager.getPassword(), "Password mismatch");
            assertEquals(existingManager.getName(), reEnteredManager.getName(), "Name mismatch");
        }
    }

    @Test
    public void test_deleteManager() {
        Manager managerToDelete = managerList.get(0);
        List<Manager> mutableManagerList = new ArrayList<>(managerList);

        try (MockedStatic<Administrator> adminMock = mockStatic(Administrator.class)) {
            adminMock.when(Administrator::getManagers).thenReturn(mutableManagerList);
            adminMock.when(() -> Administrator.getBackManager(managerToDelete))
                    .thenAnswer(invocation -> mutableManagerList.contains(managerToDelete) ? managerToDelete : null);

            adminMock.when(() -> Administrator.deleteManager(managerToDelete)).then(invocation -> {
                mutableManagerList.remove(managerToDelete);
                return true;
            });

            int initialSize = Administrator.getManagers().size();
            Administrator.deleteManager(managerToDelete);
            int finalSize = Administrator.getManagers().size();
            assertEquals(initialSize - 1, finalSize);
            assertNull(Administrator.getBackManager(managerToDelete));
        }
    }

    @Test
    public void test_getBackManager() {
        Manager existingManager = new Manager("Calv1n", "PQ532Abba", "Calvin", 900, "(912) 561-2628", "calvl@manager.com");

        try (MockedStatic<Administrator> mockedAdmin = mockStatic(Administrator.class)) {
            mockedAdmin.when(() -> Administrator.getBackManager(existingManager)).thenReturn(existingManager);

            Manager retrievedManager = Administrator.getBackManager(existingManager);
            assertNotNull(retrievedManager, "Retrieved manager is null");
            assertEquals(existingManager.getUsername(), retrievedManager.getUsername(), "Retrieved manager's username is incorrect");
            assertEquals(existingManager.getPassword(), retrievedManager.getPassword(), "Retrieved manager's password is incorrect");
            assertEquals(existingManager.getName(), retrievedManager.getName(), "Retrieved manager's name is incorrect");
        }
    }
}