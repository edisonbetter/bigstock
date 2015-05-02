package com.greenation.bigstock.service;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.transaction.Transactional;

import org.beanio.BeanWriter;
import org.beanio.StreamFactory;
import org.beanio.builder.DelimitedParserBuilder;
import org.beanio.builder.FieldBuilder;
import org.beanio.builder.RecordBuilder;
import org.beanio.builder.StreamBuilder;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class Bean2CSVService {
	private StreamFactory factory;
	private RecordBuilder headerRecord;
	private StreamBuilder builder;
	private File csvFile;
	private BeanWriter writer;
	private static String STREAM_NAME = "BigStock";
	private static String HEADER_NAME = "header";
	
	public void setHeader(List<String> header){
		RecordBuilder record = new RecordBuilder(HEADER_NAME);
		for(int i=0; i<header.size(); i++){
			record.addField(new FieldBuilder(header.get(i)).defaultValue(header.get(i)).at(i));
		}
		record.minOccurs(1);
		record.maxOccurs(1);
		record.type(HashMap.class);
		this.headerRecord = record;
	}
	
	public void setFilePath(String filePath){
		csvFile = new File(filePath);
	}
	
	public void setup(Class... targetClasses){
		factory = StreamFactory.newInstance();
		builder = new StreamBuilder(STREAM_NAME)
		.format("delimited")
		.parser(new DelimitedParserBuilder(','));
		if(null != headerRecord){
			builder.addRecord(headerRecord);
		}
		for(Class clazz: targetClasses){
			builder.addRecord(clazz);
		}
		factory.define(builder);		
	}
	
	protected void begin(){
		writer = factory.createWriter(STREAM_NAME, csvFile);
		if(null != headerRecord){
			writer.write(HEADER_NAME, new HashMap());
		}
	}
	
	protected void finish(){
		writer.flush();
		writer.close();
	}
	
	public void write(Object bean){
		begin();
		writer.write(bean);
		finish();
	}
	
	public void write(List<Object> beans){
		begin();
		for(Object bean: beans){
			writer.write(bean);
		}
		finish();
	}
}
