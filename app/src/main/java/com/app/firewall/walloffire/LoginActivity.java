package com.app.firewall.walloffire;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.firewall.walloffire.listeners.Listener;
import com.app.firewall.walloffire.services.SignInService;
import com.app.firewall.walloffire.utils.Utils;

public class LoginActivity extends Activity implements OnClickListener {

    EditText edtUsername, edtPwd;
    Button btnLogin;
    Listener listener;
    Utils utils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        utils = new com.app.firewall.walloffire.utils.Utils(this);

        edtUsername = (EditText) findViewById(R.id.editText_username);
        edtPwd = (EditText) findViewById(R.id.editText_password);
        btnLogin = (Button) findViewById(R.id.button_login);
        btnLogin.setOnClickListener(this);
        Log.d("BUGS", "stage 1" + edtUsername + " " + edtPwd);
        // The interface which listens to the Login service
        listener = new Listener() {

            @Override
            public void onSuccessful(String message) {
                Utils.showToast(message, Toast.LENGTH_LONG);
                utils.hidePB();

                //Write the Login here to navigate to the Next page after Login
                Intent MainActivity = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(MainActivity);
                finish();
            }

            @Override
            public void onError(String message) {
                //Utils.showToast(message, Toast.LENGTH_LONG);
                utils.hidePB();
                //Write the Logic here to show the error message to the user
            }
        };

    }

    @Override
    public void onClick(View v) {
        utils.showPB("Signing in...");
        SignInService signInService = new SignInService(this, listener);
        signInService.setParams(edtUsername.getText().toString().trim(), edtPwd.getText().toString().trim());
        signInService.doSignIn();
    }

}