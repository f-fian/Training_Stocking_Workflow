package drose.stocking.repository;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

import drose.stocking.model.StockingInfoDetailModel;
import jp.co.intra_mart.foundation.database.ColumnValues;
import jp.co.intra_mart.foundation.database.SQLManager;

public class StockingInfoDetailRepo {
    
    String listColumnInsertInfoHeader = "system_matter_id, "
            + "user_data_id, "
            + "nama_produk, "
            + "kategori, "
            + "harga, "
            + "stok ";
    
    private String selectContractInfoTempHeaderSystemMatterId = "SELECT "
            + listColumnInsertInfoHeader
            + "FROM wf_stocking_temp_info_detail WHERE system_matter_id = ?";
    
    public void insertDataToTempDetail(StockingInfoDetailModel tempDetail) throws Exception {
        try {
            SQLManager sqlManager = new SQLManager();
            ColumnValues result = new ColumnValues();
            result.add("user_data_id", tempDetail.getUser_data_id());
            result.add("system_matter_id", tempDetail.getSystem_matter_id());
            result.add("nama_produk", tempDetail.getNama_produk());
            result.add("kategori", tempDetail.getKategori());
            result.add("harga ", tempDetail.getHarga());
            result.add("stok ", tempDetail.getStock());
            sqlManager.insert("wf_stocking_temp_info_detail", result);
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception("DB error in insertData()", e);
        }
    }
    
    
    public Collection<StockingInfoDetailModel> selectStockingInfoTempDetail(String select_value) throws Exception {
        try {
            SQLManager sqlManager = new SQLManager();
            Collection<Object> parameters = new ArrayList<Object>();
            String select_query = selectContractInfoTempHeaderSystemMatterId;
            parameters.add(select_value);
            Collection<StockingInfoDetailModel> result = sqlManager.select(StockingInfoDetailModel.class, select_query, parameters);
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new Exception("DB error in selectContractInfoTempHeader()", e);
        }
    }
    
}

