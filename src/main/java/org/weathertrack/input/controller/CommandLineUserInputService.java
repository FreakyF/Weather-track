package org.weathertrack.input.controller;

import org.weathertrack.input.model.UserInputService;

import java.util.Scanner;

@SuppressWarnings("java:S106")
public class CommandLineUserInputService implements UserInputService {
	private final Scanner scanner;

	public CommandLineUserInputService(Scanner scanner) {
		this.scanner = scanner;
	}

	@Override
	public String getUserInput(String message) {
		System.out.print(message);
		return scanner.nextLine().trim();
	}
}
