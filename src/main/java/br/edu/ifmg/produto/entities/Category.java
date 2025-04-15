package br.edu.ifmg.produto.entities;

import br.edu.ifmg.produto.dtos.CategoryDTO;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant createdAt;
    @Column(columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
    private Instant updateAt;

    public Category(long id, String name) {
        this.name = name;
        this.id = id;
    }

    public Category() {
    }

    public Category(CategoryDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdateAt() {
        return updateAt;
    }

    @PrePersist
     private void prePersist(){
        createdAt = Instant.now();
     }
    @PreUpdate
    private void preUpdate(){
        updateAt = Instant.now();
     }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Category category)) return false;
        return id == category.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
