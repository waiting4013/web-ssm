package stu.effectivejava.secondchapter.second;

/**
 * @author zz
 *  javaBeans模式
 *
 */
public class NutritionFacts {
    private   int servingSize=-1;
    private   int servings=1;
    private   int calorie=0;
    private   int fat=0;
    private   int sodium=0;
    private   int carbohydrate=0;
    public NutritionFacts(){

    }
        //Setters
        public void setServingSize(int val){
            this.servingSize=val;
        }

        public void setServings(int servings) {
            this.servings = servings;
        }

        public void setCalorie(int calorie) {
            this.calorie = calorie;
        }

        public void setFat(int fat) {
            this.fat = fat;
        }

        public void setSodium(int sodium) {
            this.sodium = sodium;
        }

        public void setCarbohydrate(int carbohydrate) {
            this.carbohydrate = carbohydrate;
        }
}
