package com.zs.java8;

import java.util.Arrays;

public class Java8lambda {

	public static void main(String[] args) {
//		Arrays.asList( "a", "b", "d" ).forEach( e -> System.out.println( e ) );
//		Arrays.asList( "a", "b", "d" ).forEach( ( String e ) -> System.out.println( e ) );
		
//		Arrays.asList( "a", "b", "d" ).forEach( e -> {
//		    System.out.print( e );
//		    System.out.print( e );
//		} );
		
//		Lambda表达式可以引用类成员和局部变量（会将这些变量隐式得转换成final的），例如下列两个代码块的效果完全相同：
		String separator = ",";
		Arrays.asList( "a", "b", "d" ).forEach( 
		    ( String e ) -> System.out.print( e + separator ) );
		
		
//		Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );
		
		Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> {
		    int result = e1.compareTo( e2 );
		    return result;
		} );
	}
}
