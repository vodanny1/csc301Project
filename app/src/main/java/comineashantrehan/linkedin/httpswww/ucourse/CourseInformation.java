package comineashantrehan.linkedin.httpswww.ucourse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;


public class CourseInformation extends AppCompatActivity implements View.OnClickListener {






    private EditText etFirstName,etLastName,etFavFood;
    //Button btnAdd,btnView;
    private SQLManager db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.courseinformation);


        db = new SQLManager(getApplicationContext());


        ImageButton back = (ImageButton) findViewById(R.id.imageButton);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(view.getContext(), Menu.class);
                startActivity(myIntent);
            }
        });

        Button btnAdd = (Button) findViewById(R.id.btnAdd);
        btnAdd.setOnClickListener(this);

        Button btnView = (Button) findViewById(R.id.btnView);
        btnView.setOnClickListener(this);

        Button btnSearch = (Button) findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(this);
    }



    //btnView.setOnClickListener(new View.OnClickListener() {
    //   @Override
    //    public void onClick(View v) {
    //       Intent intent = new Intent(CourseInformation.this,View_CourseInformationList.class);
    //        startActivity(intent);
    //    }
    // });

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAdd:
                etFirstName = (EditText)findViewById(R.id.etFirstName);
                etFavFood = (EditText)findViewById(R.id.etFavFood);
                etLastName = (EditText)findViewById(R.id.etLastName);
                String fName = etFirstName.getText().toString();
                String lName = etLastName.getText().toString();
                String fFood = etFavFood.getText().toString();
                System.out.println(fName + lName + fFood);
                if(fName.isEmpty() || lName.isEmpty() || fFood.isEmpty()){
                    displayMessage("One or more input missing value(s).");
                    return;
                }
                else {
                    // Log.d("run", "execution is running now");
                    boolean result = db.addData(fName, lName, fFood);
                    if (result == true) {
                        displayMessage("Course info added!");
                        etFirstName.getText().clear();
                        etLastName.getText().clear();
                        etFavFood.getText().clear();
                    } else {
                        displayMessage("Error");
                    }
                }


                break;

            case R.id.btnView:
                Intent intent = new Intent(CourseInformation.this, View_CourseInformationList.class);
                //Intent intent = new Intent(CourseInformation.this, SearchSQLiteActivity.class);

                startActivity(intent);
                break;

            case R.id.btnSearch:

                //Intent intent = new Intent(CourseInformation.this, View_CourseInformationList.class);
                Intent intent1 = new Intent(CourseInformation.this, MainActivity_SearchCourse.class);

                startActivity(intent1);
                break;






        }







    }




    private void displayMessage(String message) {
        Message.message(getApplicationContext(), message);
    }



}




