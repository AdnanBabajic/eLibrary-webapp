package org.elib.repository.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(schema = "elib", name = "BOOK")
public class Book extends ModelObject{
    @SequenceGenerator(
            name = "bookSeq",
            sequenceName = "BOOK_SEQ",
            schema = "elib",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    private String name;
    private String writer;
    private String coverUrl = "";
    private Date releaseYear = java.util.Calendar.getInstance().getTime();
    private String genre = "";
    private Boolean available = true;

    @ManyToOne
    public User user;

    @Override
    public Object getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public Date getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(Date releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }
}
