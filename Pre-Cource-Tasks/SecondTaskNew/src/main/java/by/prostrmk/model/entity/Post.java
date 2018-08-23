package by.prostrmk.model.entity;

import java.util.Objects;

public class Post {

    private Long id;
    private String title;
    private String path;
    private Long userId;

    public Post() {
    }

    public Post(String title, String path, Long userId) {
        this.title = title;
        this.path = path;
        this.userId = userId;
    }

    public Post(Long id, String title, String path, Long userId) {
        this.id = id;
        this.title = title;
        this.path = path;
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id) &&
                Objects.equals(title, post.title) &&
                Objects.equals(path, post.path) &&
                Objects.equals(userId, post.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, path, userId);
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", path='" + path + '\'' +
                ", userId=" + userId +
                '}';
    }
}
