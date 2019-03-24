namespace java masquee.thrift.api

struct TMessage{
    1: string content
    2: string senderName
    3: i64 timestamp
}

service TEchoService{
    string echo(1: TMessage message)
}