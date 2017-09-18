package oracle.demo.oow.bd.util.hbase;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

public class HBaseDB
{
	private Connection conn;
	
	private static class HBaseDBInstance
	{
		private static final HBaseDB instance = new HBaseDB();
	}
	
	public static HBaseDB getInstance()
	{
		return HBaseDBInstance.instance;
	}
	
	private HBaseDB()
	{
		//获取配置类对象
		Configuration conf = HBaseConfiguration.create();
		//指定zookeeper地址
		conf.set("hbase.zookeeper.quorum", "star.hadoop");
		//指定hbase存储根目录
		conf.set("hbase.rootdir", "hdfs://star.hadoop:9000/hbase");
		try
		{
			conn = ConnectionFactory.createConnection(conf);
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据表名称和列族创建表
	 * @param tableName
	 * @param columnFamilies
	 */
	public void createTable(String tableName, String[] columnFamilies)
	{
		deleteTable(tableName);
		
		try
		{
			Admin admin = conn.getAdmin();
			//指定表名称
			HTableDescriptor descriptor = new HTableDescriptor(TableName.valueOf(Bytes.toBytes(tableName)));
			//添加列族
			for(String string : columnFamilies)
			{
				HColumnDescriptor family = new HColumnDescriptor(Bytes.toBytes(string));
				//family.setMaxVersions(maxVersions);
				descriptor.addFamily(family);
			}
			
			admin.createTable(descriptor);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据表名称删除表
	 * @param tableName
	 */
	public void deleteTable(String tableName)
	{
		try
		{
			Admin admin = conn.getAdmin();
			if(admin.tableExists(TableName.valueOf(tableName)))
			{
				//首先disable
				admin.disableTable(TableName.valueOf(tableName));
				//drop
				admin.deleteTable(TableName.valueOf(tableName));
			}
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 根据表名称获取table对象
	 * @param tableName
	 * @return
	 */
	public Table getTable(String tableName)
	{
		try
		{
			return conn.getTable(TableName.valueOf(tableName));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 根据计数器计算行键
	 * @param tableName
	 * @param family
	 * @param qualifier
	 * @return
	 */
	public Long getId(String tableName, String family,
			String qualifier)
	{
		Table table = getTable(tableName);
		try
		{
			return table.incrementColumnValue(Bytes.toBytes(ConstantsHBase.ROW_KEY_GID_ACTIVITY_ID),
					Bytes.toBytes(family), Bytes.toBytes(qualifier), 1);
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0l;
	}
	
}
