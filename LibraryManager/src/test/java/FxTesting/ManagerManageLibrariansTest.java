package FxTesting;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import LibraryManager.MainFx;
import LibraryManager.Manager;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isVisible;

public class ManagerManageLibrariansTest extends ApplicationTest {

    FxRobot robot = new FxRobot();

    private void logToMag_Librarians_list(){
        robot.clickOn("#mainPageUsername").write("1");
        robot.clickOn("#password").write("2");
        robot.clickOn("#submit");
        robot.clickOn("#checkLibrarians");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainFx mainFx = new MainFx();
        Scene scene = new Scene(mainFx.mainPage());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Test
    public void test_manager_librarians_list(){
        logToMag_Librarians_list();
        verifyThat("#librariansAllGrid", isVisible());
    }

    @Test
    public void test_manager_librarians_list_checker(){
        logToMag_Librarians_list();
        GridPane grid = lookup("#librariansAllGrid").query();
        int expectedNodeCount = Manager.getLibrarians().size();
        verifyThat(grid, (GridPane g) -> g.getChildren().size() == expectedNodeCount);
    }
}
