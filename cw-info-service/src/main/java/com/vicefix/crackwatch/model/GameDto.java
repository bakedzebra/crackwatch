package com.vicefix.crackwatch.model;

import java.util.Date;
import java.util.List;

public class GameDto {
    private String _id;
    private String title;
    private String slug;
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
    private Date updatedAt;
    private String url;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

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
}
