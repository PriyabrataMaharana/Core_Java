package com.calculatorapp;

import java.util.Scanner;

class Calculator {
	// Basic Operations
	public double add(double a, double b) {
		return a + b;
	}

	public double subtract(double a, double b) {
		return a - b;
	}

	public double multiply(double a, double b) {
		return a * b;
	}

	public double divide(double a, double b) {
		if (b == 0) {
			throw new ArithmeticException("Cannot divide by zero");
		}
		return a / b;
	}

	public double modulus(double a, double b) {
		return a % b;
	}

	public double power(double base, double exponent) {
		return Math.pow(base, exponent);
	}

	public long factorial(int num) {
		if (num < 0)
			throw new IllegalArgumentException("Factorial not defined for negative numbers");
		long result = 1;
		for (int i = 2; i <= num; i++) {
			result *= i;
		}
		return result;
	}

	public double squareRoot(double num) {
		return Math.sqrt(num);
	}
}

public class CalculatorApp {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Calculator calc = new Calculator();
		boolean running = true;

		while (running) {
			System.out.println("\n--- Java Console Calculator ---");
			System.out.println("\n********************************");
			System.out.println("1. Addition");
			System.out.println("2. Subtraction");
			System.out.println("3. Multiplication");
			System.out.println("4. Division");
			System.out.println("5. Modulus");
			System.out.println("6. Power");
			System.out.println("7. Factorial");
			System.out.println("8. Square Root");
			System.out.println("9. Exit");
			System.out.print("Choose an operation (1-9): ");

			int choice = scanner.nextInt();
			double a, b;
			int n;

			try {
				switch (choice) {
				case 1:
					System.out.print("Enter two numbers: ");
					a = scanner.nextDouble();
					b = scanner.nextDouble();
					System.out.println("Result = " + calc.add(a, b));
					break;
				case 2:
					System.out.print("Enter two numbers: ");
					a = scanner.nextDouble();
					b = scanner.nextDouble();
					System.out.println("Result = " + calc.subtract(a, b));
					break;
				case 3:
					System.out.print("Enter two numbers: ");
					a = scanner.nextDouble();
					b = scanner.nextDouble();
					System.out.println("Result = " + calc.multiply(a, b));
					break;
				case 4:
					System.out.print("Enter two numbers: ");
					a = scanner.nextDouble();
					b = scanner.nextDouble();
					System.out.println("Result = " + calc.divide(a, b));
					break;
				case 5:
					System.out.print("Enter two numbers: ");
					a = scanner.nextDouble();
					b = scanner.nextDouble();
					System.out.println("Result = " + calc.modulus(a, b));
					break;
				case 6:
					System.out.print("Enter base and exponent: ");
					a = scanner.nextDouble();
					b = scanner.nextDouble();
					System.out.println("Result = " + calc.power(a, b));
					break;
				case 7:
					System.out.print("Enter an integer: ");
					n = scanner.nextInt();
					System.out.println("Factorial = " + calc.factorial(n));
					break;
				case 8:
					System.out.print("Enter a number: ");
					a = scanner.nextDouble();
					System.out.println("Square Root = " + calc.squareRoot(a));
					break;
				case 9:
					running = false;
					System.out.println("Exiting calculator...");
					break;
				default:
					System.out.println("Invalid choice. Please choose between 1-9.");
				}
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}

		scanner.close();
	}
}
