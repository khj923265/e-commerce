package com.example.configservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConfigServiceApplicationTests {

    /**
     * Pass By Value(값에 의한 전달)는 복사된 데이터를 전달하여 구성함으로써
     * 값을 수정하여도 원본의 데이터에는 영향을 주지 않도록 하는 방식이다
     *
     * Pass By Reference(참조에 의한 전달)는 주소 값을 전달하여 실제 값에 대한 Alias를 구성함으로써
     * 값을 수정하면 원본의 데이터가 수정되도록 하는 방식이다
     */

    /**
     * 원시 타입(Primitive Type) VS 참조 타입 (Reference Type)
     * 원시 타입 : boolean, char, byte, short, int, long, float, double
     * 참조 타입 : 원시 타입 외에 문자열, 배열, 열거, 클래스, 인터페이스
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
     * 동일성 VS 동등성
     * equels 값 비교 (동등성)
     * == 주소값 비교 (동일성)
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
     * String str = "test"; (리터럴 사용시 내부적으로 intern() 사용)
     * str = "test2"; -> str 에 다른 주소를 할당 (String pool 에서 있으면 가져오고 없으면 pool에 생성)
     * 위에 str 과 아래 str 의 주소값이 달라짐 (값이 달라진것 처럼 보이지만 새로 주소를 할당시킴)
     * java 의 String 은 heep 에 String pool 에 따로 값을 보관하는데
     * 만약 같은 값이 있으면 가지고 있는 주소값을 리턴
     *
     * ++ Integer(그외 대부분의 래퍼클래스) 는 비슷하게 valueOf()에서 캐싱된 데이터를 가져옴 -128~127(기본값) jvm 설정값으로 설정 가능
     * VM.getSavedProperty("java.lang.Integer.IntegerCache.high");
     * Integer a = 127;
     * Integer b = 127;
     * a == b -> true
     * Integer a = 500;
     * Integer b = 500;
     * a == b -> false
     */

    @Test
    void test2() {
        // 첫번째 테스트
        String a = "aaa";
        String b = "bbb";

        changeStringValue(a, b);

        System.out.println(a); // aaa ? ccc ?
        System.out.println(b); // bbb ? ddd ?

        // 두번째 테스트
        String test = "test";
        String test2 = "test";
        String newTest = new String("test");
        String internTest = newTest.intern();

        System.out.println("String test : " + (test == test2)); //True
        System.out.println("String test new : " + (test2 == newTest)); //False
        System.out.println("String test intern : " + (test2 == internTest));
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
     * cat - E0001                  Cat 포도->나비 (E0001 과 연결)
     */

    @Test
    void test3() {
        Cat cat = new Cat();
        cat.name = "포도";

        changeObjectValue(cat);

        System.out.println(cat.name); // 포도 ? 나비 ?
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
