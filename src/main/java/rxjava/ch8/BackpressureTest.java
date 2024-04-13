package rxjava.ch8;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.util.concurrent.TimeUnit;

/**
 * Created by p0a00hg on 10/03/24
 **/
public class BackpressureTest {

    public static void main(String[] args) throws InterruptedException {

        Flowable.interval(6, TimeUnit.MILLISECONDS)
                .observeOn(Schedulers.io())
                .map(i -> calculation(i))
                .subscribe(System.out::println);

        Thread.sleep(Long.MAX_VALUE);
    }

    private static Long calculation(Long i) throws InterruptedException {
        Thread.sleep(7000);
        return i;
    }
}
