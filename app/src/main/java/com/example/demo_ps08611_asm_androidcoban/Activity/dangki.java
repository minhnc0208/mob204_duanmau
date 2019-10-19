package com.example.demo_ps08611_asm_androidcoban.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.demo_ps08611_asm_androidcoban.R;
import com.example.demo_ps08611_asm_androidcoban.SQL_ASM.Database;
import com.google.android.material.textfield.TextInputEditText;

public class dangki extends AppCompatActivity {
    TextInputEditText email, password, checkpass;
    ImageView img;
    Button register ,cancer;
    TextView login;

    Database db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new Database(this);
        cancer = findViewById(R.id.btnCancel);
        email = findViewById(R.id.input_name);
        password = findViewById(R.id.email11);
        img=findViewById(R.id.img);
        checkpass = findViewById(R.id.password11);
        register = findViewById(R.id.btn_signup);
        login = findViewById(R.id.link_login);

        Animation myanim = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.fadeout);
        register.startAnimation(myanim);

        Animation myanim1 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bottom_to_top);
        login.startAnimation(myanim1);
        Animation myanim2 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bottom_to_top);
        cancer.startAnimation(myanim2);

        Animation myanim3 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bottom_to_top);
        email.startAnimation(myanim3);
        Animation myanim4 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bottom_to_top);
        password.startAnimation(myanim4);

        Animation myanim5 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bottom_to_top);
        checkpass.startAnimation(myanim5);

        Animation myanim6 = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.bottom_to_top);
        img.startAnimation(myanim6);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(dangki.this, Login.class);
                startActivity(i);
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = email.getText().toString();
                String s2 = password.getText().toString();
                String s3 = checkpass.getText().toString();

                if(s1.equals("") || s2.equals("") || s3.equals("")){
                    Toast.makeText(getApplicationContext(), "Do not leave the cells blank",Toast.LENGTH_SHORT).show();
                }else {
                    if(s2.equals(s3)){
                        Boolean check_mail = db.checkmail(s1);
                        if(check_mail == true){
                            Boolean insert = db.insert(s1,s2);
                            if(insert == true){
                                Toast.makeText(getApplicationContext(),"Create Account Success",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Email already exists",Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
        });

    }
}
