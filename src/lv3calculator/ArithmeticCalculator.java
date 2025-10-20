package lv3calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 제네릭을 활용한 사칙연산 클래스
 * - 정수와 실수를 혼합하여 계산 가능 (ex) 10 + 3.5)
 * - 제네릭을 통해 타입 안정성 확보
 */
public class ArithmeticCalculator<T extends Number> {
    // 연산 결과를 저장하는 컬렉션 타입 필드 - private 사용하여 캡슐화
    private List<T> results = new ArrayList<>();

    /**
     * [ calculate 메소드 동작과정 ]
     * 1. Char 연산자를 OperatorType Enum으로 변환
     * ㄴ 잘못된 연산자면 예외처리
     * 2. OperatorType의 apply() 메소드로 실제 연산 진행
     * ㄴ 0으로 나누기 등의 문제 발생 시 예외 처리
     * 3. 연산 성공 결과를 results 리스트에 저장
     *
     * @param num1     첫번째 숫자
     * @param num2     두번째 숫자
     * @param operator 연산자
     * @return 계산된 값
     * @throws IllegalArgumentException 잘못된 연산자일경우
     * @throws ArithmeticException      0으로 나누기 시도할경우
     */
    public T calculate(Number num1, Number num2, char operator) {
        // 1. 입력 받은 char 형태의 연산자를 OperatorType 형태로 변환
        // isOperator()에서 잘못된 연산자로 판단하면 예외 발생
        OperatorType operatorType = OperatorType.isOperator(operator);

        // 2. 실제 연산을 수행
        // apply()에서 0으로 나누기 등의 문제 발생시 예외 발생
        T result = operatorType.apply(num1, num2);

        // 3. 연산 결과를 리스트에 저장
        results.add(result);
        return result;

    }

    // 간접 접근을 통해 필드에 접근하여 가져오는 메소드 - getter
    public List<T> getResults() {
        return results;
    }


    // 간접 접근을 통해 필드에 접근하여 수정하는 메소드 - setter
    public void setResults(List<T> results) {
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

    /**
     * 특정 값보다 큰 결과값들을 조회하는 메소드
     * 람다 & 스트림을 활용하여 필터링
     *
     * @param referValue 기준 값
     * @return 기준 값보다 큰 결과값들의 리스트
     */
    public List<T> getResultMoreThan(double referValue) {

        return results.stream() // 1. 데이터 준비 : 컬렉션을 스트림으로 변환
                // 입력된 기준값 referValue 보다 큰 컬렉션만 조회
                .filter(result -> result.doubleValue() > referValue) // 중간 연산 등록 : 데이터 변환 및 필터링
                .toList(); // 최종 처리 및 데이터 변환 [ collect(Collectors.toUnmodifiableList()) -> toList() 변경 ]
    }
}
