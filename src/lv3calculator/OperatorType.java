package lv3calculator;

public enum OperatorType {

    /**
     * 각각 사칙연산을
     * 추상 메소드를 상속 받아서 구현.
     * 각 Enum 상수가 익명 클래스처럼 동작한다.
     * 제네릭을 활용하여 타입 안정성을 확보하면서도 유연한 계싼 가능
     */
    PLUS('+') {
        @Override
        public <T extends Number> T apply(Number num1, Number num2) {
            // 둘 중 하나라도 실수(Double) 이면 Double연산
            if (num1 instanceof Double || num2 instanceof Double) {
                return (T) Double.valueOf(num1.doubleValue() + num2.doubleValue());
            }
            // 둘 다 정수(Integer)라면 Integer 연산
            return (T) Integer.valueOf(num1.intValue() + num2.intValue());
        }
    },

    MINUS('-') {
        @Override
        public <T extends Number> T apply(Number num1, Number num2) {
            if (num1 instanceof Double || num2 instanceof Double) {
                return (T) Double.valueOf(num1.doubleValue() - num2.doubleValue());
            }
            return (T) Integer.valueOf(num1.intValue() - num2.intValue());
        }
    },

    MULTIPLY('*') {
        @Override
        public <T extends Number> T apply(Number num1, Number num2) {
            if (num1 instanceof Double || num2 instanceof Double) {
                return (T) Double.valueOf(num1.doubleValue() * num2.doubleValue());
            }
            return (T) Integer.valueOf(num1.intValue() * num2.intValue());
        }
    },

    DIVIDE('/') {
        @Override
        public <T extends Number> T apply(Number num1, Number num2) {
            if (num2.doubleValue() == 0.0) {
                // 0으로 나눌수 없는 조건 main에서 예외처리
                throw new ArithmeticException("나눗셈 연산에서 분모(두 번째 정수)에 0이 입력될 수 없습니다.");
            }
            // 나눗셈은 정확한 결과를 위해서 항상 Double로 반환
            return (T) Double.valueOf(num1.doubleValue() / num2.doubleValue());
        }
    };

    private final char symbol;

    /**
     * Operater 생성자
     *
     * @param symbol 연산자 기호
     */
    OperatorType(char symbol) {
        this.symbol = symbol;
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
     * 사칙연산이 [숫자] [연산자] [숫자] 방식처럼
     * 이항 연산의 틀을 사용하고 있어서
     * 공통된 계산의 틀을 강제하는 방식의 추상메소드 사용
     *
     * 제네릭 메소드로 구현하여 타입 안정성 확보
     * [ 정수 ] [ 연산자 ] [ 정수 ]
     * [ 실수 ] [ 연산자 ] [ 정수 ]
     * [ 정수 ] [ 연산자 ] [ 실수 ]
     * [ 실수 ] [ 연산자 ] [ 실수 ]
     *
     * 나눗셈은 항상 Double 반환
     *
     * @param num1 첫 번째 숫자
     * @param num2 두 번째 숫자
     * @param <T>  반환 타입(Number를 상속 받는 타입)
     * @return 연산 결과
     */
    public abstract <T extends Number> T apply(Number num1, Number num2);

}
