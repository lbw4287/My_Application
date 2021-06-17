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

public class VegList extends AppCompatActivity {

    private List<String> list;
    private ListView listView;
    private EditText editSearch;
    private ArrayList<String> arrayList;
    private SearchAdapter adapter;
    private ArrayAdapter<String> search_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veg_list);

        if(Build.VERSION.SDK_INT >= 21){
            getWindow().setStatusBarColor(Color.parseColor("#507900"));
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("채소류");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0x9090EE90));
        actionBar.setDisplayHomeAsUpEnabled(true);

        String name[]= new String[]{"가지","감자","고구마","고추","당근","마늘","무","배추","브로콜리",
                "상추","시금치","아스파라거스","양배추","양파","연근",
                "오이","토마토","파","파프리카","호박"};
        int[] imgs={R.drawable.eggplant,R.drawable.potato,R.drawable.sweetpotato,R.drawable.chilipepper,R.drawable.carrot,R.drawable.garlic,
        R.drawable.radish,R.drawable.cabbage,R.drawable.brocoli,R.drawable.lettuce,R.drawable.spinach,R.drawable.asparagus,R.drawable.cabbage2,
        R.drawable.onion,R.drawable.lotusroot,R.drawable.cucumber,R.drawable.tomato,R.drawable.scallion,R.drawable.paprika,R.drawable.pumpkin};

        String yn[]=new String[]{"O","O","O","X","O","X","O","O","O","O","O","O","O","X","O","O","O","X","O","O"};
        String description[]= new String[]{"콜레스테롤 수치를 낮춰주고 칼륨이 이뇨작용을 도와 체내 나트륨과 독소를 배출한다. 또, 안토시아닌 성분이 항산화 작용을 하여 노화를 억제한다.\n" +
                "단, 생가지에는 솔라렌이라는 독성 성분이 들어있어 반드시 익혀서 급여한다.","철분이 혈관 기능 유지에 도움을 주고 비타민 C가 풍부하여 노화예방에 효과적이다. 또, 식이섬유가 풍부하여 배변활동을 활발하게 한다. 익힌 후 으깨서 주는 것이 좋고, 껍질을 제거하고 설탕이나 소금 같은 첨가물은 첨가하지 않고 급여한다."
        ,"베타카로틴이 있어 면역력을 높여준다. 마그네슘이 풍부하여 뼈와 근육의 원활한 작용을 돕는다. 또, 섬유질이 풍부하여 변비, 소화에 도움을 준다. 줄기와 껍질은 제거하여 급여하고, 당 수치가 높아 당뇨나 신장이 약할 경우 급여하지 않는 것이 좋다.",
        "캡사이신으로 인해 설사나 탈수가 일어날 수 있고 식도나 위 등의 내장기관을 손상시킬 수 있다.","" +
                "베타카로틴 성분이 풍부하여 피부, 관절, 치아 등을 건강하게 하며, 루테인과 리코펜 성분이 눈 건강에 도움을 준다. 단, 식분증이 있다면 주지 않는 것이 좋다.",
        "해독 성분이 있어 항암 작용을 하고 강한 냄새를 가지고 있어 체내 기생충을 제거하는데에 도움이 된다. \n" +
                "그러나 티오황산염이라는 성분이 독성 반응을 일으켜 설사, 황달, 빈혈 등의 증세가 나타날 수 있다.",
        "아밀라아제와 디아스타아제가 소화를 원활하게 하고 글루코시놀레이트라는 성분이 해독작용을 하여 항암효과를 향상시킨다. 단, 위염이 있을 경우 기력을 약하게 할 수 있기 때문에 급여하지 않는 것이 좋다.",
        "비타민 C, 칼슘, 칼륨, 인이 들어가 있어 뼈건강에 좋고 식이섬유가 풍부해 변비에 도움을 준다. \n" +
                "단, 이소티오시안염을 함유하고 있어 다량 급여시 갑상선 기능 저하증을 일으킬 수 있다.",
        "루테인, 제아잔틴 성분이 눈의 피로를 줄여주고 백내장과 같은 질병을 예방하고, 설포라반 성분이 분비되어 면역력을 강화시키고 노화와 관련된 질병을 예방한다. 깨끗이 씻어서 1~2분 정도 익혀서 급여한다.",
        "수분이 많이 포함되어 있어 갈증 해소에 좋고 섬유질이 풍부하여 소화에 도움을 준다. 농약이 묻어있을 수 있기 때문에 깨끗이 씻어서 급여하는 것이 좋다.",
        "비타민 A•B•C•K가 함유되어 있어 뼈를 튼튼하게 한다. 엽산과 철분이 있어 빈혈에 효과가 있다.\n" +
                "단, 시금치의 옥산살이라는 성분이 칼륨 흡수를 막기 때문에 끓는 물로 데쳐서 급여하는 것이 좋다.",
        "아스파라긴산이라는 성분이 단백질합성과 피로회복에 도움을 준다. 글루타티온 성분이 있어 암과 관절염 예방에 효과가 있다. 딱딱하고 질기기 때문에 찌거나 삶아서 주는 것이 좋다.",
                "비타민 U•K가 위점막을 보호해주고 위궤양을 예방한다. 식이섬유가 장운동을 활성화시켜 변비에 효과가 있다.\n" +
                        "단, 심지부분과 겉부분은 제거하고 속잎만 급여한다.","N-propyl disulfide라는 독성 물질이 용혈성 빈혈을 일으킨다. 일정량 이상 섭취했을 경우 구토, 설사, 혼수상태 등의 중독 증상이 나타난다.",
        "콜레스테롤이 없기 때문에 다이어트용으로 적합하다. 비타민 B, C, E가 함유되어 있어 면역력을 향상시켜준다.\n" +
                "단, 장에 무리를 줄 수 있으니 삶거나 익혀서 급여한다.","비타민 C, 베타카로틴, 망간 등의 항산화제와 플라보노이드, 트리테르펜, 리그난 같은 항염증을 가진 성분을 함유하고 있다. 또 큐커비타신이라는 성분이 암을 예방하는데에 도움을 준다.\n" +
                "단, 껍질을 벗기고 소량만 급여한다.","라이코펜 성분이 항산화 작용을 하여 암예방에 효과가 있다. 베타카로틴 성분이 시력 보호에 도움을 주고 여러 비타민이 피로회복과 에너지원을 공급한다.\n" +
                "단, 잎과 줄기에 독성성분이 있기 때문에 제거하고 급여한다.","allyl propyl disulfide 라는 성분이 중독 현상을 일으켜 빈혈, 구토, 간손상, 혈뇨 등의 증상이 나타날 수 있다.",
                "빨간색- 면역력 강화, 항암작용 효과가 있고 칼륨과 인이 풍부하다.\n\n" +
                        "주황색- 아토피 개선, 눈건강, 피부, 모질 개선에 도움을 준다.\n\n" +
                        "노란색- 스트레스를 해소해주고 혈액을 맑게 해주어 고혈압을 예방한다. 또, 파라진이라는 성분이 혈액응고를 예방한다.\n\n" +
                        "초록색- 변비, 빈혈을 예방한다.","섬유질이 풍부하여 위장 내 움직임을 원활하게 한다. 이 외에도 비타민 C, 베타카로틴, 칼륨, 철분 등의 영양소를 함유하고 있어 항산화 작용에 도움을 준다. \n" +
                "단, 소화가 힘든 씨앗과 꼭지 부분은 제거하고 익혀서 급여한다."};

        editSearch = (EditText) findViewById(R.id.editSearch);
        listView = (ListView) findViewById(R.id.listView);

        search_adapter = new ArrayAdapter<String>(this, R.layout.row_listview,R.id.label,name);
        listView.setAdapter(search_adapter);
        list = new ArrayList<String>();


        arrayList = new ArrayList<String>();
        arrayList.addAll(list);

        adapter = new SearchAdapter(list, this);

//        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);

//        listView.setAdapter(adapter);

        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                VegList.this.search_adapter.getFilter().filter(s);
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
                    Intent intent = new Intent(VegList.this, FoodDetail.class);
                    intent.putExtra("key1",name[position]);
                    intent.putExtra("key2",yn[position]);
                    intent.putExtra("key3",description[position]);
                    intent.putExtra("key4",imgs[position]);
                    startActivity(intent);}
                else if(yn[position]=="X") {
                    Intent intent = new Intent(VegList.this, FoodDetail_X.class);
                    intent.putExtra("key1",name[position]);
                    intent.putExtra("key2",yn[position]);
                    intent.putExtra("key3",description[position]);
                    intent.putExtra("key4",imgs[position]);
                    startActivity(intent);
                }
                else{
                    Intent intent = new Intent(VegList.this, FoodDetail_tri.class);
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
        Intent intent = new Intent(VegList.this,MainActivity.class);
        startActivity(intent);

    }

}