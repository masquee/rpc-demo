package masquee.thrift.service;

import lombok.extern.slf4j.Slf4j;
import masquee.thrift.api.TEchoService;
import masquee.thrift.api.TMessage;
import org.apache.thrift.TException;

@Slf4j
public class EchoService implements TEchoService.Iface {

    @Override
    public String echo(TMessage message) throws TException {
        log.info("====> message: {}", message);
        return message.content;
    }

}
