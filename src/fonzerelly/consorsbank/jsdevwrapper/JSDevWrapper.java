package fonzerelly.consorsbank.jsdevwrapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Scanner;

public class JSDevWrapper {

	private static Scanner scanner;

	public static void main(String[] args) throws IOException {
		System.out.println("JSDevWrapper");
		String inputName = args[0];
		String outputName = args[1];
		String[] tags = Arrays.copyOfRange(args, 2, args.length);
		scanner = new Scanner(new File(inputName));
		String script = scanner.useDelimiter("\\Z").next();
		System.out.println("Read: " + inputName);
		ScriptEscaper input = new ScriptEscaper(script);
		input
			.escapeQuotes()
			.newlineToMultiline();
		//System.out.println(input.toString());
		JSDevEvaluator evaluator = new JSDevEvaluator();
		ScriptConcatenator output = new ScriptConcatenator(evaluator.evaluate(input.toString(), tags));
		output.multilineToNewline();
		PrintWriter out = new PrintWriter(outputName);
		out.println(output.toString());
		out.close();
		System.out.println("Wrote: " +  outputName);
	}

}
