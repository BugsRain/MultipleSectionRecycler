package me.bugsrain.multiplesectionrecycler;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import me.bugsrain.multiplesectionrecycler.adapter.MultipleAdapter;

public class MainActivity extends AppCompatActivity {
    private List<Integer> banner = new ArrayList<>();
    private List<String> data1 = new ArrayList<>();
    private List<String> data2 = new ArrayList<>();
    private List<String> data3 = new ArrayList<>();

    RecyclerView recyclerView;
    MultipleAdapter multipleAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        banner.add(Color.BLACK);
        banner.add(Color.RED);
        banner.add(Color.GRAY);
        banner.add(Color.GREEN);
        banner.add(Color.YELLOW);
        banner.add(Color.CYAN);
        banner.add(Color.BLUE);

        for (int i = 0; i < 5; i++) {
            data1.add("数据源1");
        }

        for (int i = 0; i < 6; i++) {
            data2.add("数据源2");
        }

        for (int i = 0; i < 24; i++) {
            data3.add("数据源3");
        }

        recyclerView = (RecyclerView) findViewById(R.id.list);
        multipleAdapter = new MultipleAdapter(this, banner, data1, data2, data3, null);


        recyclerView.setAdapter(multipleAdapter);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < 4; i++) {
                    data1.add("新的数据");
                }

                for (int i = 0; i < 3; i++) {
                    data3.add("新的数据");
                }

                multipleAdapter.refreshData();
            }
        });
    }

}
