package fonzerelly.consorsbank.jsdevwrapper.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import fonzerelly.consorsbank.jsdevwrapper.*;

public class TestJSDevEvaluator {

	class TestableJSDevEvaluator extends JSDevEvaluator {

		public TestableJSDevEvaluator() throws IOException {
			super();
		}

		public String createCall(String script, String[] tags) {
			return super.createCall(script, tags);
		}

		public String concatTagArray(String[] tags) {
			return super.concatTagArray(tags);
		}

	}

	private TestableJSDevEvaluator instance;

	@Before
	public void setUp() throws Exception {
		instance = new TestableJSDevEvaluator();
	}

	@Test
	public void shouldProvideJSDevCore() throws IOException {
		assertTrue(instance.toString().startsWith("// jsdev.js"));
	}

	@Test
	public void shouldConcatArrayOfTagsToJavaScriptArray () {
		String [] tags = {"test", "debug"};
		assertEquals(instance.concatTagArray(tags), "[\"test\", \"debug\"]");
	}

	@Test
	public void shouldCreateJSDevCall(){
		String script = "/*test*/";
		String[] tags = {"test"};
		assertEquals(instance.createCall(script, tags), "JSDEV(\"/*test*/\", [\"test\"]);");
	}

	@Test
	public void shouldReturnEvaluatedScript() throws IOException {
		String script = "/*test test()*/";
		String[] tags = {"test"};
		assertEquals(instance.evaluate(script, tags), "{test()}\n");
	}

}
