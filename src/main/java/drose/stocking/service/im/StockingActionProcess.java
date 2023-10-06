package drose.stocking.service.im;

import java.util.*;

import drose.stocking.model.StockingHeaderInfoModel;
import drose.stocking.model.StockingHeaderModel;
import drose.stocking.model.StockingInfoDetailModel;
import drose.stocking.model.StockingInfoFileModel;
import drose.stocking.repository.StockingHeaderRepo;
import drose.stocking.repository.StockingInfoDetailRepo;
import drose.stocking.repository.StockingInfoFileRepo;
import drose.stocking.repository.StockingInfoHeaderRepo;
import drose.stocking.service.app.StockingWorkflowService;
import jp.co.intra_mart.foundation.workflow.exception.WorkflowExternalException;
import jp.co.intra_mart.foundation.workflow.plugin.process.action.*;
import jp.co.intra_mart.foundation.workflow.util.WorkflowNumberingManager;


public class StockingActionProcess extends ActionProcessEventListener {
    
    private void outputLog(ActionProcessParameter parameter) {
        System.out.println("LoginGroupId        : " + parameter.getLoginGroupId());
        System.out.println("LocaleId            : " + parameter.getLocaleId());
        if (parameter.getTargetLocales() != null) {
            System.out.print("TargetLocales       : ");
            for (final String str : parameter.getTargetLocales()) {
                System.out.print(str + " ");
            }
            System.out.println();
        } else {
            System.out.println("TargetLocales       : ");
        }
        System.out.println("ContentsId          : " + parameter.getContentsId());
        System.out.println("ContentsVersionId   : " + parameter.getContentsVersionId());
        System.out.println("RouteId             : " + parameter.getRouteId());
        System.out.println("RouteVersionId      : " + parameter.getRouteVersionId());
        System.out.println("FlowId              : " + parameter.getFlowId());
        System.out.println("FlowVersionId       : " + parameter.getFlowVersionId());
        System.out.println("ApplyBaseDate       : " + parameter.getApplyBaseDate());
        System.out.println("ProcessDate         : " + parameter.getProcessDate());
        System.out.println("SystemMatterId      : " + parameter.getSystemMatterId());
        System.out.println("UserDataId          : " + parameter.getUserDataId());
        System.out.println("MatterName          : " + parameter.getMatterName());
        System.out.println("MatterNumber        : " + parameter.getMatterNumber());
        System.out.println("PriorityLevel       : " + parameter.getPriorityLevel());
        System.out.println("Parameter           : " + parameter.getParameter());
        System.out.println("ActFlag             : " + parameter.getActFlag());
        System.out.println("NodeId              : " + parameter.getNodeId());
        if (parameter.getNextNodeIds() != null) {
            System.out.print("NextNodeIds         : ");
            for (final String str : parameter.getNextNodeIds()) {
                System.out.print(str + " ");
            }
            System.out.println();
        } else {
            System.out.println("NextNodeIds         : ");
        }
        System.out.println("AuthUserCd          : " + parameter.getAuthUserCd());
        System.out.println("ExecUserCd          : " + parameter.getExecUserCd());
        System.out.println("ResultStatus        : " + parameter.getResultStatus());
        System.out.println("AuthCompanyCode     : " + parameter.getAuthCompanyCode());
        System.out.println("AuthOrgzSetCode     : " + parameter.getAuthOrgzSetCode());
        System.out.println("AuthOrgzCode        : " + parameter.getAuthOrgzCode());
        System.out.println("ProcessComment      : " + parameter.getProcessComment());
        System.out.println("LumpProcessFlag     : " + parameter.getLumpProcessFlag());
        System.out.println("AutoProcessFlag     : " + parameter.getAutoProcessFlag());
    }
    
    private String getEntity_TryCatch_UserParameter(final Map<String, Object> userParameter, String input_form) {
        try {
            return userParameter.get(input_form).toString();
        } catch (Exception e) {
            return "";
        }
    }
    
    private String getEntity_TryCatch_String(List<String> input_form, int i) {
        try {
            return input_form.get(i);
        } catch (Exception e) {
            return "";
        }
    }
    
    private StockingHeaderModel getEntity_StockingHeader(final ActionProcessParameter parameter, final Map<String, Object> userParameter) {
        StockingHeaderModel entity = new StockingHeaderModel();
        entity.setUser_data_id(parameter.getUserDataId());
        entity.setSystem_matter_id(parameter.getSystemMatterId());
        entity.setStatus("1"); // apply
        return entity;
    }
    
    private StockingHeaderInfoModel getEntity_StockingInfoHeader(final ActionProcessParameter parameter, final Map<String, Object> userParameter) {
        StockingHeaderInfoModel entity = new StockingHeaderInfoModel();
        entity.setSystem_matter_id(parameter.getSystemMatterId());
        entity.setUser_data_id(parameter.getUserDataId());
                
        entity.setNama_toko(getEntity_TryCatch_UserParameter(userParameter , "f_nama_toko"));
        entity.setAlamat(getEntity_TryCatch_UserParameter(userParameter , "f_alamat"));
        return entity;
    }
    
