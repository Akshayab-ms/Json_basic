package com.example.json_task;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView myResult;
    private Button myButton;
    private RequestQueue myQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myResult = findViewById(R.id.textView);
        myButton = findViewById(R.id.button);
        myQueue = Volley.newRequestQueue(this);

    }

    public void getJSON(View v){
        getRequest();
    }


    private void getRequest(){
        String url = "https://raw.githubusercontent.com/Jaeger47/UNITY_JSON/main/student_data.json";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("students");
                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject student = jsonArray.getJSONObject(i);

                                String myName = student.getString("name");
                                int myAge = student.getInt("age");
                                String myEmail = student.getString("email");
                                Boolean myIsEnrolled = student.getBoolean("isEnrolled");


                                myResult.append(myName +", "+ String.valueOf(myAge)+ ", "+ myEmail +  ", "+ myIsEnrolled+ "\n\n");
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

        myQueue.add(request);
    }
}