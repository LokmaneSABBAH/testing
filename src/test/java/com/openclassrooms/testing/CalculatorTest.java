package com.openclassrooms.testing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.text.MessageFormat;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

public class CalculatorTest {
	
	private Calculator calculatorUnderTest;
	private static Instant startedAt;
	
	
	@BeforeAll
	public static void initStartingTime() {
		System.out.println("Appel avant tous les tests.");
		startedAt = Instant.now();
	}
	
	@AfterAll
	public static void showTestDuration() {
		Instant endAt = Instant.now();
		long duration = Duration.between(startedAt, endAt).toMillis();
		System.out.println(MessageFormat.format("Durée des tests : {0} ms", duration));
	}
	
	@BeforeEach
	public void initCalculator() {
		calculatorUnderTest = new Calculator();
		System.out.println("Appel avant chaque test.");
		
	}
	
	@AfterEach
	public void underfCalculator() {
		calculatorUnderTest = null;
		System.out.println("Appel après chaque test.");
	}

	@Test
	public void testAddTwoPositiveNumbers() {
		// Arrange
		int a = 2;
		int b = 3;
		

		// Act
		int somme = calculatorUnderTest.add(a, b);

		// Assert
		//assertEquals(5, somme);
		assertThat(somme).isEqualTo(5);
	}

	@Test
	public void multiply_shouldReturnTheProduct_ofTwoIntegers() {
		// Arrange
		int a = 42;
		int b = 11;
		

		// Act
		int produit = calculatorUnderTest.multiply(a, b);

		// Assert
		//assertEquals(462, produit);
		assertThat(produit).isEqualTo(462);
	}
	
	
	
	@ParameterizedTest(name = "{0} x 0 doit ete egale à 0")
	@ValueSource(ints = {100,5,74,89,0,4})
	public void multiply_shouldReturnZero_ofzeroWithMultipleInteger(int arg) {
		//arrange (dans ce cas, tous est pres)
		
		//act
		final int actualResult = calculatorUnderTest.multiply(arg, 0);
		
		//asset -- ca vaut toujours 0
		//assertEquals(0, actualResult);
		assertThat(actualResult).isEqualTo(0);
	}
	
	
	@ParameterizedTest(name = "{0} + {1} doit etre egale à {2}")
	@CsvSource({"2,4,6", "0,4,4", "100,120,220"})
	public void add_shouldReturnTheSum_ofMultipleIntegers(int arg1, int arg2, int expectedResult) {
		
		//arrange -- tous est pres
		
		//act
		int actuelResult = calculatorUnderTest.add(arg1, arg2);
		
		//assert
		//assertEquals(expectedResult, actuelResult);
		assertThat(actuelResult).isEqualTo(expectedResult);
		
	}
	
	@Test
	@Timeout(5)
	public void longCalcul_shouldComputeInLessThan1Second() {
		calculatorUnderTest.longCalculation();
	}
	
	
	@Test
	public void listDigits_shouldReurnsTheListOfDigits_ofPostiveInteger() {
		
		//GIVEN
		int number = -95897;
		
		//WHEN
		Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);
		
		//THEN
		//Set<Integer> expectedDigits = Stream.of(5, 7, 8, 9).collect(Collectors.toSet());
		//assertEquals(expectedDigits, actualDigits);
		
		assertThat(actualDigits).containsExactlyInAnyOrder(9, 7, 8, 5);
	}
	
	
	
	@Test
	public void listDigits_shouldReurnsTheListOfZeroOfZero() {
		
		//GIVEN
		int number = 0;
		
		//WHEN
		Set<Integer> actualDigits = calculatorUnderTest.digitsSet(number);
		
		//THEN
		//Set<Integer> expectedDigits = Stream.of(5, 7, 8, 9).collect(Collectors.toSet());
		//assertEquals(expectedDigits, actualDigits);
		
		assertThat(actualDigits).containsExactly(0);
	}
	
	
	
	
	

}
