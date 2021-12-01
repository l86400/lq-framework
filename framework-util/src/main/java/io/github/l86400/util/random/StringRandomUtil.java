package io.github.l86400.util.random;

import io.github.l86400.util.string.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author 86400
 * @date 2021/12/1 18:14
 */
@Slf4j
public class StringRandomUtil {
    /**
     * A字母char
     */
    private static final char CHAR_A = 'A';
    /**
     * a字母char
     */
    private static final char CHAR_LOWER_A = 'a';
    /**
     * 0字母char
     */
    private static final char CHAR_0 = '0';
    /**
     * 汉字的char字符长度（区间）
     */
    private final static int CHINESE_CHAR_LEN = 0x9fa5 - 0x4e00 + 1;

    private final static char[] SPECIAL_CHAR_ARRAY = new char[]{'~', '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '_', '-', '+', '=', '{', '}', '[', ']', '|', '\\', ':', ';', '"', '\'', '<', ',', '>', '.', '?', '/'};

    private StringRandomUtil() {
        log.debug("1");
    }

    public static String randomStr(Random random, boolean upperCharacter, boolean lowerCharacter, boolean numberCharacter, boolean specialCharacter, boolean chineseCharacter, String specialCharStr, int len) {
        if (len < 1) {
            return "";
        }
        random = random(random);
        StringBuilder sb = new StringBuilder();
        byte trueParamCnt = (byte) ((upperCharacter ? (byte) 1 : (byte) 0)
                + (lowerCharacter ? (byte) 1 : (byte) 0)
                + (numberCharacter ? (byte) 1 : (byte) 0)
                + (specialCharacter ? (byte) 1 : (byte) 0)
                + (chineseCharacter ? (byte) 1 : (byte) 0));
        List<Character> paramArray = new ArrayList<>(trueParamCnt);
        if (upperCharacter) {
            paramArray.add('a');
        }
        if (lowerCharacter) {
            paramArray.add('b');
        }
        if (numberCharacter) {
            paramArray.add('c');
        }
        if (specialCharacter) {
            paramArray.add('d');
        }
        if (chineseCharacter) {
            paramArray.add('e');
        }
        char[] specialCharArray = SPECIAL_CHAR_ARRAY;
        if (StringUtil.isNotEmpty(specialCharStr)) {
            specialCharArray = specialCharStr.toCharArray();
        }
        for (int i = 0; i < len; i++) {
            int paramType = random.nextInt(trueParamCnt);
            switch (paramArray.get(paramType)) {
                case 'a':
                    sb.append(randomUpperChar(random));
                    break;
                case 'b':
                    sb.append(randomLowerChar(random));
                    break;
                case 'c':
                    sb.append(randomNumberChar(random));
                    break;
                case 'd':
                    sb.append(randomSpecialChar(random, specialCharArray));
                    break;
                default:
                    sb.append(randomChineseChar(random));
            }
        }
        return sb.toString();
    }

    public static String randomStr(boolean upperCharacter, boolean lowerCharacter, boolean numberCharacter, boolean specialCharacter, boolean chineseCharacter, String specialCharStr, int lenMin, int lenMax) {
        return randomStr(random(null), upperCharacter, lowerCharacter, numberCharacter, specialCharacter, chineseCharacter, specialCharStr, lenMin, lenMax);
    }

    public static String randomStr(Random random, boolean upperCharacter, boolean lowerCharacter, boolean numberCharacter, boolean specialCharacter, boolean chineseCharacter, String specialCharStr, int lenMin, int lenMax) {
        int len = random(random).nextInt(lenMax - lenMin + 1) + lenMin;
        return randomStr(random, upperCharacter, lowerCharacter, numberCharacter, specialCharacter, chineseCharacter, specialCharStr, len);
    }

    public static String randomStr(boolean upperCharacter, boolean lowerCharacter, boolean numberCharacter, boolean specialCharacter, boolean chineseCharacter, String specialCharStr, int len) {
        return randomStr(random(null), upperCharacter, lowerCharacter, numberCharacter, specialCharacter, chineseCharacter, specialCharStr, len);
    }

    public static char randomUpperChar(Random random) {
        return (char) (CHAR_A + random(random).nextInt(26));
    }

    public static char randomUpperChar() {
        return randomUpperChar(null);
    }

    public static char randomLowerChar(Random random) {
        return (char) (CHAR_LOWER_A + random(random).nextInt(26));
    }

    public static char randomLowerChar() {
        return randomLowerChar(null);
    }

    public static char randomNumberChar(Random random) {
        return (char) (CHAR_0 + random(random).nextInt(10));
    }

    public static char randomNumberChar() {
        return randomNumberChar(null);
    }

    public static char randomSpecialChar(Random random, char[] specialCharArray) {
        return specialCharArray[random(random).nextInt(specialCharArray.length)];
    }

    public static char randomSpecialChar(char[] specialCharArray) {
        return randomSpecialChar(null, specialCharArray);
    }

    public static char randomChineseChar(Random random) {
        return (char) (0x4e00 + random(random).nextInt(CHINESE_CHAR_LEN));
    }

    public static char randomChineseChar() {
        return randomChineseChar(null);
    }

    private static Random random(Random random) {
        if (random != null) {
            return random;
        }
        return new Random();
    }
}
