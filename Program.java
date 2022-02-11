//Jennifer McCarthy, 930124-0983

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

public class Program extends Application {

    private TextArea display;
    private RadioButton nameBtn;
    private RadioButton valueBtn;

    private ArrayList<Valuable> valuables = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) {

        Color myColor = new Color(0.9, 0.1, 0.5, 0.6);
        BorderPane root = new BorderPane();
        primaryStage.setTitle("Sakregister");
        root.setStyle("-fx-font-size: 14");

        display = new TextArea();
        display.setEditable(false);
        root.setCenter(display);
        display.setStyle("-fx-background-color: pink");

        FlowPane top = new FlowPane();
        root.setTop(top);
        Label topLabel = new Label("Värdesaker");
        topLabel.setTextFill(myColor);
        top.getChildren().add(topLabel);
        top.setAlignment(Pos.CENTER);

        VBox right = new VBox();
        right.setPadding(new Insets(5));
        right.getChildren().add(new Label("Sortering"));
        nameBtn = new RadioButton("Namn");
        valueBtn = new RadioButton("Värde");
        right.getChildren().addAll(nameBtn, valueBtn);
        root.setRight(right);
        ToggleGroup group = new ToggleGroup();
        nameBtn.setToggleGroup(group);
        valueBtn.setToggleGroup(group);
        group.selectToggle(nameBtn);

        FlowPane bottom = new FlowPane();
        root.setBottom(bottom);
        bottom.setAlignment(Pos.CENTER);
        bottom.setHgap(5);
        bottom.setPadding(new Insets(5));
        bottom.setStyle("-fx-background-color: pink");

        Label newLabel = new Label("Ny");
        MenuButton menuButton = new MenuButton("Välj värdesak:");
        MenuItem item1 = new MenuItem("Smycke");
        MenuItem item2 = new MenuItem("Aktie");
        MenuItem item3 = new MenuItem("Apparat");
        menuButton.getItems().addAll(item1, item2, item3);
        item1.setOnAction(new NewJewelry());
        item2.setOnAction(new NewStock());
        item3.setOnAction(new NewDevice());

        Button displayBtn = new Button("Visa");
        displayBtn.setOnAction(new NewDisplay());

        Button crashBtn = new Button("Börskrasch");
        crashBtn.setOnAction(new NewCrash());

        bottom.getChildren().addAll(newLabel, menuButton, displayBtn, crashBtn);

        primaryStage.setScene(new Scene(root, 500, 400));
        primaryStage.show();
    }

    class NewStock implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            try {
                StockAlert dialog = new StockAlert();
                dialog.setHeaderText(null);
                dialog.setTitle("Ny aktie");
                Optional<ButtonType> result = dialog.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    String name = dialog.getName();
                    if (name.trim().isEmpty()) {
                        wrongInput();
                    } else {
                        int amount = dialog.getAmount();
                        if (amount < 1) {
                            wrongInput();
                        } else {
                            double price = dialog.getPrice();
                            if (price < 0) {
                                wrongInput();
                            } else {
                                Stock stock = new Stock(name, amount, price);
                                valuables.add(stock);
                            }
                        }
                    }
                }
            } catch (NumberFormatException e) {
                wrongInput();
            }
        }
    }

    class NewJewelry implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            try {
                JewelryAlert dialog = new JewelryAlert();
                dialog.setHeaderText(null);
                dialog.setTitle("Nytt smycke");
                Optional<ButtonType> result = dialog.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    String name = dialog.getName();
                    if (name.trim().isEmpty()) {
                        wrongInput();
                    } else {
                        int gemstones = dialog.getGems();
                        if (gemstones < 0) {
                            wrongInput();
                        } else {
                            boolean gold = dialog.isGold();
                            Jewelry jewelry = new Jewelry(name, gemstones, gold);
                            valuables.add(jewelry);
                        }
                    }
                }
            } catch (NumberFormatException e) {
                wrongInput();
            }
        }
    }

    class NewDevice implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            try {
                DeviceAlert dialog = new DeviceAlert();
                dialog.setHeaderText(null);
                dialog.setTitle("Ny apparat");
                Optional<ButtonType> result = dialog.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    String name = dialog.getName();
                    if (name.trim().isEmpty()) {
                        wrongInput();
                    } else {
                        int price = dialog.getPrice();
                        if (price <= 0) {
                            wrongInput();
                        } else {
                            double condition = dialog.getCondition();
                            if (condition < 1 || condition > 10) {
                                wrongInput();
                            } else {
                                Device device = new Device(name, price, condition);
                                valuables.add(device);
                            }
                        }
                    }
                }

            } catch (NumberFormatException e) {
                wrongInput();
            }
        }
    }


    private void wrongInput() {
        Alert msg = new Alert(Alert.AlertType.ERROR, "Felaktig inmatning!");
        msg.setTitle("Fel!");
        msg.setHeaderText(null);
        msg.showAndWait();
    }


    class NewDisplay implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            display.clear();
            if (nameBtn.isSelected()) {
                Collections.sort(valuables, new NameCmp());
                for (Valuable v : valuables) {
                    String name = v.toString().toLowerCase().trim();
                    display.appendText(name.substring(0, 1).toUpperCase() + name.substring(1) + "\n");
                }
            } else {
                valuables.sort(Comparator.comparing(Valuable::getTotal).reversed());
                for (Valuable v : valuables) {
                    String name = v.toString().toLowerCase().trim();
                    display.appendText(name.substring(0, 1).toUpperCase() + name.substring(1) + "\n");
                }
            }
        }
    }

    class NameCmp implements Comparator<Valuable> {
        @Override
        public int compare(Valuable a, Valuable b) {
            return a.getName().toLowerCase().trim().compareTo(b.getName().toLowerCase().trim());
        }
    }


    class NewCrash implements EventHandler<ActionEvent> {
        public void handle(ActionEvent event) {
            for (Valuable v : valuables)
                if (v instanceof Stock) {
                    Stock s = (Stock) v;
                    s.setStock(0);
                }
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
