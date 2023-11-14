package hello.proxy.config.v1_proxy.interface_proxy;

import com.fasterxml.jackson.databind.type.LogicalType;
import hello.proxy.app.v1.OrderRepositoryV1;
import hello.trace.TraceStatus;
import hello.trace.logtrace.LogTrace;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderRepositoryInterfaceProxy implements OrderRepositoryV1 {
    private final OrderRepositoryV1 orderRepositoryV1;
    private final LogTrace logTrace;
    @Override
    public void save(String itemId) {
        TraceStatus status = null;
        try {
            status = logTrace.begin("OrderRepository.save()");
            orderRepositoryV1.save(itemId);
            logTrace.end(status);
        } catch (Exception e) {
            logTrace.exception(status, e);
            throw e;
        }
    }
}
