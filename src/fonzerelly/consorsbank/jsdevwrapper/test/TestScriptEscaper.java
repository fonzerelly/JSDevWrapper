package fonzerelly.consorsbank.jsdevwrapper.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import fonzerelly.consorsbank.jsdevwrapper.ScriptEscaper;



public class TestScriptEscaper {
	class NewLineStringBuilder {
		private String newLineMarker;
		private List<String> lines;
		public NewLineStringBuilder() {
			this("\n");
		}
		public NewLineStringBuilder(String newLineMarker) {
			this.newLineMarker = newLineMarker;
			this.lines = new ArrayList<String>();
		}
		public NewLineStringBuilder append(String line) {
			this.lines.add(line);
			return this;
		}
		public String toString() {
			StringBuilder buffer = new StringBuilder();
			for(String line:this.lines) {
				buffer.append(line + this.newLineMarker);
			}
			return buffer.toString();
		}
		public NewLineStringBuilder setMarker(String marker) {
			this.newLineMarker = marker;
			return this;
		}
	}
	
	NewLineStringBuilder builder;

	
	@Before
	public void setUp() {
		builder =  new NewLineStringBuilder("\n")
 		.append("function test () {")
 		.append("	console.log(\"test\");")
 		.append("}")
 		.append("module.exports = {")
 		.append("    moduleFunction = function () {")
 		.append("        console.log('production');")
 		.append("        /*test test();*/")
 		.append("    }")
 		.append("};");
	}
	
	@Test
	public void shouldExtractStringAsPassedIntoWhenNotEscaped() {
	 	builder.setMarker("\r\n");
		ScriptEscaper scriptEscaper = new ScriptEscaper(builder.toString());
		assertEquals(scriptEscaper.toString(), builder.toString());
	}
	
	@Test
	public void shouldEscapeDoubleQuotes() {
		ScriptEscaper scriptEscaper = new ScriptEscaper(builder.toString())
			.escapeQuotes();
		String result = builder.toString()
				.replace("\"", "\\\"")
				.replace("'", "\\'");
		
		assertEquals(scriptEscaper.toString(), result);
	}

	@Test
	public void shouldCreatePlaceholderForSpecificNewline() {
		String expectedResult = "/*ScriptEscaper:n*/";
		assertEquals(ScriptEscaper.createNewlinePlaceholder("\n"), expectedResult);
		expectedResult = "/*ScriptEscaper:rn*/";
		assertEquals(ScriptEscaper.createNewlinePlaceholder("\r\n"), expectedResult);
	}

	@Test
	public void shouldConvertNewLinesToValidMultilineString() {
		builder.setMarker("\n");
		ScriptEscaper scriptEscaper = new ScriptEscaper(builder.toString())
			.newlineToMultiline();
		String result = builder.toString()
				.replace("\n", ScriptEscaper.createNewlinePlaceholder("\n")+"\" + \"");
		assertEquals(result, scriptEscaper.toString());

	}

}
