package com.wangheart.ericdroid;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wangheart.library.android.app.LoadingActivity;

public class MainActivity extends LoadingActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_user_list).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getThis(), UserListActivity.class);
                startActivity(intent);
            }
        });

    }

/*    @OnClick(R.id.btn_user_list)
    protected void onUserList() {
        Intent intent = new Intent(getThis(), UserListActivity.class);
        startActivity(intent);
    }*/

    @Override
    protected void loadData() {

    }
}
