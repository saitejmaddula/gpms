package com.amrita.gpms;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.Inflater;

import static com.amrita.gpms.Stu_login.user;
import static com.amrita.gpms.warden_login.user_war;

/**
 * Created by Sumanth on 8/25/2018.
 */

public class Tab2_view  extends Fragment {
    private ProgressDialog progress;
    private RecyclerView recyclerView;
    private stu_adapter adapter;
    private List<item_gate> iglist;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = (RecyclerView) view.findViewById(R.id.stu_recview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        iglist = new ArrayList<>();
        showdialog();
        loadgatepass();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab2_view, container, false);
        return rootView;
    }
    private  void  showdialog()
    {    progress = new ProgressDialog(this.getActivity());

        progress.setTitle("Fetching Data");
        progress.setMessage("Please wait...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
    }
    private  void  canceldialog()
    {
        progress.dismiss();
    }
    private void loadgatepass() {
        String url = "http://reddysaisumanth014.000webhostapp.com/stu_view.php";
        StringRequest sr = new StringRequest(Request.Method.DEPRECATED_GET_OR_POST, url, new Response.Listener<String>() {
            //@RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(String response) {
                canceldialog();
                try {

                    JSONArray gparray = new JSONArray(response);
                    for (int i = 0; i < gparray.length(); i++) {
                        JSONObject gpobject = gparray.getJSONObject(i);
                        iglist.add(new item_gate(
                                gpobject.getString("Regno"),
                                gpobject.getString("Approval"),
                                gpobject.getInt("GID")

                        ));
                    }
                    adapter = new stu_adapter(Tab2_view.this.getActivity(), iglist);
                    recyclerView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // canceldialog();
                Toast.makeText(getActivity(), "Error" + error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user", Stu_login.user);
                return params;
            }

        };
        sr.setRetryPolicy(new DefaultRetryPolicy(5000,3,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(this.getActivity()).add(sr);

    }

}
