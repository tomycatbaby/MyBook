package com.example.mybook;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.mybook.DB.MyDatabaseHelper;
import com.example.mybook.entity.Fruit;
import com.example.mybook.entity.TestAdapter;
import com.example.mybook.test.OnLoadListener;
import com.lzf.mybook.R;

import java.util.ArrayList;
import java.util.List;

public class ViewActivity extends BaseActivity implements OnLoadListener {
    private static final String TAG = "ViewActivity";
    private List<Fruit> mFruitList = new ArrayList<>();
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    MyDatabaseHelper databaseHelper;
    SQLiteDatabase sqLiteDatabase;
    private TestAdapter testAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        FloatingActionButton fab = findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });
        initView();
        initData();
    }

    private void initData() {
        databaseHelper = new MyDatabaseHelper(this, "BookStore.db", null, 1);
        sqLiteDatabase = databaseHelper.getReadableDatabase();
//        Cursor cursor = sqLiteDatabase.query("Book", new String[]{"id", "author"}, null, null, null, null, null, null);
////        int idIndex = cursor.getColumnIndex("id");
////        int nameIndex = cursor.getColumnIndex("author");
////        cursor.moveToLast();
////        while (cursor.moveToPrevious()) {
////            Fruit f = new Fruit("", 1);
////            f.setName(cursor.getString(nameIndex));
////            mFruitList.add(f);
////            //Log.d(TAG, "run: " + cursor.getString(idIndex) + "  " + cursor.getString(nameIndex));
////        }
////        cursor.close();
        for (int i = 0; i < 50; i++) {
            Fruit f = new Fruit("", 1);
            f.setName("DY"+i);
            mFruitList.add(f);
        }
        testAdapter = new TestAdapter(mFruitList,this,this);
        recyclerView.setAdapter(testAdapter);
    }

    public void initView() {
        setContentView(R.layout.activity_view);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(null);
        //swipeRefreshLayout = findViewById(R.id.swipe_refresh);
    }

    @Override
    public void onLoadFinishedListener() {

    }

    @Override
    public void onLoadingListener() {

    }
}
