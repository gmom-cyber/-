package Controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import main.Main;
import main.MyListener;
import model.Fruit;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MarketController implements Initializable {
    @FXML
    private TextField Type_here_toSearch;

    @FXML
    private Button Search;

    @FXML
    private Button CheckTheProduct;

    @FXML
    private VBox chosenFruitCard;

    @FXML
    private Label fruitNameLable;

    @FXML
    private Label fruitPriceLabel;

    @FXML
    private ImageView fruitImg;

    @FXML
    private ScrollPane scroll;

    @FXML
    private GridPane grid;

    private List<Fruit> fruits = new ArrayList<>();
    private Image image;
    private MyListener myListener;

    private List<Fruit> getData() {
        List<Fruit> fruits = new ArrayList<>();

        Fruit fruit0;
        fruit0 = new Fruit();
        fruit0.setName("Kiwi");
        fruit0.setPrice(2.99);
        fruit0.setImgSrc("/img/kiwi.png");
        fruit0.setColor("6A7324");
        fruits.add(fruit0);

        Fruit fruit1;
        fruit1 = new Fruit();
        fruit1.setName("Coconut");
        fruit1.setPrice(3.99);
        fruit1.setImgSrc("/img/coconut.png");
        fruit1.setColor("A7745B");
        fruits.add(fruit1);

        Fruit fruit2;
        fruit2 = new Fruit();
        fruit2.setName("Peach");
        fruit2.setPrice(1.50);
        fruit2.setImgSrc("/img/peach.png");
        fruit2.setColor("F16C31");
        fruits.add(fruit2);

        Fruit fruit3;
        fruit3 = new Fruit();
        fruit3.setName("Grapes");
        fruit3.setPrice(0.99);
        fruit3.setImgSrc("/img/grapes.png");
        fruit3.setColor("291D36");
        fruits.add(fruit3);

        Fruit fruit4;
        fruit4 = new Fruit();
        fruit4.setName("Watermelon");
        fruit4.setPrice(4.99);
        fruit4.setImgSrc("/img/watermelon.png");
        fruit4.setColor("22371D");
        fruits.add(fruit4);

        Fruit fruit5;
        fruit5 = new Fruit();
        fruit5.setName("Orange");
        fruit5.setPrice(2.99);
        fruit5.setImgSrc("/img/orange.png");
        fruit5.setColor("FB5D03");
        fruits.add(fruit5);

        Fruit fruit6;
        fruit6 = new Fruit();
        fruit6.setName("StrawBerry");
        fruit6.setPrice(0.99);
        fruit6.setImgSrc("/img/strawberry.png");
        fruit6.setColor("80080C");
        fruits.add(fruit6);

        Fruit fruit7;
        fruit7 = new Fruit();
        fruit7.setName("Mango");
        fruit7.setPrice(0.99);
        fruit7.setImgSrc("/img/mango.png");
        fruit7.setColor("FFB605");
        fruits.add(fruit7);

        Fruit fruit8;
        fruit8 = new Fruit();
        fruit8.setName("Cherry");
        fruit8.setPrice(0.99);
        fruit8.setImgSrc("/img/cherry.png");
        fruit8.setColor("5F060E");
        fruits.add(fruit8);

        Fruit fruit9;
        fruit9 = new Fruit();
        fruit9.setName("Banana");
        fruit9.setPrice(1.99);
        fruit9.setImgSrc("/img/banana.png");
        fruit9.setColor("E7C00F");
        fruits.add(fruit9);

        return fruits;
    }

    private void setChosenFruit(Fruit fruit) {
        fruitNameLable.setText(fruit.getName());
        fruitPriceLabel.setText( fruit.getPrice()+Main.CURRENCY);
        image = new Image(getClass().getResourceAsStream(fruit.getImgSrc()));
        fruitImg.setImage(image);
        chosenFruitCard.setStyle("-fx-background-color: #" + fruit.getColor() + ";\n" +
                "    -fx-background-radius: 30;");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        CheckTheProduct.setOnAction(event -> {
            Alert CheckTheProductEvent= new Alert(Alert.AlertType.INFORMATION);
            CheckTheProductEvent.setHeaderText("Товар '"+fruitNameLable.getText()+"' находится в магазине по адресу 'проезд Печорина д27'");
            CheckTheProductEvent.setContentText("Вы можете его купить по цене "+fruitPriceLabel.getText()+" за килограмм\"");
            CheckTheProductEvent.showAndWait();
        });

        Search.setOnAction(event -> {
            String valueForSearch = Type_here_toSearch.getText();
            Search(valueForSearch);

        });

        fruits.addAll(getData());
        if (fruits.size() > 0) {
            setChosenFruit(fruits.get(0));
            myListener = new MyListener() {
                @Override
                public void onClickListener(Fruit fruit) {
                    setChosenFruit(fruit);
                }
            };
        }
        int column = 0;
        int row = 1;
        try {
            for (int i = 0; i < fruits.size(); i++) {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("/views/item.fxml"));
                AnchorPane anchorPane = fxmlLoader.load();

                ItemController itemController = fxmlLoader.getController();
                itemController.setData(fruits.get(i),myListener);

                if (column == 3) {
                    column = 0;
                    row++;
                }

                grid.add(anchorPane, column++, row); //(child,column,row)
                //set grid width
                grid.setMinWidth(Region.USE_COMPUTED_SIZE);
                grid.setPrefWidth(Region.USE_COMPUTED_SIZE);
                grid.setMaxWidth(Region.USE_PREF_SIZE);

                //set grid height
                grid.setMinHeight(Region.USE_COMPUTED_SIZE);
                grid.setPrefHeight(Region.USE_COMPUTED_SIZE);
                grid.setMaxHeight(Region.USE_PREF_SIZE);

                GridPane.setMargin(anchorPane, new Insets(10));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void Search(String valueForSearch) {
        List<Fruit> FruitForSearch = getData();
        Fruit finded = new Fruit("NotAProduct","../www",3.2,"sss");
        for (int i = 0; i < FruitForSearch.size(); i++) {
            System.out.println(FruitForSearch.get(i).getName().toLowerCase().trim() + " = " + valueForSearch);
            if (FruitForSearch.get(i).getName().toLowerCase().trim().equals(valueForSearch.toLowerCase().trim())) {
                System.out.println("Найдено совпадение");
                finded = FruitForSearch.get(i);
                setChosenFruit(finded);
                break;
            }
        }
        if (finded.getName().equals("NotAProduct"))
        {
            Alert SearchFail = new Alert(Alert.AlertType.CONFIRMATION);
            SearchFail.setHeaderText("Искомый продукт не был найден");
            SearchFail.setContentText("");
            SearchFail.showAndWait();
        }
       System.out.println(finded);
    }

}
