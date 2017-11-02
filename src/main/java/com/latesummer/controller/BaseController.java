package com.latesummer.controller;

import com.latesummer.utils.MapCache;

/**
 *Cteate By Jenvi Sue On 2017年11月1日
 */
public abstract class BaseController {

    public static String THEME = "themes/default";

    protected MapCache cache = MapCache.single();


    public String render(String viewName) {
        return THEME + "/" + viewName;
    }

    /*public BaseController title(Request request, String title) {
        request.attribute("title", title);
        return this;
    }

    public BaseController keywords(Request request, String keywords) {
        request.attribute("keywords", keywords);
        return this;
    }

    public Users user() {
        return TaleUtils.getLoginUser();
    }

    public Integer getUid(){
        return this.user().getUid();
    }

    public String render_404() {
        return "/comm/error_404";
    }*/

}
