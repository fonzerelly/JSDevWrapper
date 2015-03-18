package fonzerelly.consorsbank.jsdevwrapper.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import fonzerelly.consorsbank.jsdevwrapper.*;

public class TestJSDevEvaluator {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void shouldProvideJSDevCore() throws IOException {
		JSDevEvaluator evaluator = new JSDevEvaluator();
		assertTrue(evaluator.toString().startsWith("// jsdev.js"));		
	}
	
	@Test
	public void shouldAppendJSDevCall() throws IOException {
		JSDevEvaluator evaluator = new JSDevEvaluator();
		String script = "/*test*/";
		String[] tags = {"test"};
		evaluator.appendCall(script, tags);
		String expectedAppendedCall = "JSDEV(\"/*test*/\", [\"test\"]);";
		assertTrue(evaluator.toString().endsWith(expectedAppendedCall));
	}
	
	@Test
	public void shouldAppendJSDevCallWithMultibleTags() throws IOException {
		JSDevEvaluator evaluator = new JSDevEvaluator();
		String script = "/*test*/";
		String[] tags = {"test", "debug"};
		evaluator.appendCall(script, tags);
		String result = "JSDEV(\"/*test*/\", [\"test\", \"debug\"]);";
		assertTrue("Resulting call not as expected", evaluator.toString().endsWith(result));
	}
	
	//@Test
	public void shouldReturnEvaluatedScript() throws IOException {
		JSDevEvaluator evaluator = new JSDevEvaluator();
		String script = "/*test*/";
		String[] tags = {"test"};
		evaluator.appendCall(script, tags);
		String expectedScriptResult = "{test};";
		assertEquals(evaluator.evaluate(), expectedScriptResult);
	}

}
