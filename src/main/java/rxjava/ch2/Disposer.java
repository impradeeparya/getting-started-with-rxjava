package rxjava.ch2;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.concurrent.TimeUnit;

/**
 * Created by p0a00hg on 19/02/24
 **/
public class Disposer {

    private static CompositeDisposable disposable = new CompositeDisposable();

    public static void main(String[] args) throws InterruptedException {
        disposeSeconds();
        disposeMultipleSeconds();
        observableEmitter();
    }

    private static void observableEmitter() throws InterruptedException {
        Disposable disposable = Observable.create(observableEmitter -> {
                    int i = 0;
                    while (!observableEmitter.isDisposed()) {
                        observableEmitter.onNext(i++);
                    }

                    if (observableEmitter.isDisposed()) {
                        return;
                    }
                    observableEmitter.onComplete();
                })
                .subscribe(System.out::println);
        Thread.sleep(5000);
        disposable.dispose();
        Thread.sleep(5000);
        System.out.println("-------------------- observableEmitter disposal done -------------------");
    }

    private static void disposeMultipleSeconds() throws InterruptedException {
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);
        disposable.add(seconds.subscribe((second) -> System.out.println("Observer 1: " + second + " sec")));
        disposable.add(seconds.subscribe((second) -> System.out.println("Observer 2: " + second + " sec")));
        Thread.sleep(5000);
        disposable.dispose();
        Thread.sleep(5000);

        System.out.println("-------------------- composite disposal done -------------------");

    }

    private static void disposeSeconds() throws InterruptedException {
        Observable<Long> seconds = Observable.interval(1, TimeUnit.SECONDS);
        Disposable disposable = seconds.subscribe(System.out::println);
        Thread.sleep(5000);
        disposable.dispose();
        Thread.sleep(5000);
        System.out.println("-------------------- disposal done -------------------");
    }
}
