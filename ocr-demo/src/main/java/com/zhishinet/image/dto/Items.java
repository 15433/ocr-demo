package com.zhishinet.image.dto;

/**
 * Copyright Shanghai Hand Co. Ltd.
 *
 * @author liguo.wang@hand-china.com
 * @version: 1.0
 */
public class Items {

    private Double itemConf;

    private String itemString;

    private ItemCoord itemCoord;

    private Double score;

    private String key;

    public Double getItemConf() {
        return itemConf;
    }

    public Items setItemConf(Double itemConf) {
        this.itemConf = itemConf;
        return this;
    }

    public String getItemString() {
        return itemString;
    }

    public Items setItemString(String itemString) {
        this.itemString = itemString;
        return this;
    }

    public ItemCoord getItemCoord() {
        return itemCoord;
    }

    public Items setItemCoord(ItemCoord itemCoord) {
        this.itemCoord = itemCoord;
        return this;
    }

    public Double getScore() {
        return score;
    }

    public Items setScore(Double score) {
        this.score = score;
        return this;
    }

    public String getKey() {
        return key;
    }

    public Items setKey(String key) {
        this.key = key;
        return this;
    }
}
