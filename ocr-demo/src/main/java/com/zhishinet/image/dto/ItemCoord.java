package com.zhishinet.image.dto;

/**
 * Copyright Shanghai Hand Co. Ltd.
 *
 * @author liguo.wang@hand-china.com
 * @version: 1.0
 */
public class ItemCoord {
    private Integer x;

    private Integer y;

    private Integer width;

    private Integer height;

    public Integer getX() {
        return x;
    }

    public ItemCoord setX(Integer x) {
        this.x = x;
        return this;
    }

    public Integer getY() {
        return y;
    }

    public ItemCoord setY(Integer y) {
        this.y = y;
        return this;
    }

    public Integer getWidth() {
        return width;
    }

    public ItemCoord setWidth(Integer width) {
        this.width = width;
        return this;
    }

    public Integer getHeight() {
        return height;
    }

    public ItemCoord setHeight(Integer height) {
        this.height = height;
        return this;
    }
}
