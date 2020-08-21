package com.demo.cloud.domain;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author: lzc
 * @Description:
 * @Date: Create in 15:54 2020/7/23
 */
@Data
@Table(name = "tb_spu")
public class Product {
    @Id
    private Long id;
    @Column(name = "title")
    private String title;
    @Column(name = "sub_title")
    private String subTitle;
    @Column(name = "cid1")
    private Long cid1;
    @Column(name = "cid2")
    private Long cid2;
    @Column(name = "cid3")
    private Long cid3;
    @Column(name = "brand_id")
    private Long brandId;
    @Column(name = "saleable")
    private Integer saleable;
    @Column(name = "valid")
    private Integer valid;
    @Column(name = "create_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    @Column(name = "last_update_time")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateTime;
}
