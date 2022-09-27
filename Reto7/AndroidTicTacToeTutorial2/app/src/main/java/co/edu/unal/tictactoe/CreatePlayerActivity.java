package co.edu.unal.tictactoe;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class CreatePlayerActivity extends Activity {
    private RequestQueue queue;
    private int playerId;
    private String playerName;
    private EditText mIdPlayerEdit;
    private EditText mPlayerNameEdit;
    private Button mCreatePlayer;
    private RecyclerView mAvailableGames;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        queue = Volley.newRequestQueue(this);
        setContentView(R.layout.create_player_main);
        mIdPlayerEdit = (EditText) findViewById(R.id.user_id_edit);
        mPlayerNameEdit = (EditText) findViewById(R.id.username_edit);
        mCreatePlayer = (Button) findViewById(R.id.create_player_button);
        mAvailableGames = (RecyclerView) findViewById(R.id.available_games);
        mCreatePlayer.setText("Crear Jugador");
        mCreatePlayer.setEnabled(true);
    }

    private void createPlayer(){
        playerId = Integer.parseInt(String.valueOf(mIdPlayerEdit.getText()));
        playerName = String.valueOf(mPlayerNameEdit.getText());
        mCreatePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String url = "https://us-central1-fb-triqui-api.cloudfunctions.net/app/api/players";
                try{
                    JSONObject jsonBody = new JSONObject();
                    jsonBody.put("id", playerId);
                    jsonBody.put("name", playerName);
                    final String mRequestBody = jsonBody.toString();
                    StringRequest postRequest = new StringRequest(Request.Method.POST, url,
                            new Response.Listener<String>()
                            {
                                @Override
                                public void onResponse(String response) {
                                    // response
                                    Log.d("Response", response);
                                }
                            },
                            new Response.ErrorListener()
                            {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                    Log.d("Error.Response", error.getMessage());
                                }
                            }
                    ) {
                        @Override
                        public String getBodyContentType() {
                            return "application/json; charset=utf-8";
                        }

                        @Override
                        public byte[] getBody() throws AuthFailureError {
                            try {
                                return mRequestBody == null ? null : mRequestBody.getBytes("utf-8");
                            } catch (UnsupportedEncodingException uee) {
                                VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", mRequestBody, "utf-8");
                                return null;
                            }
                        }

                        @Override
                        protected Response<String> parseNetworkResponse(NetworkResponse response) {
                            String responseString = "";
                            if (response != null) {
                                responseString = String.valueOf(response.statusCode);
                            }
                            return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                        }
                    };
                    queue.add(postRequest);
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
