//Jennifer McCarthy, 930124-0983

import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class StockAlert extends Alert {

    private TextField nameField = new TextField();
    private TextField amountField = new TextField();
    private TextField priceField = new TextField();

    public StockAlert(){
        super(AlertType.CONFIRMATION);

        GridPane grid = new GridPane();
        grid.addRow(0, new Label("Namn:"), nameField);
        grid.addRow(1, new Label("Antal:"), amountField);
        grid.addRow(2, new Label("Kurs:"), priceField);

        getDialogPane().setContent(grid);
    }

    public String getName() {return nameField.getText();}

    public int getAmount() {return Integer.parseInt(amountField.getText());}

    public double getPrice() {return Double.parseDouble(priceField.getText());}

}
