package com.reeinvent.backend.synonym.entities;

import com.reeinvent.backend.entities.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "synonyms", uniqueConstraints={
        @UniqueConstraint(columnNames = {"word_id", "synonym_id"})
})
public class SynonymEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name="synonym_id", nullable=false)
    private WordEntity synonym;

    @ManyToOne
    @JoinColumn(name="word_id", nullable=false)
    private WordEntity word;

    public WordEntity getSynonym() {
        return synonym;
    }

    public void setSynonym(WordEntity synonym) {
        this.synonym = synonym;
    }

    public WordEntity getWord() {
        return word;
    }

    public void setWord(WordEntity word) {
        this.word = word;
    }
}
