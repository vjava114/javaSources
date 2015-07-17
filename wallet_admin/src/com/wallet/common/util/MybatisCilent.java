package com.wallet.common.util;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.wallet.common.util.PropertiesUtil;

public class MybatisCilent {
	
	protected static SqlSession sqlMapper;
	
	static{
		try{
			String mybatisPath = PropertiesUtil.get("mybatisPath");
			FileReader reader =  new FileReader(mybatisPath);
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
			
			sqlMapper = factory.openSession();
			reader.close();
			
		} catch (FileNotFoundException e) {
			Log.error("MybatisCilent FileNotFound Error : "+e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			Log.error("MybatisCilent IO Error : "+e.toString());
			e.printStackTrace();
		}
	}
	public static SqlSession getSqlMapper(){
		return sqlMapper;
	}
}
