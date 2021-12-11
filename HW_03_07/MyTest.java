
public class MyTest {

    public void teat1(){
        System.out.println("test1");
    }
    @Test(prior = 8)
    public void test2(){
        System.out.println("test2");
    }
    @Test(prior = 1)
    public void test3(){
        System.out.println("test3");
    }
    @Test(prior = 5)
    public void test4(){
        System.out.println("test4");
    }
    @Test(prior = 7)
    public void test5(){
        System.out.println("test5");
    }
    @Test(prior = 3)
    public void test6(){
        System.out.println("test6");
    }
    @Test(prior = 9)
    public void test7(){
        System.out.println("test7");
    }
    @AfterSuite
    public void test8(){
        System.out.println("test8");
    }
    @BeforeSuite
    public void test9(){
        System.out.println("test9");
    }
}
