package com.reeinvent.backend.synonym.service;

import com.reeinvent.backend.GRPCSynonym;
import com.reeinvent.backend.GRPCWord;
import com.reeinvent.backend.GRPCWords;
import com.reeinvent.backend.Void;
import com.reeinvent.backend.synonym.entities.SynonymEntity;
import com.reeinvent.backend.synonym.entities.WordEntity;
import com.reeinvent.backend.synonym.mapper.SynonymMapper;
import com.reeinvent.backend.synonym.repository.SynonymRepository;
import com.reeinvent.backend.synonym.repository.WordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;

@Service
public class SynonymService {

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private SynonymRepository synonymRepository;

    public GRPCWord upsertWord(GRPCWord request) {
        WordEntity wordEntity = new WordEntity();
        if (!request.getId().isEmpty()) {
            wordEntity = getWordById(request.getId());
        }
        wordEntity.setText(request.getText());
        return SynonymMapper.toGRPCWord(wordRepository.save(wordEntity));
    }

    public Void deleteWord(GRPCWord request) {
        WordEntity wordEntity = getWordById(request.getId());
        wordRepository.delete(wordEntity);
        return Void.newBuilder().build();
    }

    public Void attachSynonym(GRPCSynonym request) {
        WordEntity word = getWordById(request.getWordId());
        WordEntity synonym = getWordById(request.getId());
        createSynonym(word, synonym);
        createSynonym(synonym, word);
        return Void.newBuilder().build();
    }

    private void createSynonym(WordEntity wordA, WordEntity wordB) {
        SynonymEntity synonymEntity = new SynonymEntity();
        synonymEntity.setWord(wordA);
        synonymEntity.setSynonym(wordB);
        synonymRepository.save(synonymEntity);
    }

    public Void detachSynonym(GRPCSynonym request) {
        WordEntity word = getWordById(request.getWordId());
        WordEntity synonym = getWordById(request.getId());
        SynonymEntity synonymEntity = synonymRepository.findBySynonymAndWord(synonym, word);
        synonymRepository.delete(synonymEntity);
        return Void.newBuilder().build();
    }

    private WordEntity getWordById(String id) {
        return wordRepository.findById(Long.valueOf(id))
                .orElseThrow(() -> new EntityNotFoundException("Word not found: " + id));
    }

    @Transactional
    public GRPCWord getSynonymsByWord(GRPCWord request) {
        WordEntity word = getWordById(request.getId());
        return SynonymMapper.toGRPCWord(word);
    }

    // add paging sorting filtering ...
    @Transactional
    public GRPCWords getWords(Void request) {
        return SynonymMapper.toGRPCWords(wordRepository.findAll());
    }
}
