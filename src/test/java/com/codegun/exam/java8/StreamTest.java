package com.codegun.exam.java8;

import com.codegun.exam.java8.dto.Animal;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class StreamTest {
    private List<String> list = Arrays.asList("호랑이", "캥거루", "사자", "북금곰", "염소", "낙타","고양이");
    List<String> stringCollection = Arrays.asList("ddd2", "aaa2", "bbb1", "aaa1", "bbb3", "ccc", "bbb2", "ddd1");

    @Test
    public void 기존_스트림출력() {
        for (String item : list) {
            log.info("동물 목록 :{}",item);
        }
    }
    @Test
    public void 스트림출력() {
        list.stream().forEach(s -> log.info("동물 목록: {}",s));
    }

    @Test
    public void 기존_조건에_맞는_카운트처리() {
        int count = 0;
        for (String item : list) {
            if (item.contains("이")) {
                count++;
            }
        }
        log.info("이 들어간 동물 숫자 : {}",count);
    }

    @Test
    public void 조건에_맞는_카운트_처리() {
        long count = list.stream().filter(s -> s.contains("이")).count();
        assertThat(count).isEqualTo(2);
    }

    @Test
    public void 기존_리스트값을_특정조건에_맞는_값을뽑아_특정객체에_맵핑하여_리스트로리턴() {
        List<Animal> animals = new ArrayList<>();
        for (String item : list) {
            Animal animal = new Animal(item);
            if (item.equals("호랑이")) {
                animals.add(animal);
                log.info("동물 목록 : {}", animals);
            }
        }
        assertThat(animals.size()).isEqualTo(1);
    }

    @Test
    public void 리스트값을_특정조건에_맞는_값을뽑아_특정객체에_맵핑하여_리스트로리턴() {
        List<Animal> animals =  list.stream().filter(s -> s.equals("호랑이")).map(Animal::new).collect(Collectors.toList());
        animals.stream().forEach(animal -> log.info("동물 목록 : {}",animal.getName()));
        assertThat(animals.size()).isEqualTo(1);
    }

    @Test
    public void 정렬처리() {
       String sortExam = stringCollection.stream().sorted().collect(Collectors.joining("-"));
       log.info("정렬:{}",sortExam);
        assertThat(sortExam).isEqualTo("aaa1-aaa2-bbb1-bbb2-bbb3-ccc-ddd1-ddd2");
    }

    @Test
    public void 정렬된_첫번째_인자뽑기() {
        Optional<String> first =  stringCollection.stream().sorted().findFirst();
        log.info("첫번째 인자 : {}",first.get());
        assertThat(first.get()).isEqualTo("aaa1");
    }

    @Test
    public void 객체탐색() {
        boolean result =  stringCollection.stream().anyMatch(s -> s.startsWith("a"));
        boolean failResult =  stringCollection.stream().anyMatch(s -> s.startsWith("e"));
        assertThat(result).isTrue();
        assertThat(failResult).isFalse();
    }

    @Test
    public void 객체디버깅() {
        List<String> list =  stringCollection.stream()
                    .filter(s -> {
                        log.info("filter:  {}", s);
                        return s.toLowerCase().startsWith("a");
                    })
                    .sorted((s1, s2) -> {
                        log.info("sort: {} {}\n", s1, s2);
                        return s1.compareTo(s2);
                    })
                    .map(s -> {
                        log.info("map:     {}", s);
                        return s.toUpperCase();
                    })
                    .collect(Collectors.toList());
        list.forEach(s -> log.info("forEach: {}", s));

        assertThat(list).contains("AAA1", "AAA2");
    }






}
