package org.example.entity;

import jakarta.persistence.*;
import org.example.repo.ActorRepo;
import org.hibernate.mapping.Set;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Entity
public class Actor implements ActorRepo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "Actor", )
    private String firstName;

    private String lastName;

    private



    public Long getId(){
        return id;
    }

    public void setId(Long id){
        this.id = id;
    }

    @Override
    public Optional<Actor> getId(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<Actor> getName(String name) {
        return Optional.empty();
    }

    @Override
    public List<Actor> getCountry(String country) {
        return List.of();
    }

    @Override
    public List<Actor> getLanguage(String language) {
        return List.of();
    }
}
