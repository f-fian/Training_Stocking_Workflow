package drose.stocking.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.NamingException;

import drose.stocking.model.StockingHeaderModel;
import jp.co.intra_mart.foundation.database.ColumnValues;
import jp.co.intra_mart.foundation.database.SQLManager;
import jp.co.intra_mart.foundation.database.SearchCondition;
import jp.co.intra_mart.foundation.exception.BizApiException;
import jp.co.intra_mart.foundation.security.exception.AccessSecurityException;

public class StockingHeaderRepo {
    String listColumn = "id, "
            + "user_data_id, "
            + "system_matter_id, "
            + "status,"
            + "updated_at ";
    
    private String selectStockingHeaderWhereSystemMatterId = "SELECT "
            + listColumn
            + "FROM wf_stocking_header WHERE system_matter_id = ?";
    private String selectStockingHeaderWhereStatus = "SELECT "
            + listColumn
            + "FROM wf_stocking_header WHERE status = ?";
    
    public Collection<StockingHeaderModel> selectStockingHeader(String select_value, String find_where) throws Exception {
        try {
            SQLManager sqlManager = new SQLManager();
            Collection<Object> parameters = new ArrayList<Object>();
            String select_query = new String();
            if (find_where.equals("status")) {
                select_query = selectStockingHeaderWhereStatus;
            }else if (find_where.equals("system_matter_id")) {
                select_query = selectStockingHeaderWhereSystemMatterId;
            }
            parameters.add(select_value);
            Collection<StockingHeaderModel> result = sqlManager.select(StockingHeaderModel.class, select_query, parameters);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("DB error in selectContractHeader()", e);
        }
    }
    
    public void insertStockingHeader(StockingHeaderModel StockingHeaderData) throws Exception {
        try {
            SQLManager sqlManager = new SQLManager();
            ColumnValues columnVal = new ColumnValues();
            columnVal = setHeaderValue(StockingHeaderData);
            
            sqlManager.insert("wf_stocking_header", columnVal);
        } catch (SQLException | AccessSecurityException | IllegalArgumentException | NamingException | BizApiException e) {
            e.printStackTrace();
            throw new Exception("DB error in insertContractHeader()", e);
        }
    }
    
    public void updateStatusHeader (String systemMatterId, String status) throws Exception {
        try {
            String tableName = "wf_stocking_header";
            SQLManager sqlManager = new SQLManager();
            ColumnValues columnValues = new ColumnValues();
            columnValues.add("status", status);
//          columnValues.add("log_api_mcga", logMsg);
            SearchCondition condition = new SearchCondition();
            condition.addCondition("system_matter_id", systemMatterId);
            sqlManager.update(tableName, columnValues, condition);
        } catch (SQLException | AccessSecurityException | IllegalArgumentException | NamingException | BizApiException e){
            e.printStackTrace();
            throw new Exception("DB error in updateStatusComplete()", e);
        }
    }
    
    public void deleteStockingHeader(String find_query, String find_where) throws Exception {
        try {
            SQLManager sqlManager = new SQLManager();
            SearchCondition searchCondition = new SearchCondition();
            
            if (find_where.equals("id")) {
                searchCondition.addCondition("id", find_query);
            } else if (find_where.equals("user_data_id")) {
                searchCondition.addCondition("user_data_id", find_query);
            } else if (find_where.equals("system_matter_id")) {
                searchCondition.addCondition("system_matter_id", find_query);
            } else {
                searchCondition.addCondition("system_matter_id", find_query);
            }
            
            sqlManager.delete("wf_stocking_header", searchCondition);
        } catch (SQLException | AccessSecurityException | IllegalArgumentException | NamingException | BizApiException e){
            e.printStackTrace();
            throw new Exception("DB error in deleteKtbGeneralInfoHeader()", e);
        }
    }
    
    private ColumnValues setHeaderValue (StockingHeaderModel ContractHeaderData) {
        ColumnValues result = new ColumnValues();
        result.add("user_data_id", ContractHeaderData.getUser_data_id());
        result.add("system_matter_id", ContractHeaderData.getSystem_matter_id());
        result.add("status", ContractHeaderData.getStatus());
        return result;
    }
}
