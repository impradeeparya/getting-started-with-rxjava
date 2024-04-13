package rxjava.ch7;

import io.reactivex.rxjava3.core.Observable;

/**
 * Created by p0a00hg on 09/03/24
 **/
public class WindowTest {

    public static void main(String[] args) throws InterruptedException {

        Observable.range(1, 50)
                .window(8, 3)
                .flatMapSingle(integerObservable -> integerObservable.reduce("", (combined, next) -> combined + (combined.equals("") ? "" : "|") + next))
                .subscribe(System.out::println);

        System.out.println("----------------------------------");

        Observable.range(1, 50)
                .window(Observable.just(8))
                .flatMapSingle(integerObservable -> integerObservable.reduce("", (combined, next) -> combined + (combined.equals("") ? "" : "|") + next))
                .subscribe(System.out::println);

        Thread.sleep(5000);

    }
}
