package io;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

import org.json.JSONObject;

import com.sun.corba.se.impl.ior.WireObjectKeyTemplate;
/*
 * OutputWriter类
 * 提供方法实现各种io流写入读取
 */
public class OutputWriter extends Writer
{
	private String encodeType="UTF-8";
	private OutputStream outputstream;
	
	public OutputWriter(OutputStream output,String str)
	{
		this.outputstream=output;
		this.encodeType=str;
	}
	
	
	public OutputWriter(OutputStream output)
	{
		this.outputstream=output;
	}
	
	
	public OutputWriter()
	{
		
	}
	
	
	public OutputWriter writeBytes(OutputStream output,byte[] byts,int off,int len) throws IOException
	{
	    output.write(byts, off, len);
		return this;
	}
	
	
	public OutputWriter writeBytes(byte[] byts,int off,int len) throws IOException 
	{
		if(outputstream==null)
		{
			throw new IOException("outputStream 未指定！！");
		}
		else{
			outputstream.write(byts, off, len);
			return this;
		}
	}
	
	
	public OutputWriter writeBytes(OutputStream output,byte[] byts) throws IOException
	{
		output.write(byts);
		return this;
	}
	
	
	public OutputWriter writeBytes(byte[] byts) throws IOException
	{
		if(outputstream==null)
		{
			throw new IOException("outputStream 未指定！！");
		}
		else{
			outputstream.write(byts);
			return this;
		}
	}
	
	
	public OutputWriter writeString(OutputStream output,String charast,String str) throws UnsupportedEncodingException, IOException
	{
		output.write(str.getBytes(charast));
		return this;
	}
	
	
	public OutputWriter writeString(String str,String charast) throws IOException
	{
		if(outputstream==null)
		{
			throw new IOException("outputStream 未指定！！");
		}
		else{
			outputstream.write(str.getBytes(charast));
			return this;
		}
	}
	
	
	public OutputWriter writeString(OutputStream output,String str) throws UnsupportedEncodingException, IOException
	{
		return writeString(output, str, encodeType);
	}
	
	
	public OutputWriter writeString(String str) throws IOException
	{
		return writeString(str, encodeType);
	}
	
	
	public OutputWriter writeJSONobj(JSONObject obj) throws IOException 
	{
		if(obj==null)
		{
			throw new IOException("json数据为空！！！");
		}
		String str=obj.toString();
		return writeString(str);
	}
	
	
	public OutputWriter writeJSONobj(String charest,JSONObject obj) throws Exception
	{
		if(obj==null)
		{
			throw new IOException("json数据为空！！！");
		}
		String str=obj.toString();
		return writeString(str,charest);
	}
	
	
	public OutputWriter writeJSONobj(OutputStream output,JSONObject obj) throws IOException 
	{
		if(obj==null)
		{
			throw new IOException("json数据为空！！！");
		}
		String str=obj.toString();
		return writeString(output,str);
	}
	

	public OutputWriter writeJSONobj(OutputStream output,String charest,JSONObject obj) throws IOException 
	{
		if(obj==null)
		{
			throw new IOException("json数据为空！！！");
		}
		String str=obj.toString();
		return writeString(output,charest,str);
	}
	
	
	public OutputWriter setOutputStream(OutputStream output)
	{
		this.outputstream=output;
		return this;
	}
	
	
	public OutputWriter setCharest(String str)
	{
		this.encodeType=str;
		return this;
	}
	
//	public OutputWriter close() throws IOException
//	{
//		if(outputstream!=null)
//		{
//			outputstream.close();
//		}
//		return this;
//	}


	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		if(outputstream!=null)
		{
			outputstream.close();
		}
	}


	@Override
	public void flush() throws IOException {
		
	}


	@Override
	public void write(char[] cbuf, int off, int len) throws IOException {
		char[] bu=split(cbuf, off, len);
		
//		outputstream.write();
	}
	public char[] split(char[] buf,int sta,int len)
	{
		char[] bu;
		if(len-sta>buf.length)
		{
			return buf;
		}
		else
		{
			bu=new char[len];
		}
		for(int i=0;i<len;i++,sta++)
		{
			bu[i]=buf[sta];
		}
		return bu;
	}

}
