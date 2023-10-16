package drose.stocking.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.NamingException;

import drose.stocking.model.StockingInfoFileModel;
import gpr.contract.model.ContractInfoFileModel;
import jp.co.intra_mart.foundation.database.ColumnValues;
import jp.co.intra_mart.foundation.database.SQLManager;
import jp.co.intra_mart.foundation.exception.BizApiException;
import jp.co.intra_mart.foundation.security.exception.AccessSecurityException;

public class StockingInfoFileRepo {

    String listColumn = "id, " + "user_data_id, " + "system_matter_id, " + "file_name, " + "file_path, "
            + "file_real_name ";

    private String selectStockingInfoTempFileSystemMatterId = "SELECT " + listColumn
            + "FROM wf_stocking_temp_info_file WHERE system_matter_id = ? ";

    private String selectContractInfoTempFileId = "SELECT " + listColumn
            + "FROM wf_stocking_temp_info_file WHERE id = ?";

    public void insertStockingInfoTempFile(StockingInfoFileModel stockingInfoFileModel) throws Exception {
        try {
            SQLManager sqlManager = new SQLManager();
            ColumnValues columnVal = new ColumnValues();
            columnVal = setStockingInfoTempFileValue(stockingInfoFileModel);
            sqlManager.insert("wf_stocking_temp_info_file", columnVal);
        } catch (SQLException | AccessSecurityException | IllegalArgumentException | NamingException
                | BizApiException e) {
            e.printStackTrace();
            throw new Exception("DB error in insertContractInfoTempFile()", e);
        }
    }

    public void insertStockingInfoFile(String systemMatterId) throws Exception {
        try {
            SQLManager sqlManager = new SQLManager();
            String sqlInsert = "INSERT INTO public.wf_stocking_info_file " + "(" + listColumn + ") " + "SELECT "
                    + listColumn + " FROM wf_stocking_temp_info_file WHERE system_matter_id = ?";
            Collection<Object> parameters = new ArrayList<Object>();
            parameters.add(systemMatterId);
            sqlManager.insert(sqlInsert, parameters);
        } catch (SQLException | AccessSecurityException | IllegalArgumentException | NamingException e) {
            e.printStackTrace();
            throw new Exception("DB error in insertContractInfoFile()", e);
        }
    }

    public Collection<StockingInfoFileModel> selectStockingInfoTempFile(String value, String select_where)
            throws Exception {
        try {
            SQLManager sqlManager = new SQLManager();
            Collection<Object> parameters = new ArrayList<Object>();

            String select_query = new String();
            if (select_where.equals("id")) {
                select_query = selectContractInfoTempFileId;
                parameters.add(Integer.parseInt(value));
            } else if (select_where.equals("system_matter_id")) {
                select_query = selectStockingInfoTempFileSystemMatterId;
                parameters.add(value);
            }
            Collection<StockingInfoFileModel> result = sqlManager.select(StockingInfoFileModel.class, select_query,
                    parameters);
            return result;
        } catch (SQLException | AccessSecurityException | IllegalArgumentException | NamingException e) {
            e.printStackTrace();
            throw new Exception("DB error in selectContractInfoFile()", e);
        }
    }

    private ColumnValues setStockingInfoTempFileValue(StockingInfoFileModel stockingInfoFileModel) {
        ColumnValues result = new ColumnValues();
        try {
            /*
             * result.add("id",Integer.parseInt(stockingInfoFileModel.getUser_data_id()));
             */
            result.add("system_matter_id", stockingInfoFileModel.getSystem_matter_id());
            result.add("user_data_id", stockingInfoFileModel.getSystem_matter_id());
            result.add("file_name", stockingInfoFileModel.getFile_name());
            result.add("file_real_name", stockingInfoFileModel.getFile_real_name());
            result.add("file_path", stockingInfoFileModel.getFile_path());

        } catch (IllegalArgumentException e) {
            System.out.println("Error in setContractInfoFileValue() " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
    
    
    
}