package com.example.mybook.utils;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Action9;
import rx.functions.ActionN;
import rx.functions.Func1;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class Rx {
    private static final String TAG = "Rx";

    public void test() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext: ");
            }
        };
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted: ");
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "onError: ");
            }

            @Override
            public void onNext(String c) {
                Log.d(TAG, "onNext: "+c);
            }

            @Override
            public void onStart() {
                super.onStart();
            }
        };
        List<String> list = new ArrayList<>();
        list.add("数学");
        list.add("语文");
        list.add("英语");
        Student[] students = {new Student(list),new Student(list)};

        Observable.from(students)
                .flatMap(new Func1<Student, Observable<String>>() {
                    @Override
                    public Observable<String> call(Student student) {
                        return Observable.from(student.getCourses());
                    }
                })
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(subscriber);

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onStart();
                subscriber.onNext("hello");
                subscriber.onNext("hi");
                subscriber.onCompleted();
            }
        });
        observable.subscribeOn(Schedulers.io());//指定subscribe发生在io线程
        observable.observeOn(AndroidSchedulers.mainThread());//指定subscribe的回调发生在主线程
        Observable.just(1,2,3,4).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Integer>() {
                    @Override
                    public void call(Integer integer) {
                        Log.d(TAG, "call: "+integer);
                    }
                });
        Observable.just("").map(new Func1<String,Bitmap>(){
            @Override
            public Bitmap call(String s ) {
                return null;
            }
        });

    }
    class Student{
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
