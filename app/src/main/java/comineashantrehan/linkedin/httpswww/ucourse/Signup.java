package comineashantrehan.linkedin.httpswww.ucourse;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class Signup extends AppCompatActivity implements RegisterHelper.RegisterCallback {
//    private SQLManager db;
    private RegisterHelper registerHelper;
    private EditText txt_id, txt_pass;
    private String currentUser;
    public static final String LOGIN_ID = "comineashantrehan.linkedin.httpswww.ucourse.LOGIN_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
//        db = new SQLManager(getApplicationContext());
        registerHelper = new RegisterHelper(this);
        txt_id = (EditText) findViewById(R.id.id);
        txt_pass = (EditText) findViewById(R.id.password);
        Intent intent = getIntent();
        String message = intent.getStringExtra(Login.LOGIN_ID);
        txt_id.setText(message);
    }

    public void register(View view) {
        String id = txt_id.getText().toString();
        String pass = txt_pass.getText().toString();
        if (id.isEmpty() || pass.isEmpty()) {
            displayMessage("Not enough information to register");
            return;
        }
        this.currentUser = id;
        registerHelper.register(id, pass);
    }

    private void displayMessage(String message) {
        Message.message(getApplicationContext(), message);
    }

    @Override
    public void onResult(RegisterHelper.REGISTER result) {
         if (result == RegisterHelper.REGISTER.SUCCESS) {
            displayMessage("Account for " + currentUser + " has been created");
            Intent returnIntent = new Intent();
            returnIntent.putExtra(LOGIN_ID, currentUser);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }
        else if (result == RegisterHelper.REGISTER.EXIST) {
            displayMessage("This user already exists");
        }
        else {
             displayMessage("Failed to sign up due to server issue");
         }
    }
}
