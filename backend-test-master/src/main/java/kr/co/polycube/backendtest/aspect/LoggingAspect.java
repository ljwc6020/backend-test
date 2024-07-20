package kr.co.polycube.backendtest.aspect;

import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

// @Aspect 애노테이션을 통해 이 클래스가 AOP(Aspect Oriented Programming)에서 사용할 'Aspect'임을 선언합니다.
@Aspect
@Component // @Component 애노테이션을 통해 이 클래스가 Spring의 Bean으로 관리됨을 나타냅니다.
public class LoggingAspect {

    // HttpServletRequest 객체를 주입받습니다. 이를 통해 요청 정보를 접근할 수 있습니다.
    private final HttpServletRequest request;

    // 생성자를 통해 HttpServletRequest 객체를 주입받습니다.
    public LoggingAspect(HttpServletRequest request) {
        this.request = request;
    }

    // @Before 애노테이션은 지정된 메서드가 실행되기 전에 실행될 advice(충고)를 정의합니다.
    // "execution(* kr.co.polycube.backendtest.controller.UserController.*(..))"는
    // UserController 클래스의 모든 메서드를 대상으로 합니다.
    @Before("execution(* kr.co.polycube.backendtest.controller.UserController.*(..))")
    public void logClientAgent(JoinPoint joinPoint) {
        // HttpServletRequest 객체에서 "User-Agent" 헤더 값을 가져옵니다.
        String clientAgent = request.getHeader("User-Agent");
        // 콘솔에 "Client Agent: "와 함께 User-Agent 값을 출력합니다.
        System.out.println("Client Agent: " + clientAgent);
    }
}
