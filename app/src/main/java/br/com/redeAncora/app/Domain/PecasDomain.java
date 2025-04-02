package br.com.redeAncora.app.Domain;

import java.io.Serializable;

/**
 * Classe que representa uma peca no sistema.
 * Implementa Serializable para permitir a transferência de objetos entre Activities.
 */
public class PecasDomain implements Serializable {
    private String title;
    private String description;
    private String picUrl;
    private String detalhes;
    private String marca;
    private String HighestSpeed;
    private double price;
    private double rating;
    private String category; // Adicionando categoria

    public PecasDomain() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getDetalhes() {
        return detalhes;
    }

    public void setDetalhes(String detalhes) {
        this.detalhes = detalhes;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getHighestSpeed() {
        return HighestSpeed;
    }

    public void setHighestSpeed(String highestSpeed) {
        HighestSpeed = highestSpeed;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}