package com.ugiant.myandroidview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.ugiant.myandroidview.view.SimpleTopBar;

public class MainActivity extends AppCompatActivity {

//    private SimpleTopBar tb_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        tb_title = (SimpleTopBar) findViewById(R.id.tb_title);
//        tb_title.setOnTopBarClickListener(new SimpleTopBar.topbarClickListener() {
//            @Override
//            public void leftClick() {
//                Toast.makeText(MainActivity.this , "左边" , Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void rightClick() {
//                Toast.makeText(MainActivity.this , "右边" , Toast.LENGTH_SHORT).show();
//            }
//        });


    }
}
