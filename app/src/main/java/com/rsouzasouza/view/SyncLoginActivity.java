//Front End APP Login Tcc Puc Minas
//Autor: Rodrigo Carlos de Souza


package com.rsouzasouza.view;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.rsouzasouza.synclogin.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

public class SyncLoginActivity extends AppCompatActivity {

    private EditText userMail, pSenha;
    private Button bLogin;

    //Variável para conectar APP com Heroku
    private static String URL_LOGIN = "https://logandroid.herokuapp.com/api/login";

    public SyncLoginActivity(String email, String password) {

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sync_login);

        userMail = findViewById(R.id.fieldEmail);
        pSenha = findViewById(R.id.fieldPassword);
        bLogin = findViewById(R.id.btnLogin);
        bLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = userMail.getText().toString().trim();
                String password= pSenha.getText().toString().trim();


                //Verifica se o usuário deixou algum campo vazio
                if (!email.isEmpty() || !password.isEmpty()) {
                    Login(email, password);

                } else {
                    userMail.setError("Por favor insira um email válido");
                    pSenha.setError("Por favor insira uma senha válida");
                }


            }
        });

    }

    private void Login(final String userMail, final String pSenha) {

        //Mantém o botão LOGIN visível
        bLogin.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");

                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            //Verifica se o login foi com sucesso ou não
                            if (success.equals("1")) {

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String nome = object.getString("nome").trim();
                                    String email = object.getString("email").trim();
                                    Toast.makeText(SyncLoginActivity.this, "Login com Sucesso. \nNome: " +
                                            ""+nome+"\nEmail :" +email, Toast.LENGTH_SHORT).show();




                                }
                            }

                           //Tratamento de exceção
                        } catch (JSONException e) {
                            e.printStackTrace();
                            bLogin.setVisibility(View.VISIBLE);
                            Toast.makeText(SyncLoginActivity.this, "Erro no login  " +e.toString(), Toast.LENGTH_SHORT).show();

                        }
                    }

                },

                new Response.ErrorListener() {

                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(SyncLoginActivity.this, "Erro no login" +error.toString(), Toast.LENGTH_SHORT).show();

                    }
                })


        {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("email", userMail);
                params.put("senha", pSenha);
                return params;

            }

        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
