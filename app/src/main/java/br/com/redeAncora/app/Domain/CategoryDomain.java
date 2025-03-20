package br.com.redeAncora.app.Domain;

/**
 * Classe que representa uma categoria de carro no sistema.
 */
public class CategoryDomain {
    private String title;
    private int id;
    private String picUrl;

    public CategoryDomain() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}


