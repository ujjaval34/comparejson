package com.ujjaval.comparejson.interfaces;

import com.ujjaval.comparejson.model.PostDetails;
import com.ujjaval.comparejson.model.UserDetails;

import java.util.List;

public interface FlowCallBack {

    public void onSuccess(List<UserDetails> response);
    public void onFailed(Throwable t);


    public void onPostSuccess(List<PostDetails> response);
    public void onPostFailed(Throwable t);

}
