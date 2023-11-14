package hello.trace.template;

import hello.trace.TraceStatus;
import hello.trace.logtrace.LogTrace;

public abstract class AbstractTemplate<T> {
    private LogTrace logTrace;

    public AbstractTemplate(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    public T execute(String message) {
        TraceStatus status = null;
        try {
            status = logTrace.begin(message);
            T call = call();
            logTrace.end(status);
            return call;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
    protected abstract T call();
}
