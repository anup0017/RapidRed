package aaatkgdsu.register.com.redbloodcell;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.location.Address;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.regex.Pattern;

import static android.widget.Toast.LENGTH_SHORT;

public class RegisterActivity extends AppCompatActivity {
    int PLACE_PICKER_HOLDER = 1;

    TextInputLayout FullName, Phone, Email, Password, ConfirmPassword;
    EditText name, phone, email, pass, conPass, Address, Dob;
    Button Register;
    ProgressDialog progressDialog;
    Spinner bloodGroup;
    ArrayAdapter<CharSequence> adapter;
    RadioGroup radioGroup;
    RadioButton radioButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        FullName = (TextInputLayout) findViewById(R.id.textInputName);
        Phone = (TextInputLayout) findViewById(R.id.textInputPhone);
        Email = (TextInputLayout) findViewById(R.id.textInputEmail);
        Password = (TextInputLayout) findViewById(R.id.textInputPass);
        ConfirmPassword = (TextInputLayout) findViewById(R.id.textInputConfirmPass);

        name = (EditText) findViewById(R.id.regName);
        email = (EditText) findViewById(R.id.regEmail);
        pass = (EditText) findViewById(R.id.regPass);
        phone = (EditText) findViewById(R.id.regPhone);
        conPass = (EditText) findViewById(R.id.regRePass);
        Address = (EditText) findViewById(R.id.address);
        Dob = (EditText) findViewById(R.id.dob);
        bloodGroup = (Spinner) findViewById(R.id.bloodGroup);
        Register = (Button) findViewById(R.id.submit);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);

        progressDialog = new ProgressDialog(this);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });

        ////BloodGroup Start
        bloodGroup.setTag("Blood Group");
        adapter = ArrayAdapter.createFromResource(this, R.array.Blood_Group, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        bloodGroup.setAdapter(adapter);
        bloodGroup.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), parent.getItemAtPosition(position) + " Selected", LENGTH_SHORT).show();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        ////BloodGroup End

        ////Address Start
        Address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                Intent intent;
                try {
                    intent = builder.build(RegisterActivity.this);
                    startActivityForResult(intent, PLACE_PICKER_HOLDER);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

            }
        });
        ////Address End

        /////Age Start
        Dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), dateSetListener, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(new Date().getTime());
                datePickerDialog.show();
            }
        });

        ///////Age End


    }

    private void registerUser() {
        final String Name = name.getText().toString().trim();
        final String Phone = phone.getText().toString().trim();
        final String Email = email.getText().toString().trim();
        final String Password = pass.getText().toString().trim();
        final String ConPassword = conPass.getText().toString().trim();

        final String blood_group = bloodGroup.getSelectedItem().toString().trim();
        final String dob = Dob.getText().toString().trim();
        final String place = Address.getText().toString().trim();

        int radioId = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(radioId);
        final String RadioButton = radioButton.getText().toString().trim();

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        if (Phone.length() == 10) {
            if (Password.length() >= 8 && Password.equals(ConPassword)) {

                StringRequest stringRequest = new StringRequest(Request.Method.POST,
                        Constants.URL_REGISTER,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                progressDialog.dismiss();
                                //Toast.makeText(RegisterActivity.this, "" + response, LENGTH_SHORT).show();
                                if (response.contains("ok")) {
                                    Toast.makeText(getApplicationContext(), "Registration success!!", Toast.LENGTH_LONG).show();
                                    finish();
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Registration failed...", Toast.LENGTH_SHORT).show();
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
                                progressDialog.hide();
                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        })

                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("name", Name);
                        params.put("phone", Phone);
                        params.put("email", Email);
                        params.put("gender", RadioButton);
                        params.put("age", dob);
                        params.put("blood_group", blood_group);
                        params.put("address", place);
                        params.put("password", Password);

                        return params;
                    }
                };
                RequestQueue requestQueue = Volley.newRequestQueue(this);
                requestQueue.add(stringRequest);
            } else {
                Toast.makeText(RegisterActivity.this, "Password does not match \n Password should contain atleast 8 characters", LENGTH_SHORT).show();
                conPass.setText("");
                progressDialog.hide();
            }

        } else {
            phone.setError("Please enter a valid Phone number");
            progressDialog.hide();
            return;
        }

    }

    private boolean validateEmail() {
        String inputEmail = Email.getEditText().getText().toString().trim();

        if (inputEmail.isEmpty()) {
            Email.setError("Field cannot be empty");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(inputEmail).matches()) {
            Email.setError("Please enter a valid email address");
            return false;
        } else {
            Email.setError(null);
            return true;
        }
    }

    private boolean validateName() {
        String inputName = FullName.getEditText().getText().toString().trim();

        if (inputName.isEmpty()) {
            FullName.setError("Field can't be empty");
            return false;
        } else {
            FullName.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String inputPass = Password.getEditText().getText().toString().trim();

        if (inputPass.isEmpty()) {
            Password.setError("Field can't be empty");
            return false;
        } else {
            Password.setError(null);
            return true;
        }
    }

    private boolean validateConfirmPassword() {
        String inputConfirmPassword = ConfirmPassword.getEditText().getText().toString().trim();

        if (inputConfirmPassword.isEmpty()) {
            ConfirmPassword.setError("Field can't be empty");
            return false;
        } else {
            ConfirmPassword.setError(null);
            return true;
        }
    }

    private boolean validatePhone() {
        String inputPhone = Phone.getEditText().getText().toString().trim();

        if (inputPhone.isEmpty()) {
            Phone.setError("Field can't be empty");
            return false;
        } else {
            Phone.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {
        if (!validateEmail() || !validateName() || !validatePhone() || !validatePassword() || !validateConfirmPassword()) {
            return;
        }

        String input = "Name" + FullName.getEditText().getText().toString();
        input += "\n";
        input += "Phone" + Phone.getEditText().getText().toString();
        input += "\n";
        input += "Email" + Email.getEditText().getText().toString();
        input += "\n";
        input += "Password" + Password.getEditText().getText().toString();
        input += "\n";
        input += "Confirm Password" + ConfirmPassword.getEditText().getText().toString();
    }

    //////ADDRESS START
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_HOLDER) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(data, this);
                String address = String.format("%s", place.getAddress());
                Address.setText(address);
            }
        }
    }
    /////ADDRESS END

    //////Age Start
    private DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int day) {
            Calendar c = Calendar.getInstance();
            c.set(Calendar.YEAR, year);
            c.set(Calendar.MONTH, month);
            c.set(Calendar.DAY_OF_MONTH, day);
            //String format = new SimpleDateFormat("dd MMM YYYY").format(c.getTime());
            Dob.setText(Integer.toString(Calculate(c.getTimeInMillis())));
        }
    };

    int Calculate(long date) {
        Calendar dob = Calendar.getInstance();
        dob.setTimeInMillis(date);

        Calendar today = Calendar.getInstance();

        int a_age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_MONTH) < dob.get(Calendar.DAY_OF_MONTH)) {
            a_age--;
        }
        return a_age;
    }
    ////Age End

}
