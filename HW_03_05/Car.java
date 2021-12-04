import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;

public class Car implements Runnable {
    private static int CARS_COUNT;
    private static AtomicBoolean ab = new AtomicBoolean(false);
    static {
        CARS_COUNT = 0;
    }
    CyclicBarrier carBarrier;
    private Race race;
    private int speed;
    private String name;

    public Car(Race race, int speed, CyclicBarrier carBarrier) {
        this.race = race;
        this.speed = speed;
        this.carBarrier = carBarrier;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }

    public String getName() {
        return name;
    }
    public int getSpeed() {
        return speed;
    }
    /*
    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник #" + CARS_COUNT;
    }
    */
    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            carBarrier.await();
            carBarrier.await();
            for (int i = 0; i < race.getStages().size(); i++) {
                race.getStages().get(i).go(this);
            }
            if (ab.compareAndSet(false,true)){
                System.out.println("Car " + name + " Winner");
            }
            carBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}