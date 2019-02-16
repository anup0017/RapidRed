package aaatkgdsu.register.com.redbloodcell;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.internal.InternalTokenProvider;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;

import static android.widget.Toast.LENGTH_SHORT;

public class UserActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    RequestQueue rq;
    Bundle extras;
    String newString;
    TextView Naam,Emaill, mTextViewResults;
    RequestQueue mQueue;
    int num = 0;
    String Name2, Email2, Phone2, Gender2, Blood_Group2, Address2, Age2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        mTextViewResults = (TextView)findViewById(R.id.mTextView);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mQueue = Volley.newRequestQueue(this);


        if (savedInstanceState == null)
        {
            extras = getIntent().getExtras();
            if (extras == null)
            {
                newString = null;
            }
            else
            {
                newString = extras.getString("mobile");
            }
        }

        //Toast.makeText(this, newString, Toast.LENGTH_SHORT).show();
        Naam = (TextView)findViewById(R.id.naam);
        Emaill = (TextView)findViewById(R.id.emaill);

        sendJsonData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        rq = Volley.newRequestQueue(this);
        sendJsonData();


    }
    boolean twice;
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(twice == true){
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
            System.exit(0);
            //super.onBackPressed();
        }
        if (!drawer.isDrawerOpen(GravityCompat.START)){
            Toast.makeText(this, "Please press back again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    twice = false;
                }
            }, 3000);
            twice = true;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.user, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.dotsLogout) {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(UserActivity.this);
            a_builder.setMessage("Are you sure you want to logout ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(UserActivity.this,"Successfully Logged Out!",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(UserActivity.this,LoginActivity.class));
                            finish();
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
            //return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.donate) {
            // Handle the donate action
            Intent i = new Intent(getApplicationContext(), DonateActivity.class);
            i.putExtra("name2", Name2);
            i.putExtra("mobile2", Phone2);
            i.putExtra("email2", Email2);
            i.putExtra("gender2", Gender2);
            i.putExtra("bg2", Blood_Group2);
            i.putExtra("age2", Age2);
            i.putExtra("address2", Address2);
            startActivity(i);

        } else if (id == R.id.receive) {
            Intent i = new Intent(getApplicationContext(), ReceiveActivity.class);
            startActivity(i);
        } else if (id == R.id.edit) {

        } else if (id == R.id.settings) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_logout) {
            AlertDialog.Builder a_builder = new AlertDialog.Builder(UserActivity.this);
            a_builder.setMessage("Are you sure you want to logout ?")
                    .setCancelable(false)
                    .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(UserActivity.this,"Successfully Logged Out!",Toast.LENGTH_LONG).show();

                            startActivity(new Intent(UserActivity.this,LoginActivity.class));
                            finish();
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
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void sendJsonData(){

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, Constants.URL_DISPLAY, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                                JSONArray jsonArray = response.getJSONArray("details");

                                for (int i=0;i<jsonArray.length(); i++) {
                                    JSONObject details = jsonArray.getJSONObject(i);

                                    if (details.getString("phone").equals(newString))
                                    {
                                        String Name = details.getString("name");
                                        Long Phone = details.getLong("phone");
                                        String Email = details.getString("email");
                                        String Gender = details.getString("gender");
                                        String BloodGroup = details.getString("blood_group");
                                        int Age = details.getInt("age");
                                        String Address = details.getString("address");

                                        num = Age;

                                        Name2 = Name;
                                        Phone2 = String.valueOf(Phone);
                                        Email2 = Email;
                                        Gender2 = Gender;
                                        Blood_Group2 = BloodGroup;
                                        Age2 = String.valueOf(Age);
                                        Address2  = Address;

                                        mTextViewResults.setText(
                                                "Welcome "+Name2 + "\n"
                                                        +"Mobile No: "+ Phone2
                                                        + "\n" + "Email ID: " + Email2 + "\n"
                                                + "You are a " + Gender2 + "\n"
                                                + "Blood Group: " + Blood_Group2 + "\n"
                                                        + "You are " + Age2 + " years old." + "\n"
                                                        + "Address: " + Address2 + "\n\n"
                                        );


                                    }

                                }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mQueue.add(request);



    }
}
