package Data.storage.sqlmap;

import java.io.IOException;
import java.io.Reader;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import constants.ConfigProject;

public class SqlMap {
 
	private static final String local_config = "Data/storage/config/local_iBatis-config.xml";
	private static final String dev_config = "Data/storage/config/dev_iBatis-config.xml";
	private static final String config = "Data/storage/config/iBatis-config.xml";
 
	protected static SqlMapClient sqlMapper;

	/**
	 * It's not a good idea to put code that can fail in a class initializer,
	 * but for sake of argument, here's how you configure an SQL Map.
	 */
	static {
		Reader reader = null;
		System.out.println(" Server STAT [" + ConfigProject.getServer_stat()  + "]");
		try {
			if ( ConfigProject.getServer_stat() == 9)
			{
				reader = Resources.getResourceAsReader(local_config);
				sqlMapper = SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsReader(local_config));
			}
			else if ( ConfigProject.getServer_stat() == 1)
			{
				reader = Resources.getResourceAsReader(dev_config);
				sqlMapper = SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsReader(dev_config));
			}
			else {
				reader = Resources.getResourceAsReader(config);
				sqlMapper = SqlMapClientBuilder.buildSqlMapClient(Resources.getResourceAsReader(config));
			}
		} catch (IOException e) {
			throw new RuntimeException(
					"Something bad happened while building the SqlMapClient instance."
							+ e, e);
		} finally {
			try {
				if (reader != null)
					reader.close();
			} catch (IOException e) {
			}
		}
	}
}
