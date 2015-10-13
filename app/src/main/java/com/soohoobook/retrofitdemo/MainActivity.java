package com.soohoobook.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.soohoobook.retrofitdemo.Receivers.RouteReceiver;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Response;


public class MainActivity extends AppCompatActivity implements APIHandler.OnAPIResponseListener, View.OnClickListener {

    private Button mBtnCall1,mBtnCall2;
    private Button mBtnCancel;
    private TextView mTxtMsg;
    private ArrayList<RouteReceiver> mRouteReceiver;
    private final int API_RETURN_ROUTE = 0;
    APIHandler apiHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBtnCall1 = (Button) findViewById(R.id.btnCall1);
        mBtnCall2 = (Button) findViewById(R.id.btnCall2);
        mTxtMsg = (TextView) findViewById(R.id.txtMessage);
        mBtnCancel = (Button) findViewById(R.id.btnCancel);
        mBtnCall1.setOnClickListener(this);
        mBtnCall2.setOnClickListener(this);
        mBtnCancel.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        Call call;
        switch (id) {
            case R.id.btnCall1:
                mTxtMsg.setText("Call with ProgressDialog");
                apiHandler = new APIHandler(null, API_RETURN_ROUTE, "不會顯示提示文字");
                /*create 新的APIHandler 來處理Call API的問題*/
                //Params 1.Context(可以為Null) 2.Return Type(判斷是哪個API的CallBack)  3.Message(context != Null時，要顯示的提示文字)
                apiHandler = new APIHandler(MainActivity.this, API_RETURN_ROUTE, "要顯示的提示文字");

                //設定Callback事件，必須要去implements APIHandler 的 OnJsonResultListener interface
                //Callback事件由在onJsonResult Method處裡
                apiHandler.setOnResponseListener(MainActivity.this);

                //宣告要使用APIService裡哪個API
                call = MyApplication.getApiService().routeList("1");

                //將要使用的API放入APIHandler後回直接執行
                apiHandler.AsyncExecute(call);
                break;
            case R.id.btnCall2:
                mTxtMsg.setText("Call on background");

                /*create 新的APIHandler 來處理Call API的問題*/
                //Params 1.Context(可以為Null) 2.Return Type(判斷是哪個API的CallBack)  3.Message(context != Null時，要顯示的提示文字)
                apiHandler = new APIHandler(null, API_RETURN_ROUTE, "不會顯示提示文字");

                //設定Callback事件，必須要去implements APIHandler 的 OnJsonResultListener interface
                //Callback事件由在onJsonResult Method處裡
                apiHandler.setOnResponseListener(MainActivity.this);

                //宣告要使用APIService裡哪個API
                call = MyApplication.getApiService().routeList("1");

                //將要使用的API放入APIHandler後回直接執行
                apiHandler.AsyncExecute(call);
                break;
            case R.id.btnCancel:
                if (apiHandler != null) {
                    apiHandler.CancelExecute(MainActivity.this, "已取消");
                }
                break;
        }
    }

    @Override
    public void onSuccessResult(int returnType, Response response) {
        switch (returnType) {
            case API_RETURN_ROUTE:
                mRouteReceiver = (ArrayList<RouteReceiver>) response.body();
                String title = "";
                for (RouteReceiver receiver : mRouteReceiver) {
                    title = title + receiver.getTitle() + "\n";
                }
                mTxtMsg.setText(title);
                break;
        }
    }

    @Override
    public void onFailureResult(int returnType, Throwable throwable) {
        switch (returnType) {
            case API_RETURN_ROUTE:
                mTxtMsg.setText(throwable.getMessage());
                break;
        }
    }
}
