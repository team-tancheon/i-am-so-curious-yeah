package edu.spring.security.response;

/**
 * 참고
 * https://cheese10yun.github.io/spring-guide-exception/
 *
 * RESTful 서비스 설계와 개발 - 메시지와 예외 처리
 * https://eblo.tistory.com/48
 *
 * Spring Boot, REST API 예외 응답 구조 설계하기
 * https://jsonobject.tistory.com/501
 *
 * Spring Boot, ExceptionHandler, 전역 예외 처리 로직 작성하기
 * https://javafactory.tistory.com/1560?category=618813
 */
public enum ErrorResponse {

    TEST(200,"","");

    private int status;
    private String code;
    private String message;

    ErrorResponse(int status, String code, String message) {
        this.status = status;
        this.code = code;
        this.message = message;
    }
//    public static ErrorResponse test= new ErrorResponse(200, "dsf","dfs");
}
