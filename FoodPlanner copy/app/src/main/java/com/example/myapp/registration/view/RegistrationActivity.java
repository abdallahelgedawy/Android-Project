package com.example.myapp.registration.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.myapp.R;
import com.example.myapp.dailyMeal.view.DailyMealActivity;
import com.example.myapp.dailyMeal.view.DailyMealAdapter;
import com.example.myapp.login.view.LoginActivity;
import com.example.myapp.network.FirebaseUsers;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import java.util.Arrays;


public class RegistrationActivity extends AppCompatActivity {
    CallbackManager callbackManager;

    private static final int RC_SIGN_IN =100 ;

    GoogleSignInClient mGoogleSignInClient;

    Button button;
    EditText name;
    EditText email;
    EditText password;
    EditText confirmps;
    TextView login;
    ProgressDialog progressDialog;
    ImageView google;
    ImageView twitter;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    ImageView loginButton;




    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        //initiale
        button=findViewById(R.id.start_btn);
        name=findViewById(R.id.fullname);
        password=findViewById(R.id.password);
        email=findViewById(R.id.email);
        confirmps=findViewById(R.id.password2);
        login=findViewById(R.id.tv_signin);
        google=findViewById(R.id.google);
        loginButton=findViewById(R.id.facebook);
        createResult();


        FacebookSdk.sdkInitialize(getApplicationContext());
        FacebookSdk.addLoggingBehavior(LoggingBehavior.INCLUDE_ACCESS_TOKENS);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logInWithReadPermissions(RegistrationActivity.this, Arrays.asList("public_profile"));
             //  callbackManager = CallbackManager.Factory.create();
                LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        Log.d("TAG", "facebook:onSuccess:" + loginResult);
                        handleFacebookAccessToken(loginResult.getAccessToken());
                        startActivity(new Intent(RegistrationActivity.this, DailyMealActivity.class));
                        finish();
                    }
                    @Override
                    public void onCancel() {
                        Log.d("TAG", "facebook:onCancel");
                    }
                    @Override
                    public void onError(FacebookException exception) {
                        Log.d("TAG", "facebook:onError");
                    }
                });

            }

        });

        callbackManager = CallbackManager.Factory.create();



//signInstance
        mAuth = FirebaseAuth.getInstance();

//progressDialoge initiale
        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("Registering user");
//Button Registration
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username=name.getText().toString().trim();
                String userpassword=password.getText().toString().trim();
                String confirm=confirmps.getText().toString().trim();
                String useremail=email.getText().toString().trim();
                if(!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()){
                    email.setError("Invalid Email");
                    email.setFocusable(true);
                }else  if (userpassword.length()<6){
                    password.setError("Password length less than 6 characters");
                    password.setFocusable(true);
                }else {
                    registerUser(useremail,userpassword);
                }
            }
        });
        //Text Login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivities(new Intent[]{new Intent(RegistrationActivity.this, LoginActivity.class)});
            }
        });

//Button Google >>>>>>>>>>>>>>>>>>Google
        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signinIntent=mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signinIntent,RC_SIGN_IN);
            }
        });
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        if (firebaseUser != null) {
            startActivity(new Intent(this, DailyMealActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }

    public void createResult(){
        GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id)).requestEmail().build();
        mGoogleSignInClient= GoogleSignIn.getClient(this,gso);

    }
    //Activity Google>>>>>>>>>>>>>>>>>>Google
    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            if (signInAccountTask.isSuccessful()) {
                try {
                    GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                    if (googleSignInAccount != null) {
                        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                        mAuth.signInWithCredential(authCredential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    FirebaseUsers.getFavFromFire(RegistrationActivity.this,mAuth.getCurrentUser());
                                    FirebaseUsers.getPlanFromFire(RegistrationActivity.this , mAuth.getCurrentUser() , "1");
                                    FirebaseUsers.getPlanFromFire(RegistrationActivity.this , mAuth.getCurrentUser() , "2");
                                    FirebaseUsers.getPlanFromFire(RegistrationActivity.this , mAuth.getCurrentUser() , "3");
                                    FirebaseUsers.getPlanFromFire(RegistrationActivity.this , mAuth.getCurrentUser() , "4");
                                    FirebaseUsers.getPlanFromFire(RegistrationActivity.this , mAuth.getCurrentUser() , "5");
                                    FirebaseUsers.getPlanFromFire(RegistrationActivity.this , mAuth.getCurrentUser() , "6");
                                    FirebaseUsers.getPlanFromFire(RegistrationActivity.this , mAuth.getCurrentUser() , "7");
                                    startActivity(new Intent(RegistrationActivity.this, DailyMealActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                                } else {
                                    Toast.makeText(RegistrationActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

//Activity Facebook>>>>>>>>>>>>>>>>>>>>FaceBook
    private void handleFacebookAccessToken(AccessToken token) {
        Log.d("TAG", "handleFacebookAccessToken:" + token);
        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                        } else {
                            Toast.makeText(RegistrationActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);

                        }
                    }
                });
    }

    private void updateUI(FirebaseUser user) {
        if(user !=null){

            user.getEmail();
           // mAuth = FirebaseAuth.getInstance().
        }

    }

    //To register
    private void registerUser(String useremail, String userpassword) {
        progressDialog.show();
        mAuth.createUserWithEmailAndPassword(useremail, userpassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete( Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(RegistrationActivity.this, "Registered...\n"+user.getEmail(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
                            finish();

                        } else {
                            progressDialog.dismiss();
                            Toast.makeText(RegistrationActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            Log.i("Error",task.getException().toString());
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressDialog.dismiss();
                        Toast.makeText(RegistrationActivity.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
// To login

    public void actiivitymain(View view) {
       startActivity(new Intent(RegistrationActivity.this,LoginActivity.class));
        finish();
    }
    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        updateUI(user);

    }


}




