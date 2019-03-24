package masquee.thrift.server;

import lombok.extern.slf4j.Slf4j;
import masquee.thrift.api.TEchoService;
import masquee.thrift.server.core.AbstractServer;
import masquee.thrift.service.EchoService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

@Slf4j
public class NonblockingServer extends AbstractServer {

    private final static int SERVER_PORT = 7099;


    @Override
    public TProtocolFactory getProtocolFactory() {
        log.info("====> use TBinaryProtocol...");
        return new TBinaryProtocol.Factory();
    }

    @Override
    public TServer getTServer() {
        TNonblockingServer.Args args;
        try {
            args = new TNonblockingServer.Args(new TNonblockingServerSocket(SERVER_PORT));
        } catch (TTransportException e) {
            throw new RuntimeException(e);
        }
        TFramedTransport.Factory transportFactory = new TFramedTransport.Factory();
        args.processor(new TEchoService.Processor(new EchoService()));
        args.protocolFactory(getProtocolFactory());
        args.transportFactory(transportFactory);
        return new TNonblockingServer(args);
    }


    public static void main(String[] args) {
        NonblockingServer server = new NonblockingServer();
        server.startServer();
    }

}
