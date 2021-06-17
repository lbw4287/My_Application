package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Notification;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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

public class FishList extends AppCompatActivity {

    private List<String> list;
    private ListView listView;
    private EditText editSearch;
    private ArrayList<String> arrayList;
    private SearchAdapter adapter;
    private ArrayAdapter<String> search_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fish_list);

        if(Build.VERSION.SDK_INT >= 21){
            getWindow().setStatusBarColor(Color.parseColor("#5F86A5"));
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("어패류");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xADADD8E6));
        actionBar.setDisplayHomeAsUpEnabled(true);


        String name[]= new String[]{"고등어","굴","꽁치","멸치","삼치","연어","오징어","조개","참치"};
        int imgs[]={R.drawable.mackerel, R.drawable.oyster,R.drawable.pacificsaury,R.drawable.anchovy,R.drawable.spanishmackerel,R.drawable.salmon,R.drawable.squid,R.drawable.clam,R.drawable.tuna};
        String yn[]= new String[]{"△","X","△","O","△","O","X","X","△"};
        String description[]= new String[]{"오메가-3 지방산이 풍부하여 피모를 개선하고 털빠짐을 완화해준다. 셀레늄 성분이 활성산소를 제거하여 질병을 예방한다. 단, 지속적으로 또는 과다하게 섭취할 경우 수은 중독 위험이 있어 유의해야 한다. 또한 날것으로 급여할 경우 기생충 감염의 위험이 있으므로 조림이나 스튜 형태로 급여하는 것이 좋다.",
        "날 것으로 먹을 경우 식중독이 생길 수 있고 티아민 성분을 분해하여 티아민 결핍이 일어나면 경련, 발작 등의 증세를 나타낼 수 있다.",
        "칼슘과 콜라겐, 비타민이 풍부하여 혈액순환과 관절건강에 도움이 된다. 그러나 지방 함량이 높아 장에 무리를 줄 수 있다. 조미료가 들어가지 않은 것을 급여하고 뼈를 제거하여 익혀서 급여하는 것을 권장한다.",
        "오메가-3 지방산인 EPA• DHA가 풍부하여 두뇌발달, 관절염, 암 면역에 도움이 된다. 끓는 물에 데치거나 익혀서 급여하는 것이 좋다.",
        "아미노산의 일종인 타우린이 일정체온을 유지할 수 있게 하고 칼슘이 풍부해 뼈 강화에 도움을 준다. 그러나 과잉 섭취할 경우 황색지방증이 생길 수 있어 한번에 많이, 자주 급여하지 않도록 해야한다.",
        "탄수화물보다 단백질이 더 풍부하여 부담이 적고, 탄수비타민을 다량 함유하고 있어 사료나 간식으로 많이 사용된다. 연어회는 중독 증상을 일으킬 수 있기 때문에 푹 익혀서 가시를 제거하고 급여해야 한다.",
        "미네랄과 단백질, 비타민이 풍부하지만 매우 질겨 삼킬 경우 소화기관에 무리를 줄 수 있고 심하면 소화가 되지 않고 위에서 부패되어 조직을 괴사시킬 수 있다. 또 다른 음식에 비해 염분이 많아 독이 될 수 있다.",
        "염분이 많아 복통이나 구토를 유발할 수 있다. 독성이 있는 것을 먹게 될 경우 식중독이나 장염, 심할경우 마비성패류중독에 감염될 수 있다.",
        "육류 알레르기가 있을 경우 대체 가능한 고단백질 식품이며 오메가-3와 비타민 B군이 풍부하다. 그러나 사람이 먹는 참치캔은 염분과 기름이 많아 좋지 않으므로 기름을 제거하고 물에 끓여 염분을 제거해야한다. 강아지 전용 참치를 급여하는 것이 가장 좋다."};

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
                FishList.this.search_adapter.getFilter().filter(s);
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
                    Intent intent = new Intent(FishList.this, FoodDetail.class);
                    intent.putExtra("key1",name[position]);
                    intent.putExtra("key2",yn[position]);
                    intent.putExtra("key3",description[position]);
                    intent.putExtra("key4",imgs[position]);
                    startActivity(intent);}
                else if(yn[position]=="X") {
                    Intent intent = new Intent(FishList.this, FoodDetail_X.class);
                    intent.putExtra("key1",name[position]);
                    intent.putExtra("key2",yn[position]);
                    intent.putExtra("key3",description[position]);
                    intent.putExtra("key4",imgs[position]);
                    startActivity(intent);
                }
                else{
                Intent intent = new Intent(FishList.this, FoodDetail_tri.class);
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
        Intent intent = new Intent(FishList.this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}