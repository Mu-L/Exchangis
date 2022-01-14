package com.webank.wedatasphere.exchangis.appconn.model;


import org.apache.linkis.httpclient.request.DeleteAction;
import org.apache.linkis.httpclient.request.UserAction;

public class ExchangisDeleteAction extends DeleteAction implements UserAction {

    String url;
    String user;

    @Override
    public String getURL() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String getUser() {
        return user;
    }
}
