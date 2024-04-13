package rxjava.ch3;

import io.reactivex.rxjava3.core.Observable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * Created by p0a00hg on 23/02/24
 **/
public class SuppressingTest {

    public static void main(String[] args) throws InterruptedException {
        Observable.interval(300, TimeUnit.MILLISECONDS)
                .take(2, TimeUnit.SECONDS)
                .subscribe(i -> System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("ss:SSS")) + " Received " + i));
        Thread.sleep(5000);

        Observable.just(1, 2, 2, 3, 3, 1, 2, 1, 1, 3)
                .distinctUntilChanged()
                .subscribe(System.out::println);
    }
}
