package fonzerelly.consorsbank.jsdevwrapper;

public class ScriptManipulator {

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

	public ScriptManipulator() {
		super();
	}

	public static String resolveNewlinePlaceholder(String newlinePlaceholder) {
		if (newlinePlaceholder.equals(buildTag("n"))) {
			return "\n";
		} else if (newlinePlaceholder.equals(buildTag("rn"))) {
			return "\r\n";
		}
		throw new Error("Unknown Newline Placeholder");
	}

}