package aaatkgdsu.register.com.redbloodcell;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    TextView Reg;
    EditText PhoneField,Password;
    Button Login;
    AlertDialog.Builder builder;
    String phone, password;
    private ProgressDialog progressDialog;
    boolean twice;

    @Override
    public void onBackPressed() {
        if(twice == true){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
            //super.onBackPressed();
        }
        Toast.makeText(this, "Please press back again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                twice = false;
            }
            }, 3000);
            twice = true;
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        PhoneField = (EditText)findViewById(R.id.mobile);
        Password = (EditText)findViewById(R.id.pass);
        Reg = (TextView)findViewById(R.id.register);
        Login = (Button)findViewById(R.id.login);


        builder = new AlertDialog.Builder(LoginActivity.this);
        progressDialog  = new ProgressDialog(this);

        Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });
    }

    private void loginUser(){
        final String phone = PhoneField.getText().toString().trim();
        final String password = Password.getText().toString().trim();

        progressDialog.setMessage("Logging in");
        progressDialog.show();
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        String response = null;

        final String finalResponse = response;

        final StringRequest stringRequest = new StringRequest(Request.Method.POST,
                Constants.URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.hide();

                        if(response.contains("Welcome")) {
                            Toast.makeText(LoginActivity.this, "Login Success!!!", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(LoginActivity.this, UserActivity.class);
                            i.putExtra("mobile", phone);
                            startActivity(i);
                            finish();
                        }
                        else {
                            Toast.makeText(LoginActivity.this, "Login Failed...", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.hide();
                        Log.d("ErrorResponse", finalResponse);
                    }
                }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("phone", phone);
                params.put("password", password);

                return params;
            }
        };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(stringRequest);
        //RequestQueue requestQueue = Volley.newRequestQueue(this);
        //requestQueue.add(stringRequest);
    }
}
