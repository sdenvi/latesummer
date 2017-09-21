package com.latesummer.extension;

import jetbrick.template.runtime.JetTagContext;

import java.io.IOException;

import com.latesummer.utils.StringUtil;

/**
 * 主题公共标签
 * Create By Jenvi Sue On 2017年9月21日
 */
public class JetTag {

    public static void social(JetTagContext ctx, String name) throws IOException {
        String value = Commons.site_option("social_" + name);
        if (StringUtil.isNotBlank(value)) {
            value = ctx.getBodyContent();
        }
        ctx.getWriter().print(value.toString());
    }

}
