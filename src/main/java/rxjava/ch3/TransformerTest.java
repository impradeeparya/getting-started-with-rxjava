package rxjava.ch3;

import io.reactivex.rxjava3.core.Observable;

import java.util.Comparator;

/**
 * Created by p0a00hg on 23/02/24
 **/
public class TransformerTest {

    public static void main(String[] args) {
        Observable.just(9,2,3,4,1,5,8,7,6,0)
                .sorted()
                .subscribe(System.out::println);

        Observable.just(2, 3,4, 9,0,7,6,5,8,1)
                .sorted(Comparator.reverseOrder())
                .subscribe(System.out::println);

        System.out.println("--------------------");

        Observable.just(2, 3,4, 9,0,7,6,5,8,1)
                .sorted(Comparator.comparing(i -> i > 4))
                .subscribe(System.out::println);
    }
}
