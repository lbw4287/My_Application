package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class NutsList extends AppCompatActivity {

    private List<String> list;
    private ListView listView;
    private EditText editSearch;
    private ArrayList<String> arrayList;
    private SearchAdapter adapter;
    private ArrayAdapter<String> search_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuts_list);

        if(Build.VERSION.SDK_INT >= 21){
            getWindow().setStatusBarColor(Color.parseColor("#B3722F"));
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("견과류");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xDADAA86D));
        actionBar.setDisplayHomeAsUpEnabled(true);

        String name[]= new String[]{"땅콩","마카다미아","밤","아몬드","잣","피스타치오","호두"};
        int imgs[]={R.drawable.peanut,R.drawable.macadamia,R.drawable.chestnut,R.drawable.almond,R.drawable.pinenuts,R.drawable.pistachio,R.drawable.walnut};
        String yn[]= new String[]{"△","X","O","X","X","△","X"};
        String description[]= new String[]{"칼륨이 나트륨 배출을 도와 심혈관질환 예방에 도움을 준다. 비타민 B가 풍부하여 콜레스테롤 수치를 낮춰준다. 그러나 지방이 많아 다량 급여시 췌장염의 위험이 있다. 껍질 벗긴 상태로 소량만 급여해야 한다.",
        "비틀거림, 종양, 구토 등의 중독 증세가 나타날 수 있다. 섭취했을 경우 가능한 빨리 동물병원에 내원하여 체내에 흡수되는 것을 막아야 한다."
        ,"비타민B1, 티아민이 풍부하여 피로회복에 좋다. 또한 섬유질이 풍부하여 변비가 있거나 장이 좋지 않은 반려견에게 효과가 있다. 되도록이면 껍질을 제거하고 잘게 부수거나 삶아서 급여한다.",
        "아스페르질루스(Aspergillus)라는 곰팡이가 필 경우 중독 증상 일으킨다. 다량 섭취시 소화장애가 발생할 수 있다.","지방과 인 함량이 매우 높아 췌장염이나 요로 감염 합병증 등을 일으킨다.",
        "과다 섭취시 위장장애, 비만, 췌장염이 발생할 위험이 있다. 곰팡이가 배양될 수 있는 환경을 제공하여 \n" +
                "곰팡이의 아플라톡신이라는 성분이 황달, 간 장애, 구토 발작 등을 일으키고 심한 경우 죽음에 이르게 할 수 있다.","호두 사이 핀 곰팡이가 위장장애와 신경장애 등을 일으킨다."};


        editSearch =(EditText)

                findViewById(R.id.editSearch);

        listView =(ListView)

                findViewById(R.id.listView);

        search_adapter =new ArrayAdapter<String>(this,R.layout.row_listview,R.id.label,name);
        listView.setAdapter(search_adapter);

        list =new ArrayList<String>();

        arrayList =new ArrayList<String>();
        arrayList.addAll(list);

        adapter =new SearchAdapter(list, this);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                NutsList.this.search_adapter.getFilter().filter(s);
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
                    Intent intent = new Intent(NutsList.this, FoodDetail.class);
                    intent.putExtra("key1",name[position]);
                    intent.putExtra("key2",yn[position]);
                    intent.putExtra("key3",description[position]);
                    intent.putExtra("key4",imgs[position]);
                    startActivity(intent);}
                else if(yn[position]=="X") {
                    Intent intent = new Intent(NutsList.this, FoodDetail_X.class);
                    intent.putExtra("key1",name[position]);
                    intent.putExtra("key2",yn[position]);
                    intent.putExtra("key3",description[position]);
                    intent.putExtra("key4",imgs[position]);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(NutsList.this, FoodDetail_tri.class);
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

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(NutsList.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}