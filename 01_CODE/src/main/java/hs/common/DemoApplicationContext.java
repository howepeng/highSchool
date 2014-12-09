package hs.common;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DemoApplicationContext implements ApplicationContextAware {

    /**
     * AcpplitionContext.
     */
    private static ApplicationContext context = null;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        context = applicationContext;
    }

    /**
     * BEAN
     *
     * @param id BEAN_ID
     * @return BEAN
     */
    public static Object getBean(String id) {
        return context.getBean(id);
    }
}
