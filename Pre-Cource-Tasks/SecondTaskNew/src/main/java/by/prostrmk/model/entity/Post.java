package by.prostrmk.model.entity;

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
}
