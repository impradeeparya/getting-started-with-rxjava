package rxjava.ch3;

import io.reactivex.rxjava3.core.Observable;

/**
 * Created by p0a00hg on 22/02/24
 **/
public class ConditionOperatorTest {

    public static void main(String[] args) {
        Observable.range(1, 100)
                .takeWhile(number -> number < 5)
                .subscribe(System.out::println);

        Observable.range(1, 100)
                .skipWhile(number -> number < 96)
                .subscribe(System.out::println);

        Observable.just("Apple", "Banana")
                .filter(fruit -> fruit.startsWith("C"))
                .defaultIfEmpty("NA")
                .subscribe(System.out::println);

        Observable.just("apple", "banana")
                .filter(fruit -> fruit.startsWith("o"))
                .switchIfEmpty(Observable.just("orange", "pineapple"))
                .subscribe(System.out::println);
    }
}
