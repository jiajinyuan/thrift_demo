package com.sefonsoft.test.service.rpc.impl;

import com.sefonsoft.test.service.rpc.TestResult;
import com.sefonsoft.test.service.rpc.TestRpcService;
import org.apache.thrift.TException;

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
public class RpcServiceImpl implements TestRpcService.Iface {
    @Override
    public TestResult addDoc(String str) throws TException {
        out.println(" -[DEBUG]-  : " + System.currentTimeMillis() + "  " + str);
        TestResult result = new TestResult();
        result.setCode(1);
        result.setDesc("success");
        return result;
    }
}
