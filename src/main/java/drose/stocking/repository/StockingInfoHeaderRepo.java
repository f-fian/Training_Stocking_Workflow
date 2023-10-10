package drose.stocking.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import drose.stocking.model.StockingHeaderInfoModel;
import jp.co.intra_mart.foundation.database.ColumnValues;
import jp.co.intra_mart.foundation.database.SQLManager;

public class StockingInfoHeaderRepo {
    
    String listColumnInsertInfoHeader = "system_matter_id, "
            + "user_data_id, "
            + "nama_toko,"
            + "alamat ";
    
    private String selectStockingInfoTempHeaderSystemMatterId = "SELECT "
            + listColumnInsertInfoHeader
            + "FROM wf_stocking_temp_info_header WHERE system_matter_id = ?";

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
            Collection<StockingHeaderInfoModel> result = sqlManager.select(StockingHeaderInfoModel.class, select_query, parameters);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("DB error in selectContractInfoTempHeader()", e);
        }
    }
}
