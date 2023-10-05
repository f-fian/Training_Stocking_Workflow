package drose.stocking.service.im;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*import gpr.contract.model.ContractHeaderModel;
import gpr.contract.model.ContractInfoBudgetModel;
import gpr.contract.model.ContractInfoFileModel;
import gpr.contract.model.ContractInfoHeaderModel;
import gpr.contract.repository.ContractHeaderRepository;
import gpr.contract.repository.ContractInfoBudgetRepository;
import gpr.contract.repository.ContractInfoFileRepository;
import gpr.contract.repository.ContractInfoHeaderRepository;
import gpr.contract.service.app.ContractWorkflowService;*/
import jp.co.intra_mart.foundation.workflow.exception.WorkflowExternalException;
import jp.co.intra_mart.foundation.workflow.plugin.process.action.*;
import jp.co.intra_mart.foundation.workflow.util.WorkflowNumberingManager;

public class ContractActionProcess extends ActionProcessEventListener {	
	
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
	public String apply(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
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
			 * ContractInfoBudgetRepository(); ContractInfoFileRepository
			 * contractInfoTempFileRepository = new ContractInfoFileRepository();
			 * 
			 * final ContractHeaderModel entity_header = getEntity_ContractHeader(parameter,
			 * userParameter); final ContractInfoHeaderModel entity_infoTempHeader =
			 * getEntity_ContractInfoHeader(parameter, userParameter); final
			 * List<ContractInfoBudgetModel> entity_infoTempBudget =
			 * getEntity_ContractInfoBudget(parameter, userParameter); final
			 * List<ContractInfoFileModel> entity_infoTempFile =
			 * getEntity_ContractInfoFile(parameter, userParameter,
			 * parameter.getSystemMatterId());
			 */

			/*
			 * contractHeaderRepository.insertContractHeader(entity_header);
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
			 */
		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
		return number;
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

	@Override
	public String applyFromUnapply(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
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
	public void approveEnd(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
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
	public void discontinue(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
		System.out.println("----- ActionProcessParameter - discontinue -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - discontinue -----");

		try {

		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
	}

	@Override
	public void matterHandle(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
		System.out.println("----- ActionProcessParameter - matterHandle -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - matterHandle -----");

		try {

		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
	}

	@Override
	public void pullBack(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
		System.out.println("----- ActionProcessParameter - pullBack -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - pullBack -----");

		try {

		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
	}

	

	@Override
	public void reserve(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
		System.out.println("----- ActionProcessParameter - reserve -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - reserve -----");

		try {

		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
	}

	@Override
	public void reserveCancel(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
		System.out.println("----- ActionProcessParameter - reserveCancel -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - reserveCancel -----");

		try {

		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
	}

	@Override
	public void sendBack(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
		System.out.println("----- ActionProcessParameter - sendBack -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - sendBack -----");

		try {

		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
	}

	@Override
	public void sendBackToPullBack(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
		System.out.println("----- ActionProcessParameter - sendBackToPullBack -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - sendBackToPullBack -----");

		try {

		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
	}

	@Override
	public void tempSaveCreate(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
		System.out.println("----- ActionProcessParameter - tempSaveCreate -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - tempSaveCreate -----");

		try {

		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
	}

	@Override
	public void tempSaveDelete(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
		System.out.println("----- ActionProcessParameter - tempSaveDelete -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - tempSaveDelete -----");

		try {

		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
	}

	@Override
	public void tempSaveUpdate(final ActionProcessParameter parameter, final Map<String, Object> userParameter) throws Exception {
		System.out.println("----- ActionProcessParameter - tempSaveUpdate -----");
		outputLog(parameter);
		System.out.println("----- ActionProcessParameter - tempSaveUpdate -----");

		try {

		} catch (final Exception e) {
			throw new WorkflowExternalException("Error Message", e);
		}
	}
}
