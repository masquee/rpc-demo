package masquee.thrift.server;

import lombok.extern.slf4j.Slf4j;
import masquee.thrift.api.TEchoService;
import masquee.thrift.server.core.AbstractServer;
import masquee.thrift.service.EchoService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadPoolServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TTransportException;

@Slf4j
public class ThreadPoolServer extends AbstractServer {

    private final static int SERVER_PORT = 7099;


    @Override
    public TProtocolFactory getProtocolFactory() {
        log.info("====> use TBinaryProtocol...");
        return new TBinaryProtocol.Factory(true, true);
    }

    @Override
    public TServer getTServer() {
        TThreadPoolServer.Args args;
        try {
            args = new TThreadPoolServer.Args(new TServerSocket(SERVER_PORT));
        } catch (TTransportException e) {
            throw new RuntimeException(e);
        }
        args.processor(new TEchoService.Processor(new EchoService()));
        args.protocolFactory(getProtocolFactory());
        return new TThreadPoolServer(args);
    }


    public static void main(String[] args) {
        ThreadPoolServer server = new ThreadPoolServer();
        server.startServer();
    }

}
