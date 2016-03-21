package com.soohoobook.retrofitdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.soohoobook.retrofitdemo.Receivers.RouteReceiver;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit.Call;
import retrofit.Response;


public class MainActivity extends AppCompatActivity implements APIHandler.OnAPIResponseListener {

    @Bind(R.id.btnCall1)
    Button mBtnCall1;
    @Bind(R.id.btnCall2)
    Button mBtnCall2;
    @Bind(R.id.btnCancel)
    Button mBtnCancel;
    @Bind(R.id.txtMessage)
    TextView mTxtMsg;
    private ArrayList<RouteReceiver> mRouteReceiver;
    private final int API_RETURN_ROUTE = 0;
    APIHandler apiHandler;

    @OnClick(R.id.btnCall1)
    void callApi1() {
        mTxtMsg.setText("Call with ProgressDialog");
        getData(this);
    }

    @OnClick(R.id.btnCall2)
    void callAPI2() {
        mTxtMsg.setText("Call on background");
        getData(null);
    }

    private void getData(Context context) {
                 /*create 新的APIHandler 來處理Call API的問題*/
        //Params 1.Context(可以為Null) 2.Return Type(判斷是哪個API的CallBack)  3.Message(context != Null時，要顯示的提示文字)
        apiHandler = new APIHandler(context, API_RETURN_ROUTE, "Context不為null，顯示提示文字");

        //設定Callback事件，必須要去implements APIHandler 的 OnJsonResultListener interface
        //Callback事件由在onJsonResult Method處裡
        apiHandler.setOnResponseListener(MainActivity.this);

        //宣告要使用APIService裡哪個API
        Call call = MyApplication.getApiService().routeList("1");

        //將要使用的API放入APIHandler後回直接執行
        apiHandler.AsyncExecute(call);
    }

    @OnClick(R.id.btnCancel)
    void cancelCall() {
        if (apiHandler != null) {
            apiHandler.CancelExecute(MainActivity.this, "已取消");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        callApi1();

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
