package com.websarva.wings.android.menusample;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuListActivity extends AppCompatActivity {
    //リストビューに表示すルフォールド
    private ListView _lvMenu;
    //リストビューに表示するリストデータ
    private List<Map<String,Object>> _menuList;
    //SimpleAdapterの第４引数fromに使用する定数フィールド
    private static final String[] FROM = {"name","price"};
    //SimpleAdapterの第５引数toに使用する定数フィールド
    private  static final int[] TO = {R.id.tvMenuName,R.id.tvMenuPrice};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_list);
        //画面部品ListViewを取得し、フィールドに格納
        _lvMenu = findViewById(R.id.lvMenu);
        //定食メニューListオプジェクトをprivateMethodを利用して、フィールドに格納
        _menuList = createTeishokuList();
        //SimpleAdapterの生成
        SimpleAdapter adapter = new SimpleAdapter(MenuListActivity.this, _menuList, R.layout.row, FROM, TO);
        //アダプタの登録
        _lvMenu.setAdapter(adapter);
        //リストタップのリスナクラス登録
        _lvMenu.setOnItemClickListener(new ListItemClickListener());

        //長押しで表示させるビュー 部品登録
        registerForContextMenu(_lvMenu);

    }

    private List<Map<String,Object>> createTeishokuList() {//Method
        //定食メニューリスト用のListオブジェクト
        List<Map<String, Object>> menuList = new ArrayList<>();
        //「唐揚げ定食」のデータを格納するMapオプジェクト
        Map<String, Object> menu = new HashMap<>();
        menu.put("name", "唐揚げ定食");//put:Mapとキーを関連付ける
        menu.put("price", 800);
        menu.put("desc", "若鶏の唐揚にサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu); //sdd:listに追加するMethod

        menu = new HashMap<>();
        menu.put("name", "ハンバーグ定食");
        menu.put("price", 850);
        menu.put("desc", "手ごねハンバーグにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name","生姜焼き定食");
        menu.put("price",850);
        menu.put("desc","生姜焼きにサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name","ステーキ定食");
        menu.put("price",1000);
        menu.put("desc","ステーキとサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name","野菜炒め定食");
        menu.put("price",700);
        menu.put("desc","野菜炒めとサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name","とんかつ");
        menu.put("price",900);
        menu.put("desc","とんかつとサラダ、ご飯とお味噌汁が付きます。");
        menuList.add(menu);

        return menuList;
    }
    private class ListItemClickListener implements AdapterView.OnItemClickListener {

        public void onItemClick(AdapterView<?> parent, View view,int position,long id) {
            //タップされがデーターを取得     一行分のデータはMap型 キャストが必要
            Map<String,Object> item =(Map<String, Object>) parent.getItemAtPosition(position);

            order(item);
        }
    }
      //オプションメニュー onCreateMenu() Method実装
    public boolean onCreateOptionsMenu(Menu menu){
        //メニューインフレーターの取得
        MenuInflater inflater = getMenuInflater();//メニューxmlファイルをMenuオプジェクトにインスタンス化するためのMethod
        //オプションメニュー用.xmlファイルをインフレート(膨らます）
        inflater.inflate(R.menu.menu_options_menu_list,menu);//指定されたXMLリソースからメニュー階層を拡張
        //親クラスのMethodを呼び出し、その戻り値を返却
        return super.onCreateOptionsMenu(menu);
    }

     //カレーメニューを生成するMethod
    private List<Map<String,Object>> createCurryList(){
        //カレーメニューリスト用のlistオプジェクトを用意
        List<Map<String,Object>> menuList = new ArrayList<>();
        //「ビーフカレー」のデーターを格納するMapオブジェクトの用意
        Map<String,Object> menu = new HashMap<>();
        menu.put("name","ビーフカレー");
        menu.put("price",520);
        menu.put("desc","特製スパイスをきかせた国産ビーフ100%のカレーです。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name","ポークカレー");
        menu.put("price",420);
        menu.put("desc","特製スパイスをきかせた国産ポーク100%のカレーです。");
        menuList.add(menu);

        menu = new HashMap<>();
        menu.put("name","野菜カレー");
        menu.put("price",450);
        menu.put("desc","特製スパイスをきかせた地元野菜たっぷりのカレーです。");
        menuList.add(menu);

        return menuList;

    }
    //オプションメニュー選択時の処理のMethod
    public boolean onOptionsItemSelected(MenuItem item) {//MenuItem: 以前作成したメニューに直接アクセスするためのインターフェース
        //選択されたメニューのIDを取得
        int itemId = item.getItemId();
        //IDのR値による処理の分岐
        switch (itemId) {
            //定食メニューが選択された時の処理
            case R.id.menuListOptionTeishoku:
                //定食メニュー知るとデーターの生成
                _menuList = createTeishokuList();
                break;
            //カレーメニューが選択された時の処理
            case R.id.menuListOptionCurry:
                //カレーデータを生成
                _menuList = createCurryList();
                break;
        }
        //SimpleAdapterを選択されたメニューデーターで生成
        SimpleAdapter adapter = new SimpleAdapter(MenuListActivity.this, _menuList, R.layout.row, FROM, TO);
        //アダプタ登録
        _lvMenu.setAdapter(adapter);
        //親クラスの同盟Methodを呼び出し、その戻り値を返却
        return super.onOptionsItemSelected(item);

    }

    //コンテンツメニュー  Method生成
    @Override
   public void onCreateContextMenu(ContextMenu menu,View view ,ContextMenu.ContextMenuInfo menuInfo){
        //親クラスの同盟Methodの呼び出し
       super.onCreateContextMenu(menu,view,menuInfo);
        //メニューインフレーターを取得
        MenuInflater inflater = getMenuInflater();
        //コンテキストメニュー用の.xmlファイルをインフレート
        inflater.inflate(R.menu.menu_context_menu_list,menu);
        //コンテキストメニューのヘッダタイトルを設定
        menu.setHeaderTitle(R.string.menu_list_context_header);

    }

    //注文メニュー処理
    //ListItemClickListenerのonItemClick()メソッド内の処理をPriveteメソッドで切り出し再利用
    private  void order(Map<String,Object> menu) {
        //定食名と金額を取得 Mapの部分がオブジェクトなのでキャストが必要
        String menuName = (String) menu.get("name");
        Integer menuPrice = (Integer) menu.get("price");
        //インテントを生成
        Intent intent = new Intent(MenuListActivity.this, MenuThanksActivity.class);
        //２画面に送るデーターを格納
        intent.putExtra("menuName", menuName);
        intent.putExtra("menuPrice",menuPrice+"円");
        //第二画面で起動
        startActivity(intent);
    }
    //コンテキストメニューの処理
    public boolean onContextItemSelected(MenuItem item){
        //長押しされビューに関する情報が格納されたオブジェクトを取得  どのビューを長押ししたか
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        //長押しされたリストのボジションを取得
        int listPosition = info.position;//位置を取得するメソッド
        //ポジションからの長押しされた情報のMapオブジェクトを取得
        Map<String,Object>menu = _menuList.get(listPosition);
        //選択されたメニューのIDを取得
        int itemId = item.getItemId();
        //IDのR値による処理の分岐
        switch (itemId){
            case R.id.menuListContextDesc:
                 String desc = (String)menu.get("desc");

                Toast.makeText(MenuListActivity.this,desc,Toast.LENGTH_LONG).show();
                break;
            case R.id.menuListContextOrder:

                order(menu);
                break;

        }
        return super.onContextItemSelected(item);
    }



}