package miniCodePrjPkg;

public class perf {
	
	
	   /**
     * ����쳲���������n��ֵ,n��0��ʼ
     * ʵ�ַ�ʽ�����ڵݹ�ʵ��
     * @param n
     * @return
     * @author mhy2011@163.com
     * @since 2015��8��18������9:41:30
     */
    public static int getFib(int n){
        if(n < 0){
            return -1;
        }else if(n == 0){
            return 0;
        }else if(n == 1 || n ==2){
            return 1;
        }else{
            return getFib(n - 1) + getFib(n - 2);
        }
    }
	
	public static void main(String[] args) {
		System.out.println("---");
		System.out.println(getFib(39));
	}

}
