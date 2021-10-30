package com.hb.util;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhanglei
 * @date 2021/10/31 1:01 上午
 */
@Component
public class HBaseUtil {

    @Resource
    private Admin hbaseAdmin;

    /**
     * 判断表是否存在
     * @param tableName 表名
     * @return
     */
    public boolean isExists(String tableName) {
        boolean tableExists = false;
        try {
            TableName table = TableName.valueOf(tableName);
            tableExists = hbaseAdmin.tableExists(table);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tableExists;
    }

    /**
     * 获取数据（全表）
     * @param tableName 表名
     * @return
     */
    public List<Map<String, String>> getData(String tableName) {
        List<Map<String, String>> list = new ArrayList<>();
        try {
            Table table = hbaseAdmin.getConnection().getTable(TableName.valueOf(tableName));
            Scan scan = new Scan();
            ResultScanner resultScanner = table.getScanner(scan);
            for(Result result : resultScanner) {
                HashMap<String, String> map = new HashMap<>();
                //rowKey
                String row = Bytes.toString(result.getRow());
                map.put("row", row);
                for (Cell cell : result.listCells()) {
                    //列族
                    String family = Bytes.toString(cell.getFamilyArray(),
                            cell.getFamilyOffset(), cell.getFamilyLength());
                    //列
                    String qualifier = Bytes.toString(cell.getQualifierArray(),
                            cell.getQualifierOffset(), cell.getQualifierLength());
                    //值
                    String data = Bytes.toString(cell.getValueArray(),
                            cell.getValueOffset(), cell.getValueLength());
                    map.put(family + ":" + qualifier, data);
                }
                list.add(map);
            }
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 获取数据
     * @param tableName 表名
     * @param rowKey
     * @return
     */
    public Map<String, String> getData(String tableName, String rowKey) {
        HashMap<String, String> map = new HashMap<>();
        try {
            Table table = hbaseAdmin.getConnection().getTable(TableName.valueOf(tableName));
            Get get = new Get(Bytes.toBytes(rowKey));
            Result result = table.get(get);
            if (result != null && !result.isEmpty()) {
                for (Cell cell : result.listCells()) {
                    //列族
                    String family = Bytes.toString(cell.getFamilyArray(),
                            cell.getFamilyOffset(), cell.getFamilyLength());
                    //列
                    String qualifier = Bytes.toString(cell.getQualifierArray(),
                            cell.getQualifierOffset(), cell.getQualifierLength());
                    //值
                    String data = Bytes.toString(cell.getValueArray(),
                            cell.getValueOffset(), cell.getValueLength());
                    map.put(family + ":" + qualifier, data);
                }
            }
            table.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 获取table
     * @param tableName 表名
     * @return Table
     * @throws IOException
     */
    public Table getTable(String tableName) throws IOException {
        return hbaseAdmin.getConnection().getTable(TableName.valueOf(tableName));
    }


    /**
     * 获取数据库中所有的表名
     * @return
     * @throws IOException
     */
    public List<String> getAllTableNames() throws IOException {

        List<String> result = new ArrayList<>();
        TableName[] tableNames = hbaseAdmin.listTableNames();
        for (TableName tableName : tableNames) {
            result.add(tableName.getNameAsString());
        }
        return result;
    }
}
