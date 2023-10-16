package drose.stocking.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import javax.naming.NamingException;

import drose.stocking.model.StockingHeaderInfoModel;
import jp.co.intra_mart.foundation.database.ColumnValues;
import jp.co.intra_mart.foundation.database.SQLManager;
import jp.co.intra_mart.foundation.database.SearchCondition;
import jp.co.intra_mart.foundation.exception.BizApiException;
import jp.co.intra_mart.foundation.security.exception.AccessSecurityException;

public class StockingInfoHeaderRepo {

    String listColumnInsertInfoHeader = "system_matter_id, " + "user_data_id, " + "nama_toko," + "alamat ";

    private String selectStockingInfoTempHeaderSystemMatterId = "SELECT " + listColumnInsertInfoHeader
            + "FROM wf_stocking_temp_info_header WHERE system_matter_id = ?";

    private String selectStockingInfoHeaderSystemMatterId = "SELECT " + listColumnInsertInfoHeader
            + "FROM wf_stocking_info_header WHERE system_matter_id = ?";

    public void insertDataToTempHeader(StockingHeaderInfoModel tempHeader) throws Exception {
        try {
            SQLManager sqlManager = new SQLManager();
            ColumnValues columnValues = new ColumnValues();
            columnValues.add("user_data_id", tempHeader.getUser_data_id());
            columnValues.add("system_matter_id", tempHeader.getSystem_matter_id());
            columnValues.add("nama_toko", tempHeader.getNama_toko());
            columnValues.add("alamat", tempHeader.getAlamat());
            sqlManager.insert("wf_stocking_temp_info_header", columnValues);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("DB error in insertData()", e);
        }
    }

    public Collection<StockingHeaderInfoModel> selectStockingInfoTempHeader(String select_value) throws Exception {
        try {
            SQLManager sqlManager = new SQLManager();
            Collection<Object> parameters = new ArrayList<Object>();
            String select_query = selectStockingInfoTempHeaderSystemMatterId;
            parameters.add(select_value);
            Collection<StockingHeaderInfoModel> result = sqlManager.select(StockingHeaderInfoModel.class, select_query,
                    parameters);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("DB error in selectStockingInfoTempHeader()", e);
        }
    }

    public Collection<StockingHeaderInfoModel> selectStockingInfoHeader(String select_value) throws Exception {
        try {
            SQLManager sqlManager = new SQLManager();
            Collection<Object> parameters = new ArrayList<Object>();
            String select_query = selectStockingInfoHeaderSystemMatterId;
            parameters.add(select_value);
            Collection<StockingHeaderInfoModel> result = sqlManager.select(StockingHeaderInfoModel.class, select_query,
                    parameters);
            return result;
        } catch (SQLException | AccessSecurityException | IllegalArgumentException | NamingException e) {
            e.printStackTrace();
            throw new Exception("DB error in selectStockingInfoTempHeader()", e);
        }
    }

    public void insertStockingInfoHeader(String systemMatterId) throws Exception {
        try {
            SQLManager sqlManager = new SQLManager();
            String sqlInsert = "INSERT INTO public.wf_stocking_info_header " + "(" + listColumnInsertInfoHeader + ") "
                    + "SELECT " + listColumnInsertInfoHeader
                    + " FROM wf_stocking_temp_info_header WHERE system_matter_id = ?";
            Collection<Object> parameters = new ArrayList<Object>();
            parameters.add(systemMatterId);
            sqlManager.insert(sqlInsert, parameters);
        } catch (SQLException | AccessSecurityException | IllegalArgumentException | NamingException e) {
            e.printStackTrace();
            throw new Exception("DB error in insertStockingInfoHeader()", e);
        }
    }

    public void updateStockingInfoTempHeader(StockingHeaderInfoModel infoHeader, String update_where) throws Exception {
        try {
            SQLManager sqlManager = new SQLManager();
            ColumnValues columnVal = new ColumnValues();
            SearchCondition searchCondition = new SearchCondition();

            if (update_where.equals("user_data_id")) {
                searchCondition.addCondition("user_data_id", infoHeader.getUser_data_id());
            } else if (update_where.equals("system_matter_id")) {
                searchCondition.addCondition("system_matter_id", infoHeader.getSystem_matter_id());
            } else {
                searchCondition.addCondition("system_matter_id", infoHeader.getSystem_matter_id());
            }

            columnVal = setContractInfoTempHeaderValue(infoHeader);
            sqlManager.update("wf_contract_temp_info_header", columnVal, searchCondition);
        } catch (SQLException | AccessSecurityException | IllegalArgumentException | NamingException
                | BizApiException e) {
            e.printStackTrace();
            throw new Exception("DB error in updateContractInfoTempHeader()", e);
        }
    }

    private ColumnValues setContractInfoTempHeaderValue(StockingHeaderInfoModel infoHeader) throws BizApiException {
        ColumnValues result = new ColumnValues();
        try {
            result.add("user_data_id", infoHeader.getUser_data_id());
            result.add("system_matter_id", infoHeader.getSystem_matter_id());
            result.add("nama_toko", infoHeader.getNama_toko());
            result.add("alamat", infoHeader.getAlamat());
        } catch (IllegalArgumentException e) {
            System.out.println("Error in setStockInfoTempHeaderValue() " + e.getMessage());
            e.printStackTrace();
        }
        return result;
    }
}