package com.fengguncao.learnfit;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.fengguncao.learnfit.hotfix.Demo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Demo.test();
    }

}
