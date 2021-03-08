package org.elib.repository.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "elib", name = "USER")
public class User extends  ModelObject{
    @SequenceGenerator(
            name = "userSeq",
            sequenceName = "USER_SEQ",
            schema = "elib",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSeq")
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    private String name;
    private String password;
    private Boolean admin = false;
    private String avatarUrl;

    @JsonbTransient
    @OneToMany
    @JoinTable(
            schema = "codecta",
            name = "user_books",
            joinColumns = @JoinColumn(name = "USER_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID")
    )
    public List<Book> bookList = new ArrayList<>();

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }
}
