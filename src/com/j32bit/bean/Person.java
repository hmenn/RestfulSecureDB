package com.j32bit.bean;

public class Person {
	private long tc;
	private String name;
	private String phone;
	
	public Person(){}
	
	public Person(long tc, String name, String phone){
		this.tc=tc;
		this.name=name;
		this.phone=phone;
	}
	
	public long getTC(){
		return tc;
	}
	
	public String getName(){
		return name;
	}
	
	public String getPhone(){
		return phone;
	}
	
	public void setTC(long newTC){
		tc=newTC;
	}
	
	public void setName(String newName){
		name=newName;
	}
	
	public void setPhone(String newPhone){
		phone=newPhone;
	}
	
	@Override
	public String toString(){
		return "TC: "+getTC()+", Name: "+getName()+", Phone: "+getPhone();
	}
	
	public boolean equals(Person other){
		return tc==other.getTC();
	}

}
