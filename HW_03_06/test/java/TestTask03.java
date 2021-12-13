import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collection;


@RunWith(Parameterized.class)
public class TestTask03 {
    private int[] array;
    private boolean result;
    public TestTask03(int[] array, boolean result) {
        this.array = array;
        this.result = result;
    }

    @Test
    public void testTask03() {
        Assert.assertEquals(HomeWork06.task3(array), result);
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {new int[]{1, 4, 1, 4, 4, 4,}, true},
                {new int[]{1, 1, 1}, false},
                {new int[]{1, 4, 1, 5}, false},
                {new int[]{4, 4, 4, 4}, false}
        });
    }

}