package fonzerelly.consorsbank.jsdevwrapper.test;

import fonzerelly.consorsbank.jsdevwrapper.ScriptManipulator;

public class ScriptConcatenator extends ScriptManipulator {
	public ScriptConcatenator(String script) {
		this.script = script;
	}
	
	public String toString() {
		return this.script;
	}

	private String script;

	private void resolveNewlinePlaceholders(String newlineType) {
		this.script = this.script.replace(createNewlinePlaceholder(newlineType), newlineType);
	}
	
	public void multilineToNewline() {
		resolveNewlinePlaceholders("\n");
		resolveNewlinePlaceholders("\r\n");
	}
	
}
