package com.diagne.angular.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class AnimeCharacter implements Serializable {

    @Id
    @GeneratedValue
    private Long idAnime;

    private String animeName;

    private String category;

    private String strength;

    private byte[] photo;

    private boolean shared;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    public AnimeCharacter() {
    }

    public Long getIdAnime() {
        return idAnime;
    }

    public void setIdAnime(Long idAnime) {
        this.idAnime = idAnime;
    }

    public String getAnimeName() {
        return animeName;
    }

    public void setAnimeName(String animeName) {
        this.animeName = animeName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

