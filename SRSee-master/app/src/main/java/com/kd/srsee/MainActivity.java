package com.kd.srsee;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        setContentView(R.layout.activity_main);
        Button login=(Button)findViewById(R.id.login);
        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId())
        {

            case R.id.login:
                String email=((EditText)findViewById(R.id.email)).getText().toString().trim();
                String password=((EditText)findViewById(R.id.pass)).getText().toString().trim();
                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success
                                    Toast.makeText(getApplicationContext(), "Login success",Toast.LENGTH_LONG).show();
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(getApplicationContext(), "Authentication failed.",Toast.LENGTH_LONG).show();
                                }
                            }
                        });
                break;
            case R.id.reset:
                Intent intent=new Intent(this,ResetPass.class);
                startActivity(intent);

            break;
            default:
                Toast.makeText(getApplicationContext(),"Feature not supported",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
