package com.doodledoodle.backend.dictionary.service;

import com.doodledoodle.backend.dictionary.dto.response.DictionaryResponse;
import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.dictionary.repository.DictionaryRepository;
import com.doodledoodle.backend.support.database.DatabaseTest;
import com.doodledoodle.backend.utils.RandomGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@DatabaseTest
@DisplayName("Dictionary 서비스의")
class DictionaryServiceTest {
    @Autowired private DictionaryService dictionaryService;
    @Autowired private DictionaryRepository dictionaryRepository;
    @MockBean private RandomGenerator randomGenerator;

    @Test
    @DisplayName("랜덤 단어 조회가 수행되는가")
    void getRandomDictionary() {
        //given
        long randomNum = 10L;
        when(randomGenerator.generateRandom()).thenReturn(randomNum);

        //when
        DictionaryResponse dictionaryResponse = dictionaryService.getRandomDictionary();

        //then
        Dictionary dictionary = dictionaryRepository.findById(randomNum).orElseThrow();
        assertThat(dictionaryResponse.getKoreanName()).isEqualTo(dictionary.getKoreanName());
    }

    @Test
    @DisplayName("PK로 조회가 수행되는가")
    void loadEntity() {
        //given
        Long id = 50L;

        //when
        Dictionary dictionary = dictionaryService.loadEntity(id);

        //then
        assertThat(dictionary.getId()).isEqualTo(id);
    }
}
