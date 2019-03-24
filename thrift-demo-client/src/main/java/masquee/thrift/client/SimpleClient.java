package masquee.thrift.client;

import lombok.extern.slf4j.Slf4j;
import masquee.thrift.api.TEchoService;
import masquee.thrift.api.TMessage;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

@Slf4j
public class SimpleClient {

    public static final int SERVER_PORT = 7099;
    public static final String SERVER_IP = "localhost";

    public void startClient() {
        TTransport transport;
        try {
            transport = new TSocket(SERVER_IP, SERVER_PORT);

            //协议要和服务端一致
            TProtocol protocol = new TBinaryProtocol(transport);
            TEchoService.Client client = new TEchoService.Client(protocol);
            transport.open();

            String result = client.echo(getMessage());
            log.info("====> response: {}", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private TMessage getMessage() {
        TMessage message = new TMessage();
        message.content = "hello world!";
        message.senderName = "Me";
        message.timestamp = System.currentTimeMillis();
        return message;
    }

    public static void main(String[] args) {
        SimpleClient client = new SimpleClient();
        client.startClient();
    }

}
