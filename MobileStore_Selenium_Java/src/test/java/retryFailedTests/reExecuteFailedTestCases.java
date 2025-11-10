package retryFailedTests;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class reExecuteFailedTestCases implements IRetryAnalyzer {

	
	int count =0;
	int maxTry =2;
	@Override
	public boolean retry(ITestResult result) {
		// TODO Auto-generated method stub
		
		if(count<maxTry)
		{
			count++;
			return true;
		}
		
		
		return false;
	}

}
