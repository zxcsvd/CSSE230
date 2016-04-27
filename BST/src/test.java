
public class test {

	public static void main(String[] args) {
		int n=10;
		int sum = 0;
		for(int i=1; i<= n;i++){
			for(int j=1;j<=i*i; j++){
				if(j%i==0)
				for(int k=0; k<j;k++){
					sum++;
				}
				
			}
		}
//		for(int i=1;i<n;i*=2){
//			sum++;
//		}
		System.out.println(sum);
	}

}
