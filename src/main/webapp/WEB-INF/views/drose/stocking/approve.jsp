



<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="imui" uri="http://www.intra-mart.co.jp/taglib/imui"%>
<%@ taglib prefix="imart"
    uri="http://www.intra-mart.co.jp/taglib/core/standard"%>
<%@ taglib prefix="workflow"
    uri="http://www.intra-mart.co.jp/taglib/imw/workflow"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://terasoluna.org/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<%@ taglib prefix="im"
    uri="http://www.intra-mart.co.jp/taglib/im-tenant"%>

<imui:head>
    <title>Stocking Approval Workflow</title>

    <workflow:workflowOpenPageCsjs />
    <script type="text/javascript">
    $(function(){
    	
    	$('#back').click(function(){
			$('#backForm').submit();
			return false;
		});
    	
    	$("#close").click(function(){
            window.close();
            return false;
        });
    	
    	$('#openPage').click(function(){
			imuiResetForm('#workflowOpenPageForm');
			/* if (node == 'operation_node'){
				if (!imuiValidate('#workflowOpenPageForm', rulesOperation, messagesOperation)) return;
			}else if (node == 'ga_node'){
				if (!imuiValidate('#workflowOpenPageForm', rulesGA, messagesGA)) return;
			} */
			workflowOpenPage('${f:h(StockingForm.imwPageType)}');
			return false;
		});
    	
        
    })
</script>
    <style>
input[type=text] {
    height: 20px;
}

#imui-container {
    min-width: 0px;
}
</style>
</imui:head>


<div class="imui-title-small-window">
    <h1>Product Approval Workflow</h1>
</div>
<div class="imui-toolbar-wrap">
 	<div class="imui-toolbar-inner">
		<ul class="imui-list-toolbar">
			<li>
				<a href="javascript:void(0);" id="back">
					<span class="im-ui-icon-common-16-back"></span>
				</a>
			</li>
		</ul>
	</div>
</div>

<workflow:workflowUserContentsAuth
    imwApplyBaseDate='${f:h(StockingForm.imwApplyBaseDate)}'
    imwAuthUserCode='${f:h(StockingForm.imwAuthUserCode)}'
    imwFlowId='${f:h(StockingForm.imwFlowId)}'
    imwNodeId='${f:h(StockingForm.imwNodeId)}'
    imwPageType='${f:h(StockingForm.imwPageType)}'
    imwSystemMatterId='${f:h(StockingForm.imwSystemMatterId)}'
    imwUserDataId='${f:h(StockingForm.imwUserDataId)}' />



<workflow:workflowOpenPage name="workflowOpenPageForm"
           id="workflowOpenPageForm"
           method="POST"
           target="_top"
           imwUserDataId="${f:h(StockingForm.imwUserDataId)}"
           imwSystemMatterId="${f:h(StockingForm.imwSystemMatterId)}"
           imwAuthUserCode="${f:h(StockingForm.imwAuthUserCode)}"
           imwApplyBaseDate="${f:h(StockingForm.imwApplyBaseDate)}"
           imwNodeId="${f:h(StockingForm.imwNodeId)}"
           imwFlowId="${f:h(StockingForm.imwFlowId)}"
           imwCallOriginalParams="${f:h(StockingForm.imwCallOriginalParams)}"
           imwNextScriptPath="${f:h(StockingForm.imwCallOriginalPagePath)}">
