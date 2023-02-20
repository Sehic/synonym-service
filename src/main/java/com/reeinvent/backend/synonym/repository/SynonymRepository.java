package com.reeinvent.backend.synonym.repository;

import com.reeinvent.backend.synonym.entities.SynonymEntity;
import com.reeinvent.backend.synonym.entities.WordEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SynonymRepository extends CrudRepository<SynonymEntity, Long> {
    SynonymEntity findBySynonymAndWord(WordEntity synonym, WordEntity word);
}
