package com.djonce.customviewdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


/**
 * 继承View的自定义控件步骤
 * a. value 文件夹下，定义attrs属性
 * b. 构造器中初始化，定义的属性
 * c. 根据需求是否重写onMeasure方法
 * d. 实现onDraw逻辑
 *
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }


}
