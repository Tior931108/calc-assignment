package lv3calculator;

public enum OperatorType {

    /**
     * 각각 사칙연산을
     * 추상 메소드를 상속 받아서 구현.
     * 각 Enum 상수가 익명 클래스처럼 동작한다.
     */
    PLUS('+') {
        @Override
        public int apply(int num1, int num2) {
            return num1 + num2;
        }
    },

    MINUS('-') {
        @Override
        public int apply(int num1, int num2) {
            return num1 - num2;
        }
    },

    MULTIPLY('*') {
        @Override
        public int apply(int num1, int num2) {
            return num1 * num2;
        }
    },

    DIVIDE('/') {
        @Override
        public int apply(int num1, int num2) {
            if (num2 == 0) {
                // 0으로 나눌수 없는 조건 main에서 예외처리
                throw new ArithmeticException("나눗셈 연산에서 분모(두 번째 정수)에 0이 입력될 수 없습니다.");
            }
            return num1 / num2;
        }
    };

    private final char symbol;

    /**
     * Operater 생성자
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
     * @param num1 첫 번째 숫자
     * @param num2 두 번째 숫자
     * @return 연산 결과
     */
    public abstract int apply(int num1, int num2);

}
