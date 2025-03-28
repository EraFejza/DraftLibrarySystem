package FxTesting;

import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationTest;
import LibraryManager.MainFx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.control.LabeledMatchers.hasText;
import static org.testfx.matcher.base.NodeMatchers.isVisible;


public class ManagerAddCategoryTest extends ApplicationTest {

    FxRobot robot = new FxRobot();

    private void logToMag_Supply_Category() {
        robot.clickOn("#mainPageUsername").write("1");
        robot.clickOn("#password").write("2");
        robot.clickOn("#submit");
        robot.clickOn("#supply");
        robot.clickOn("#bttNewCategory");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        MainFx mainFx = new MainFx();
        Scene scene = new Scene(mainFx.mainPage());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Test
    public void test_manager_category_list() {
        logToMag_Supply_Category();
        verifyThat("#categoryChoiceBox", isVisible());
    }

    @Test
    public void test_manager_add_same_category() {
        logToMag_Supply_Category();
        ChoiceBox menuNewCategory = lookup("#categoryChoiceBox").query();
        robot.interact(() -> menuNewCategory.getSelectionModel().select("Epic"));
        robot.clickOn("#bttAddCategory");
        assertEquals("Failed, Not New", ((TextField) lookup("#addCategoryWarning").query()).getText());
    }

    @Test
    public void test_manager_add_new_category() {
        logToMag_Supply_Category();
        ChoiceBox menuNewCategory = lookup("#categoryChoiceBox").query();
        robot.interact(() -> menuNewCategory.getSelectionModel().select("Autobiography and memoir"));
        robot.clickOn("#bttAddCategory");
        assertEquals("Added!", ((TextField) lookup("#addCategoryWarning").query()).getText());
    }
}