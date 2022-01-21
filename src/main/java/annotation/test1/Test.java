package annotation.test1;

import java.lang.reflect.Field;

public class Test {

    public static void getFruitInfo(String clas){
               try {
                  Class<?> cls = Class.forName(clas);
                    Field[] fields = cls.getDeclaredFields();

                        for (Field field : fields) {
                                if(field.isAnnotationPresent(FruitName.class)){
                                      FruitName name = field.getAnnotation(FruitName.class);
                                        System.out.println("Fruit Name:"+name.value());
                                }
                              if(field.isAnnotationPresent(FruitColor.class)){
                                       FruitColor color = field.getAnnotation(FruitColor.class);
                                         System.out.println("Fruit Color:"+color.fruitColor());
                                    }
                                if(field.isAnnotationPresent(FruitProvider.class)){
                                    FruitProvider Provider = field.getAnnotation(FruitProvider.class);
                                        System.out.println("Fruit FruitProvider: ProviderID:"+Provider.id()+" Provider:"+Provider.user() +" ProviderAddress:"+Provider.address());
                                    } }
                  } catch (ClassNotFoundException e) {
                       e.printStackTrace();
                     }
             }

             public static void main(String[] args) {
                 getFruitInfo("annotation.test1.Apple");
             }
}
