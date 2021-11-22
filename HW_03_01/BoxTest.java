package HW_03_01;

/**
 * Created by annav on 22.11.2021.
 */
public class BoxTest {
    public static void main(String[] arags) {
        Box<Apple> boxApple1 = new Box<Apple>(new Apple("Blue"), new Apple("Grin"), new Apple("Red"));
        Box<Orange> boxOrange1 = new Box<Orange>(new Orange("Blue"), new Orange("Red"));
        Box<Orange> boxOrange2 = new Box<Orange>(new Orange("Blue1"), new Orange("Red1"));

        System.out.println("Вес коробки с Orange 1 = "+ boxOrange1.getWeight());
        System.out.println("Вес коробки с яблоками 1 = "+ boxApple1.getWeight());
        System.out.println("Коробки по весу равны  " + boxApple1.boxSameWeight(boxOrange1));
        boxOrange1.addFruit(new Orange());
        System.out.println("Коробки по весу равны  " + boxApple1.boxSameWeight(boxOrange1));
        System.out.println("Коробка с яблоками 1" + boxApple1);
        System.out.println("Коробка с апельсинами 1 " + boxOrange1);
        System.out.println("Коробка с апельсинами 2 " + boxOrange2);
        System.out.println("Пересыпать из одной коробки в другую");
        boxOrange2.putFruit(boxOrange1);

        System.out.println("Коробка с апельсинами 1 " + boxOrange1);
        System.out.println("Коробка с апельсинами 2 " + boxOrange2);
    }

}