package hs.common.log;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class OperationLogInterceptor implements MethodInterceptor {

    /**
     * LOG.
     */
    private static Log log = LogFactory.getLog(OperationLogInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        if (log.isTraceEnabled()) {
            doLog(invocation, true);
        }

        Object result = invocation.proceed();

        if (log.isTraceEnabled()) {
            doLog(invocation, false);
        }

        return result;
    }

    private void doLog(MethodInvocation invocation, boolean isStart) {

        String mark = null;
        if (isStart) {
            mark = "START";
        } else {
            mark = "END";
        }

        StringBuffer message = new StringBuffer();
        message.append("[");
        message.append(invocation.getThis().getClass().getName());
        message.append(":");
        message.append(mark);
        message.append("]");

        log.trace(message.toString());
    }
}
