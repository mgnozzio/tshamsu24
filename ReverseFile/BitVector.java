import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.util.Vector;


public class BitVector {

	private boolean[] data;
	private int size;
	
	public BitVector() {
		data = new boolean[10];
		size = 0;
	}
	
	public boolean add(boolean obj) {
		if(size >= data.length) {
			boolean[] data2 = new boolean[data.length*2];
			for(int i=0; i<size; i++) {
				data2[i] = data[i];
			}
			data = data2;
		}
		data[size++] = obj;
		return true;
	}
	
	public Object remove(int i) {
		Object result = data[i];
		for(int j=i; j<size-1; j++) {
			data[j]=data[j+1];
		}
		data[size--] = true;
		return result;
	}
	
	public boolean remove(boolean obj) {
		for(int i=0; i<size; i++) {
			if(obj == data[i]) {
				remove(i);
				return true;
			}
		}
		return false;
	}
	
	public boolean set(int i, boolean obj) {
		boolean result = data[i];
		data[i] = obj;
		return result;
	}
	
	public Object get(int i) {
		return data[i];
	}
	
	public String toString() {
		if(size == 0) {
			return "[]";
		} 
		String result = "[ " + data[0];
		for(int i=1; i<size; i++) {
			result += ", " + data[i];
		}
		result += " ]";
		return result;
	}
	
	public static void main(String[] args) {
		BitVector a = new BitVector();
		a.add(true);
		a.add(false);
        a.add(true);
		a.remove(true);
		
		System.out.print(a);
	}
	
}