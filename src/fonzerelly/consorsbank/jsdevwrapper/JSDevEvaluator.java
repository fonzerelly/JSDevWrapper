package fonzerelly.consorsbank.jsdevwrapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;



public class JSDevEvaluator {
	private String jsdev;
	public JSDevEvaluator () throws IOException {
		StringBuffer sbuf = new StringBuffer();
		StringBuilder sbuild = new StringBuilder(sbuf);
		InputStream in = JSDevEvaluator.class.getResourceAsStream("jsdev.js");
		InputStreamReader inReader = new InputStreamReader(
			in
		);
		BufferedReader reader = new BufferedReader(inReader);
		int letter = reader.read();
		while(letter != -1) {
			sbuild.append((char) letter);
			letter = reader.read();
		}
		this.jsdev = sbuild.toString();
	}
	
	public String toString() {
		return this.jsdev;
	}

	public JSDevEvaluator readJSDev () {
		return this;
	}
	
	protected String concatTagArray(String[] tags) {
		String result = "";
		int tagsLeft = tags.length - 1;
		for(String tag : tags) {
			result += "\"" + tag + "\"";
			
			if(tagsLeft > 0) {
				result += ", ";
			}
			tagsLeft--;
		}
		return "[" + result + "]";
	}

	protected String createCall(String script, String[] tags) {
		String result = "JSDEV(\"" + script + "\", ";
		result += concatTagArray(tags);
		result += ");";
		return result;
	}

	public String evaluate(String script, String[] tags) {
		String setup = jsdev + createCall(script, tags);
		Context cx = Context.enter();
		String result;
		try {
			Scriptable scope = cx.initStandardObjects();
			Object resultObj = cx.evaluateString(scope, setup, "jsdev", 1, null);
			result = Context.toString(resultObj);
		} finally {
			Context.exit();
		}
		return result;
	};
}