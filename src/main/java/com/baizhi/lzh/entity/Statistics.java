package com.baizhi.lzh.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Statistics  implements Serializable {

    private String months;
    private String sex;
    private String counts;
    private String city;
}
