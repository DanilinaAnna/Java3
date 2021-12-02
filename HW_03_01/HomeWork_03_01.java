package HW_03_01;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by annav on 22.11.2021.
 */
public class HomeWork_03_01 {
    static Integer arr[] = {1, 2, 3, 5, 7, 8, 9, 10};

    static Double varArray1[] = {1.5, 2d, 3d, 5.5, 7d, 8.7};
    static ArrayList<Double> arrDoublList = new ArrayList();
    public static void main(String[] args) {
        // task1

        System.out.println(Arrays.toString(arr));
        ch2Array(2,4,arr);
        System.out.println("Переместить элементы в архиве");
        System.out.println(Arrays.toString(arr) + "\n");

        // task2
        System.out.println("Array "+Arrays.toString(varArray1));
        arrToArrayList(varArray1,arrDoublList);
        System.out.println("ArrayList " +arrDoublList);

    }
    public static <T>  void ch2Array(int el1, int el2, T[] arr1) {
        T vl3;
        if (el1 != el2 && el1 < arr1.length && el2 < arr1.length) {
            vl3 = arr1[el2];
            arr1[el2] = arr1[el1];
            arr1[el1] = vl3;
        }
    }
    public static <T> void arrToArrayList(T[]ar1, ArrayList<T> arlist1){
        for (int i = 0; i < ar1.length; i++) {
            arlist1.add(ar1[i]);

        }
    }
}
