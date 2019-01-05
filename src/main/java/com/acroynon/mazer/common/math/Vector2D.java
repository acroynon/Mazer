package com.acroynon.mazer.common.math;

public class Vector2D {

	private int x;
	private int y;
	
	public Vector2D(){
		
	}
	
	public Vector2D(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	
	public Vector2D add(Vector2D v){
		return new Vector2D(getX() + v.getX(), getY() + v.getY());
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		boolean equals = false;
		if(obj instanceof Vector2D){
			Vector2D v = (Vector2D) obj;
			if(v.getX() == getX() && v.getY() == getY()){
				equals = true;
			}
		}
		return equals;
	}
	
	@Override
	public String toString() {
		return "Vector2D [x:" + getX() + ", y:" + getY() + "];";
	}
	

}
