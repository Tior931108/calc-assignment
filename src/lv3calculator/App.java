package lv3calculator;

import lv2calculator.Calculator;

import java.util.List;
import java.util.Scanner;

/**
 * Lv.3 Enum, 제네릭, 람다&스트림을 이해한 계산기 만들기
 *
 * [요약]
 * a. 양의 정수만 받을 수 있었지만, 이제부터는 실수도 받을 수 있게 수정한다.
 * b. 결과가 저장되어 있는 컬렉션을 조회하는 기능을 만든다.
 * c. 그때 특정 값보다 큰 결과값을 출력할 수 있도록 한다.
 *
 * 1) Enum 타입을 활용하여 연산자 타입에 대한 정보를 관리하고 이를 사칙연산 계산기 ArithmeticCalculator 클래스 활용
 * 2) 제네릭을 활용하여 실수, 즉 double 타입의 값을 전달 받아도 연산이 수행하도록 만들기
 * 3) 람다와 스트림을 활용하여 저장된 연산 결과들 중 Scanner로 입력받은 값보다 큰 결과값들을 출력
 */
public class App {
    public static void main(String[] args) {
        // ArithmeticCalculator 객체로 변경,  코드 유지를 위해 변수 이름은 동일하게 유지
        ArithmeticCalculator calculator = new ArithmeticCalculator();
        Scanner sc = new Scanner(System.in);

        while (true) { // 전체 계산 반복
            System.out.println("====== 사칙연산 계산기 ======");

            // 입력 받은 수 저장할 변수 선언
            Number num1, num2;
            // 연산자 저장할 변수 선언
            char operator;
            // 연산 결과 저장할 변수 선언
            Number result;

            // 첫 번째 숫자 입력
            num1 = getIsNumber(sc, "첫 번째");

            // 두 번째 숫자 입력
            num2 = getIsNumber(sc, "두 번째");

            System.out.print("사칙연산 기호를 입력하세요 : ");
            operator = sc.next().charAt(0);


            // ArithmeticCalculator 객체를 이용해서 연산수행
            // 잘못된 나눗셈 및 잘못된 연산자 오류 발생할 수 있어서 try-catch 구문 사용
            try {
                // 연산 정상 실행
                result = calculator.calculate(num1, num2, operator);
                // 결과 타입에 맞게 출력
                if (result instanceof Integer) {
                    System.out.println("결과(정수) : " + result);
                } else if (result instanceof Double) {
                    System.out.println("결과(실수) : " + result);
                }
            } catch (Exception e) {
                // 잘못된 나눗셈 및 잘못된 연산자 사용시 오류 문구 출력
                System.out.println(e.getMessage());
                // 무한 루프를 위해 continue; 적용
                continue;
            }

            // 반복 여부 확인 (exit 입력해야만 계산기 종료)
            System.out.print("더 계산하시겠습니까? (exit 입력 시 종료) : ");
            String exit = sc.next(); // 종료할 문자열 입력


            // ArithmeticCalculator 클래스에서 구현한 컬렉션 필드의 getter, setter와 컬렉션 삭제 메소드 활용
            if (exit.equalsIgnoreCase("exit")) {
                System.out.println("====== 사칙연산 종료 =======");

                // 결과값 삭제 여부 메소드
                deleteLoopResults(sc, calculator);

                // 최종 저장된 결과 리스트 및 계산기 종료
                System.out.println("최종 저장된 연산결과 : " + calculator.getResults());
                System.out.println("====== 사칙연산 계산기 종료 =====");
                break;
            }
        }
        sc.close(); // 입력 닫기
    }

    /**
     * 양의 정수 입력 받는 메소드
     *
     * @param sc    Scanner 객체
     * @param order 순서 ("첫 번째", "두 번째")
     * @return 입력 받은 양의 숫자
     */
    private static Number getIsNumber(Scanner sc, String order) {
        while (true) {
            System.out.print(order + " 숫자를 입력하세요 (정수 or 실수) : ");
            // 입력한 숫자값이 정수라면
            if (sc.hasNextInt()) {
                int numInt = sc.nextInt();
                return numInt; // Integer 반환
            // 입력한 숫자값이 실수라면
            } else if (sc.hasNextDouble()) {
                double numDouble = sc.nextDouble();
                return numDouble; // Double 반환
            } else {
                System.out.println("올바른 숫자만 입력해주세요.");
                sc.next(); // 잘못된 입력 제거
            }
        }
    }

    /**
     * 사칙연산 결과문 삭제 처리 여부 메소드
     *
     * @param sc         Scanner 객체
     * @param calculator ArithmeticCalculator 객체
     */
    private static void deleteLoopResults(Scanner sc, ArithmeticCalculator calculator) {
        boolean notice = false; // 최초 결과값 리스트 및, 가장 먼저 삭제됨을 알리는 안내문 출력여부

        // 삭제 반복문(무한 루프)
        while (true) {

            // 삭제시에 최초 안내 사항
            if (!notice) {
                // getter로 리스트를 가져오기
                System.out.println("현재 저장된 연산결과 : " + calculator.getResults());
                System.out.println("가장 먼저 저장된 값만 삭제할 수 있습니다.");

                // 결과값 삭제 여무
                System.out.print("삭제하시겠습니까? (no 입력시 종료) : ");
                String removeValue = sc.next();
                if (removeValue.equalsIgnoreCase("no")) {
                    break;
                }

                notice = true; // 이후에는 재반복 안되도록 설정
            }

            // 저장된 연산결과가 없을 경우
            if (calculator.getResults().isEmpty()) {
                System.out.println("저장된 연산결과가 없습니다.");
                break;
            }


            // 결과리스트중 가장 먼저 저장된 값 삭제 - removeResult()
            calculator.removeResult();
            // setter를 활용하여 변경된 결과값 삽입
            calculator.setResults(calculator.getResults());

            // 계속 삭제할지 물어보기
            System.out.println("삭제 후 저장된 연산결과 : " + calculator.getResults());
            System.out.print("계속 삭제하시겠습니까? (no 입력시 종료) : ");
            String continueRemove = sc.next();

            if (continueRemove.equalsIgnoreCase("no")) {
                break;
            }
        }
    }
}