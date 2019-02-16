package aaatkgdsu.register.com.redbloodcell;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.os.Binder;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DonateActivity extends AppCompatActivity {

    Bundle extras;
    private Button donate;
    String name2, phone2, email2, age2, gender2, blood_group2, address2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        donate = (Button)findViewById(R.id.clickToDonate);

        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickToDonate();
            }
        });

        if (savedInstanceState == null)
        {
            extras = getIntent().getExtras();
            if (extras == null)
            {
                name2 = null;
                phone2 = null;
                email2 = null;
                age2 = null;
                gender2 = null;
                blood_group2 = null;
                address2 = null;
            }
            else
            {
                name2 = extras.getString("name2");
                phone2 = extras.getString("mobile2");
                email2 = extras.getString("email2");
                age2 = extras.getString("age2");
                gender2 = extras.getString("gender2");
                blood_group2 = extras.getString("bg2");
                address2 = extras.getString("address2");
            }
        }

    }

    @Override
    public void onBackPressed() {
        finish();
    }

    private void clickToDonate()
    {
        AlertDialog.Builder a_builder = new AlertDialog.Builder(DonateActivity.this);
        a_builder.setMessage("Are you sure you want to Donate Blood ?")
                .setCancelable(false)
                .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                                Constants.URL_DONATE,
                                new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        //Toast.makeText(RegisterActivity.this, "" + response, LENGTH_SHORT).show();
                                        if (response.contains("ok")) {
                                            Toast.makeText(getApplicationContext(), "Congratulations, You are now a Donor! Please visit the nearest blood bank to donate blood", Toast.LENGTH_LONG).show();
                                            finish();
                                        }
                                        else {
                                            Toast.makeText(getApplicationContext(), "You are already a donor...", Toast.LENGTH_SHORT).show();
                                            finish();
                                        }

                                        try {
                                            JSONObject jsonObject = new JSONObject(response);
                                            //Toast.makeText(getApplicationContext(), jsonObject.getString("message"), Toast.LENGTH_LONG).show();
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                },
                                new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                                    }
                                })

                        {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                Map<String, String> params = new HashMap<>();
                                params.put("phone", phone2);
                                return params;
                            }
                        };
                        RequestQueue requestQueue = Volley.newRequestQueue(DonateActivity.this);
                        requestQueue.add(stringRequest);
                    }
                })
                .setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }) ;
        AlertDialog alert = a_builder.create();
        alert.setTitle("Alert !!!");
        alert.show();

        //Toast.makeText(this, name2+phone2+email2+age2+gender2+blood_group2+address2, Toast.LENGTH_SHORT).show();
    }


}

