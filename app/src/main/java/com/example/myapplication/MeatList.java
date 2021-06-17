package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MeatList extends AppCompatActivity {

    private List<String> list;
    private ListView listView;
    private EditText editSearch;
    private ArrayList<String> arrayList;
    private SearchAdapter adapter;
    private ArrayAdapter<String> search_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meat_list);

        if(Build.VERSION.SDK_INT >= 21){
            getWindow().setStatusBarColor(Color.parseColor("#C02918"));
        }


        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("육류");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xE7E74C3C));
        actionBar.setDisplayHomeAsUpEnabled(true);


        String name[] = new String[]{"닭", "돼지", "소", "양", "오리" };
        int imgs[]={R.drawable.chicken,R.drawable.pork,R.drawable.beef, R.drawable.lamb,R.drawable.duck};
        String yn[]= new String[]{"O","O","O","O","O"};
        String description[]= new String[]{"고단백 저지방 식품으로 비타민이 많아 각종 질병을 예방하고 섬유질이 가늘어 소화흡수가 용이하다. 익혀서 줄 경우 뼈는 제거하고, 껍질에 다량의 지방이 농축되어 있어 제거하는 것이 좋다.",
        "단백질과 필수 아미노산이 들어있어 면역을 향상시키고, 사르코신이라는 성분이 근력을 강화시킨다.\n" +
                "날고기에는 바이러스가 있을 수 있어 익힌 것을 주는 것이 좋고, 되도록 지방이 적은 부위를 급여한다.","비타민 B 함유량이 다른 육류보다 훨씬 높아 혈관질환을 예방하는데에 도움이 된다. 양념된 고기나 날고기는 주지 않아야 하며 기름기가 많으므로 지방을 제거하고 급여하는 것이 좋다. 또 돼지고기 뼈는 익혔을 때 매우 날카로우므로 뼈를 제거해야한다."
        ,"비타민B3와 철분이 다량 함유되어있어 혈액순환과 빈혈 예방에 효과가 있다. 특유의 잡내가 있어 급여를 거부하는 경우가 있으므로 애견 상품으로 판매하는 고기를 급여하는 것을 권한다.",
        "불포화지방산을 포함하고 있어 피부와 모질 개선에 도움을 주고 다른 육류보다 기름기가 적어 단백질 섭취에 좋다. \n\n" +
                "단, 훈제는 첨가물과 조미료 성분이 들어있기 때문에 급여해서는 안되고, 푹 삶아서 주는 것이 좋다."};

        editSearch = (EditText) findViewById(R.id.editSearch);
        listView = (ListView) findViewById(R.id.listView);

        search_adapter = new ArrayAdapter<String>(this, R.layout.row_listview, R.id.label, name);
        listView.setAdapter(search_adapter);
        list = new ArrayList<String>();


        arrayList = new ArrayList<String>();
        arrayList.addAll(list);

        adapter = new SearchAdapter(list, this);


        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MeatList.this.search_adapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

                String text = editSearch.getText().toString();
                search(text);
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(yn[position]=="O"){
                    Intent intent = new Intent(MeatList.this, FoodDetail.class);
                    intent.putExtra("key1",name[position]);
                    intent.putExtra("key2",yn[position]);
                    intent.putExtra("key3",description[position]);
                    intent.putExtra("key4",imgs[position]);
                    startActivity(intent);}
                else if(yn[position]=="X") {
                    Intent intent = new Intent(MeatList.this, FoodDetail_X.class);
                    intent.putExtra("key1",name[position]);
                    intent.putExtra("key2",yn[position]);
                    intent.putExtra("key3",description[position]);
                    intent.putExtra("key4",imgs[position]);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(MeatList.this, FoodDetail_tri.class);
                    intent.putExtra("key1",name[position]);
                    intent.putExtra("key2",yn[position]);
                    intent.putExtra("key3",description[position]);
                    intent.putExtra("key4",imgs[position]);
                    startActivity(intent);}
            }
        });
    }
    public void search(String charText){

        list.clear();
        if(charText.length() == 0){
            list.addAll(arrayList);
        }
        else{
            for(int i =0;i< arrayList.size();i++) {
                if (arrayList.get(i).toLowerCase().contains(charText)){
                    list.add(arrayList.get(i));
                }
            }
        }
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MeatList.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}