package com.latesummer.domain.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

import com.latesummer.domain.Contents;

/**
 * 文章归档
 * Create By Jenvi Sue On 2017年9月21日
 */
@Data
public class Archive {

    private String         date_str;
    private Date           date;
    private String         count;
    private List<Contents> articles;

}
