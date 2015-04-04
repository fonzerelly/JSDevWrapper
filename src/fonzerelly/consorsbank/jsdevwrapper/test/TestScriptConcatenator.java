package fonzerelly.consorsbank.jsdevwrapper.test;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TestScriptConcatenator extends TestScriptManipulator {
	@Test
	public void itShouldReplaceNewLinePlaceholderWithProperNewLine() {
		String scriptWithPlaceholders = "var/*ScriptEscaper:rn*/k = 42,/*ScriptEscaper:n*/timesTwo=double(k);";
		String resolvedScript = "var\r\n" +
		                        "k = 42,\n" +
				                "timesTwo=double(k);";
		ScriptConcatenator concatenator = new ScriptConcatenator(scriptWithPlaceholders);
		concatenator.multilineToNewline();
		assertEquals(concatenator.toString(), resolvedScript);
	}
}
