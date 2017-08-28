package com.example.glen.githubapi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.username) EditText mUsername;

    @BindView(R.id.login) Button mLogin;

    private Unbinder mUnbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    protected void onStart() {
        super.onStart();

        mUnbinder = ButterKnife.bind(this);

        mLogin.setOnClickListener(v -> {

            String username = mUsername.getText().toString().trim();

            if (username.length() > 0) {
                Intent intent = new Intent(this, UserActivity.class);
                intent.putExtra(UserActivity.USER_NAME_KEY, mUsername.getText().toString());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (mUnbinder != null) {
            mUnbinder.unbind();
            mUnbinder = null;
        }
    }
}
