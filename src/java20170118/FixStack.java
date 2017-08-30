package java20170118;

/**
 * 
 * 堆栈转换计算字符串为逆波兰式
 * 
 * @author DZG
 * @since V1.0 2017年1月22日
 */
public class FixStack {

	private Stack theStack;
	private String in;
	private String out = "";

	public FixStack(String input) {
		in = input;
		int stackSize = input.length();
		theStack = new Stack(stackSize);
	}

	public static void main(String[] args) {
		String input = "(1+2)*(4/2)-6/1*2";
		FixStack stack = new FixStack(input);
		String out = stack.doTrans();
		System.out.println(out);
	}

	public String doTrans() {
		for (int j = 0; j < in.length(); j++) {
			char ch = in.charAt(j);
			switch (ch) {
			case '+':
			case '-':
				getOper(ch, 1);
				break;
			case '*':
			case '/':
				getOper(ch, 2);
				break;
			case '(':
				theStack.push(ch);
				break;
			case ')':
				getParen(ch);
				break;
			default:
				out = out + ch;
				break;
			}
		}

		while (!theStack.isEmpty()) {
			out = out + theStack.pop();
		}
		return out;
	}

	public void getOper(char op, int pre) {
		while (!theStack.isEmpty()) {
			char top = theStack.pop();
			if ('(' == top) {
				theStack.push(top);
				break;
			} else {
				int pre2;
				if ('+' == top || '-' == top) {
					pre2 = 1;
				} else {
					pre2 = 2;
				}

				if (pre2 < pre) {
					theStack.push(top);
					break;
				} else {
					out = out + top;
				}
			}
		}
		theStack.push(op);
		System.out.println("X1 " + theStack.getStackStr());
	}

	public void getParen(char ch) {
		while (!theStack.isEmpty()) {
			char chx = theStack.pop();
			System.out.println("X2 " + theStack.getStackStr());
			if ('(' == chx) {
				break;
			} else {
				out = out + chx;
			}			
		}
	}
}
