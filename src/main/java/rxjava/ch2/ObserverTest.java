package rxjava.ch2;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

/**
 * Created by p0a00hg on 19/02/24
 **/
public class ObserverTest {

    private static int start = 1;
    private static int end = 5;

    public static void main(String[] args) throws InterruptedException {

        observer();
        connectedObservable();
        range();
        interval();
        hotInterval();
        Thread.sleep(5000);
        defer();
        fromCallable();
        singleObserver();
        maybeObserver();
    }

    private static void maybeObserver() {
        Maybe.just("Apple")
                .subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("maybe completed"));

        Maybe.empty()
                .subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("maybe completed"));

        System.out.println("---------------------- maybe observer done ----------------------");
    }

    private static void singleObserver() {
        Single.just("Hello world")
                .subscribe(System.out::println, e -> System.out.println("Single observer exception: " + e.getMessage()));

        Observable.just("Hello", "World")
                .first("NA")
                .subscribe(System.out::println, e -> System.out.println("Single observer first exception: " + e.getMessage()));
        System.out.println("---------------------- single observer done ----------------------");
    }

    private static void fromCallable() {
        Observable.fromCallable(() -> 1 / 0)
                .subscribe(System.out::println,
                        (e) -> System.out.println("fromCallable exception: " + e.getMessage()),
                        () -> System.out.println("---------------------- fromCallable observer done ----------------------"));
    }

    private static void defer() {
        Observable<Integer> observable = Observable.defer(() -> Observable.range(start, end));
        observable.subscribe(System.out::println);
        end = 10;
        observable.subscribe(System.out::println);
        System.out.println("---------------------- defer observer done ----------------------");
    }

    private static void hotInterval() throws InterruptedException {
        ConnectableObservable<Long> publish = Observable.interval(1, TimeUnit.SECONDS).publish();
        publish.subscribe(seconds -> System.out.println("HotObserver1: " + seconds));
        publish.connect();
        Thread.sleep(2000);
        publish.subscribe(seconds -> System.out.println("HotObserver2: " + seconds));
        System.out.println("---------------------- hot interval observer done ----------------------");

    }

    private static void interval() throws InterruptedException {
        Observable<Long> observable = Observable.interval(1, TimeUnit.SECONDS);
        observable.subscribe(seconds -> System.out.println("Observer1: " + seconds));
        Thread.sleep(2000);
        observable.subscribe(seconds -> System.out.println("Observer2: " + seconds));
        System.out.println("---------------------- interval observer done ----------------------");
    }

    private static void range() {
        Observable.range(1, 5)
                .subscribe(System.out::println);
        System.out.println("---------------------- range observer done ----------------------");

    }

    private static void connectedObservable() {
        ConnectableObservable<String> connectableObservable = Observable.just("apple", "mango", "orange").publish();
        connectableObservable.subscribe(System.out::println);
        connectableObservable.map(String::length)
                .subscribe(System.out::println);
        connectableObservable.connect();
        System.out.println("---------------------- connectedObservable done ----------------------");
    }

    private static void observer() {
        Observable<String> observable = Observable.just("Apple", "Mango", "Pineapple", "Dragon fruit", "Orange");
        observable.map(String::length)
                .filter(length -> length > 5)
                .subscribe(new Observer<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull Integer integer) {
                        System.out.println("RECEIVED: " + integer);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                        System.out.println("---------------------- Observer done ----------------------");
                    }
                });

        observable.map(String::length)
                .filter(length -> length > 5)
                .subscribe(System.out::println, Throwable::printStackTrace, () -> System.out.println("---------------------- Observer done ----------------------"));
    }
}
