module project.librarymanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens LibraryManager to javafx.fxml;
    exports LibraryManager;
}