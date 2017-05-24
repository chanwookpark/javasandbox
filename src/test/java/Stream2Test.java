import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author chanwook
 */
public class Stream2Test {

    @Test
    public void filter() throws Exception {
        List<Member> list = getMembers();

        final List<Member> result =
                list.stream().filter(m -> m.getAge() > 10).collect(Collectors.toList());

        System.out.println("size : " + result.size());
    }

    private List<Member> getMembers() {
        List<Member> list = new ArrayList<>();
        list.add(new Member("철수", 10));
        list.add(new Member("영희", 12));
        list.add(new Member("길동", 9));
        return list;
    }

    @Test
    public void distinct와limit과skip() throws Exception {
        final List<Integer> list = Arrays.asList(1, 1, 1, 2, 3, 3, 4, 4, 5, 5, 5, 6, 7, 8, 8, 9);

        List<Integer> result = list.stream().distinct().collect(Collectors.toList());
        assert 9 == result.size();

        result = list.stream().distinct().limit(5).collect(Collectors.toList());
        assert 5 == result.size();

        result = list.stream().distinct().skip(5).collect(Collectors.toList());
        assert 4 == result.size();
    }

    @Test
    public void map() throws Exception {
        List<Member> list = getMembers();

        final List<String> result = list.stream().map(Member::getName).collect(Collectors.toList());
        assert result.size() == 3;
    }

    @Test
    public void flatMap() throws Exception {
        List<String> list = Arrays.asList("Hello", "World");

        final List<String> result = list.stream()
                .map(s -> s.split(""))
                .flatMap(Arrays::stream)
                .collect(Collectors.toList());

        assert 10 == result.size();
    }

    @Test
    public void match() throws Exception {
        assert true == getMembers().stream().anyMatch(m -> m.getAge() > 10);
        assert true == getMembers().stream().allMatch(m -> m.getAge() > 8);
        assert true == getMembers().stream().noneMatch(m -> m.getAge() > 20);
    }

    @Test
    public void reduce() throws Exception {
        final List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        assert 55 == list.stream().reduce(0, (a, b) -> a + b);
        assert 3628800 == list.stream().reduce(1, (a, b) -> a * b);

    }

    public static class Member {

        private String name;

        private int age;

        public Member(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
