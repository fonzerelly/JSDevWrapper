package fonzerelly.consorsbank.jsdevwrapper;

public class ScriptEscaper extends ScriptManipulator {
	private String script = "";
	public ScriptEscaper(String script) {
		this.script = script;
	}
	
	@Override	
	public String toString () {
		return this.script;
	}


	public ScriptEscaper escapeQuotes() {
		this.script = this.script
				.replace("\"", "\\\"")
				.replace("'", "\\'");
		return this;
	}

	private ScriptEscaper replaceNewLine(String newlineType) {
		this.script = this.script.replace(newlineType, createNewlinePlaceholder(newlineType) + "\" + \"");
		return this;
	}

	public ScriptEscaper newlineToMultiline() {
		replaceNewLine("\r\n");
		replaceNewLine("\n");
		return this;
	}
}
