package lv2calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    // 연산 결과를 저장하는 컬렉션 타입 필드
    public List<Integer> results = new ArrayList<>();

    /**
     * 사칙 연산 수행 메서드
     * @param첫번째 양의 정수(0포함) 숫자
     * @param두번째 양의 정수(0포함) 숫자
     * @param연산자
     * @return 계산된 값
     */
    public int calculate(int num1, int num2, char operator) {
        // 계산된 결과겂을 저장할 변수
        int result = 0;
        // 사칙연산 수행 (switch, if)
        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
//                    System.out.println("나눗셈 연산에서 분모(두 번째 정수)에 0이 입력될 수 없습니다.");
                    // main으로 예외처리 전가
                    // 나눗셈 0으로 나누는것은 의도하지 않은 예외이나
                    // 해당 예외시 계산기 무한 루프 시키기 위해 throw로 의도적인 예외를 사용
                    throw new ArithmeticException("나눗셈 연산에서 분모(두 번째 정수)에 0이 입력될 수 없습니다.");

                }
                result = num1 / num2;
                break;
            default:
//                System.out.println("잘못된 연산 기호 입니다. (+, -, *, / 중 입력하세요)");
                // main으로 예외처리 전가 - 의도한 예외
                throw new IllegalArgumentException("잘못된 연산 기호 입니다. (+, -, *, / 중 입력하세요)");
        }
        return result;
    }

    // 지금까지의 연산 결과 리스트를 반환하는 메서드
}
