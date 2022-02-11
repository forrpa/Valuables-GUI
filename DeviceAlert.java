//Jennifer McCarthy, 930124-0983

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class DeviceAlert extends Alert {

    private TextField nameField = new TextField();
    private TextField priceField = new TextField();
    private TextField conditionField = new TextField();

    public DeviceAlert(){
        super(AlertType.CONFIRMATION);

        GridPane grid = new GridPane();
        grid.addRow(0, new Label("Namn:"), nameField);
        grid.addRow(1, new Label("Pris:"), priceField);
        grid.addRow(2, new Label("Skick:"), conditionField);

        getDialogPane().setContent(grid);
    }

    public String getName() {return nameField.getText();}

    public int getPrice() {return Integer.parseInt(priceField.getText());}

    public double getCondition() {return Double.parseDouble(conditionField.getText());}

}