package com.min.seed.entity;

import javax.persistence.*;

public class Websites {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 站点名称
     */
    private String name;

    private String url;

    /**
     * Alexa 排名
     */
    private Integer alexa;

    /**
     * 国家
     */
    private String country;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取站点名称
     *
     * @return name - 站点名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置站点名称
     *
     * @param name 站点名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 获取Alexa 排名
     *
     * @return alexa - Alexa 排名
     */
    public Integer getAlexa() {
        return alexa;
    }

    /**
     * 设置Alexa 排名
     *
     * @param alexa Alexa 排名
     */
    public void setAlexa(Integer alexa) {
        this.alexa = alexa;
    }

    /**
     * 获取国家
     *
     * @return country - 国家
     */
    public String getCountry() {
        return country;
    }

    /**
     * 设置国家
     *
     * @param country 国家
     */
    public void setCountry(String country) {
        this.country = country;
    }
}