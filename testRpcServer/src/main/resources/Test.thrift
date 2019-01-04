namespace java com.sefonsoft.test.service.rpc

struct TestResult {
    1:required  i32 code;
	
    2:required  string desc;
	
    3:required  string data;
}

service TestRpcService {
    TestResult addDoc(1: string str);
}

