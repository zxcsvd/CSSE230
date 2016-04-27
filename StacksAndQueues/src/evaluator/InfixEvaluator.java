package evaluator;

import java.util.Stack;

public class InfixEvaluator extends Evaluator {

	@Override
	public int evaluate(String expression) throws ArithmeticException {
		String temp = convertToPostfix(expression);
		PostfixEvaluator post = new PostfixEvaluator();
		return post.evaluate(temp);
//		return 0;
	}

	public String convertToPostfix(String exp) {
		// TODO Auto-generated method stub
		int opCounter = 0;
		int numCounter =0;
		int paraCounter = 0;
		StringBuilder result = new StringBuilder();
		Stack<Character> charStack =  new Stack<Character>();
		if(exp.length()<=0){
			throw new ArithmeticException();
		}
		for(int i=0; i<exp.length(); i++){
			String temp = exp.substring(i, i+1);
			
			if(isOperator(temp)){
				
				opCounter++;
				if(charStack.isEmpty()){
					charStack.push(temp.charAt(0));
				}
				else{
					if(getLevel(temp) > getLevel(charStack.peek()+"")){
						charStack.push(temp.charAt(0));
					}
					else{
						char a =' ';
						while(charStack.isEmpty() == false && getLevel(temp) <= getLevel(charStack.peek()+"")){
							if(result.length()!=0 && !result.substring(result.length()-1, result.length()).equals(" ")){
								result.append(" ");
							}
							a = charStack.pop();
							result.append(a);
						}
						charStack.push(temp.charAt(0));
					}
				}
			}
			
			else if(isLeftPar(temp)){
				paraCounter++;
				charStack.push(temp.charAt(0));
			}
			
			else if(isRightPar(temp)){
				
				char a = ' ';
				if(charStack.isEmpty()){
					throw new ArithmeticException(); 
				}
				else if(paraCounter<=0){
					throw new ArithmeticException(); 
				}
				while(charStack.isEmpty() == false && charStack.peek()!='('){
					if(!result.substring(result.length()-1, result.length()).equals(" ")){
						result.append(" ");
					}
					a=charStack.pop();
					result.append(a);
				}
				if(charStack.isEmpty() == false){
					charStack.pop();
				}
				paraCounter--;	
				
				
			}
			else if(isNum(temp)){
				if(result.length() != 0){
					if(isNum(result.substring(result.length()-1, result.length()))){
//						continue;
					}
					else{
						if(result.substring(result.length()-1, result.length()).equals(" ")){
							numCounter++;
						}
						else{
							result.append(" ");
							numCounter++;
						}
					}
				}
				else{
					numCounter++;
				}
				result.append(temp);
				
			}
			else if(temp.equals(" ") && result.length()>=1){
				if(isNum(result.substring(result.length()-1, result.length()))){
					result.append(" ");
				}
				continue;
			}
			else if(temp.equals(" ") && result.length()==0){
				continue;
			}
			else{
				throw new ArithmeticException();
			}
		}
		result.append(" ");
		while (charStack.isEmpty()==false){
			if(isLeftPar(charStack.peek()+"")){
				throw new ArithmeticException(); 
			}
			result.append(charStack.pop() + " ");
			
		}
		result = result.delete(result.length()-1, result.length());
		
		if(numCounter - opCounter != 1){
//			System.out.print(result.toString());
			throw new ArithmeticException();
		}
		else{
		

//			result = spaceFix(result);
			return result.toString();
		}
		
	}
	
	public boolean isOperator(String testString){
		
		if(testString.equals("+") || testString.equals("-") || testString.equals("*") || testString.equals("/") || 
				 testString.equals("^")){
			return true;
		}
		return false;
		
	}
	
	public Boolean isLeftPar(String testString){
		if(testString.equals("(")){
			return true;
		}
		return false;
	}
	
	public Boolean isRightPar(String testString){
		if(testString.equals(")")){
			return true;
		}
		return false;
	}
	
	public int getLevel(String operation){
		if(operation.equals("+") || operation.equals("-")){
			return 1;
		}
		else if(operation.equals("*") || operation.equals("/")){
			return 2;
		}
		else if(operation.equals("^")){
			return 3;
		}
		else if(operation.equals("(")){
			return 0;
		}
		else if(operation.equals(")")){
			return 5;
		}
		return 0;
	}

	public Boolean isNum(String num){
		if((int)num.charAt(0)>= 48 && (int)num.charAt(0)<= 57){
			return true;
		}
		return false;
	}
	
}
