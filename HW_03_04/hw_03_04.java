
public class hw_03_04 {
    public static Object monitor = new Object();
    static volatile char priintCh = 'A';

    public static void main(String[] args) {
        Object mon = new Object();

        class Task01 implements Runnable {
            char chCurrent, chNext;

            public Task01(char chCurrent, char chNext) {
                this.chCurrent = chCurrent;
                this.chNext = chNext;
            }

            @Override
            public void run() {
                for (int i = 0; i < 5; i++) {
                    try {
                        synchronized (mon) {
                            while (priintCh != chCurrent) {
                                mon.wait();
                            }
                            System.out.print(priintCh);
                            priintCh = chNext;
                            mon.notifyAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        new Thread(new Task01('A','B')).start();
        new Thread(new Task01('B','C')).start();
        new Thread(new Task01('C','A')).start();

    }

}
