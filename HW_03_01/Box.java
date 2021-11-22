package HW_03_01;

import java.util.ArrayList;
import java.util.Arrays;
/**
 * Created by annav on 22.11.2021.
 */
public class Box<T extends Fruit> {
    ArrayList<T> boxFruitList = new ArrayList<>();

    public Box(T... arg) {
        boxFruitList.addAll(Arrays.asList(arg));
    }

    public Box() {
    }

    public void addFruit(T adFr) {
        boxFruitList.add(adFr);
    }


    public float getWeight() {
        return boxFruitList.get(0).weight * boxFruitList.size();
    }

    public boolean boxSameWeight(Box<? extends Fruit> another) {
        return this.getWeight() == another.getWeight();
    }

    @Override
    public String toString() {
        if (boxFruitList.isEmpty()) {
            return "Пустая коробка";
        } else {
            return " " + boxFruitList;
        }
    }

    public void putFruit(Box<T> another) {
        for (T fr : boxFruitList) {
            another.boxFruitList.add(fr);
        }
        boxFruitList.clear();
    }
}
