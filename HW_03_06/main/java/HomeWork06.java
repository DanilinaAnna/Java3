

public class HomeWork06 {
    public static int[] task02(int[] arr) {
        for (int i = arr.length - 1; i >= 0; i--) {
            if (arr[i] == 4) {
                int[] retArray = new int[arr.length - 1 - i];
                System.arraycopy(arr, i + 1, retArray, 0, arr.length - 1 - i);
                return retArray;
            }

        }
        throw new RuntimeException();
    }

    public static boolean task3(int[] arr) {
        boolean one = false;
        boolean four = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 1) one = true;
            if (arr[i] == 4) four = true;
            if (arr[i] != 1 && arr[i] != 4) return false;
        }
        return one && four;
    }

}