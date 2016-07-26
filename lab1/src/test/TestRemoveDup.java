package test;

import static org.junit.Assert.*;

import org.junit.Test;

import set.RemoveDuplicates;

public class TestRemoveDup {

	@Test
	public void test() {
		int[] test = {1, 2, 5, 3, 3};
		test = RemoveDuplicates.removeDup(test);
		assertArrayEquals(test, new int[] {1, 2, 3, 5});
	}

}
