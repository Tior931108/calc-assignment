package lv2calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    // 연산 결과를 저장하는 컬렉션 타입 필드 - private 사용하여 캡슐화
    private List<Integer> results = new ArrayList<>();

    /**
     * 사칙 연산 수행 메서드
     * @param num1 첫번째 양의 정수(0포함) 숫자
     * @param num2 두번째 양의 정수(0포함) 숫자
     * @param operator 연산자
     * @return 계산된 값
     */
    public int calculate(int num1, int num2, char operator) {
        // 계산된 결과겂을 저장할 변수
        int result;
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
                    // main으로 예외처리 전가
                    // 나눗셈 0으로 나누는것은 의도하지 않은 예외이나
                    // 해당 예외시 계산기 무한 루프 시키기 위해 throw로 의도적인 예외를 사용
                    throw new ArithmeticException("나눗셈 연산에서 분모(두 번째 정수)에 0이 입력될 수 없습니다.");
                }
                result = num1 / num2;
                break;
            default:
                // main으로 예외처리 전가 - 의도한 예외
                throw new IllegalArgumentException("잘못된 연산 기호 입니다. (+, -, *, / 중 입력하세요)");
        }

        // 연산 결과를 리스트에 저장
        results.add(result);
        return result;
    }


    // 간접 접근을 통해 필드에 접근하여 가져오는 메소드 - getter
    public List<Integer> getResults() {
        return results;
    }


    // 간접 접근을 통해 필드에 접근하여 수정하는 메소드 - setter
    public void setResults(List<Integer> results) {
        this.results = results;
    }

    // 가장 먼저 저장된 데이터를 삭제하는 메소드
    public void removeResult() {
        if (!results.isEmpty()) {
            // 저장된 데이터가 있다면 첫번째 요소 삭제 : 인덱스 0
            results.remove(0);
            System.out.println("가장 먼저 저장된 값이 삭제되었습니다.");
        } else {
            // 저장된 데이터가 없다면
            System.out.println("삭제할 결과값이 없습니다.");
        }
    }
}
