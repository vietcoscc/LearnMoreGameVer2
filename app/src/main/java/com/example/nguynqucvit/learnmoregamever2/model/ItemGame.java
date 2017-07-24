package com.example.nguynqucvit.learnmoregamever2.model;

/**
 * Created by Nguyễn Quốc Việt on 19/07/2017.
 */

public class ItemGame {
    private int typeId; // Xác định item nằm trong listView nào
    private String imageUrl;// url của hình ảnh
    private String name;// tên của game
    private String type;// thể loại game
    private String date;// ngày ra mắt game
    private String views;// số lượng xem
    private String detailsUrl;// url của nội dung game

    public ItemGame(int typeId, String imageUrl, String name, String type, String date, String views, String detailsUrl) {
        this.typeId = typeId;
        this.imageUrl = imageUrl;
        this.name = name;
        this.type = type;
        this.date = date;
        this.views = views;
        this.detailsUrl = detailsUrl;
    }

    public int getTypeId() {
        return typeId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }

    public String getViews() {
        return views;
    }

    public String getDetailsUrl() {
        return detailsUrl;
    }
}
