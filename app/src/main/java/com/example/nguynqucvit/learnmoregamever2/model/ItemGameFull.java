package com.example.nguynqucvit.learnmoregamever2.model;

/**
 * Created by viet on 27/07/2017.
 */

public class ItemGameFull {
    private ItemGame itemGame;
    private String contentHtml;

    public ItemGameFull(ItemGame itemGame, String contentHtml) {
        this.itemGame = itemGame;
        this.contentHtml = contentHtml;
    }

    public ItemGame getItemGame() {
        return itemGame;
    }

    public void setItemGame(ItemGame itemGame) {
        this.itemGame = itemGame;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }
}
