package com.sefonsoft.test.service.rpc.impl;

import com.sefonsoft.test.service.rpc.TestResult;
import com.sefonsoft.test.service.rpc.TestRpcService;
import org.apache.thrift.TException;
import org.apache.thrift.async.AsyncMethodCallback;

import static java.lang.System.out;

/**
 * <p>Description: TODO.</p>
 * <p>Copyright: Copyright(c) 2020.</p>
 * <p>Company: Sefonsoft.</p>
 * <p>CreateTime: 2019/1/4.</p>
 *
 * @author SF2121
 * @version 1.0
 */
public class RpcAsyncServiceImpl implements TestRpcService.AsyncIface {

    @Override
    public void addDoc(String str, AsyncMethodCallback<TestResult> resultHandler) throws TException {
        try {
            out.println(" -[DEBUG]-  Async : " + System.currentTimeMillis() + "  " + Integer.parseInt(str));
            TestResult result = new TestResult();
            result.setCode(1);
            result.setDesc("success");
            result.setData("ok");
            resultHandler.onComplete(result);
            out.println(" -[DEBUG]-   OK ------------------------------");
        }catch (Exception e){
            out.println(" -[DEBUG]-   NO ------------------------------");
            resultHandler.onError(e);
        }
    }
}
