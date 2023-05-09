package com.springboot.framework.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AopAdvice {

    /*
     执行顺序：Around -> Before -> AfterReturning -> After -> Around

     尽可能使用满足要求的最低功能的方法增强

     1、前置增强 @Before
     2、后置返回增强 @AfterReturning，执行正常返回时执行，如果需要访问返回的值，使用 returning = "参数" 绑定，它被形参类型限制，
        因此你可以设置为 Object 类型匹配所有
     3、出现异常时执行 @AfterThrowing，使用 throwing = "参数" 绑定异常类型，它被形参类型限制，，需要的时候，
        可以使用 Throwable 作为异常类型
     4、方法运行结束后执行 @After，advice 必须准备好处理正常和异常返回条件。它通常用于释放资源等。
     5、环绕增强 @Around，它的第一个参数必须是 ProceedingJoinPoint，在 advice 主体中，调用 proceed() 会使 ProceedingJoinPoint
        底层方法继续执行（包括 Before、After 继续执行），该proceed方法也可以通过传递一个参数来调用Object[]——数组中的值将在方法执行时用作参数。
    */

    /**
     * execution: 执行时触发，public: 返回值权限修饰符；* 表示通配符 代表所有；
     * <br> 第一个 * : 返回值类型; 第二个 * : 任意类，*(..): 所有方法所有参数；路径上的 .. : 表示匹配当前路径及子路径
     * <br> 指定参数可以通过关键字 args :execution(public * com.springboot.framework.demo.controller..*.*(..)) && args(param)
     */
    @Pointcut("execution(public * com.springboot.framework.demo.controller..*.*(..))")
    public void pointcut() {
    }

    // 前置增强
    @Before("pointcut()")
    public void beforeAdvice() {
        System.out.println("beforeAdvice");
    }

    @AfterReturning(pointcut = "pointcut()", returning = "returning")
    public void afterReturning(String returning) {
        System.out.println("returning = " + returning);
    }

    @AfterThrowing(pointcut = "pointcut()", throwing = "throwable")
    public void afterThrowing(JoinPoint joinPoint, Throwable throwable) {
        System.out.println("afterThrowing joinPoint:" + joinPoint);
        System.out.println("afterThrowing throwable:" + throwable);
    }

    @After("pointcut()")
    public void afterAdvice() {
        System.out.println("afterAdvice");
    }

    @Around("pointcut()")
    public Object aroundAdvice(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("around 开始执行：" + pjp);
        // 执行 proceed() 此处开始执行环绕
        Object proceed = pjp.proceed();
        System.out.println("around 再次执行 comeback...");
        // 返回之后，环绕通知停止，此处可以修改被增强的方法的返回值，但返回类型要相同
        return "proceed";
    }

}
