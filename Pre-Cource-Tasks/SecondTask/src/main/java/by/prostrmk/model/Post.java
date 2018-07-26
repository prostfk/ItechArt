package by.prostrmk.model;

public class Post {

    private Long id;
    private String description;
    private String pathToPhoto;

    public Post() {
    }

    public Post(Long id, String description, String pathToPhoto) {
        this.id = id;
        this.description = description;
        this.pathToPhoto = pathToPhoto;
    }

    public Post(String description, String pathToPhoto) {
        this.description = description;
        this.pathToPhoto = pathToPhoto;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPathToPhoto() {
        return pathToPhoto;
    }

    public void setPathToPhoto(String pathToPhoto) {
        this.pathToPhoto = pathToPhoto;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", pathToPhoto='" + pathToPhoto + '\'' +
                '}';
    }
}
