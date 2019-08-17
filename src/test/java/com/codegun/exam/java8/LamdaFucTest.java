package com.codegun.exam.java8;

import com.codegun.exam.java8.service.Conversion;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import static org.assertj.core.api.Assertions.*;

@Slf4j
public class LamdaFucTest {

    @Test
    public void 기본함수호출(){
        Conversion conversion = String::valueOf;
        String convertValue = conversion.convert(10);
        log.info("정수->문자열로 변환 : {}",convertValue);

        assertThat(convertValue).isEqualTo("10");
    }
}

