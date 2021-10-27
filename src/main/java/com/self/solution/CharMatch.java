package com.self.solution;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 检查嵌套括号 是否 配套
 * 只看3种括号: ()、[]、{}
 *
 * Example Case：
 *
 * - true
 *    - '{(x[x)]}'
 *    - '{}()'
 *    - 'x{}(x)'
 * - false
 *    - '}'
 *    - '{(})'
 * @author yuanxin
 * @date 2021-10-28
 */
public class CharMatch {
    /**
     * 配套符号的kv对
     */
    static Map<Character, Character> map = new HashMap<>();
    static {
        for (String s : Arrays.asList("()", "[]", "{}")) {
            if (StringUtils.isBlank(s) || s.length() != 2) {
                continue;
            }
            char[] chars = s.toCharArray();
            map.put(chars[0], chars[1]);
        }
    }

    /**
     * 检查嵌套括号 是否配套
     * @param str 需校验的字符串
     * @return 是否配套
     */
    public boolean solution(String str) {
        if (StringUtils.isBlank(str)) {
            return true;
        }
        Stack<Character> res = new Stack<>();
        for (char c : str.toCharArray()) {
            if (map.containsKey(c)) {
                res.push(map.get(c));
            } else if (map.containsValue(c)) {
                if (res.isEmpty() || c != res.pop()) {
                    return false;
                }
            }
        }
        return res.isEmpty();
    }
}
