package lv3calculator;

import java.util.function.BiFunction;

public enum OperatorType {

    /**
     * 람다식을 활용한 사칙 연산 구현
     * 각 Enum 상수가 BiFunction 함수형 인터페이스를 통해 연산 로직을 간경하게 표현
     *
     * @funtionalInterface가 이미 선언된 표준 함수형 인터페이스
     * BiFunction <Number, Number, Number> : 2개의 Number를 받아서 Number를 반환하는 함수형 인터페이스
     */
    PLUS('+', (num1, num2) -> {
        // 둘 중 하나라도 실수(Double) 이면 Double연산
        if (num1 instanceof Double || num2 instanceof Double) {
            // 1. num1.doubleValue() + num2.doubleValue() : double형 기본형
            // 2. Double.valueOf(...) : Double Wrapper 클래스로 감싸주기
            return Double.valueOf(num1.doubleValue() + num2.doubleValue());
        }
        // 둘 다 정수(Integer)라면 Integer 연산
        return Integer.valueOf(num1.intValue() + num2.intValue());
    }),

    MINUS('-', (num1, num2) -> {
        if (num1 instanceof Double || num2 instanceof Double) {
            return Double.valueOf(num1.doubleValue() - num2.doubleValue());
        }
        return Integer.valueOf(num1.intValue() - num2.intValue());
    }),

    MULTIPLY('*', (num1, num2) -> {
        if (num1 instanceof Double || num2 instanceof Double) {
            return Double.valueOf(num1.doubleValue() * num2.doubleValue());
        }
        return Integer.valueOf(num1.intValue() * num2.intValue());
    }),

    DIVIDE('/', (num1, num2) -> {
        if (num2.doubleValue() == 0.0) {
            // 0으로 나눌수 없는 조건 main에서 예외처리
            throw new ArithmeticException("나눗셈 연산에서 분모(두 번째 정수)에 0이 입력될 수 없습니다.");
        }
        // 나눗셈은 정확한 결과를 위해서 항상 Double로 반환
            return Double.valueOf(num1.doubleValue() / num2.doubleValue());
    });

    private final char symbol;
    private final BiFunction<Number, Number, Number> operation;

    /**
     * Operator 생성자
     *
     * @param symbol 연산자 기호
     * @param operation 람다식으로 표현된 연산 함수 (BiFunction 함수형 인터페이스)
     */
    OperatorType(char symbol, BiFunction<Number, Number, Number> operation) {
        this.symbol = symbol;
        this.operation = operation;
    }

    // 연산자 기호 반환
    public char getSymbol() {
        return symbol;
    }

    /**
     * 입력된 연산자 문자가 사칙연산에 해당하는지 판별하는 메소드
     *
     * @param symbol 연산자 기호 문자
     * @return 해당하는 OperatorType
     * @throws IllegalArgumentException 잘못된 연산자일경우
     */
    public static OperatorType isOperator(char symbol) {
        for (OperatorType operatorType : OperatorType.values()) {
            if (operatorType.getSymbol() == symbol) {
                return operatorType;
            }
        }
        // 잘못된 연산자 일경우 main 에서 예외 처리
        throw new IllegalArgumentException("잘못된 연산 기호 입니다. (+, -, *, / 중 입력하세요)");
    }

    /**
     * 실제 연산을 수행하는 추상 메소드
     * 람다식으로 정의된 operation 함수형 인터페이스를 실행
     *
     * 제네릭 메소드로 구현하여 타입 안정성 확보
     * [ 정수 ] [ 연산자 ] [ 정수 ] > Integer
     * [ 실수 ] [ 연산자 ] [ 정수 ] > Double
     * [ 정수 ] [ 연산자 ] [ 실수 ] > Double
     * [ 실수 ] [ 연산자 ] [ 실수 ] > Double
     *
     * 나눗셈은 항상 Double 반환
     *
     * @param num1 첫 번째 숫자
     * @param num2 두 번째 숫자
     * @param <T>  반환 타입(Number를 상속 받는 타입)
     * @return 연산 결과
     */
    public <T extends Number> T apply(Number num1, Number num2) {
        // Bifuction의 apply 메소드를 호출하려 람다식 실행
        // (T)는 Number 하위 타입(Double 이거나 Integer)이므로 안전하게 캐스팅이 가능
        return (T) operation.apply(num1, num2);
    }

}
