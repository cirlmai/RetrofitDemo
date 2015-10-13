package com.soohoobook.retrofitdemo;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by Danny on 2015/10/6.
 */
public class APIHandler {
    //判斷API
    private int mReturnType;
    //ProgressDialog
    private ProgressDialog mProgressDialog;
    private Context mContext;
    //CallBack監聽事件
    private OnAPIResponseListener mListener;
    //要Call的API
    private Call mCall;
    //判斷是否正在Call API(預設false)
    private boolean isCall =false;

    public APIHandler(Context context, int returnType, String message) {
        mReturnType = returnType;
        if (context != null) {
            mContext = context;
            mProgressDialog = new ProgressDialog(mContext);
            mProgressDialog.setCancelable(false);
            mProgressDialog.setMessage(message);
            mProgressDialog.show();
        }
    }

    //show ProgressDialog
    private void showDialog() {
        if (mContext != null) mProgressDialog.show();
    }

    //dismiss ProgressDialog
    private void dismissDialog() {
        if (mContext != null) mProgressDialog.dismiss();
    }

    //取消Call API
    private void cancel() {
        mCall.cancel();
        mCall = null;
        dismissDialog();
    }

    public void setOnResponseListener(OnAPIResponseListener listener) {
        mListener = listener;
    }

    //非同步執行Call API
    public void AsyncExecute(Call call) {
        if(isCall){
            //如果正在call api,則直接return，防止一直重複導致crash
            return;
        }
        isCall=true;
        //顯示ProgressDialog
        showDialog();
        mCall = call;
        mCall.enqueue(new Callback() {
            @Override
            public void onResponse(Response response, Retrofit retrofit) {
                mCall=null;
                mListener.onSuccessResult(mReturnType,response);
                dismissDialog();
                isCall=false;
            }

            @Override
            public void onFailure(Throwable t) {
                mCall=null;
                mListener.onFailureResult(mReturnType,t);
                dismissDialog();
                isCall=false;
            }
        });

    }

    //取消Call API
    public void CancelExecute() {
        if (mCall != null) {
            cancel();
        }
    }

    //取消Call API 並Toast提示訊息
    public void CancelExecute(Context context, String message) {
        if (mCall != null) {
            cancel();
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    //callback interface
    public interface OnAPIResponseListener {
        public void onSuccessResult(int returnType, Response response);
        public void onFailureResult(int returnType, Throwable throwable);
    }
}


