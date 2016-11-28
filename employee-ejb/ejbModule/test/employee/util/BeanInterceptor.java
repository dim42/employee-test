package test.employee.util;

import static java.util.logging.Level.INFO;
import static test.employee.util.LogHelper.log;

import java.util.logging.Logger;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class BeanInterceptor {

    @AroundInvoke
    public Object logMethod(InvocationContext context) {
        Logger logger = Logger.getLogger(context.getTarget().getClass().getName());
        Object result = null;
        try {
            Object[] params = context.getParameters();
            System.out.println("params:" + params);
            log(logger, INFO, "params... {0}", params);
            StringBuilder builder = new StringBuilder();
            if (params != null) {
                for (int j = 0; j < params.length; j++) {
                    builder.append("{" + j + "}");
                }
            }
            log(logger, INFO, builder.insert(0, "start... ").toString(), params);
            result = context.proceed();
            return result;
        } catch (Exception ex) {
            log(logger, INFO, "catch... {0}", ex);
            throw new RuntimeException(ex);
        } finally {
            log(logger, INFO, "finally... {0}", result);
        }
    }
}
