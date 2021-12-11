import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MyTest2 {

    public static void main(String[] args) {
        task(MyTest.class);

    }

    public static void task(Class cl) {
        ArrayList<Method> tests = new ArrayList<>();
        Method aft = null;
        Method bef = null;
        for (Method m : cl.getDeclaredMethods()) {
            if (m.isAnnotationPresent(Test.class)) {
                tests.add(m);
            }
            if (m.isAnnotationPresent(BeforeSuite.class)) {
                if (bef != null) throw new RuntimeException("Больше одного Before");
                bef = m;
            }
            if (m.isAnnotationPresent(AfterSuite.class)) {
                if (aft != null) throw new RuntimeException("Больше одного After");
                aft = m;
            }
        }

        Collections.sort(tests, new Comparator<Method>() {
            @Override
            public int compare(Method o1, Method o2) {
                int i1 = o1.getAnnotation(Test.class).prior();
                int i2 = o2.getAnnotation(Test.class).prior();
                return Integer.compare(i2,i1);
            }
        });
        try {
            Object obj = cl.newInstance();
            if (bef != null) bef.invoke(obj);
            for (Method m : tests){
                m.invoke(obj);
            }
            if(aft != null) aft.invoke(obj);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