<div class="imui-form-container-wide">
    <div class="imui-form-container-full">
        <header class="imui-chapter-title">
            <h2>General Information</h2>
        </header>
        <table class="imui-form">
            <%-- <tbody>
                <tr>
                    <th width="250">
                        <label for="f_type_contract">
                            Tipe Kontrak
                        </label>
                    </th>
                    <td>
                        <label><input value="option_baru" ${StockingFormClassRows.type_contract_option_baru} name="f_type_contract" id="f_type_contract_baru" type="radio" /> Baru</label><br>
                        <label><input value="option_ekstensi" ${StockingFormClassRows.type_contract_option_ekstensi} name="f_type_contract" id="f_type_contract_ekstensi" type="radio" /> Ekstensi</label><br>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label class="imui-required" for="f_company_name">
                            Nama Perusahaan
                        </label>
                    </th>
                    <td>
                        <input value="${StockingFormClassRows.company_name}" readonly name="f_company_name" id="f_company_name" class="imui-text-readonly" type="text" size="50"/>
                    </td>
                </tr>
                <tr class="row_kontrak_sebelumnya">
                    <th>
                        <label class="imui-required" for="f_id_contract_before">
                            Kontrak Sebelumnya
                        </label>
                    </th>
                    <td>
                        <input value="${StockingFormClassRows.id_contract_before}" name="f_id_contract_before" id="f_id_contract_before" type="hidden" />
                        <input value="${StockingFormClassRows.no_contract_previous}" readonly class="imui-text-readonly" type="text"/>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label class="imui-required" for="f_no_contract">
                            Nomor Kontak
                        </label>
                    </th>
                    <td>
                        <input value="${StockingFormClassRows.no_contract}" readonly name="f_no_contract" id="f_no_contract" class="imui-text-readonly" type="text" size="50" placeholder="Nomor Kontrak"/>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label class="imui-required" for="f_name_sign_contract">
                            Nama Penanda Tangan Perjanjian
                        </label>
                    </th>
                    <td>
                        <input value="${StockingFormClassRows.name_sign_contract}" readonly name="f_name_sign_contract" id="f_name_sign_contract" class="imui-text-readonly" type="text" size="50" placeholder="Nama Penandatangan Perjanjian"/>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label class="imui-required" for="f_no_telp">
                            No. Telephone
                        </label>
                    </th>
                    <td>
                        <input value="${StockingFormClassRows.no_telp}" readonly name="f_no_telp" id="f_no_telp" class="imui-text-readonly" type="text" size="50" placeholder="No. Telephone"/>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label class="imui-required" for="f_total_unit">
                            Total Unit
                        </label>
                    </th>
                    <td>
                        <input value="${StockingFormClassRows.total_unit}" readonly name="f_total_unit" id="f_total_unit" class="imui-text-readonly" type="text" size="50" placeholder="Total Unit"/>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label class="imui-required" for="f_start_contract">
                            Start Sewa
                        </label>
                    </th>
                    <td>
                        <input value="${StockingFormClassRows.start_contract}" readonly name="f_start_contract" id="f_start_contract" class="imui-text-readonly" type="text" size="50" placeholder="Start Sewa (yyyy/MM/dd)" />
                    </td>
                </tr>
                <tr>
                    <th>
                        <label class="imui-required" for="f_periode_months">
                            Periode (Bulan)
                        </label>
                    </th>
                    <td>
                        <input value="${StockingFormClassRows.periode}" readonly name="f_periode_months" id="f_periode_months" class="imui-text-readonly" type="text" size="10" onchange="getEndContract(this.value)" placeholder="Periode"> 
                    </td>
                </tr>
                <tr>
                    <th>
                        <label class="imui-required" for="f_end_contract">
                            End Sewa
                        </label>
                    </th>
                    <td>
                        <input value="${StockingFormClassRows.end_contract}" readonly name="f_end_contract" id="f_end_contract" class="imui-text-readonly" type="text" size="50" placeholder="End Sewa (yyyy/MM/dd)"/>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="f_type_payment">
                            Jenis Pembayaran
                        </label>
                    </th>
                    <td>
                        <label><input value="option_tunai" ${StockingFormClassRows.type_payment_option_tunai} name="f_type_payment" id="f_type_payment_tunai" type="radio" /> Tunai</label><br>
                        <label><input value="option_credit" ${StockingFormClassRows.type_payment_option_credit} name="f_type_payment" id="f_type_payment_credit" type="radio" /> Credit</label><br>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label for="f_trade_pattern">
                            Trade Pattern
                        </label>
                    </th>
                    <td>
                        <input value="${StockingFormClassRows.trade_pattern_code}" type="hidden" name="f_trade_pattern" id="f_trade_pattern"/> 
                        <input value="${StockingFormClassRows.trade_pattern_code}:${StockingFormClassRows.trade_pattern_name}" readonly class="imui-text-readonly" type="text" size="50" />
                    </td>
                </tr>
                <tr>
                    <th>
                        <label class="imui-required" for="f_no_management_approval">
                            Nomor Management Approval
                        </label>
                    </th>
                    <td>
                        <input value="${StockingFormClassRows.no_management_approval}" readonly name="f_no_management_approval" id="f_no_management_approval" class="imui-text-readonly" type="text" size="50" placeholder="Nomor Management Approval"/>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label class="imui-required" for="f_date_management_approval">
                            Tanggal Management Approval
                        </label>
                    </th>
                    <td>
                        <input value="${StockingFormClassRows.date_management_approval}" readonly name="f_date_management_approval" id="f_date_management_approval" class="imui-text-readonly" type="text" size="50" placeholder="Tanggal Management (yyyy/MM/dd)"/>
                    </td>
                </tr>
                <tr>
                    <th>
                        <label class="imui-required" for="f_marketing_name">
                            Nama Marketing
                        </label>
                    </th>
                    <td>
                        <input value="${StockingFormClassRows.marketing_name)}" readonly name="f_marketing_name" id="f_marketing_name" class="imui-text-readonly" type="text" size="50" placeholder="Nama Marketing"/>
                    </td>
                </tr>
            </tbody> --%>
            <tbody>
                <tr>
                    <th><label class="imui-required"
                        for="f_nama_toko"> Nama Toko </label></th>
                    <td><input value="${StockingFormClassRows.nama_toko}" readonly
                        name="f_nama_toko" id="f_nama_toko" class="imui-text-readonly"
                        type="text" size="50" placeholder="Nama Toko" /></td>
                </tr>
                <tr>
                    <th><label class="imui-required"
                        for="f_alamat"> Alamat </label></th>
                    <td><input value="${StockingFormClassRows.alamat}" readonly
                        name="f_alamat" id="f_alamat" class="imui-text-readonly"
                        type="text" size="50" placeholder="Alamat" /></td>
                </tr>
            </tbody>
        </table>
    </div>
    <div class="imui-form-container-full">
        <header class="imui-chapter-title">
            <div class="flexbox-container">
                <h2>Product Information</h2>
            </div>
        </header>
        <%-- <div style="overflow-x:scroll" class="imui-box-article horizontal-scroll-wrapper squares">
            <div class="imui-form-container-full">
                <table class="imui-form">
                    <tr>
                        <th>
                            <label for="f_brandTypeVariant">Brand/Type/Variant</label>
                        </th>
                        <th>
                            <label for="f_rental_price">Harga Sewa</label>
                        </th>
                        <th>
                            <label for="f_biaya_own_risk">Biaya Own Risk</label>
                        </th>
                        <th>
                            <label for="f_id_vehicle">No. Polisi</label>
                        </th>
                        <th>
                            <label for="f_date_bastk">Tgl. BASTK</label>
                        </th>
                        <th>
                            <label for="f_no_bastk">No. BASTK</label>
                        </th>
                        <th>
                            <label for="f_budget_replace">Budget Replace</label>
                        </th>
                        <th>
                            <label for="f_budget_maintenance">Budget Maintenance</label>
                        </th>
                        <th>
                            <label for="f_budget_stnk_keur">Budget STNK & KEUR</label>
                        </th>
                        <th>
                            <label for="f_budget_accessories">Budget Aksesoris Tambahan</label>
                        </th>
                        <th>
                            <label for="f_total_budget">Total Budget</label>
                        </th>
                    </tr>
                    <tbody class="contract_insert_detail">
                        <!-- Loop Item already from data table -->
                        <c:forEach items="${StockingFormClassRows.d_list_info_temp_budget}" var="v_budget_info">
                        <tr class="contract_get_detail">
                            <td style="display:none;"><input type="hidden" name="budget_id" value="${v_budget_info.id}"></td>
                            <td>
                                <input value="${v_budget_info.brand_type_varian}" name="f_brand_type_variant" type="hidden" >
                                <c:forEach items="${StockingFormClassRows.d_list_brandVarianType }" var="v_brandVarianType">
                                    <c:if test="${v_brandVarianType.id == v_budget_info.brand_type_varian}">
                                        <input type="text" readonly class="imui-text-readonly"
                                         value="${v_brandVarianType.brand}/${v_brandVarianType.type}/${v_brandVarianType.varian}"
                                         size="60"/>
                                    </c:if>
                                </c:forEach>
                            </td>
                            <td>
                                <input readonly value="${v_budget_info.rental_price}"
                                 name="f_rental_price" class="f_rental_price imui-text-readonly" type="text" 
                                 size="20" placeholder="Harga Sewa (0.00)">
                            </td>
                            <td>
                                <input readonly value="${v_budget_info.cost_own_risk}" 
                                 name="f_cost_own_risk" class="f_cost_own_risk imui-text-readonly" type="text" 
                                 size="20" placeholder="Biaya Own Risk (0.00)">
                            </td>
                            <td>
                                <c:forEach items="${StockingFormClassRows.d_list_vehicleInfo}" var="v_data_vehicle">
                                    <c:if test="${v_budget_info.id_vehicle == v_data_vehicle.id}">
                                        <input readonly value="${v_data_vehicle.no_police}" type="text" class="imui-text-readonly" placeholder="No Polisi"/>
                                        <input type="hidden" value="${v_data_vehicle.id}" name="f_id_vehicle" />
                                    </c:if>
                                </c:forEach>
                                <c:if test="${v_budget_info.id_vehicle == 0 }">
                                    <input readonly type="text" class="imui-text-readonly" placeholder="No Polisi"/>
                                    <input value="0"  name="f_id_vehicle" type="hidden">
                                </c:if>
                            </td>
                            <td>
                                <input readonly value="${v_budget_info.date_bastk}" 
                                name="f_date_bastk" class="f_date_bastk imui-text-readonly" type="text" 
                                size="15" placeholder="Tgl. BASTK"/>
                            </td>
                            <td>
                                <input readonly value="${v_budget_info.no_bastk}" 
                                 name="f_no_bastk" class="f_no_bastk imui-text-readonly" type="text" 
                                 size="10" placeholder="No. BASTK">
                            </td>
                            <td>
                                <input readonly value="${v_budget_info.budget_replace}" 
                                 name="f_budget_replace" class="f_budget_replace imui-text-readonly" type="text"
                                 size="20" placeholder="0.00">
                            </td>
                            <td>
                                <input readonly value="${v_budget_info.budget_maintenance}" 
                                 name="f_budget_maintenance" class="f_budget_maintenance imui-text-readonly" type="text"
                                 size="20" placeholder="0.00">
                            </td>
                            <td>
                                <input readonly value="${v_budget_info.budget_stnk_keur}" 
                                 name="f_budget_stnk_keur" class="f_budget_stnk_keur imui-text-readonly" type="text" 
                                 size="20" placeholder="0.00">
                            </td>
                            <td>
                                <input readonly value="${v_budget_info.budget_accessories}" 
                                 name="f_budget_accessories" class="f_budget_accessories imui-text-readonly" type="text"
                                 size="20" placeholder="0.00">
                            </td>
                            <td>
                                <input readonly value="${v_budget_info.total_budget}" 
                                 name="f_total_budget" class="f_total_budget imui-text-readonly" type="text"
                                 size="20" placeholder="Total Budget (0.00)">
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div> --%>
        <table class="imui-form" id="detail-table">
            <tr>
                <th style="width: 135px;"><label for="f_nama_produk">Nama
                        Produk</label></th>
                <th style="width: 135px;"><label for="f_kategori">Kategori</label></th>
                <th style="width: 135px;"><label for="harga">harga</label></th>
                <th style="width: 135px;"><label for="stok">Stok</label></th>
            </tr>
            <tbody class="contract_insert_detail">
            
            			<!-- <td><a href="gpr_contract/download/">dsaodks</a></td> -->
                        <!-- Loop Item already from data table -->
                        <c:forEach items="${StockingFormClassRows.detailData}" var="detailData">
                        	<tr>
                        		<td>${detailData.nama_produk}</td>
                        		<td>${detailData.kategori}</td>
                        		<td>${detailData.harga}</td>
                        		<td>${detailData.stok}</td>
                        	
                        	</tr>
	                        
                        </c:forEach>
                    </tbody>
        </table>
    </div>
    <br>

    <div class="imui-form-container-full">
        <header class="imui-chapter-title">
            <h2>Lampiran Kontrak</h2>
        </header>
        <table class="Imui-form">
            <tbody>
                <c:forEach items="${StockingFormClassRows.fileData}"
                    var="fileData">
                    <tr>
                        <td><a>${fileData.file_name}</a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</workflow:workflowOpenPage>

<div class="imui-operation-parts">
	<input type="button" value='Process' id="openPage" name="openPage" class="imui-large-button" escapeXml="true" escapeJs="false" />
</div>


<form name="backForm" id="backForm" method="POST" action="${f:h(StockForm.imwCallOriginalPagePath)}">
	<input type="hidden" name=imwCallOriginalParams value="${f:h(StockForm.imwCallOriginalParams)}" />
</form>