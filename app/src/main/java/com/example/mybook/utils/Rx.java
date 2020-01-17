package com.example.mybook.utils;

import android.util.Log;

import com.example.mybook.entity.UserInfo;
import com.google.gson.JsonObject;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class Rx {
    private static final String TAG = "Rx";

    public void test() {
        Observer<String> observer = new Observer<String>() {
            //一次性用品
            private Disposable disposable;
            private int i;

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: " + s);
                i++;
                if (i == 2) {
                    Log.d(TAG, "dispose: ");
                    disposable.dispose();
                }
            }
        };
        Flowable flowable = new Flowable() {
            @Override
            protected void subscribeActual(Subscriber s) {

            }
        };
        Subscriber<String> subscriber = new Subscriber<String>() {


            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onComplete() {

            }

            @Override
            public void onSubscribe(Subscription s) {

            }

            @Override
            public void onNext(String c) {
                Log.d(TAG, "onNext: " + c);
            }

        };
        List<String> list = new ArrayList<>();
        list.add("数学");
        list.add("语文");
        list.add("英语");
        Student[] students = {new Student(list), new Student(list)};


       /* Observable.fromArray(students)
                .flatMap(new Function<Student, ObservableSource<?>>() {
                    @Override
                    public ObservableSource<?> apply(Student student) throws Exception {
                        return null;
                    }
                })
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();*/

        Observable<String> observable1 = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                Log.d(TAG, "emitter: 1");
                emitter.onNext("1");
                Log.d(TAG, "emitter: 2");
                emitter.onNext("2");
                Log.d(TAG, "emitter: 3");
                emitter.onNext("3");

            }
        });
        Observable<Integer> observable2 = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                Log.d(TAG, "emitter: 111");
                emitter.onNext(11);
                Log.d(TAG, "emitter: 22");
                emitter.onNext(22);
            }
        });

        Observable.zip(observable1, observable2, new BiFunction<String, Integer, String>() {
            @Override
            public String apply(String s, Integer integer) throws Exception {
                return s + integer;
            }
        }).subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String o) {
                Log.d(TAG, "onNext: " + o);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
        observable1.subscribe();

        /* observable.subscribeOn(Schedulers.io())//指定subscribe发生在io线程 上游事件发送线程
                 .observeOn(AndroidSchedulers.mainThread())//指定subscribe的回调发生在主线程 下游事件处理线程
                 .subscribe();*/


    }

    public void request() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl("https://www.wanandroid.com/")
                .build();
        WeatherService weatherService = retrofit.create(WeatherService.class);

//        weatherService.login("408","3")
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(Schedulers.io())
//                .doOnNext(new Consumer<JsonObject>() {
//                    @Override
//                    public void accept(JsonObject jsonObject) throws Exception {
//                        Log.d(TAG, "accept: ");
//                    }
//                }).subscribe(new Observer<JsonObject>() {
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(JsonObject jsonObject) {
//                Log.d(TAG, "onNext: ");
//                Log.d(TAG, "on: "+jsonObject.getAsJsonObject("data").get("curPage").getAsString());
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "onError: ");
//            }
//
//            @Override
//            public void onComplete() {
//
//            }
//        });

        Observable<JsonObject> observable1 = weatherService.login("408", "1")
                .subscribeOn(Schedulers.newThread())
                .observeOn(Schedulers.io())
                .doOnNext(new Consumer<JsonObject>() {
                    @Override
                    public void accept(JsonObject jsonObject) throws Exception {

                    }
                });
        observable1.subscribe(new Observer<JsonObject>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(JsonObject jsonObject) {
                Log.d(TAG, "onNext: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onComplete() {

            }
        });

//        Observable<JsonObject> observable2 = weatherService.login("408", "2")
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(Schedulers.io())
//                .doOnNext(new Consumer<JsonObject>() {
//                    @Override
//                    public void accept(JsonObject jsonObject) throws Exception {
//
//                    }
//                });
//
//        Observable.zip(observable1, observable2, new BiFunction<JsonObject, JsonObject, String>() {
//            @Override
//            public String apply(JsonObject jsonObject, JsonObject jsonObject2) throws Exception {
//                Log.d(TAG, "apply: ");
//                String s1 = jsonObject.getAsJsonObject("data").get("curPage").getAsString();
//                String s2 = jsonObject2.getAsJsonObject("data").get("curPage").getAsString();
//
//                return s1 + s2;
//            }
//        }).subscribe(new Observer<String>() {
//
//            @Override
//            public void onSubscribe(Disposable d) {
//                Log.d(TAG, "onSubscribe: ");
//            }
//
//            @Override
//            public void onNext(String s) {
//                Log.d(TAG, "onNext: " + s);
//            }
//
//            @Override
//            public void onError(Throwable e) {
//                Log.d(TAG, "onError: " + e.getMessage());
//            }
//
//            @Override
//            public void onComplete() {
//                Log.d(TAG, "onComplete: ");
//            }
//        });

    }

    class Student {
        List<String> courses;

        public Student(List<String> courses) {
            this.courses = courses;
        }

        public List<String> getCourses() {
            return courses;
        }

        public void setCourses(List<String> courses) {
            this.courses = courses;
        }
    }
}
