package drose.stocking.service.app;

import java.util.*;

import drose.stocking.app.StockingForm;
import drose.stocking.model.StockingHeaderInfoModel;
import drose.stocking.model.StockingInfoDetailModel;
import drose.stocking.model.StockingInfoFileModel;
import drose.stocking.repository.StockingInfoDetailRepo;
import drose.stocking.repository.StockingInfoFileRepo;
import drose.stocking.repository.StockingInfoHeaderRepo;
import jp.co.intra_mart.foundation.service.client.file.PublicStorage;
import jp.co.intra_mart.foundation.service.client.file.SessionScopeStorage;


public class StockingWorkflowService {
    private StockingInfoHeaderRepo infoHeaderRepository = new StockingInfoHeaderRepo();
    private StockingInfoDetailRepo infoDetailRepository = new StockingInfoDetailRepo();
    private StockingInfoFileRepo infoFileRepository = new StockingInfoFileRepo();
    
    public Map<String, Object> createValueMap(Object... objArray){
        Map<String, Object> map = new HashMap<String, Object>();
        int size = objArray.length;
        for (int i = 0; i < size; i++) {
            if (((i + 1) & 1) == 1) {
                map.put(objArray[i].toString(), objArray[i + 1]);
            }
        }
        return map;
    }
    
    public final Boolean StockingFileTransfer(String system_matter_id, String file_real_name) throws Exception {
        PublicStorage create_directory = new PublicStorage("gpr_contract/" + system_matter_id + "/gpr_contract_flow");
        PublicStorage create_file = new PublicStorage("gpr_contract/" + system_matter_id +"/gpr_contract_flow/" + file_real_name);
        SessionScopeStorage get_original_file = new SessionScopeStorage("gpr_contract_flow/"+ file_real_name);
        try {
            create_directory.makeDirectories();
        } catch (Exception e) {
            System.out.print("gagal bikin directory public storage" + e);
        }
        try {
            if(!create_file.isFile()) {
                create_file.save(org.apache.commons.io.IOUtils.toByteArray(get_original_file.open()));
            }else {
            }
        } catch (Exception e) {
            System.out.println("gagal copy file ke public storage" + e);
        }
        return true;
    }
    
    public final StockingForm getStockingInfoTemp(String systemMatterId) throws Exception {
        Collection<StockingHeaderInfoModel> rows_info_temp_header = infoHeaderRepository.selectStockingInfoTempHeader(systemMatterId);
        Collection<StockingInfoDetailModel> rows_info_temp_detail = infoDetailRepository.selectStockingInfoTempDetail(systemMatterId);
        Collection<StockingInfoFileModel> rows_info_temp_file = infoFileRepository.selectStockingInfoTempFile(systemMatterId, "system_matter_id");
        return setStockingInfoForm(rows_info_temp_header, rows_info_temp_detail, rows_info_temp_file);
    }
    
    public final StockingForm setStockingInfoForm(Collection<StockingHeaderInfoModel> rows_info_temp_header, 
            Collection<StockingInfoDetailModel> rows_info_temp_detail, Collection<StockingInfoFileModel> rows_info_temp_file) {
            
            StockingHeaderInfoModel StockingInfoTempHeaderRows = rows_info_temp_header.iterator().next();
            List<StockingInfoDetailModel> stockingInfoDetailModel =(List<StockingInfoDetailModel>) rows_info_temp_detail;
            List<StockingInfoFileModel> stockingInfoFileModel =(List<StockingInfoFileModel>) rows_info_temp_file;
            StockingForm result = new StockingForm();
          
            try {
                result.setNama_toko(StockingInfoTempHeaderRows.getNama_toko());
                result.setAlamat(StockingInfoTempHeaderRows.getAlamat());
                result.setFileData(stockingInfoFileModel);
                result.setDetailData(stockingInfoDetailModel);
            } catch (Exception e) {
                System.out.println(e);
            }
            return result;
        }

}