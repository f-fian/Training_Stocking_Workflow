package drose.stocking.service.im;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import drose.stocking.model.StockingInfoFileModel;
import drose.stocking.repository.StockingInfoFileRepo;
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

	@Override
	public String apply(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
			throws Exception {
		System.out.println("----- ActionProcessParameter - apply -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - apply -----");

		String number = null;
		try {
			number = WorkflowNumberingManager.getNumber();
			/*
			 * ContractWorkflowService contractQuery = new ContractWorkflowService();
			 * ContractHeaderRepository contractHeaderRepository = new
			 * ContractHeaderRepository(); ContractInfoHeaderRepository
			 * contractInfoTempHeaderRepository = new ContractInfoHeaderRepository();
			 * ContractInfoBudgetRepository contractInfoTempBudgetRepository = new
			 * ContractInfoBudgetRepository(); ContractInfoFileRepository final
			 * ContractHeaderModel entity_header = getEntity_ContractHeader(parameter,
			 * userParameter); final ContractInfoHeaderModel entity_infoTempHeader =
			 * getEntity_ContractInfoHeader(parameter, userParameter); final
			 * List<ContractInfoBudgetModel> entity_infoTempBudget =
			 * getEntity_ContractInfoBudget(parameter, userParameter); final
			 * List<ContractInfoFileModel> entity_infoTempFile =
			 * getEntity_ContractInfoFile(parameter, userParameter,
			 * parameter.getSystemMatterId());
			 * 
			 * 
			 * /* contractHeaderRepository.insertContractHeader(entity_header);
			 * contractInfoTempHeaderRepository.insertContractInfoTempHeader(
			 * entity_infoTempHeader);
			 * 
			 * for(int i=0; i<entity_infoTempBudget.size(); i++) {
			 * contractInfoTempBudgetRepository.insertContractInfoTempBudget(
			 * entity_infoTempBudget.get(i)); }
			 * 
			 * for(int i=0; i<entity_infoTempFile.size(); i++) {
			 * contractInfoTempFileRepository.insertContractInfoTempFile(entity_infoTempFile
			 * .get(i)); contractQuery.contractFileTransfer(parameter.getSystemMatterId() ,
			 * entity_infoTempFile.get(i).getFile_real_name()); }
			 * 
			 */
			
			
			StockingWorkflowService stockingWorkFlowService = new StockingWorkflowService();
			final List<StockingInfoFileModel> entity_infoTempFile = getEntity_StockingInfoFile(parameter, userParameter, parameter.getSystemMatterId());
			StockingInfoFileRepo stockingFileRepo = new StockingInfoFileRepo();
			for(int i=0; i<entity_infoTempFile.size(); i++) {
				stockingFileRepo.insertStockingInfoTempFile(entity_infoTempFile.get(i));
				stockingWorkFlowService.StockingFileTransfer(parameter.getSystemMatterId() , entity_infoTempFile.get(i).getFile_real_name());
			}

			
		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
		return number;
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
	
	
	

	@Override
	public String applyFromTempSave(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
			throws Exception {
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

	@Override
	public String applyFromUnapply(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
			throws Exception {
		System.out.println("----- ActionProcessParameter - applyFromUnapply -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - applyFromUnapply -----");

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

	/*
	 * @Override public void approve(final ActionProcessParameter parameter, final
	 * Map<String, Object> userParameter) throws Exception {
	 * System.out.println("----- ActionProcessParameter - approve -----");
	 * outputLog(parameter);
	 * System.out.println("----- ActionProcessParameter - approve -----");
	 * 
	 * try { ContractInfoBudgetRepository contractInfoTempBudgetRepository = new
	 * ContractInfoBudgetRepository();
	 * if(parameter.getNodeId().equals("operation_node")) { final
	 * List<ContractInfoBudgetModel> entity_infoTempBudget =
	 * getEntity_ContractInfoBudget(parameter, userParameter); for(int i=0;
	 * i<entity_infoTempBudget.size(); i++) {
	 * contractInfoTempBudgetRepository.updateInfoTempBudgetOperation(
	 * entity_infoTempBudget.get(i)); } }else
	 * if(parameter.getNodeId().equals("ga_node")) { final
	 * List<ContractInfoBudgetModel> entity_infoTempBudget =
	 * getEntity_ContractInfoBudget(parameter, userParameter); for(int i=0;
	 * i<entity_infoTempBudget.size(); i++) {
	 * contractInfoTempBudgetRepository.updateInfoTempBudgetGA(entity_infoTempBudget
	 * .get(i)); } } } catch (final Exception e) { throw new
	 * WorkflowExternalException("Error Message", e); } }
	 */

	@Override
	public void approveEnd(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
			throws Exception {
		System.out.println("----- ActionProcessParameter - approveEnd -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - approveEnd -----");

		try {

		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
	}

	@Override
	public void deny(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
		System.out.println("----- ActionProcessParameter - deny -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - deny -----");

		try {

		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
	}

	@Override
	public void discontinue(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
			throws Exception {
		System.out.println("----- ActionProcessParameter - discontinue -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - discontinue -----");

		try {

		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
	}

	@Override
	public void matterHandle(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
			throws Exception {
		System.out.println("----- ActionProcessParameter - matterHandle -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - matterHandle -----");

		try {

		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
	}

	@Override
	public void pullBack(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
			throws Exception {
		System.out.println("----- ActionProcessParameter - pullBack -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - pullBack -----");

		try {

		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
	}

	@Override
	public void reserve(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
			throws Exception {
		System.out.println("----- ActionProcessParameter - reserve -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - reserve -----");

		try {

		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
	}

	@Override
	public void reserveCancel(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
			throws Exception {
		System.out.println("----- ActionProcessParameter - reserveCancel -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - reserveCancel -----");

		try {

		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
	}

	@Override
	public void sendBack(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
			throws Exception {
		System.out.println("----- ActionProcessParameter - sendBack -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - sendBack -----");

		try {

		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
	}

	@Override
	public void sendBackToPullBack(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
			throws Exception {
		System.out.println("----- ActionProcessParameter - sendBackToPullBack -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - sendBackToPullBack -----");

		try {

		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
	}

	@Override
	public void tempSaveCreate(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
			throws Exception {
		System.out.println("----- ActionProcessParameter - tempSaveCreate -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - tempSaveCreate -----");

		try {

		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
	}

	@Override
	public void tempSaveDelete(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
			throws Exception {
		System.out.println("----- ActionProcessParameter - tempSaveDelete -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - tempSaveDelete -----");

		try {

		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
	}

	@Override
	public void tempSaveUpdate(final ActionProcessParameter parameter, final Map<String, Object> userParameter)
			throws Exception {
		System.out.println("----- ActionProcessParameter - tempSaveUpdate -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - tempSaveUpdate -----");

		try {

		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
	}
}
