package com.example.mybook.test;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.util.Log;
import android.util.SparseArray;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.mybook.BaseActivity;
import com.example.mybook.DB.MyDatabaseHelper;
import com.example.mybook.entity.Fruit;
import com.example.mybook.entity.TestAdapter;
import com.example.mybook.utils.Constants;
import com.lzf.mybook.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Route(path = Constants.TESTPATH)
public class TestActivity extends BaseActivity implements OnLoadListener {

    private static final String TAG = "TestActivity";
    private List<Fruit> mFruitList = new ArrayList<>();
    //private SwipeRefreshLayout refreshLayout;
    private Fruit loading = new Fruit(true);
    MyDatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    private TestAdapter testAdapter;
    private LinearLayoutManager linearLayoutManager;
    ArrayMap m = null;
    SparseArray s = null;
    boolean isFirst = true;
    boolean isEnd = false;
    int i = 0;
    private Fruit[] fruits = {new Fruit("apple is 14￥ per kilogram,so I cant afford it", R.drawable.apple), new Fruit("banana", R.drawable.banana),
            new Fruit("orange", R.drawable.orange), new Fruit("peer", R.drawable.peer)};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        ARouter.getInstance().inject(this);

        initView();
        //insert();
    }

    private void insert() {
        databaseHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        sqLiteDatabase.beginTransaction();
        for (int i = 0; i < 400; i++) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", "");
            Random random = new Random();
            int index = random.nextInt(fruits.length);
            contentValues.put("price", fruits[index].getImageId());
            contentValues.put("author", "Gucci" + i);
            sqLiteDatabase.insert("Book", null, contentValues);
        }

        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();

    }

    private void initView() {
        databaseHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);
        sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("Book", new String[]{"id", "author"}, null, null, null, null, "id" + " DESC", i + "," + 60);
        int idIndex = cursor.getColumnIndex("id");
        int nameIndex = cursor.getColumnIndex("author");
        cursor.moveToLast();
        while (cursor.moveToPrevious()) {
            Fruit f = new Fruit("", 1);
            f.setName(cursor.getString(nameIndex));
            mFruitList.add(f);
            //Log.d(TAG, "run: " + cursor.getString(idIndex) + "  " + cursor.getString(nameIndex));
        }
        cursor.close();
        final RecyclerView recyclerView = findViewById(R.id.testRecyclerView);
        recyclerView.setItemAnimator(null);
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        testAdapter = new TestAdapter(mFruitList, this, this);
        recyclerView.setAdapter(testAdapter);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int firstCompletelyVisibleItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
                if (firstCompletelyVisibleItemPosition == 0) {
                    if (!isFirst) {
                        i = i + 20;
                        List<Fruit> newFruitList = new ArrayList<>();
                        Fruit head = mFruitList.get(0);
                        Cursor cursor = sqLiteDatabase.query("Book", new String[]{"id", "author"}, null, null, null, null, "id" + " DESC", i + "," + 20);
                        //mFruitList.clear();
                        int idIndex = cursor.getColumnIndex("id");
                        int nameIndex = cursor.getColumnIndex("author");
                        cursor.moveToLast();
                        do {
                            if (head.getName().equals(cursor.getString(nameIndex))) {
                                Log.d(TAG, "获取到的name是已经存在于原先List: ");
                                isEnd = true;
                                break;
                            } else {
                                Fruit f = new Fruit("", 1);
                                f.setName(cursor.getString(nameIndex));
                                newFruitList.add(f);
                            }
                        } while (cursor.moveToPrevious());
                        cursor.close();
                        newFruitList.addAll(mFruitList);
                        mFruitList.clear();
                        mFruitList = newFruitList;


                        testAdapter.setmFruitList(mFruitList);
                        testAdapter.notifyItemRemoved(0);
                    } else {
                        Log.d(TAG, "第一次滑动到顶部: ");
                        isFirst = false;
                    }
                }
                int lastCompletelyVisibleItemPosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastCompletelyVisibleItemPosition == linearLayoutManager.getItemCount() - 1)
                    Log.i(TAG, "滑动到底部");
            }
        });
        //recyclerView.smoothScrollToPosition(testAdapter.getItemCount() - 1);
        //refreshLayout = findViewById(R.id.swipeRefreshLayout);
//        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {

//            }
//        });
    }

    @Override
    public void onLoadFinishedListener() {
        Log.d(TAG, "onLoadFinishedListener: ");
        //先从数据库取到数据，然后调用移除
        testAdapter.notifyItemRemoved(0);
    }

    @Override
    public void onLoadingListener() {
        Log.d(TAG, "onLoadingListener: ");
    }
}
