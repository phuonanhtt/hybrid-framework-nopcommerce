package javaBasic;

import static org.assertj.core.api.Assertions.*;

import org.testng.Assert;
import org.testng.annotations.Test;
public class Topic_07_Assert {
	 
//	@Test
	public void TC_01() {
		// Fluent Assert
		  Topic_07_Assert frodo = new Topic_07_Assert();
		  Topic_07_Assert sauron = new Topic_07_Assert();
		  
			// basic assertions
		  assertThat(frodo.getName()).isEqualTo("Frodo");
		  assertThat(frodo).isNotEqualTo(sauron);

		  // chaining string specific assertions
		  assertThat(frodo.getName()).startsWith("Fro")
		                             .endsWith("do")
		                             .isEqualToIgnoringCase("frodo");
		  assertThat(frodo.getName()).startsWith("Fro").endsWith("do").isEqualToIgnoringCase("frodo");
	}
	Object a = null;
	
	@Test
	public void TC_02_Valid() {
		Assert.assertEquals(sumNumber(5, 6), 11);
	}
	
	@Test
	public void TC_02_Invalid() {
		Assert.assertEquals(sumNumber((Integer) a, 6), 11);
	}
	
	public static int sumNumber(Integer a, Integer b) {
		int sum = 0;
		if (a != null && b != null) {
			sum = a + b;
		} else {
			System.out.println("Number is not valid");
		}
		return sum;
	}
	public String getName() {
		return "Frodo";
	}
}
