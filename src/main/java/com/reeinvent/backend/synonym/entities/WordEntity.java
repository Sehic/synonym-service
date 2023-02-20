package com.reeinvent.backend.synonym.entities;

import com.reeinvent.backend.entities.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "words")
public class WordEntity extends BaseEntity {

    @Column(nullable = false)
    private String text;

    @OneToMany(mappedBy="word", orphanRemoval = true)
    private Set<SynonymEntity> synonyms;

    @OneToMany(mappedBy="synonym", orphanRemoval = true)
    private Set<SynonymEntity> words;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<SynonymEntity> getSynonyms() {
        return synonyms;
    }

    public void setSynonyms(Set<SynonymEntity> synonyms) {
        this.synonyms = synonyms;
    }

    public Set<SynonymEntity> getWords() {
        return words;
    }

    public void setWords(Set<SynonymEntity> words) {
        this.words = words;
    }
}
