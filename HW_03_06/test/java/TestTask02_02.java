import org.junit.Test;



public class TestTask02_02 {
    @Test(expected = RuntimeException.class)
    public void testTask02_02() {
        HomeWork06.task02(new int[]{1, 2, 3,0 ,5,7,9});
    }

}
