package rxjava.ch4;

import io.reactivex.rxjava3.core.Observable;

import java.util.concurrent.TimeUnit;

/**
 * Created by p0a00hg on 27/02/24
 **/
public class CombineObservableTest {

    public static void main(String[] args) throws InterruptedException {

//        merge();
//        flatMap();

        Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> "Source2: " + l + " millisecond")
                .subscribe(System.out::println);
        Thread.sleep(5000);


    }

    private static void flatMap() throws InterruptedException {
        Observable.just("Alpha", "Beta", "Gamma")
                .flatMap(input -> Observable.fromArray(input.split("")))
                .subscribe(System.out::println);

        Observable.just(1, 2, 3)
                .flatMap(input -> Observable.interval(input, TimeUnit.SECONDS)
                        .map(second -> input + " interval " + (second + 1) * input + " second"))
                .subscribe(System.out::println);
        Thread.sleep(5000);
    }

    private static void merge() throws InterruptedException {
        Observable<String> firstSource = Observable.just("One", "two");
        Observable<String> secondSource = Observable.just("three", "four");
        Observable<String> thirdSource = Observable.just("five", "six");

        firstSource.mergeWith(secondSource)
                .subscribe(System.out::println);
        Observable.merge(firstSource, secondSource)
                .subscribe(System.out::println);

        Observable.mergeArray(firstSource, secondSource, thirdSource)
                .subscribe(System.out::println);


        System.out.println("-----------------------------------------------------------------");

        Observable<String> seconds = Observable.interval(1, TimeUnit.SECONDS)
                .map(l -> l + 1)
                .map(l -> "Source1: " + l + " seconds");

        Observable<String> milliseconds = Observable.interval(300, TimeUnit.MILLISECONDS)
                .map(l -> (l + 1) * 300)
                .map(l -> "Source2: " + l + " millisecond");

        Observable.merge(seconds, milliseconds)
                .subscribe(System.out::println);

        Thread.sleep(5000);
    }
}
