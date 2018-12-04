package stu.effectivejava.secondchapter.second;

/**
 * 遇到多个构造器参数时要考虑用构造器
 * 重叠构造器
 * @author zz
 */
public class Second {
    private  final int servingSize;
    private  final int servings;
    private  final int calories;
    private  final int fat;
    private  final int sodium;
    private  final int carbohydrate;

    public Second(int servingSize, int servings){

        this(servingSize,servings,0);


    }
    public Second(int servingSize, int servings, int calories){

        this(servingSize,servings,servings,0);
    }
    public Second(int servingSize, int servings, int calories, int fat){
        this(servingSize,servings,servings,calories,0);

    }
    public Second(int servingSize, int servings, int calories, int fat, int sodium){
        this(servingSize,servings,servings,calories,fat,0);

    }

    public Second(int servingSize, int servings, int calories, int fat, int sodium, int carbohydrate){
        this.servingSize=servingSize;
        this.servings=servings;
        this.calories=calories;
        this.fat=fat;
        this.sodium=sodium;
        this.carbohydrate=carbohydrate;

    }
}
