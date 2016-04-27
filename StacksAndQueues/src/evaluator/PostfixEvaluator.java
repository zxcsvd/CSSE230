package evaluator;

import java.util.Stack;

public class PostfixEvaluator extends Evaluator {

	@Override
	public int evaluate(String expression) throws ArithmeticException {
		Stack<Integer> intStack = new Stack<Integer>();
		Stack<Character> charStack = new Stack<Character>();
		int intCounter = 0;
		int opCounter = 0;

		if(expression.length()==1){
			return Integer.parseInt(expression);
		}
		
		for (int i = 0; i < expression.length(); i++) {
			char temp = expression.charAt(i);
			int first = 0;
			int second = 0;
			
			if(temp=='('){
			throw new ArithmeticException();	
				
				
			}else if (temp == '^') {
				opCounter++;
				if (intStack.isEmpty()) {
					throw new ArithmeticException();
				} else {
					first = intStack.pop();
				}
				if (intStack.isEmpty()) {
					throw new ArithmeticException();
				} else {
					second = intStack.pop();
				}

				intStack.push((int) Math.pow(second, first));
			} else if (temp == '*') {
				opCounter++;
				if (intStack.isEmpty()) {
					throw new ArithmeticException();
				} else {
					first = intStack.pop();
				}
				if (intStack.isEmpty()) {
					throw new ArithmeticException();
				} else {
					second = intStack.pop();
				}
				intStack.push(first * second);
			} else if (temp == '/') {
				opCounter++;
				if (intStack.isEmpty()) {
					throw new ArithmeticException();
				} else {
					first = intStack.pop();
				}
				if (intStack.isEmpty()) {
					throw new ArithmeticException();
				} else {
					second = intStack.pop();
				}

				intStack.push(second / first);
			} else if (temp == '+') {
				opCounter++;
				if (intStack.isEmpty()) {
					throw new ArithmeticException();
				} else {
					first = intStack.pop();
				}
				if (intStack.isEmpty()) {
					throw new ArithmeticException();
				} else {
					second = intStack.pop();
				}

				intStack.push(first + second);
			} else if (temp == '-') {
				opCounter++;
				if (intStack.isEmpty()) {
					throw new ArithmeticException();
				} else {
					first = intStack.pop();
				}
				if (intStack.isEmpty()) {
					throw new ArithmeticException();
				} else {
					second = intStack.pop();
				}

				intStack.push(second - first);
			} else if (temp == ' ') {
				continue;
			} else if ((int) temp <= 122 && (int) temp >= 95
					|| (int) temp >= 65 && (int) temp <= 93) {
				throw new ArithmeticException();

			} else {
				intCounter++;
				String num = "" + temp;
				while (expression.charAt(i + 1) != ' ') {
					num = "" + num + expression.charAt(i + 1);
					i++;

				} 
				int operand = Integer.parseInt(num);
				intStack.push(operand);

			}
		}
		if (intCounter - 1 == opCounter) {
			return intStack.pop();
		} else {
			throw new ArithmeticException();
		}
	}

}