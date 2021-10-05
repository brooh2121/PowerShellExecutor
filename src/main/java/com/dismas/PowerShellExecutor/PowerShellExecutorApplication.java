package com.dismas.PowerShellExecutor;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.util.Arrays;


@SpringBootApplication
public class PowerShellExecutorApplication implements CommandLineRunner {

	String cmd = "schtasks /query /fo LIST";

	public static void main(String[] args) {
		SpringApplication.run(PowerShellExecutorApplication.class, args);
	}

	@Override
	public void run(String... args) throws IOException {
		Process process = Runtime.getRuntime().exec(cmd);
		BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream(),"windows-1251"));
		String bufferBool;
		String allTasksStr = "";
		String [] strs;
		FileWriter writer = new FileWriter("D:\\idea_projects\\PowerShellExecutor\\test.txt");
		BufferedWriter bw = new BufferedWriter(writer);
		while ((bufferBool = stdInput.readLine()) != null) {
			allTasksStr = allTasksStr + bufferBool + " \n";
		}
		stdInput.close();
		strs = allTasksStr.split("\\n\\s");
		for (String str : strs) {
			bw.write(str);
			bw.newLine();
		}

	}

}
