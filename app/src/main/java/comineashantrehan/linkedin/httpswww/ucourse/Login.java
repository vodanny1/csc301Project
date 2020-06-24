package comineashantrehan.linkedin.httpswww.ucourse;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Login extends AppCompatActivity implements LoginHelper.LoginCallback {
//    private SQLManager db;
    private LoginHelper loginHelper;
    private EditText txt_id, txt_pass;
    private String currentUser;
    public static final String LOGIN_ID = "comineashantrehan.linkedin.httpswww.ucourse.LOGIN_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginHelper = new LoginHelper(this);
//        db = new SQLManager(getApplicationContext());
        txt_id = (EditText) findViewById(R.id.id);
        txt_pass = (EditText) findViewById(R.id.password);
    }

    public void login(View view) {
        String id = txt_id.getText().toString();
        String pass = txt_pass.getText().toString();
        if (id.isEmpty() || pass.isEmpty()) {
            displayMessage("Invalid username/ password");
            return;
        }
        this.currentUser = id;
        this.loginHelper.login(id, pass);
    }

    public void register(View view) {
        Intent intent = new Intent(this, Signup.class);
        intent.putExtra(LOGIN_ID, txt_id.getText().toString());
        startActivityForResult(intent, 1);
    }

    private void displayMessage(String message) {
        Message.message(getApplicationContext(), message);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                String id = data.getStringExtra(Signup.LOGIN_ID);
                txt_id.setText(id);
                txt_pass.setText("");
            }
        }
    }

    @Override
    public void onResult(boolean result) {
        if (result) {
            this.txt_pass.setText("");
            Intent intent = new Intent(this, Menu.class);
            intent.putExtra(LOGIN_ID, this.currentUser);
            startActivity(intent);
            displayMessage("Logged in as " + this.currentUser);
        }
        else {
            displayMessage("Login failed");
        }
    }
}
