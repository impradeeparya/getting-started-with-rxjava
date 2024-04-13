package rxjava.ch2;

import io.reactivex.rxjava3.core.Observable;

import java.util.List;

/**
 * Created by p0a00hg on 19/02/24
 **/
public class ObservableTest {

    public static void main(String[] args) {

        emitter();
        throwable();
        fromIterable();
    }

    private static void fromIterable() {
        List<String> names = List.of("Pradeep", "Deepu", "Arya");
        Observable<String> observable = Observable.fromIterable(names);
        observable.map(String::length)
                .filter(item -> item > 4)
                .subscribe(System.out::println);
        System.out.println("--------------------fromIterable end---------------------");
    }

    private static void throwable() {
        Observable<String> observable = Observable.create(emitter -> {
            try {
                emitter.onNext("Apple");
                emitter.onNext("Mango");
                System.out.println(0 / 0);
                emitter.onNext("pineapple");
                emitter.onComplete();
            } catch (Exception e) {
                emitter.onError(e);
            }
        });

        observable.subscribe(System.out::println, Throwable::printStackTrace);

        System.out.println("--------------------throwable end---------------------");
    }

    private static void emitter() {
        Observable<String> observable = Observable.create(emitter -> {
            emitter.onNext("Apple");
            emitter.onNext("Mango");
            emitter.onNext("Orange");
            emitter.onComplete();
        });
        observable.subscribe(System.out::println);
        System.out.println("--------------------emitter end---------------------");
    }


}
