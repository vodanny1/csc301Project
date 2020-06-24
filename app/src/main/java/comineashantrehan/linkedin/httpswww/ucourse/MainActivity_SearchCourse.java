package comineashantrehan.linkedin.httpswww.ucourse;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity_SearchCourse extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchCourseAdapter adapter;

    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();
    private SQLManager db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_searchcourse);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_search);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        materialSearchBar = (MaterialSearchBar)findViewById(R.id.search_bar);

        db = new SQLManager(getApplicationContext());

        materialSearchBar.setHint("Search Course Code");
        materialSearchBar.setCardViewElevation(10);

        loadSuggestList();

        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<String> suggest = new ArrayList<>();
                for (String search:suggestList)
                {
                    if(search.toLowerCase().contains(materialSearchBar.getText().toLowerCase())){
                        suggest.add(search);
                    }

                }
                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if (!enabled){
                    recyclerView.setAdapter(adapter);
                }
            }


            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }



            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });


        adapter = new SearchCourseAdapter(this, db.getCourseItem());
    }


    private void startSearch(String text) {

        adapter = new SearchCourseAdapter(this, db.getCoursebyCourseCode(text));
        recyclerView.setAdapter(adapter);
    }

    private void loadSuggestList() {
        suggestList = db.getCourseCode();
        materialSearchBar.setLastSuggestions(suggestList);

    }


    //btnAdd = (Button) findViewById(R.id.btnAdd);
    //btnView = (Button) findViewById(R.id.btnView);












}