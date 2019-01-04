package com.sefonsoft.test.service.rpc;

import com.sefonsoft.test.service.rpc.impl.RpcAsyncServiceImpl;
import com.sefonsoft.test.service.rpc.impl.RpcServiceImpl;
import org.apache.thrift.TProcessor;
import org.apache.thrift.TProcessorFactory;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.server.TNonblockingServer;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TThreadedSelectorServer;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TNonblockingServerSocket;
import org.apache.thrift.transport.TTransportException;

import java.net.InetSocketAddress;

/**
 * <p>Description: TODO.</p>
 * <p>Copyright: Copyright(c) 2020.</p>
 * <p>Company: Sefonsoft.</p>
 * <p>CreateTime: 2019/1/4.</p>
 *
 * @author SF2121
 * @version 1.0
 */
public class ThriftServer {
    private final static int DEFAULT_PORT = 30001;
    private final static String DEFAULT_IP = "10.0.2.99";

    public static void main(String[] args) {
        //sync();
        async();
    }
    public static void sync(){
        try {
            TNonblockingServerSocket socket = new TNonblockingServerSocket(new InetSocketAddress(DEFAULT_IP, DEFAULT_PORT));
            TestRpcService.Processor processor = new TestRpcService.Processor(new RpcServiceImpl());
            TNonblockingServer.Args arg = new TNonblockingServer.Args(socket);
            arg.protocolFactory(new TCompactProtocol.Factory());
            arg.transportFactory(new TFramedTransport.Factory());
            arg.processorFactory(new TProcessorFactory(processor));
            new TNonblockingServer(arg).serve();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
    public static void async(){
        try {
            TProcessor processor1 = new TestRpcService.AsyncProcessor<>(new RpcAsyncServiceImpl());

            TNonblockingServerSocket serverTransport = new TNonblockingServerSocket(new InetSocketAddress(DEFAULT_IP, DEFAULT_PORT));
            TThreadedSelectorServer.Args args = new TThreadedSelectorServer.Args(serverTransport);
            args.processor(processor1);
            args.transportFactory(new TFramedTransport.Factory());
            //处理socket网络IO的线程数
            args.selectorThreads(5);
            //处理业务的线程数
            args.workerThreads(10);
            //采用高效密集型二进制协议
            args.protocolFactory(new TCompactProtocol.Factory());
            //采用多线程半同步半异步服务端
            TServer server = new TThreadedSelectorServer(args);
            //启动服务
            new Thread(() -> server.serve()).start();
        } catch (TTransportException e) {
            e.printStackTrace();
        }
    }
}
