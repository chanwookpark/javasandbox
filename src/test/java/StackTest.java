import org.junit.Test;

import java.util.Stack;

/**
 * @author chanwook
 */
public class StackTest {

    @Test
    public void test() throws Exception {
        assert 1 == logic("{[()()]}");
        assert 0 == logic("{{{{");
        assert 0 == logic("}}}}");
        assert 1 == logic("{}");
        assert 1 == logic("[]");
        assert 1 == logic("()");
        assert 0 == logic(")(");
    }

    private int logic(String s) {
        Stack<Character> stack = new Stack<>();
        try {
            s.chars().forEach(v -> {
                        final char c = (char) v;
                        if ('{' == c || '[' == c || '(' == c) {
                            stack.push(c);
                        } else {
                            // 닫는 케이스인지 확인
                            final Character i = stack.pop();
                            if ((')' == c && '(' != i) ||
                                    (']' == c && '[' != i) ||
                                    ('}' == c && '{' != i))
                                throw new RuntimeException();
                        }
                    }
            );
            if (stack.isEmpty()) {
                return 1;
            } else {
                return 0;
            }
        } catch (Exception e) {
            return 0;
        }
    }
}
