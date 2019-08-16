package com.codegun.exam.java8;

import com.codegun.exam.java8.dto.Animal;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class StreamTest {
    private List<String> list = Arrays.asList("호랑이", "캥거루", "사자", "북금곰", "염소", "낙타","고양이");

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
    public void 기존_리스트값을_특정객체에_맵핑하여_리스트로리턴() {
        List<Animal> animals = new ArrayList<>();
        for (String item : list) {
            Animal animal = new Animal(item);
            animals.add(animal);
            log.info("동물 목록 : {}", animal.getName());
        }
        assertThat(list.size()).isEqualTo(animals.size());
    }

    @Test
    public void 리스트값을_특정객체에_맵핑하여_리스트로리턴() {
        List<Animal> animals =  list.stream().map(Animal::new).collect(Collectors.toList());
        animals.stream().forEach(animal -> log.info("동물 목록 : {}",animal.getName()));
        assertThat(list.size()).isEqualTo(animals.size());
    }




}
