package com.example.myapp.login.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapp.R;
import com.example.myapp.dailyMeal.view.DailyMealActivity;
import com.example.myapp.network.FirebaseUsers;
import com.example.myapp.registration.view.RegistrationActivity;
import com.example.myapp.search.byIngredients.view.IngredientsActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    FirebaseAuth firebaseAuth;
    Button btn;
    Button gest;
    EditText email;
    EditText password;
    TextView register;
    TextView forgot;
    ProgressDialog progressDialog;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        firebaseAuth = FirebaseAuth.getInstance();

// initial data
        btn = findViewById(R.id.login_btn);
        gest = findViewById(R.id.start_btn);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.tv_register);
        forgot=findViewById(R.id.forgot);
        mAuth = FirebaseAuth.getInstance();

// Button Login

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userpassword = password.getText().toString();
                String useremail = email.getText().toString().trim();
                if (!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()) {
                    email.setError("Invalid Email");
                    email.setFocusable(true);
                } else {
                    loginUser(useremail, userpassword);
                }
            }
        });

// Button Login as a Gust
        gest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,DailyMealActivity.class);
                startActivity(intent);
            }
        });

// text Register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,RegistrationActivity.class);
                startActivity(intent);
            }
        });

//text forgot password
        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRecoverPasswordDialog();
            }
        });
        progressDialog=new ProgressDialog(this);
    }

//to revover the password forgotten

    private void showRecoverPasswordDialog() {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setTitle("Recover Password");
        LinearLayout linearLayout=new LinearLayout(this);
        final EditText emailEt=new EditText(this);
        emailEt.setHint("Email");
        emailEt.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        emailEt.setMinEms(16);
        linearLayout.addView(emailEt);
        linearLayout.setPadding(10,10,10,10);
        builder.setView(linearLayout);

        builder.setPositiveButton("Recover", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
               String mayada=emailEt.getText().toString().trim();
                beginRecovery(mayada);
            }
        });

        builder.setNegativeButton("Cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.create().show();
    }

// to final recover password
    private void beginRecovery(String useremail) {
        progressDialog.setMessage("Sending Email  ...");
        progressDialog.show();
        mAuth.sendPasswordResetEmail(useremail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                if (task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Email Sent", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(LoginActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                }

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

// to login to daily mael page
    private void loginUser(String useremail, String userpassword) {
        progressDialog.setMessage("Loging in ...");
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(useremail, userpassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            FirebaseUser user = mAuth.getCurrentUser();
                            FirebaseUsers.getFavFromFire(LoginActivity.this,user);
                            FirebaseUsers.getPlanFromFire(LoginActivity.this , user , "1");
                            FirebaseUsers.getPlanFromFire(LoginActivity.this , user , "2");
                            FirebaseUsers.getPlanFromFire(LoginActivity.this , user , "3");
                            FirebaseUsers.getPlanFromFire(LoginActivity.this , user , "4");
                            FirebaseUsers.getPlanFromFire(LoginActivity.this , user , "5");
                            FirebaseUsers.getPlanFromFire(LoginActivity.this , user , "6");
                            FirebaseUsers.getPlanFromFire(LoginActivity.this , user , "7");
                            startActivity(new Intent(LoginActivity.this, DailyMealActivity.class));
                            finish();
                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

// to check user email and password



// to start the check user
    @Override
    protected void onStart() {
        super.onStart();

    }

    //to back to registeration page

    public void register(View view) {
        startActivities(new Intent[]{new Intent(LoginActivity.this, RegistrationActivity.class)});
    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
}

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}