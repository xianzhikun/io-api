package io;

import java.io.IOException;
import java.io.InputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
/*
 * inputreader类
 * 提供方法实现各种io流读取
 */
public class InputReader
{
	private String encodeType="UTF-8";
	private byte[] buffer=new byte[1024*8];//8b
	private InputStream inputStream;
	
	
	public InputReader(InputStream input,String charest)
	{
		this.inputStream=input;
		this.encodeType=charest;
	}
	
	
	public InputReader(InputStream input)
	{
		this.inputStream=input;
	}
	
	
	public InputReader()
	{
		
	}
	
	
	public byte[] readBytes() throws IOException
	{
		if(inputStream==null)
		{
			throw new IOException("inputStream 未指定！！");
		}
		else{
			return readBytes(inputStream);
		}
	}
	
	
	public byte[] readBytes(InputStream input) throws IOException
	{
		byte[] result=new byte[0];
		while(true)
		{
			int off = 0;
			off=input.read(buffer);
			if(off==0)
			{
				continue;
			}
			else if(off<0)
			{
				break;
			}
			else{
				result=concat(result,result.length, buffer,off);
			}
		}
		//System.out.println(new String(result,"utf-8"));
		return result;
	}
	
	
	public byte[] readBytes(int len) throws IOException
	{
		if(inputStream==null)
		{
			throw new IOException("inputStream 未指定！！");
		}
		else
		{
			return readBytes(inputStream,len);
		}
	}
	
	
	public byte[] readBytes(InputStream input,int len) throws IOException
	{
		byte[] result=new byte[len];
		int i=input.read(result, 0, len);
		System.out.println("目标函数 : readBytes(InputStream input,int len)");
		return result;
	}
	
	
	public InputReader read(byte[] byts,int off,int len) throws IOException
	{
		if(inputStream==null)
		{
			throw new IOException("inputStream 未指定！！");
		}
		else{
			return read(inputStream,byts,off,len);
		}
	}
	
	
	public  InputReader read(InputStream input,byte[] byts,int off,int len) throws IOException
	{
		input.read(byts, off, len);
		return this;
	}
	
	
	public InputReader read(byte[] byts) throws IOException
	{
		if(inputStream==null)
		{
			throw new IOException("inputStream 未指定！！");
		}
		else
		{
			return read(inputStream,byts);
		}
	}
	
	
	public InputReader read(InputStream input,byte[] byts) throws IOException
	{
		return read(input,byts,0,byts.length);
	}
	
	
	public String readString(String charest) throws IOException
	{
		if(inputStream==null)
		{
			throw new IOException("inputStream 未指定！！");
		}
		else
		{
			return readString(inputStream, charest);
		}
	}
	
	
	public String readString(InputStream input,String charest) throws IOException
	{
		byte[] byt=readBytes(input);
		if(byt==null)
		{
			return null;
		}
		String str=null;
		str = new String(byt,charest);
		//System.out.println(str);
		return str;
	}
	
	
	public String readString() throws IOException
	{
		if(inputStream==null)
		{
			throw new IOException("inputStream 未指定！！");
		}
		else
		{
			return readString(inputStream,encodeType);
		}
	}
	public String readString(InputStream input) throws IOException
	{
		byte[] byt=readBytes(input);
		if(byt==null)
		{
			return null;
		}
		String str= new String(byt,encodeType);
		//System.out.println(str);
		return str;
	}
	
	
	public JSONObject readJSONobj(InputStream input) throws IOException 
	{
		return readJSONobj(input,encodeType);
	}
	
	
	public JSONObject readJSONobj(String charest) throws IOException
	{
		return readJSONobj(inputStream,charest);
	}
	
	public JSONObject readJSONobj(InputStream input,String charest) throws IOException
	{
		String str=readString(input,charest);
		if(str==null||str.equals(""))
		{
			return null;
			//throw new IOException("未读到json数据！！");
		}
		JSONObject obj=null;
		try {
			obj=new JSONObject(str);
		}
		catch(JSONException e)
		{
			throw new IOException("读到非json数据！！");
		}
		System.out.println(obj.toString(1));
		return obj;
	}
	
	public JSONObject readJSONobj() throws IOException
	{

		return  readJSONobj(inputStream,encodeType);
	}
	
	public JSONArray readJSONArray() throws IOException
	{
		return readJSONArray(inputStream,encodeType);
	}
	public JSONArray readJSONArray(InputStream input,String charest) throws IOException
	{
		String str=readString(input,charest);
		if(str==null||str.equals(""))
		{
			return null;
			//throw new IOException("未读到json数据！！");
		}
		JSONArray arr=null;
		try {
			arr=new JSONArray(str);
		}
		catch(JSONException e)
		{
			throw new IOException("读到非json数据！！");
		}
		System.out.println(arr.toString(1));
		return arr;
	}
	
	public InputReader setCharest(String str)
	{
		encodeType=str;
		return this;
	}
	
	public InputReader setInputstream(InputStream input)
	{
		this.inputStream=input;
		return this;
	}
	
	public InputReader close() throws IOException
	{
		if(inputStream!=null)
		{
			inputStream.close();
		}
		return this;
	}
	
	public byte[] concat(byte[] a,byte[] b)
	{
		byte[] con=new byte[a.length+b.length];
		int aSet;
		for(aSet=0;aSet<a.length;aSet++)
		{
			con[aSet]=a[aSet];
		}
		int bSet;
		for(bSet=aSet;bSet<a.length+b.length;bSet++)
		{
			con[bSet]=b[bSet-aSet];
		}
		return con;
	}
	
	
	public byte[] concat(byte[] a,int alen,byte[] b,int blen)
	{
		byte[] con=new byte[alen+blen];
		int aSet;
		for(aSet=0;aSet<alen;aSet++)
		{
			con[aSet]=a[aSet];
		}
		int bSet;
		for(bSet=aSet;bSet<alen+blen;bSet++)
		{
			con[bSet]=b[bSet-aSet];
		}
		return con;
	}
	

}
