package org.exemple.demo;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "shelf")
public class Shelf {
    @Id
    @Column(name = "shelf_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String label;

    @OneToMany(mappedBy = "shelf")
    private Collection<Book> bookCollection;

    public Collection<Book> getBookCollection() {
        return bookCollection;
    }

    public void setBookCollection(Collection<Book> bookCollection) {
        this.bookCollection = bookCollection;
    }


    public Shelf() {
    }


    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
