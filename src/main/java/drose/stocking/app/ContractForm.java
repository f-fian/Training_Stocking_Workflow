package drose.stocking.app;

import java.util.*;



public class ContractForm extends ContractWorkflowForm {
	/* General Information */
	
	/* Radio button Tipe Kontrak */
	private String type_contract;
	private String type_contract_option_baru;
	private String type_contract_option_ekstensi;

	/* Radio button Jenis Pembayaran */
	private String type_payment;
	private String type_payment_option_tunai;
	private String type_payment_option_credit;
	
	private String trade_pattern_code;	
	private String trade_pattern_name;
	
	/* Form Control */
	private String disableInputForOperation;
	private String disableInputForGA;
	
	private String id_contract_before;
	private String no_contract_previous;
	private int id_customer;
	private String company_name;
	private String no_contract;
	private String name_sign_contract;
	private String no_telp;
	private String total_unit;
	private int periode;
	private String start_contract;
	private String end_contract;
	private String no_management_approval;
	private String date_management_approval;
	private String marketing_name;
	
	private Collection<ContractInfoFileModel> d_list_info_temp_file;
	
	public Collection<ContractInfoFileModel> getD_list_info_temp_file() {
		return d_list_info_temp_file;
	}
	public void setD_list_info_temp_file(Collection<ContractInfoFileModel> d_list_info_temp_file) {
		this.d_list_info_temp_file = d_list_info_temp_file;
	}
	
	
	public String getType_contract() {
		return type_contract;
	}
	public void setType_contract(String type_contract) {
		this.type_contract = type_contract;
	}
	public String getId_contract_before() {
		return id_contract_before;
	}
	public void setId_contract_before(String id_contract_before) {
		this.id_contract_before = id_contract_before;
	}
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getNo_contract() {
		return no_contract;
	}
	public void setNo_contract(String no_contract) {
		this.no_contract = no_contract;
	}
	public String getName_sign_contract() {
		return name_sign_contract;
	}
	public void setName_sign_contract(String name_sign_contract) {
		this.name_sign_contract = name_sign_contract;
	}
	public String getNo_telp() {
		return no_telp;
	}
	public void setNo_telp(String no_telp) {
		this.no_telp = no_telp;
	}
	public String getTotal_unit() {
		return total_unit;
	}
	public void setTotal_unit(String total_unit) {
		this.total_unit = total_unit;
	}
	public String getStart_contract() {
		return start_contract;
	}
	public void setStart_contract(String start_contract) {
		this.start_contract = start_contract;
	}
	public String getEnd_contract() {
		return end_contract;
	}
	public void setEnd_contract(String end_contract) {
		this.end_contract = end_contract;
	}
	public String getType_payment() {
		return type_payment;
	}
	public void setType_payment(String type_payment) {
		this.type_payment = type_payment;
	}
	public String getNo_management_approval() {
		return no_management_approval;
	}
	public void setNo_management_approval(String no_management_approval) {
		this.no_management_approval = no_management_approval;
	}
	public String getDate_management_approval() {
		return date_management_approval;
	}
	public void setDate_management_approval(String date_management_approval) {
		this.date_management_approval = date_management_approval;
	}
	public String getMarketing_name() {
		return marketing_name;
	}
	public void setMarketing_name(String marketing_name) {
		this.marketing_name = marketing_name;
	}
	public String getType_contract_option_baru() {
		return type_contract_option_baru;
	}
	public void setType_contract_option_baru(String type_contract_option_baru) {
		this.type_contract_option_baru = type_contract_option_baru;
	}
	public String getType_contract_option_ekstensi() {
		return type_contract_option_ekstensi;
	}
	public void setType_contract_option_ekstensi(String type_contract_option_ekstensi) {
		this.type_contract_option_ekstensi = type_contract_option_ekstensi;
	}
	public String getType_payment_option_tunai() {
		return type_payment_option_tunai;
	}
	public void setType_payment_option_tunai(String type_payment_option_tunai) {
		this.type_payment_option_tunai = type_payment_option_tunai;
	}
	public String getType_payment_option_credit() {
		return type_payment_option_credit;
	}
	public void setType_payment_option_credit(String type_payment_option_credit) {
		this.type_payment_option_credit = type_payment_option_credit;
	}

	
	public String getDisableInputForOperation() {
		return disableInputForOperation;
	}
	public void setDisableInputForOperation(String disableInputForOperation) {
		this.disableInputForOperation = disableInputForOperation;
	}
	public String getDisableInputForGA() {
		return disableInputForGA;
	}
	public void setDisableInputForGA(String inputForGA) {
		this.disableInputForGA = inputForGA;
	}
	
	
	public int getId_customer() {
		return id_customer;
	}
	public void setId_customer(int id_customer) {
		this.id_customer = id_customer;
	}
	
	public String getNo_contract_previous() {
		return no_contract_previous;
	}
	public void setNo_contract_previous(String no_contract_previous) {
		this.no_contract_previous = no_contract_previous;
	}
	
	public String getTrade_pattern_code() {
		return trade_pattern_code;
	}
	public void setTrade_pattern_code(String trade_pattern_code) {
		this.trade_pattern_code = trade_pattern_code;
	}
	public int getPeriode() {
		return periode;
	}
	public void setPeriode(int periode) {
		this.periode = periode;
	}
	public String getTrade_pattern_name() {
		return trade_pattern_name;
	}
	public void setTrade_pattern_name(String trade_pattern_name) {
		this.trade_pattern_name = trade_pattern_name;
	}
}
