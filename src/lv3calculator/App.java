package lv3calculator;

import lv2calculator.Calculator;

import java.util.List;
import java.util.Scanner;

/**

Lv.3 Enum, 제네릭, 람다&스트림을 이해한 계산기 만들기

 [요약]
 a. 양의 정수만 받을 수 있었지만, 이제부터는 실수도 받을 수 있게 수정한다.
 b. 결과가 저장되어 있는 컬렉션을 조회하는 기능을 만든다.
 c. 그때 특정 값보다 큰 결과값을 출력할 수 있도록 한다.

 1) Enum 타입을 활용하여 연산자 타입에 대한 정보를 관리하고 이를 사칙연산 계산기 ArithmeticCalculator 클래스 활용

 */
public class App {
    public static void main(String[] args) {
        // ArithmeticCalculator 객체로 변경
        // 코드 유지를 위해 변수 이름은 동일하게 유지
        ArithmeticCalculator calculator = new ArithmeticCalculator();
        Scanner sc = new Scanner(System.in);

        while(true) { // 전체 계산 반복
            System.out.println("====== 사칙연산 계산기 ======");

            // 입력 받은 수 저장할 변수 선언
            int num1, num2;
            // 연산자 저장할 변수 선언
            char operator;
            // 연산 결과 저장할 변수 선언
            int result;

            // 첫 번째 숫자 입력
            while (true) {
                System.out.print("첫 번째 숫자를 입력하세요 (0 이상): ");
                num1 = sc.nextInt();
                // 양의 정수일때만 반복문 탈출
                if (num1 >= 0) {
                    break;
                }
                System.out.println(" 0 이상의 양의 정수를 입력해야 합니다.");
            }

            // 두 번째 숫자 입력
            for (;;) {
                System.out.print("두 번째 숫자를 입력하세요 (0 이상): ");
                num2 = sc.nextInt();
                if (num2 >= 0) {
                    break;
                }
                System.out.println(" 0 이상의 양의 정수를 입력해야 합니다.");
            }

            System.out.print("사칙연산 기호를 입력하세요 : ");
            operator = sc.next().charAt(0);


            // Calculator 객체를 이용해서 연산수행
            // 잘못된 나눗셈 및 잘못된 연산자 오류 발생할 수 있어서 try-catch 구문 사용
            try {
                // 연산 정상 실행
                result = calculator.calculate(num1, num2, operator);
                System.out.println("결과: " + result);
            } catch (Exception e) {
                // 잘못된 나눗셈 및 잘못된 연산자 사용시 오류 문구 출력
                System.out.println(e.getMessage());
                // 무한 루프를 위해 continue; 적용
                continue;
            }

            // 반복 여부 확인 (exit 입력해야만 계산기 종료)
            System.out.print("더 계산하시겠습니까? (exit 입력 시 종료) : ");
            String exit = sc.next(); // 종료할 문자열 입력



            // Calculator 클래스에서 구현한 컬렉션 필드의 getter, setter와 컬렉션 삭제 메소드 활용
            if(exit.equals("exit")) {
                System.out.println("====== 사칙연산 종료 =======");

                boolean notice = false; // 최초 결과값 리스트 및, 가장 먼저 삭제됨을 알리는 안내문 출력여부

                // 삭제 반복문(무한 루프)
                while (true) {
                    // getter로 리스트를 가져오기
                    List<Integer> calculatorResults = calculator.getResults();

                    // 삭제시에 최초 안내 사항
                    if(!notice) {
                        System.out.println("현재 저장된 연산결과 : " + calculatorResults);
                        System.out.println("가장 먼저 저장된 값만 삭제할 수 있습니다.");

                        // 결과값 삭제 여무
                        System.out.print("삭제하시겠습니까? (no 입력시 종료) : ");
                        String removeValue = sc.next();
                        if (removeValue.equals("no")) {
                            break;
                        }

                        notice = true; // 이후에는 재반복 안되도록 설정
                    }

                    // 저장된 연산결과가 없을 경우
                    if(calculatorResults.isEmpty()) {
                        System.out.println("저장된 연산결과가 없습니다.");
                        break;
                    }


                    // 결과리스트중 가장 먼저 저장된 값 삭제 - removeResult()
                    calculator.removeResult();
                    // setter를 활용하여 변경된 결과값 삽입
                    calculator.setResults(calculatorResults);

                    // 계속 삭제할지 물어보기
                    System.out.println("삭제 후 저장된 연산결과 : " + calculatorResults);
                    System.out.print("계속 삭제하시겠습니까? (no 입력시 종료) : ");
                    String continueRemove = sc.next();

                    if (continueRemove.equals("no")) {
                        break;
                    }
                }

                // 최종 저장된 결과 리스트 및 계산기 종료
                System.out.println("최종 저장된 연산결과 : " + calculator.getResults());
                System.out.println("====== 사칙연산 게산기 종료 =====");
                break;
            }
        }
        sc.close(); // 입력 닫기
    }
}