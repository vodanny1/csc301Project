package comineashantrehan.linkedin.httpswww.ucourse;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.view.inputmethod.InputMethodManager;

public class ProfDetail extends AppCompatActivity implements View.OnClickListener{
    private SQLManager db;
    private EditText txt_name, txt_email, txt_rate;
    public String login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prof_detail);
        db = new SQLManager(getApplicationContext());

        Intent t = getIntent();
        login = t.getStringExtra("login");

        ImageButton back = (ImageButton) findViewById(R.id.btnBack);
        back.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent myIntent = new Intent(view.getContext(), ProfSearch.class);
                myIntent.putExtra("login", login);
                startActivity(myIntent);
            }
        });



        Button rate = (Button) findViewById(R.id.button7);
        rate.setOnClickListener(this);

        Button avg = (Button)findViewById(R.id.button6);
        avg.setOnClickListener(this);



        Bundle extras = getIntent().getExtras();
        if (extras != null){
            String name = extras.getString("name");
            String email = extras.getString("email");
            //login = extras.getString("login");
            //Log.d("NAMES", name);
            //Log.d("NAMES", email);
            EditText temp_name = (EditText)findViewById(R.id.profName);
            EditText temp_email = (EditText)findViewById(R.id.profEmail);
            temp_name.setText(name);
            temp_email.setText(email);
        }


    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button7: // rating button

                // add professor and rating to database

                txt_name = (EditText)findViewById(R.id.profName);
                txt_email = (EditText)findViewById(R.id.profEmail);
                txt_rate = (EditText)findViewById(R.id.profRating);
                String name = txt_name.getText().toString();
                String email = txt_email.getText().toString();
                String temp = txt_rate.getText().toString();
                if(name.isEmpty() || temp.isEmpty() || email.isEmpty()){
                    displayMessage("One or more input missing value(s).");
                    return;
                }

                if (temp.matches("\\d+(?:\\.\\d+)?")){
                    int rating = Integer.parseInt(temp);
                    if (rating > 5 || rating < 1){
                        displayMessage("Rating must be between 1-5.");
                        return;
                    } else {
                        boolean result = db.rate(name, email, rating, login);
                        //boolean result = db.rate(name, email, rating);
                        if (result == true){
                            displayMessage("Professor rating added.");
                            //txt_name.getText().clear();
                            //txt_email.getText().clear();
                            txt_rate.getText().clear();

                            // disable keyboard after button click
                            try {
                                InputMethodManager imm = (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
                                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                            } catch (Exception e) {
                                // TODO: handle exception
                            }
                        } else {
                            displayMessage("Error");
                        }

                        //finish();
                    }
                } else {
                    displayMessage("Invalid rating.");
                    return;
                }
                break;

            case R.id.button6: // average button

                // Returning the Average Rating

                txt_name = (EditText)findViewById(R.id.profName);
                txt_email = (EditText)findViewById(R.id.profEmail);
                String name2 = txt_name.getText().toString();
                String email2 = txt_email.getText().toString();
                TextView avgText = (TextView)findViewById(R.id.txtAvg);

                if(name2.isEmpty() || email2.isEmpty()){
                    displayMessage("One or more input missing value(s).");
                    return;
                }
                double average = db.getAvg(name2, email2);
                long avg = Math.round(average);
                //String f_average = Double.toString(average);
                double roundOff = Math.round(average * 100.0) / 100.0;
                String f_average = Double.toString(roundOff);
                //String f_average = Long.toString(avg);
                avgText.setText(f_average);
                break;


        }
    }

    private void displayMessage(String message) {
        Message.message(getApplicationContext(), message);
    }
}
