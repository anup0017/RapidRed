package aaatkgdsu.register.com.redbloodcell;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReceiveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);
        getdonordetails();

    }

    void getdonordetails() {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.URL_DISPLAY_DONORS,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                       // Toast.makeText(ReceiveActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                        JSONparsingforprofiledata(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ReceiveActivity.this, error.toString(), Toast.LENGTH_LONG).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void JSONparsingforprofiledata(String result) {
        JSONArray mJsonArray = null;
        try {
            mJsonArray = new JSONArray(result);
            int length = mJsonArray.length();
            String[] blood_group = new String[length], name = new String[length], phone = new String[length];

            for (int i = 0; i <= mJsonArray.length() - 1; i++) {
                JSONObject mJsonObject = mJsonArray.getJSONObject(i);
                blood_group[i] = mJsonObject.getString("blood_group");
                name[i] = mJsonObject.getString("name");
                phone[i] = mJsonObject.getString("phone");
            }
            displayList(name, blood_group, phone);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    void displayList(final String name[], final String blood_group[], final String phone[]) {
        ListView lists = (ListView) findViewById(R.id.acceptedList);
        MyListAdapterAccepted adapter = new MyListAdapterAccepted(this, name, blood_group, phone);
        lists.setAdapter(adapter);
        lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(ReceiveActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(ReceiveActivity.this);
                }
                builder.setTitle("CONFIRM CALL ?")
                        .setMessage("Are you sure you want request blood? ")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ReceiveActivity.this, "Calling...", Toast.LENGTH_SHORT).show();
                                Intent callIntent = new Intent(Intent.ACTION_CALL);
                                callIntent.setData(Uri.parse("tel:"+phone[position]));
                                if (ActivityCompat.checkSelfPermission(ReceiveActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                    return;
                                }
                                startActivity(callIntent);
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        })
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .show();
            }
        });
    }

}
