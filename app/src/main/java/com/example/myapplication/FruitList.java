package com.example.myapplication;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ScheduledExecutorService;

public class FruitList extends AppCompatActivity {

    private List<String> list;
    private ListView listView;
    private EditText editSearch;
    private ArrayList<String> arrayList;
    private SearchAdapter adapter;
    private ArrayAdapter<String> search_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fruit_list);

        if(Build.VERSION.SDK_INT >= 21){
            getWindow().setStatusBarColor(Color.parseColor("#F45D4C"));
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("과일류");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFFFF6347));
        actionBar.setDisplayHomeAsUpEnabled(true);

        String name[]= new String[]{"감","귤","대추","딸기","망고","멜론","무화과","바나나","배","복숭아","블루베리","사과","수박","아보카도","오렌지",
        "자두","자몽","체리","키위","파인애플","포도/건포도"};
        int imgs[] = {R.drawable.persimmon,R.drawable.tangerine, R.drawable.jujube,R.drawable.strawberry,R.drawable.mango,R.drawable.melon,R.drawable.fig,R.drawable.banana,
        R.drawable.pear,R.drawable.peach,R.drawable.blueberry, R.drawable.apple,R.drawable.watermelon ,R.drawable.avocado, R.drawable.orange,R.drawable.plum, R.drawable.grapefruit,
        R.drawable.cherry,R.drawable.kiwi,R.drawable.pineapple,R.drawable.grape};

        String yn[] = new String[]{"O","O","O","O","O","O","X","O","O","O","O","O","O","X","O","O","X","△"
        ,"O","O","X"};
        String description[]= new String[]{"칼륨, 베타카로틴이 포함되어 있어 시력 건강과 노화 방지에 도움을 준다. 저지방 고섬유질로 규칙적인 배변활동을 유지할 수 있게 한다.\n" +
                "단, 덜익은 감보다 홍시나 단감의 속살만 급여한다.","비타민C, 엽산, 베타카로틴 등이 풍부하여 면역력, 눈, 장 건강, 나트륨 배출에 도움을 준다. \n" +
                "단 당이 많기 때문에 과체중일 경우 독이 될 수 있다. 귤껍질과 흰색껍질까지 제거해서 급여한다.","식이섬유, 비타민, 항산화물질이 풍부하여 면역력 향상과 장건강에 도움을 준다. 단, 씨앗과 꼭지는 제거하고 하루 1~2개만 급여한다.",
        "항산화제와 영양소가 풍부해 치아건강에 도움이 되고 항암 작용 효과가 있다.","비타민 A • C • D, 식이섬유, 항산화물질이 함유되어있어 변비 개선, 모질, 면역력 강화에 도움이 된다."
        ,"라이코펜 성분이 체내 유해산소를 제거하고 암을 예방한다. \n" +
                "칼륨 성분이 풍부해 염분을 배출하고 이뇨작용으로 신장을 좋게 해준다.\n" +
                "단, 씨앗에 시안화물이라는 독성물질이 있어 씨앗과 껍질은 제거하고 급여한다.","솔라렌, 피신이라는 물질이 구토, 구내염을 일으킬 수 있다. 다량 섭취시 중독 위험 있다.","일반 과일보다 비타민 C를 10배 높게 함유하고 있어 피로 회복 및 무기력증 해소에 도움. 펙틴, 미네랄, 섬유질 등의 영양소가 장 운동, 면역력 강화에 도움을 준다.\n" +
                "단 신장이 좋지 않을 경우 독이 될 수 있다.","사포닌, 루테올린이라는 성분이 기관지 질환을 예방에 도움을 준다. 인버테이스, 옥시데이스라는 소화효소가 풍부해서 소화 기능이 약할 경우 효과적이다. 단, 씨에는 독성이 존재하므로 제거하고 급여한다."
        ,"비타민 C • E, 칼륨, 식이섬유를 함유하여 면역을 향상시키고 대사를 원활하게 한다.\n" +
                "복숭아의 씨앗, 껍질, 잎에 아미그달린이라는 물질이 소화가 될 때 시안화수소를 발생시키므로 반드시 이를 제거하고 급여한다.",
        "풍부한 섬유질이 노화를 방지해주고 심장질환, 뇌졸중 등 여러 가지 질병을 예방해주어 노견에게도 효과적이다.\n" +
                "단 당분이 많아 치석이 쌓일 수 있어 적당량 급여한다.","비타민 A • B • C • E, 베타카로틴, 식이섬유가 있어 건강에 이롭다. 펙틴 성분은 유독 성분을 흡수해 피부 또한 좋게 해준다. \n" +
                "단, 씨앗에 독성 성분이 있으므로 반드시 제거해야 한다.","수분 함량이 높아 갈증 해소에 좋고 비타민 A•B•C와 티아민 등이 함유되어 있어 면역력 개선에 도움을 준다. 칼로리가 낮아 부담이 없다. 라이코펜 성분이 암 예방에 도움을 준다. \n" +
                "단 수박 씨를 먹을 경우 장폐색을 일으킬 수 있으므로 씨를 반드시 제거하고 급여한다."
        ,"페르신이라는 성분이 호흡곤란, 위장장애, 복통 등을 일으킬 수 있다.","비타민 C가 풍부하고 섬유질이 소화에 도움을 준다. 단, 껍질과 씨앗을 제거한 후 적당량 급여한다.\n" +
                "당분이 많이 포함되어 있어 당뇨, 비만일 경우 급여하지 않는 것이 좋다.","섬유질이 풍부하여 콜레스테롤 수치를 낮춰준다. 비타민 K가 뼈 건강을 개선하고 관절염을 예방할 수 있게 한다.\n" +
                "단, 껍질과 씨를 제거하고 급여한다.","산성함량이 높아 위장 장애, 소화 문제 일으킬 수 있다. 씨앗에 소랄렌이라는 성분이 중독 현상을 일으킬 수 있다.","칼륨 성분이 혈압을 조절하고 고혈압과 뇌졸중의 위험을 감소시킨다. 철분이 매우 풍부해 빈혈 예방과 개선에 도움을 준다.\n" +
                "단, 씨앗에 시안화물이라는 독성 성분이 함유되어 있어 청색증, 호흡곤란, 구토, 설사 등을 일으킬 수 있으니 씨앗, 줄기, 잎은 제거하고 과육만 급여한다.","비타민 C가 풍부하여 감기예방 및 면역력 개선에 효과적이다. 비타민 E, 칼륨, 베타카로틴 성분이 변비 예방, 피로회복, 심혈관질환 예방 등에 도움을 준다."
        ,"식이섬유와 비타민 C가 면역력향상과 모질에 도움을 주고 구연산과 브로멜린이라는 호소가 소화를 용이하게 해준다. 섬유질과 당이 다량 함유되어 있어 당뇨가 있거나 비만견일 경우 주지 않는 것이 좋다.",
        "안토시아닌이라는 성분이 급성신부전증 등의 질환을 일으킬 수 있다."};

        editSearch = (EditText) findViewById(R.id.editSearch);
        listView = (ListView) findViewById(R.id.listView);

        search_adapter = new ArrayAdapter<String>(this, R.layout.row_listview,R.id.label,name);
        listView.setAdapter(search_adapter);
        list = new ArrayList<String>();



        arrayList = new ArrayList<String>();
        arrayList.addAll(list);

        adapter = new SearchAdapter(list, this);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

//        listView.setAdapter(adapter);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                        FruitList.this.search_adapter.getFilter().filter(s);
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
                    Intent intent = new Intent(FruitList.this, FoodDetail.class);
                    intent.putExtra("key1",name[position]);
                    intent.putExtra("key2",yn[position]);
                    intent.putExtra("key3",description[position]);
                    intent.putExtra("key4",imgs[position]);
                    startActivity(intent);}
                else if(yn[position]=="X") {
                    Intent intent = new Intent(FruitList.this, FoodDetail_X.class);
                    intent.putExtra("key1",name[position]);
                    intent.putExtra("key2",yn[position]);
                    intent.putExtra("key3",description[position]);
                    intent.putExtra("key4",imgs[position]);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(FruitList.this, FoodDetail_tri.class);
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
        Intent intent = new Intent(FruitList.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}



