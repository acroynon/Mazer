package com.acroynon.mazer.util;

import java.util.Random;

public class MathUtil {

	private static Random r = new Random();
	
	public static int randomIntBetween(int min, int max){
		return ((int) (r.nextDouble() * (max-min) + min));
	}
	
}
