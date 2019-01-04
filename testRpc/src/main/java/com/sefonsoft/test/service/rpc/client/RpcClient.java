package com.sefonsoft.test.service.rpc.client;

import com.sefonsoft.test.service.rpc.TestResult;
import com.sefonsoft.test.service.rpc.TestRpcService;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

/**
 * <p>Description: TODO.</p>
 * <p>Copyright: Copyright(c) 2020.</p>
 * <p>Company: Sefonsoft.</p>
 * <p>CreateTime: 2019/1/4.</p>
 *
 * @author Junfeng
 * @version 1.0
 */
public class RpcClient {

    public static void main(String[] args) {
        try {
            TTransport tTransport = getTTransport();
            TProtocol protocol = new TBinaryProtocol(tTransport);
            TestRpcService.Client client = new TestRpcService.Client(protocol);
            TestResult result = client.addDoc("abc");
            System.out.println("code=" + result.code + " msg=" + result.desc);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static TTransport getTTransport() throws Exception {
        try {
            TTransport tTransport = getTTransport("10.0.2.99", 30001, 5000);
            if (!tTransport.isOpen()) {
                tTransport.open();
            }
            return tTransport;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static TTransport getTTransport(String host, int port, int timeout) {
        final TSocket tSocket = new TSocket(host, port, timeout);
        final TTransport transport = new TFramedTransport(tSocket);
        return transport;
    }
}
