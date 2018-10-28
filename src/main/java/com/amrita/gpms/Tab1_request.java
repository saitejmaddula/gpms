package com.amrita.gpms;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.amrita.gpms.Stu_login.user;


public class Tab1_request extends Fragment {
 private EditText wid,outdate,indate,reason;
   private Button submit;
    private ProgressDialog progress;
   private String w,r,sysdate;
   private Date Indate,Outdate,date,tdate;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab1_request, container, false);
        return rootView;
    }
    public void onViewCreated(View view, Bundle savedInstanceState) {
        wid=(EditText)view.findViewById(R.id.et_wid);
        outdate=(EditText)view.findViewById(R.id.et_outdate);
        indate=(EditText)view.findViewById(R.id.et_indate);
        reason=(EditText)view.findViewById(R.id.et_reason);
        submit=(Button)view.findViewById(R.id.bt_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                w=wid.getText().toString().trim();
                r=reason.getText().toString().trim();
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
                try {
                     Indate=sdf.parse(indate.getText().toString().trim());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                try {
                     Outdate=sdf.parse(outdate.getText().toString().trim());
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                 date=new Date();
                 sysdate=sdf.format(date);
                try {
                     tdate=sdf.parse(sysdate);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if(((!w.isEmpty())&&(!outdate.getText().toString().trim().isEmpty()))&&((!indate.getText().toString().trim().isEmpty())&&!r.isEmpty())) {
                    // Toast.makeText(getActivity().getApplicationContext(),"hello",Toast.LENGTH_SHORT).show();
                    if (((Indate.compareTo(Outdate) >= 0) && (Indate.compareTo(tdate) >= 0)) && (Outdate.compareTo(tdate) >= 0)) {
                        showdialog();
                        Submit();
                    }
                    else
                    {
                        AlertDialog alertDialog1 = new AlertDialog.Builder(
                                getActivity()).create();
                        alertDialog1.setTitle("Error");
                        alertDialog1.setMessage("         Invalid dates!!!");
                        alertDialog1.setIcon(R.drawable.error);
                        alertDialog1.show();
                    }
                }
                else
                {
                    Toast.makeText(getActivity().getApplicationContext(),"Please fill all the details",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
private void Submit()
{
    String url="http://reddysaisumanth014.000webhostapp.com/Req_submit.php";
    RequestQueue requestQueue= Volley.newRequestQueue(this.getActivity());
    StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
        @Override
        public void onResponse(String response) {

            if(response.equals("success"))
            {   canceldialog();
                //Toast.makeText(getActivity().getApplicationContext(),"Request Submitted Sucessfully!",Toast.LENGTH_SHORT).show();
                AlertDialog alertDialog = new AlertDialog.Builder(
                        getActivity()).create();
                alertDialog.setTitle("Successful");
                alertDialog.setMessage("Request submitted successfully");
                alertDialog.setIcon(R.drawable.tick);
                alertDialog.show();

            }
            else{
                 canceldialog();
                Toast.makeText(getActivity().getApplicationContext(),"Error:Request not submitted",Toast.LENGTH_LONG).show();
            }
        }
    },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    canceldialog();
                    Toast.makeText(getActivity().getApplicationContext(),"error:"+error.toString(),Toast.LENGTH_LONG).show();
                }
            }
    ){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> params=new HashMap<>();
            params.put("Regno",user);
            params.put("Wid",wid.getText().toString().trim());
            params.put("Outdate",outdate.getText().toString().trim());
            params.put("Indate",indate.getText().toString().trim());
            params.put("Reason",reason.getText().toString().trim());
            params.put("Approval","NOT_APPROVED");
            return params;
        }
    };
    stringRequest.setRetryPolicy(new DefaultRetryPolicy(5000,3,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    requestQueue.add(stringRequest);
}
   private  void  showdialog()
    {    progress = new ProgressDialog(this.getActivity());

        progress.setTitle("Loading");
        progress.setMessage("Submitting Request...");
        progress.setCancelable(false); // disable dismiss by tapping outside of the dialog
        progress.show();
    }
    private  void  canceldialog()
    {
        progress.dismiss();
    }
    private  void clear()
    {
        wid.setText(null);
        outdate.setText(null);
        indate.setText(null);
        reason.setText(null);
    }
}
