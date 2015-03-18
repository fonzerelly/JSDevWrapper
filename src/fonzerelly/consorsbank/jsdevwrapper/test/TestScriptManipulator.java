package fonzerelly.consorsbank.jsdevwrapper.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fonzerelly.consorsbank.jsdevwrapper.ScriptEscaper;

public class TestScriptManipulator {

	public TestScriptManipulator() {
		super();
	}

	@Test
	public void shouldCreatePlaceholderForSpecificNewline() {
		String expectedResult = "/*ScriptEscaper:n*/";
		assertEquals(ScriptEscaper.createNewlinePlaceholder("\n"), expectedResult);
		expectedResult = "/*ScriptEscaper:rn*/";
		assertEquals(ScriptEscaper.createNewlinePlaceholder("\r\n"), expectedResult);
	}

}