package rxjava.ch7;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by p0a00hg on 09/03/24
 **/
public class BufferingTest {

    public static void main(String[] args) throws InterruptedException {


        Observable.range(1, 100)
                .buffer(10)
                .subscribe(System.out::println);

        System.out.println("--------------------------------------------------");

        Observable.range(1, 100)
                .buffer(10, 3)
                .subscribe(System.out::println);

        System.out.println("--------------------------------------------------");

        Observable.interval(300, TimeUnit.MILLISECONDS)
                .buffer(1, TimeUnit.SECONDS)
                .subscribe(System.out::println);



        Thread.sleep(50000);
    }
}
