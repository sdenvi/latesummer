package com.latesummer.domain.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * 后台统计对象
 * Create By Jenvi Sue On 2017年9月21日
 */
@Data
public class Statistics implements Serializable {

    // 文章数
    private long articles;
    // 页面数
    private long pages;
    // 评论数
    private long comments;
    // 分类数
    private long categories;
    // 标签数
    private long tags;
    // 附件数
    private long attachs;

}
