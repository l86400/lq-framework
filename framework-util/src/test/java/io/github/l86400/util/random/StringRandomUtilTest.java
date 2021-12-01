package io.github.l86400.util.random;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

/**
 * @author 86400
 * @date 2021/12/1 18:32
 */
@Slf4j(topic = "StringRandomUtilTest")
class StringRandomUtilTest {
    /**
     * {@linkplain StringRandomUtil#randomStr(boolean, boolean, boolean, boolean, boolean, String, int)} 性能测试
     */
    @SneakyThrows
    @Test
    public void randomStrPerformance() {
        long start = System.currentTimeMillis();
        int len = Integer.MAX_VALUE / 1000;
        for (int i = 0; i < len; i++) {
            StringRandomUtil.randomStr(true, true, true, true, false, "!@#$%^&*", 16);
        }
        log.info("性能测试结果,执行randomStr{}次，耗时:{}", len, System.currentTimeMillis() - start);
    }
}