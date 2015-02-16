package fonzerelly.consorsbank.jsdevwrapper;

public class ScriptEscaper {
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
	
}
