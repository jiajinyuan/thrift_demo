package com.sefonsoft.test.service.rpc.client;

import com.sefonsoft.test.service.rpc.TestResult;
import com.sefonsoft.test.service.rpc.TestRpcService;
import org.apache.thrift.async.AsyncMethodCallback;
import org.apache.thrift.async.TAsyncClientManager;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.protocol.TProtocolFactory;
import org.apache.thrift.transport.TNonblockingSocket;
import org.apache.thrift.transport.TNonblockingTransport;

import static java.lang.System.out;

/**
 * <p>Description: TODO.</p>
 * <p>Copyright: Copyright(c) 2020.</p>
 * <p>Company: Sefonsoft.</p>
 * <p>CreateTime: 2019/1/4.</p>
 *
 * @author Junfeng
 * @version 1.0
 */
public class RpcAsyncClient {

    public static void main(String[] args) {
        try {
            TNonblockingTransport tTransport = new TNonblockingSocket("10.0.2.99", 30001, 5000);
            TProtocol protocol = new TBinaryProtocol(tTransport);

            TProtocolFactory factory = new TCompactProtocol.Factory();
            TAsyncClientManager manager = new TAsyncClientManager();

            TestRpcService.AsyncClient client = new TestRpcService.AsyncClient(factory, manager, tTransport);

            final String str = "3333Q";
            client.addDoc(str, new AsyncMethodCallback<TestResult>() {
                public void onComplete(TestResult testResult) {
                    out.println(" -[DEBUG]-  OK: " + System.currentTimeMillis() + "  " + str);
                    out.println(" -[DEBUG]-  : " + testResult.desc);
                }

                public void onError(Exception e) {
                    out.println(" -[DEBUG]-  NO: " + System.currentTimeMillis() + "  " + str);
                    e.printStackTrace();
                }
            });
            out.println(" -[DEBUG]-  : " + System.currentTimeMillis());
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
