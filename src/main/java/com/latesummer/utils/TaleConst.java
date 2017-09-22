package com.latesummer.utils;


import java.util.*;

import com.latesummer.model.dto.PluginMenu;

/**
 * 常量存储
 * Create By Jenvi Sue On 2017年9月21日
 */
public class TaleConst {

    public static final String      USER_IN_COOKIE    = "S_L_ID";
    public static       String      AES_SALT          = "0123456789abcdef";
    public static       String      LOGIN_SESSION_KEY = "login_user";
    //public static       Environment OPTIONS           = Environment.of(new HashMap<>());
    public static       Boolean     INSTALL           = false;
    //public static       Environment BCONF             = null;

    /**
     * 最大页码
     */
    public static final int MAX_PAGE = 100;

    /**
     * 最大获取文章条数
     */
    public static final int MAX_POSTS = 9999;

    /**
     * 文章最多可以输入的文字数
     */
    public static final int MAX_TEXT_COUNT = 200000;

    /**
     * 文章标题最多可以输入的文字个数
     */
    public static final int MAX_TITLE_COUNT = 200;

    /**
     * 插件菜单
     */
    public static final List<PluginMenu> plugin_menus = new ArrayList<>();

    /**
     * 上传文件最大20M
     */
    public static Integer MAX_FILE_SIZE = 204800;

    /**
     * 要过滤的ip列表
     */
    public static final Set<String> BLOCK_IPS = new HashSet<>(16);

}