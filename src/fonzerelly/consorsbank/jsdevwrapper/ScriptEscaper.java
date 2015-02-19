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

	public ScriptEscaper newlineToMultiline() {
		// TODO Auto-generated method stub
		return null;
	}

	private static String buildTag(String newlineTag) {
		String startTag = "/*ScriptEscaper:";
		String endTag = "*/";
		return startTag + newlineTag + endTag;
	}
	public static String createNewlinePlaceholder(String newlineType) {
		if (newlineType == "\n") {
			return buildTag("n");
		} else if (newlineType == "\r\n") {
			return buildTag("rn");
		}
		throw new Error("Unknown Line Ending");
	}

	
}
