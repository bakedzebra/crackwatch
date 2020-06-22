package com.vicefix.crackwatch.elastic.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Document(collection = "games")
public class Game implements Serializable {
    @MongoId
    private String id;
    @Indexed
    private String title;
    private String slug;
    @Indexed
    private Date releaseDate;
    private List<String> protections;
    private List<String> versions;
    private String image;
    private String imagePoster;
    private boolean isAAA;
    private int NFOsCount;
    private int commentsCount;
    private int followersCount;
    private List<String> groups;
    @Indexed
    private Date updatedAt;
    @Indexed
    private Date crackDate;
    private String url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public List<String> getProtections() {
        return protections;
    }

    public void setProtections(List<String> protections) {
        this.protections = protections;
    }

    public List<String> getVersions() {
        return versions;
    }

    public void setVersions(List<String> versions) {
        this.versions = versions;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImagePoster() {
        return imagePoster;
    }

    public void setImagePoster(String imagePoster) {
        this.imagePoster = imagePoster;
    }

    public boolean isAAA() {
        return isAAA;
    }

    public void setAAA(boolean AAA) {
        isAAA = AAA;
    }

    public int getNFOsCount() {
        return NFOsCount;
    }

    public void setNFOsCount(int NFOsCount) {
        this.NFOsCount = NFOsCount;
    }

    public int getCommentsCount() {
        return commentsCount;
    }

    public void setCommentsCount(int commentsCount) {
        this.commentsCount = commentsCount;
    }

    public int getFollowersCount() {
        return followersCount;
    }

    public void setFollowersCount(int followersCount) {
        this.followersCount = followersCount;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getCrackDate() {
        return crackDate;
    }

    public void setCrackDate(Date crackDate) {
        this.crackDate = crackDate;
    }

    public String getId() {
        return id;
    }

    @JsonProperty("_id")
    public void setId(String id) {
        this.id = id;
    }
}
