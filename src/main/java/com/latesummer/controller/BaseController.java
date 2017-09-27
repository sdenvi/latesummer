package com.latesummer.controller;

import org.apache.catalina.connector.Request;

import com.latesummer.model.entity.Users;
import com.latesummer.utils.LSUtils;
import com.latesummer.utils.MapCache;

/**
 * Created by biezhi on 2017/2/21.
 */
public abstract class BaseController {

    public static String THEME = "themes/default";

    protected MapCache cache = MapCache.single();


    public String render(String viewName) {
        return THEME + "/" + viewName;
    }

    public BaseController title(Request request, String title) {
        request.setAttribute("title", title);
        return this;
    }

    public BaseController keywords(Request request, String keywords) {
        request.setAttribute("keywords", keywords);
        return this;
    }

    public Users user() {
        //return LSUtils.getLoginUser();
        return null;
    }

    public Integer getUid(){
        return this.user().getUid();
    }

    public String render_404() {
        return "/comm/error_404";
    }

}
