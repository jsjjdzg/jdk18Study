package java20170118;

import java.util.Arrays;

/**
 * 
 * 栈
 * 
 * @author DZG
 * @since V1.0 2017年1月22日
 */
public class Stack {

	private int maxSize;
	private char[] stackArray;
	private int top;

	public Stack(int max) {
		maxSize = max;
		stackArray = new char[max];
		top = -1;
	}

	public int getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(int maxSize) {
		this.maxSize = maxSize;
	}

	public char[] getStackArray() {
		return stackArray;
	}

	public void setStackArray(char[] stackArray) {
		this.stackArray = stackArray;
	}

	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		this.top = top;
	}

	public void push(char j) {
		stackArray[++top] = j;
	}

	public char pop() {
		return stackArray[top--];
	}

	public char peek() {
		return stackArray[top];
	}

	public boolean isEmpty() {
		return top == -1;
	}

	public String getStackStr() {
		return Arrays.toString(stackArray);
	}
}
