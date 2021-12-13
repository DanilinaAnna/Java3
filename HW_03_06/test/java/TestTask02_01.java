import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class TestTask02_01 {
    private int[] array;
    private int[] arrRet;
    public TestTask02_01(int[] array, int[] arrRet) {
        this.array = array;
        this.arrRet = arrRet;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 4, 0, 7}, new int[]{0, 7}},
                {new int[]{1, 2, 3, 4}, new int[0]},
                {new int[]{1, 2, 3, 4, 4, 3, 2, 1, 0}, new int[]{3, 2, 1, 0}}
        });
    }

    @Test
    public void test() {
        Assert.assertArrayEquals(arrRet, HomeWork06.task02(array));
    }
}
