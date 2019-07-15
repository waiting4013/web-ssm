package annotation.test1;

public class Apple {


    @FruitName(value="FuShi Apple")
    private String fruitName;

    @FruitColor(fruitColor= FruitColor.Color.WHITE)
    private String fruitColor;

    @FruitProvider(id=1,user="Tom",address="China")
    private FruitProvider provider;
}
