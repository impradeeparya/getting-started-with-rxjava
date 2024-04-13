package rxjava.ch6;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * Created by p0a00hg on 09/03/24
 **/
public class ConcurrencyTest {


    public static void main(String[] args) {

        Observable.just("Alpha", "Beta", "Gamma")
                .subscribeOn(Schedulers.computation())
                .blockingSubscribe(System.out::println);


        Observable.interval(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.computation())
                .blockingSubscribe(System.out::println);

    }
}
