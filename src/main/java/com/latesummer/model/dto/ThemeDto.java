package com.latesummer.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * Create By Jenvi Sue On 2017年9月21日
 */
@Data
public class ThemeDto implements Serializable {

    /**
     * 主题名称
     */
    private String name;

    /**
     * 是否有设置项
     */
    private boolean hasSetting;

    public ThemeDto(String name) {
        this.name = name;
    }

}
