package fonzerelly.consorsbank.jsdevwrapper.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import fonzerelly.consorsbank.jsdevwrapper.ScriptManipulator;

public class TestScriptManipulator {

	public TestScriptManipulator() {
		super();
	}

	@Test
	public void shouldCreatePlaceholderForSpecificNewline() {
		String linuxStylePlaceholder = "/*ScriptEscaper:n*/";
		assertEquals(ScriptManipulator.createNewlinePlaceholder("\n"), linuxStylePlaceholder);
		String windowsStylePlaceholder = "/*ScriptEscaper:rn*/";
		assertEquals(ScriptManipulator.createNewlinePlaceholder("\r\n"), windowsStylePlaceholder);
	}

	@Test
	public void shouldResolvePlaceholderForSpecificNewLine() {
		String linuxStyleNewline = "\n";
		assertEquals(ScriptManipulator.resolveNewlinePlaceholder("/*ScriptEscaper:n*/"), linuxStyleNewline);
		String windowsStyleNewline = "\r\n";
		assertEquals(ScriptManipulator.resolveNewlinePlaceholder("/*ScriptEscaper:rn*/"), windowsStyleNewline);

	}

}
