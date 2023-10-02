package com.doodledoodle.backend.test;

import com.doodledoodle.backend.dictionary.entity.Dictionary;
import com.doodledoodle.backend.dictionary.repository.DictionaryRepository;
import com.doodledoodle.backend.draw.entity.Draw;
import com.doodledoodle.backend.draw.repository.DrawRepository;
import com.doodledoodle.backend.game.entity.Game;
import com.doodledoodle.backend.game.repository.GameRepository;
import com.doodledoodle.backend.result.entity.Result;
import com.doodledoodle.backend.result.mapper.ResultMapper;
import com.doodledoodle.backend.result.repository.ResultRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class TestService {

    GameRepository gameRepository;
    DrawRepository drawRepository;
    ResultRepository resultRepository;
    DictionaryRepository dictionaryRepository;
    ResultMapper resultMapper;
    EntityManager em;


    public void dataInsert() {
        List<Result> entites = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <=20000; i++) {  // 0 ~ 10 , 11회
            Long randomDictionaryId = (long) random.nextInt(99) + 1;
            int randomPlayerNum = random.nextInt(6) + 1;

            //여기까지 game 데이터 넣기
            Game game = Game.builder()
                .playerNum(randomPlayerNum)
                .build();

            Dictionary dictionary = dictionaryRepository.findById(randomDictionaryId).orElseThrow();
            game.updateDictionary(dictionary);

//            em.flush();
//            em.clear();

            //랜덤한 draw 데이터 넣기

            for (int j = 1; j <= randomPlayerNum; j++) {
                Draw draw = Draw.builder()
                    .imageUrl("123")
                    .playerNo(j)
                    .game(game)
                    .build();

                entites.addAll(List.of(
                    Result.builder()
                        .draw(draw)
                        .dictionary(dictionary)
                        .similarity(getRandomValue(random))
                        .game(game)
                        .isRepresent(true)
                        .build(),
                    Result.builder()
                        .draw(draw)
                        .dictionary(
                            dictionaryRepository.findById((long) random.nextInt(99) + 1).orElseThrow())
                        .similarity(getRandomValue(random))
                        .game(game)
                        .isRepresent(false)
                        .build(),
                    Result.builder()
                        .draw(draw)
                        .dictionary(
                            dictionaryRepository.findById((long) random.nextInt(99) + 1).orElseThrow())
                        .similarity(getRandomValue(random))
                        .game(game)
                        .isRepresent(false)
                        .build(),
                    Result.builder()
                        .draw(draw)
                        .dictionary(
                            dictionaryRepository.findById((long) random.nextInt(99) + 1).orElseThrow())
                        .similarity(getRandomValue(random))
                        .game(game)
                        .isRepresent(false)
                        .build(),
                    Result.builder()
                        .draw(draw)
                        .dictionary(
                            dictionaryRepository.findById((long) random.nextInt(99) + 1).orElseThrow())
                        .similarity(getRandomValue(random))
                        .game(game)
                        .isRepresent(false)
                        .build(),
                    Result.builder()
                        .draw(draw)
                        .dictionary(
                            dictionaryRepository.findById((long) random.nextInt(99) + 1).orElseThrow())
                        .similarity(getRandomValue(random))
                        .game(game)
                        .isRepresent(false)
                        .build())
                );

            }
        }
        resultRepository.saveAll(entites);
    }

    private double getRandomValue(Random random) {
        double randomValue = 0.0 + (100.0 - 0.0) * random.nextDouble();
        return  randomValue;
    }
}
