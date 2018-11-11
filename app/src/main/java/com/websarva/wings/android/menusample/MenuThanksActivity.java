package com.websarva.wings.android.menusample;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MenuThanksActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_thanks);

        //インテントオブジェクトを取得。
        Intent intent = getIntent();
        //リスト画面から渡されたデータを取得。
        String menuName = intent.getStringExtra("menuName");
        String menuPrice = intent.getStringExtra("menuPrice");

        //定食名と金額を表示させるTextViewを取得。
        TextView tvMenuName = findViewById(R.id.tvMenuName);
        TextView tvMenuPrice = findViewById(R.id.tvMenuPrice);

        //TextViewに定食名と金額を表示。output
        tvMenuName.setText(menuName);
        tvMenuPrice.setText(menuPrice);


        //戻るメニューの表示  アクションバーを取得
        ActionBar actionBar = getSupportActionBar();
        //アクションバーの戻るボタンを有効に設定
        actionBar.setDisplayHomeAsUpEnabled(true);

    }

    /**
     * 戻るボタンをタップした時の処理。
     * @param view 画面部品。
     */
    //public void onBackButtonClick(View view) {

     //   finish();
    //}

    //アクションバーの戻るメニュー選択時の処理
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //選択されたメニューIDを取得
        int itemId = item.getItemId();
        //選択されたメニューが「戻る」の場合、アクティビティを終了
        if(itemId == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }





}
