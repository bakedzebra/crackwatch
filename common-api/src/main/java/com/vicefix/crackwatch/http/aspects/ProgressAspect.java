package com.vicefix.crackwatch.http.aspects;

import com.vicefix.crackwatch.http.annotations.ProgressJoinPoint;
import com.vicefix.crackwatch.http.annotations.ProgressStart;
import com.vicefix.crackwatch.utils.InfiniteProgressBar;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

import static com.vicefix.crackwatch.utils.CommonUtils.cast;

@Aspect
@Component
public class ProgressAspect {
    private final ApplicationContext context;

    public ProgressAspect(ApplicationContext context) {
        this.context = context;
    }

    @After("@annotation(com.vicefix.crackwatch.http.annotations.ProgressStart)")
    public void startProgressBar(JoinPoint joinPoint) throws NoSuchMethodException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget()
                .getClass()
                .getMethod(signature.getMethod().getName(),
                        signature.getMethod().getParameterTypes());

        ProgressStart progressStart = method.getAnnotation(ProgressStart.class);

        InfiniteProgressBar progressBar = new InfiniteProgressBar(progressStart.title(), progressStart.extraMessage());

        ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) this.context).getBeanFactory();
        beanFactory.registerSingleton(InfiniteProgressBar.class.getCanonicalName(), progressBar);
    }

    @After("@annotation(com.vicefix.crackwatch.http.annotations.ProgressEnd)")
    public void stopProgressBar() {
        InfiniteProgressBar progressBar = cast(this.context.getBean(InfiniteProgressBar.class.getCanonicalName()));

        ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) this.context).getBeanFactory();
        beanFactory.destroyBean(progressBar);
    }

    @After("@annotation(com.vicefix.crackwatch.http.annotations.ProgressJoinPoint)")
    public void joinProgress(JoinPoint joinPoint) throws NoSuchMethodException {
        InfiniteProgressBar progressBar = cast(this.context.getBean(InfiniteProgressBar.class.getCanonicalName()));

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = joinPoint.getTarget()
                .getClass()
                .getMethod(signature.getMethod().getName(),
                        signature.getMethod().getParameterTypes());

        ProgressJoinPoint progressJoinPoint = method.getAnnotation(ProgressJoinPoint.class);

        progressBar.update(progressJoinPoint.stepTo());
    }

}
