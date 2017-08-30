package lambdaStudy01;

import java.io.BufferedReader;
import java.io.IOException;

@FunctionalInterface
public interface BufferReaderProcessor {
	public String process(BufferedReader b) throws IOException;
}
