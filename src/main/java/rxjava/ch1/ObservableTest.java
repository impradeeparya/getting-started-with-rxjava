package rxjava.ch1;


import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by p0a00hg on 19/02/24
 **/
public class ObservableTest {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Observable start");

        Observable<String> fruits = Observable.just("Apple", "Orange", "Dragon fruit", "Papaya");
        fruits.subscribe(System.out::println);
        fruits.map(String::length)
                .subscribe(System.out::println);


        Observable<Long> secondIntervals = Observable.interval(1, TimeUnit.SECONDS);
        secondIntervals.subscribe(System.out::println);
        Thread.sleep(5000);

        System.out.println("Observable end");
    }
}
