package com.bjbt.monitoring.kafka.util;

import java.util.ArrayList;
import java.util.Arrays;

public class Test {
	public static void main(String[] args) {
		num1();
		int num = 1233;
		System.out.println("数字 " + num + "比他大的排列为" + num2(num));
	}

	private static void num1() {
		int[] ints = { 0, 0, -1, 0, 1, 0 };
		int j = ints.length - 1;
		for (int i = 0; i < j; i++) {
			if (ints[j] == 0) {
				j--;
				i--;
				continue;
			}
			if (ints[i] == 0) {
				ints[i] = ints[j];
				ints[j] = 0;
			}
		}
		System.out.println(Arrays.toString(ints));
	}

	private static String num2(int num) {
		ArrayList<Integer> nums = new ArrayList<Integer>();
		while ((num % 10) > 0) {
			int tmp = num % 10;
			num = num / 10;
			nums.add(0, tmp);
		}
		int criticalPoint = findCriticalPoint(nums);
		if (criticalPoint == -1) {
			return "不存在";
		}
		return reorder(nums, criticalPoint).toString();
	}

	private static int findCriticalPoint(ArrayList<Integer> nums) {
		for (int i = nums.size() - 1; i > 0; i--) {
			if (nums.get(i) > nums.get(i - 1)) {
				return i;
			}
		}
		return -1;
	}

	private static ArrayList<Integer> reorder(ArrayList<Integer> nums, int index) {
		int left = nums.get(index - 1);
		for (int i = nums.size() - 1; i >= index; i--) {
			int right = nums.get(i);
			if (left < right) {
				nums.set(index - 1, right);
				nums.set(i, left);
				break;
			}
		}
		for (int i = index, j = nums.size() - 1; i < j; i++, j--) {
			int temp = nums.get(i);
			nums.set(i, nums.get(j));
			nums.set(j, temp);
		}
		return nums;
	}
}
