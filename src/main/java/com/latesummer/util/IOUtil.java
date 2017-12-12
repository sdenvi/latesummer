package com.latesummer.util;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class IOUtil {
	public static void closeQuietly(Closeable closeable) {
		try {
			if (null == closeable) {
				return;
			}
			closeable.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String readToString(String file) throws IOException {
		BufferedReader crunchifyBufferReader = Files.newBufferedReader(Paths.get(file, new String[0]));
		return (String) crunchifyBufferReader.lines().collect(Collectors.joining());
	}

	public static String readToString(InputStream input) throws IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(input, "UTF-8"));
		Throwable localThrowable3 = null;
		try {
			return (String) buffer.lines().collect(Collectors.joining("\n"));
		} catch (Throwable localThrowable4) {
			localThrowable3 = localThrowable4;
			throw localThrowable4;
		} finally {
			if (buffer != null)
				if (localThrowable3 != null)
					try {
						buffer.close();
					} catch (Throwable localThrowable2) {
						localThrowable3.addSuppressed(localThrowable2);
					}
				else
					buffer.close();
		}
	}

	@SuppressWarnings("resource")
	public static void copyFileUsingFileChannels(File source, File dest) throws IOException {
		FileChannel inputChannel = null;
		FileChannel outputChannel = null;
		try {
			inputChannel = new FileInputStream(source).getChannel();
			outputChannel = new FileOutputStream(dest).getChannel();
			outputChannel.transferFrom(inputChannel, 0L, inputChannel.size());
		} finally {
			assert (inputChannel != null);
			inputChannel.close();
			assert (outputChannel != null);
			outputChannel.close();
		}
	}
}