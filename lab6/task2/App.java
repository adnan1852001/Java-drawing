import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class App extends Application {
    File file;
    String content;
    TextArea textArea = new TextArea();
    @Override
    public void start(Stage primaryStage) throws Exception {
        FileChooser file = new FileChooser();
        file.setTitle("select file");
        this.file = file.showOpenDialog(primaryStage);
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle("waring");
        alert.setHeaderText(null);
        alert.setContentText("please select file to show it");
        while (this.file == null){
            alert.showAndWait();
            this.file = file.showOpenDialog(primaryStage);
        }

        Pane root = new Pane();
        root.setMinSize(100, 100);
        Scene scene = new Scene(root,800, 600);
        textArea.prefHeightProperty().bind(root.heightProperty());
        textArea.prefWidthProperty().bind(root.widthProperty());
        textArea.setText(getStringFronFile(this.file));
        textArea.setWrapText(true);
        root.getChildren().add(textArea);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(200);
        primaryStage.setMinWidth(300);
        primaryStage.setTitle("files editor");
        primaryStage.show();
        

    }

    @Override
    public void stop() throws Exception {
        FileWriter output = new FileWriter(this.file);
        output.write(this.textArea.getText());
        output.close();
    }
    public static void main(String[] args) {
        launch(args);
    }

    String getStringFronFile(File file) throws Exception {
        Scanner fileInput = new Scanner(file);
        String content = "";
        while (fileInput.hasNext()){
            content += fileInput.nextLine();
        }
        fileInput.close();
        return content;
    }

}