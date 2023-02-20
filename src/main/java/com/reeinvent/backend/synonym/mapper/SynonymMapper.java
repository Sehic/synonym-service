package com.reeinvent.backend.synonym.mapper;

import com.reeinvent.backend.GRPCWord;
import com.reeinvent.backend.GRPCWords;
import com.reeinvent.backend.synonym.entities.SynonymEntity;
import com.reeinvent.backend.synonym.entities.WordEntity;

import java.util.List;
import java.util.stream.Collectors;

public class SynonymMapper {
    public static GRPCWord toGRPCWord(WordEntity word) {
        GRPCWord.Builder builder = GRPCWord.newBuilder();

        builder.setId(word.getId().toString());
        builder.setText(word.getText());
        if (word.getSynonyms() != null && !word.getSynonyms().isEmpty()) {
            builder.addAllSynonyms(word.getSynonyms().stream()
                    .map(SynonymMapper::toGRPCWordFromSynonymEntity)
                    .collect(Collectors.toList())
            );
        }

        return builder.build();
    }

    private static GRPCWord toGRPCWordFromSynonymEntity(SynonymEntity synonymEntity) {
        return GRPCWord.newBuilder()
                .setId(synonymEntity.getSynonym().getId().toString())
                .setText(synonymEntity.getSynonym().getText())
                .build();
    }

    public static GRPCWords toGRPCWords(List<WordEntity> list) {
        return GRPCWords.newBuilder()
                .addAllWord(list.stream()
                        .map(SynonymMapper::toGRPCWord)
                        .collect(Collectors.toList()))
                .build();
    }
}
