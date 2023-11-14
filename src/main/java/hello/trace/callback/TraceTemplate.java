package hello.trace.callback;


import hello.trace.TraceStatus;
import hello.trace.logtrace.LogTrace;

public class TraceTemplate{
    private final LogTrace logTrace;

    public TraceTemplate(LogTrace logTrace) {
        this.logTrace = logTrace;
    }

    public <T> T execute(String message, TraceCallback<T> traceCallback) {
        TraceStatus status = null;
        try {
            status = logTrace.begin(message);
            T call = traceCallback.call();
            logTrace.end(status);
            return call;
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