    @Override
    public String apply(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
        System.out.println("----- ActionProcessParameter - apply -----");
        outputLog(parameter);
        System.out.println("----- ActionProcessParameter - apply -----");

        String number = null;
        try {
            number = WorkflowNumberingManager.getNumber();
            StockingWorkflowService contractQuery = new StockingWorkflowService();
            StockingHeaderRepo stockingHeaderRepository = new StockingHeaderRepo();
            StockingInfoHeaderRepo stockingInfoTempHeaderRepository = new StockingInfoHeaderRepo();
            StockingInfoDetailRepo stockingInfoTempDetailRepository = new StockingInfoDetailRepo();
            StockingInfoFileRepo stockingInfoTempFileRepository = new StockingInfoFileRepo();
            
            final StockingHeaderModel entity_header = getEntity_StockingHeader(parameter, userParameter);
            final StockingHeaderInfoModel entity_infoTempHeader = getEntity_StockingInfoHeader(parameter, userParameter);
            final StockingInfoDetailModel entity_infoTempDetail = getEntity_StockingInfoDetail(parameter, userParameter);
            final List<StockingInfoFileModel> entity_infoTempFile = getEntity_StockingInfoFile(parameter, userParameter, parameter.getSystemMatterId());

            stockingHeaderRepository.insertStockingHeader(entity_header);
            stockingInfoTempHeaderRepository.insertDataToTempHeader(entity_infoTempHeader);
            stockingInfoTempDetailRepository.insertDataToTempDetail(entity_infoTempDetail);
            
            
            for(int i=0; i<entity_infoTempFile.size(); i++) {
                stockingInfoTempFileRepository.insertStockingInfoTempFile(entity_infoTempFile.get(i));
                contractQuery.StockingFileTransfer(parameter.getSystemMatterId() , entity_infoTempFile.get(i).getFile_real_name());
            }
        } catch (final Exception e) {
            throw new WorkflowExternalException("Error Message", e);
        }
        return number;
        
        
    }
    
    private StockingInfoDetailModel getEntity_StockingInfoDetail(final ActionProcessParameter parameter, final Map<String, Object> userParameter) {
        StockingInfoDetailModel entity = new StockingInfoDetailModel();
            entity.setSystem_matter_id(parameter.getSystemMatterId());
            entity.setUser_data_id(parameter.getUserDataId());
            entity.setNama_produk(getEntity_TryCatch_UserParameter(userParameter , "f_nama_produk"));
            entity.setKategori(getEntity_TryCatch_UserParameter(userParameter , "f_kategori"));
            entity.setHarga(getEntity_TryCatch_UserParameter(userParameter , "f_harga"));
            entity.setStok(getEntity_TryCatch_UserParameter(userParameter , "f_stok"));
            entity.setNama_toko(getEntity_TryCatch_UserParameter(userParameter , "f_nama_toko"));               
            return entity;
    		}

    
    private List<StockingInfoFileModel> getEntity_StockingInfoFile(final ActionProcessParameter parameter, final Map<String, Object> userParameter, String system_matter_id) {
        List<StockingInfoFileModel> result = new ArrayList<StockingInfoFileModel>();
        System.out.println(userParameter.get("f_contract_upload_file_id"));
        System.out.println("f_contract_upload_file_id");
        System.out.println(userParameter.get("f_contract_upload_file_name"));
        System.out.println("f_contract_upload_file_name");
        System.out.println(userParameter.get("f_contract_upload_file_real_name"));
        System.out.println("f_contract_upload_file_id");
        try {
            List<String> varId = (List<String>) userParameter.get("f_contract_upload_file_id");
            List<String> varFileName = (List<String>)userParameter.get("f_contract_upload_file_name");
            List<String> varFileRealName = (List<String>)userParameter.get("f_contract_upload_file_real_name");
            
            for(int i=0; i<varId.size(); i++) {
                StockingInfoFileModel entity = new StockingInfoFileModel();
                entity.setId(Integer.parseInt(varId.get(i)));
                entity.setSystem_matter_id(parameter.getSystemMatterId());
                entity.setUser_data_id(parameter.getUserDataId());
                
                entity.setFile_name(getEntity_TryCatch_String(varFileName , i));  
                entity.setFile_real_name(getEntity_TryCatch_String(varFileRealName , i));
                
                entity.setFile_path("gpr_contract/" + system_matter_id  + "/gpr_contract_flow/" + entity.getFile_real_name());
                
                if(!entity.getFile_name().equals("") && !entity.getFile_real_name().equals("")) {
                    if(!entity.getFile_name().equals("-") && !entity.getFile_real_name().equals("-")) {
                        result.add(entity);
                    }
                }
            }
        } catch (Exception e) {
            StockingInfoFileModel entity = new StockingInfoFileModel();
            try {
            	entity.setId(Integer.parseInt(userParameter.get("f_contract_upload_file_id").toString()));
                entity.setSystem_matter_id(parameter.getSystemMatterId());
                entity.setUser_data_id(parameter.getUserDataId());
                
                entity.setFile_name(getEntity_TryCatch_UserParameter(userParameter , "f_contract_upload_file_name"));
                entity.setFile_real_name(getEntity_TryCatch_UserParameter(userParameter , "f_contract_upload_file_real_name"));
                                
                entity.setFile_path("gpr_contract/" + system_matter_id + "/gpr_contract_flow/" + entity.getFile_real_name());
                
                if(!entity.getFile_name().equals("") && !entity.getFile_real_name().equals("")) {
                    if(!entity.getFile_name().equals("-") && !entity.getFile_real_name().equals("-")) {
                        result.add(entity);
                    }
                }
            } catch (Exception e2) {
                System.out.println("no file entity");
            }
        }
        return result;
    }
    
    
    
    @Override
    public String applyFromTempSave(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
        System.out.println("----- ActionProcessParameter - applyFromTempSave -----");
        outputLog(parameter);
        System.out.println("----- ActionProcessParameter - applyFromTempSave -----");
        String number = null;
        try {
            number = WorkflowNumberingManager.getNumber();
        } catch (final Exception e) {
            throw new WorkflowExternalException("Error Message", e);
        }
        try {

        } catch (final Exception e) {
            throw new WorkflowExternalException("Error Message", e);
        }

        return number;
    }

}