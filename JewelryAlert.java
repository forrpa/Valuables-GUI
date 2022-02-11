//Jennifer McCarthy, 930124-0983

import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class JewelryAlert extends Alert {

    private TextField nameField = new TextField();
    private TextField gemField = new TextField();
    private CheckBox goldBox = new CheckBox("Av guld:");


    public JewelryAlert(){
        super(Alert.AlertType.CONFIRMATION);

        GridPane grid = new GridPane();
        grid.addRow(0, new Label("Namn:"), nameField);
        grid.addRow(1, new Label("Stenar:"), gemField);
        grid.addRow(2, goldBox);

        getDialogPane().setContent(grid);
    }

    public String getName() {return nameField.getText();}

    public int getGems() {return Integer.parseInt(gemField.getText());}

    public boolean isGold() {return goldBox.isSelected();}


}