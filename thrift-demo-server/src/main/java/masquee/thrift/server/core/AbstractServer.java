package masquee.thrift.server.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;

@Slf4j
public abstract class AbstractServer {

    public abstract TServer getTServer();

    public abstract TProtocolFactory getProtocolFactory();

    public void startServer() {
        try {
            log.info("====> server starting...");
            getTServer().serve();
        } catch (Exception e) {
            log.info("====> server start failed");
            e.printStackTrace();
        }
    }

}
