package com.example.configservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConfigServiceApplicationTests {

    /**
     * 원시 타입(Primitive Type) VS 참조 타입 (Reference Type)
     * 원시 타입 : boolean, char, byte, short, int, long, float, double
     * 참초 타입 : 원시 타입 외에 문자열, 배열, 열거, 클래스, 인터페이스
     * 원시 타입은 스택에 값 자체를 담지만 참초 타입은 주소값을 스택에 담고 실제 값은 힙 영역에 할당된다.
     * 동적 메모리인 힙영역은 사용된 값이 담겨져 있다가 참조하는 변수가 없어지만 가비지컬렉터에서 이를 제거한다.
     */

    /**
     * Stack VS Heep
     * Stack 은 로컬변수와 파라미터가 임시로 할당되는 공간
     * 메소드 호출시 할당되고 메소드가 끝나면 사라짐
     * Heep 은 인스턴스 변수가 할당되는 공간
     * new 로 생성되는 모든 인스턴스틀은 Heep 영역에 담기는데 (String "" 마찬가지)
     * 메소드가 종료되어도 사라지지 않고 GC 발생시 참조를 잃어 버리거나 jvm 종료시까지 유지된다
     */

    /**
     * 원시 변수의 Pass By Value (Call By Value)
     * 원시 변수(Primitive Value) 는 Stack 에 값이 할당된다.
     * 그래서 원본데이터 값이 변경되지 않음
     * ex) stack ->  x = 10
     */

    @Test
    void test1() {
        int a = 10;
        int b = 20;
        changeIntValue(a, b);

        System.out.println(a); // 10 ? 30 ?
        System.out.println(b); // 20 ? 40 ?
    }

    private void changeIntValue(int a, int b) {
        a = 30;
        b = 40;
    }

    /**
     * String 의 Pass By Value (Call By Value)
     * String 은 class 면서 불면이기 때문에 복사된 주소값을 변경한다고 해서 해당주소값의 값이 변경되지는 않음
     * ex)
     * String str = "test";
     * str = "test2"; -> str 에 새로운 주소를 할당
     * 위에 str 과 아래 str 의 주소값이 달라짐 (값이 달라진것 처럼 보이지만 새로 주소를 할당시킴)
     * java 의 String 은 heep 에 String pool 에 따로 값을 보관하는데
     * 만약 같은 값이 있으면 가지고 있는 주소값을 바라봄
     * ex)
     * String str = "test";
     * String str2 = "test";
     * str == str2 -> true
     */

    @Test
    void test2() {
        String a = "aaa";
        String b = "bbb";
        changeStringValue(a, b);

        System.out.println(a); // aaa ? ccc ?
        System.out.println(b); // bbb ? ddd ?
    }

    private void changeStringValue(String a, String b) {
        a = "ccc";
        b = "ddd";
    }

    /**
     * Object 의 Field 값 변경
     * 주소값을 복사해서 넘기지만 heep 에 연결되어있는 값은 같은걸 바라보기 때문에
     * 원본 데이터는 변경된다.
     * ex)
     * Stack                        Heep
     * cat - E0001(복사본)->포도>나비
     * cat - E0001                  Cat 포도 (E0001 과 연결)
     */

    @Test
    void test3() {
        Cat cat = new Cat();
        cat.name = "포도";

        changeObjectValue(cat);

        System.out.println(cat); // 포도 ? 나비 ?
    }

    private void changeObjectValue(Cat cat) {
        cat.name = "나비";
    }

    /**
     * Object 를 새로 할당
     * new 로 인스턴스를 생성시 Stack 과 Heep 에 새로 할당하게 됨
     * 따라서 원본데이터는 변경되지 않음 (주소값이 바꼈기 때문)
     * ex)
     * Stack                Heep
     * cat - EX0002         Cat 나비
     * cat - EX0001         Cat 포도
     */

    @Test
    void test4() {
        Cat cat = new Cat();
        cat.name = "포도";

        changeObject(cat);

        System.out.println(cat); // 포도 ? 나비 ?
    }

    private void changeObject(Cat cat) {
        Cat changedCat = new Cat();
        changedCat.name = "나비";
        cat = changedCat;
    }

    /**
     * 배열의 Pass By Value (Call By Value)
     * 배열의 각 인덱스 주소값은 Heep 에 저장되어 각 값이 Heep의 주소값을 바라봄
     * 그래서 원본데이터가 변경됨
     * ex)
     * Stack                Heep
     * ints EX0001(복사본)
     * ints EX0001 -->      EXL001 - 0
     *                      EXL002 - 1
     *                      EXL003 - 2
     */

    @Test
    void test5() {
        int[] ints = new int[3];
        ints[0] = 0;
        ints[1] = 1;
        ints[2] = 2;

        changeArray(ints);
        System.out.println(ints[0]); // 0 ? 3 ?
        System.out.println(ints[1]); // 1 ? 4 ?
        System.out.println(ints[2]); // 2 ? 5 ?
    }

    private void changeArray(int[] ints) {
        ints[0] = 3;
        ints[1] = 4;
        ints[2] = 5;
    }

    static class Cat {
        private String name;
    }
}
