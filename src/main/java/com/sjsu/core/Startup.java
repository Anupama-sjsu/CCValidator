package com.sjsu.core;

public class Startup {

	public static void main(String[] args) {
		try {
			String inputFileName = args[0];
			String outputFileName = args[1];

			CCApp app = new CCApp(inputFileName, outputFileName);
			app.run();

		} catch (Exception e) {
			System.out.print("Error: " + e);
		}
	}

}
