package com.mm.tool;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MySpring {
   private volatile static MySpring context;
	private ClassPathXmlApplicationContext spriContext;
	{
		context = null;
		spriContext = null;
	}
   public static MySpring getInstance(){
	   if(context == null){
		   synchronized (MySpring.class){
			   if(context == null){
				   context = new MySpring();
			   }
		   }
	   }
	   return context;
   }
   private MySpring(){
		spriContext = new ClassPathXmlApplicationContext("applicationContext.xml");
	}
	
	public ClassPathXmlApplicationContext getContext(){
		return spriContext;
	}
}

