package com.apps.tilde.library;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.apps.tilde.Tilde;
import com.apps.tilde.TildeKt;

import java.util.ArrayList;

import kotlin.jvm.functions.Function0;

public class JavaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java);
    }
}