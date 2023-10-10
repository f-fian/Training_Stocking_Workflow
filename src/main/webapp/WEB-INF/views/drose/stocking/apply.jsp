<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="imui" uri="http://www.intra-mart.co.jp/taglib/imui"%>
<%@ taglib prefix="imart"
	uri="http://www.intra-mart.co.jp/taglib/core/standard"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://terasoluna.org/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="workflow"
	uri="http://www.intra-mart.co.jp/taglib/imw/workflow"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core"%>
<imui:head>
	<title>Stock Product</title>
	<workflow:workflowOpenPageCsjs />
	<script src="ui/libs/jquery-validation-1.9.0/jquery.validate.js"></script>
	<script type="text/javascript">
		
	
		function removeDetailRow(element){
			var table = document.getElementById('detail-table');
            var rowCount = table.rows.length;
            if(rowCount <= 1){
                alert("Minimum 1 product data shold be inserted");
                return;
            }
            if(element){
                //delete specific row
                element.parentNode.parentNode.remove();
            }else{
                //delete last row
                table.deleteRow(rowCount-1);
            }
			
		}
		
		
	
		var rules = {
			temp_f_nama_produk : {
				required : true
			},
			temp_f_kategori : {
				required : true
			},
			temp_f_harga : {
				required : true
			},
			temp_f_stok : {
				required : true
			},
			f_nama_toko : {
				required : true
			},
			f_alamat : {
				required : true
			}
		};

		var messages = {
			temp_f_nama_produk : {
				required : "Plese input the product name !"
			},
			temp_f_kategori : {
				required : "Please select the category !"
			},
			temp_f_harga : {
				required : "Please input harga !"
			},
			temp_f_stok : {
				required : "Please input quantity !"
			},
			f_nama_toko : {
				required : "Please input"
			},
			f_alamat : {
				required : "Please input"
			}
		};

		function callbackSuccess_contract(e, data) {
			console.log(e)
			console.log(data)
			var file = data.files[0];
			var fileName = file.name;
			var fileSize = file.size;
			var fileType = file.type;

			var receiveFile = data.result[0];
			var receiveFileName = receiveFile.name;
			var receivePhysicalFileName = receiveFile.physicalName;
			var receiveFileSize = receiveFile.size;
			var fileType = receiveFile.type;

			$(".contract_file_upload")
					.prepend(
							"<div class='"+ receivePhysicalFileName +"'>"
									+ "<input type='hidden' value='0' id='f_contract_upload_file_id' name='f_contract_upload_file_id'>"
									+ "<input type='hidden' value='"+ receiveFileName +"' id='f_contract_upload_file_name' name='f_contract_upload_file_name'>"
									+ "<input type='hidden' value='"+ receivePhysicalFileName +"' id='f_contract_upload_file_real_name' name='f_contract_upload_file_real_name'>"
									+ "</div>");
		}

		function callbackRemove_contract(e, data) {
			var file = data.response[0];
			var fileName = file.name;

			$("." + fileName).remove();
		}

		function callbackError_contract(e, data) {
			var file = data.files[0];
			var fileName = file.name;
			var fileSize = file.size;
			var fileType = file.type;
		}

		$(function() {
			stocking_detail_id = 1;
			
			$('#openPage').click(function() {
				var tbodyElement = $('.stocking_insert_detail');
			    // Get all the table rows inside the tbody
			    var rows = tbodyElement.find('tr');
			    // Get the number of rows
			    var rowCount = rows.length;
				console.log("atas bro")
				if(rowCount == 0){
					imuiAlert("Please input at least one product")
					return;
				}
				
				workflowOpenPage('${f:h(StockForm.imwPageType)}');
				return false;
			});
			
			$('#addDetail').click(()=>{
				
				
				
				var tbodyElement = $('.stocking_insert_detail');
			    // Get all the table rows inside the tbody
			    var rows = tbodyElement.find('tr');
			    // Get the number of rows
			    var rowCount = rows.length;
			
				
				if (!imuiValidate('#workflowOpenPageForm', rules, messages))
					return;
				
				console.log("ke sini22")
			
				var temp_nama_produk = $('#temp_f_nama_produk').val()
				var temp_kategori = $('#temp_f_kategori').val()
				var temp_harga = $('#temp_f_harga').val()
				var temp_stok = $('#temp_f_stok').val()
				
				console.log(temp_nama_produk)
				
				stocking_detail_id = stocking_detail_id + 1;
				$(".stocking_insert_detail").append("<tr>"
						+"<td style='display:none;'><input type='hidden' name='stocking_detail_id' value="+stocking_detail_id+"></td> "
						+"<td><input type='text' class='f_nama_produk' readonly value="+temp_nama_produk+" name='f_nama_produk' "
						+"style='width: 135px;' /></td> " 
						+"<td><input type='text' class='f_kategori' readonly value="+temp_kategori+" name='f_kategori' "
						+"style='width: 135px;' /></td> " 
						+"<td><input type='number' class='f_harga' readonly value="+temp_harga+" name='f_harga' "
						+"style='width: 135px;' /></td> " 
						+"<td><input type='number' class='f_stok' readonly value="+temp_stok+" name='f_stok' "
						+"style='width: 135px;' /></td> " 
						+"<td><input type='button' value='Delete' onClick='removeDetailRow(this)' "
						+"style='width: 135px;' /></td> " 
						+ "</tr>")
						
				
				$('#temp_f_nama_produk').val("")
				$('#temp_f_kategori').val("")
				$('#temp_f_harga').val("")
				$('#temp_f_stok').val("")
			})
		});
	</script>
</imui:head>

<workflow:workflowUserContentsAuth
	imwApplyBaseDate='${f:h(StockForm.imwApplyBaseDate)}'
	imwAuthUserCode='${f:h(StockForm.imwAuthUserCode)}'
	imwFlowId='${f:h(StockForm.imwFlowId)}'
	imwNodeId='${f:h(StockForm.imwNodeId)}'
	imwPageType='${f:h(StockForm.imwPageType)}'
	imwSystemMatterId='${f:h(StockForm.imwSystemMatterId)}'
	imwUserDataId='${f:h(StockForm.imwUserDataId)}' />
<!-- 画面タイトル -->
<div class="imui-title">
	<h1 id="h1">Stock Product</h1>
</div>

<!-- ツールバー -->
<div class="imui-toolbar-wrap">
	<div class="imui-toolbar-inner">
		<ul class="imui-list-toolbar">
			<!-- 戻るボタン -->
			<li><a href="#" class="imui-toolbar-icon" title="Back"><span
					class="im-ui-icon-common-16-back"></span></a></li>
		</ul>
	</div>
</div>




<div class="imui-form-container-narrow">
	<workflow:workflowOpenPage name="workflowOpenPageForm"
		id="workflowOpenPageForm" method="POST" target="_top"
		imwUserDataId="${f:h(StockForm.imwUserDataId)}"
		imwSystemMatterId="${f:h(StockForm.imwSystemMatterId)}"
		imwAuthUserCode="${f:h(StockForm.imwAuthUserCode)}"
		imwApplyBaseDate="${f:h(StockForm.imwApplyBaseDate)}"
		imwNodeId="${f:h(StockForm.imwNodeId)}"
		imwFlowId="${f:h(StockForm.imwFlowId)}"
		imwCallOriginalParams="${f:h(StockForm.imwCallOriginalParams)}"
		imwNextScriptPath="${f:h(StockForm.imwCallOriginalPagePath)}">




		<div class="imui-form-container-full">
			<!-- h2 -->
			<div class="imui-chapter-title">
				<h2>Insert Toko</h2>
			</div>

			<table class="imui-form">
				<tbody>	
					<tr>
						<th><label for="f_nama_toko">Nama Toko</label></th>
						<td><input type="text" class="f_nama_toko" name="f_nama_toko"
							style="width: 135px;"></td>
					</tr>
					<tr>
						<th><label for="f_alamat">Alamat</label></th>
						<td><input type="text" class="f_alamat" name="f_alamat"
							style="width: 135px;"></td>
					</tr>
				</tbody>
			</table>
		</div>
		
		
		
		
		
		<div style="overflow-x: scroll"
			class="imui-box-article horizontal-scroll-wrapper squares">
			<div class="imui-form-container-full">
				<div class="imui-chapter-title">
					<h2>Insert Product</h2>
				</div>
				
				<table class="imui-form" id="detail-table">
					<tbody>
                    <tr>
                        <th><label for="temp_f_nama_produk">Nama Produk</label></th>
                        <td><imui:textbox id="temp_f_nama_produk" name="temp_f_nama_produk"
                                style="width: 200px;" /></td>
                    </tr>
                    <tr>
                        <th><label for="temp_f_kategori">Kategori</label></th>
                        <td><select name="temp_f_kategori" id="temp_f_kategori"
                            style="width: 212px;">
                                <option value="" selected disabled>Select Category :</option>
                                <option value="sepatuLari">Sepatu Lari</option>
                                <option value="sepatuFutsal">Sepatu Futsal</option>
                                <option value="sepatuBola">Sepatu Bola</option>
                                <option value="sepatuBadminton">Sepatu Badminton</option>
                                <option value="kaosJersey">Kaos jersey</option>
                                <option value="celanaPendekJersey">Celana Pendek Jersey</option>
                                <option value="celanaTraining">Celana Training</option>
                                <option value="jaketTraining">Jaket Training</option>
                                <option value="bolaFutsal">Bola Futsal</option>
                                <option value="bolaBasket">Bola Basket</option>
                                <option value="shuttleCock">ShuttleCock</option>
                        </select></td>
                    </tr>
                    <tr>
                        <th><label for="temp_f_harga">harga</label></th>
                        <td><input type="number" id="temp_f_harga" name="temp_f_harga"
                            style="width: 200px;"></td>
                    </tr>
                    <tr>
                        <th><label for="temp_f_stok">Stok</label></th>
                        <td><input type="number" id="temp_f_stok" name="temp_f_stok"
                            style="width: 200px;"></td>
                    </tr>
                	</tbody>
                	
                </table>
                <div class="imui-operation-parts">
					<input type="button" value='Add Detail' id="addDetail"
						name="addDetail" class="imui-small-button" 
						style="color: blue;margin-bottom: 15px;"/>
				</div>
                	
                <table class="imui-form" id="detail-table">
					<tr>
						<th style="width: 135px;"><label for="f_nama_produk" >Nama Produk</label></th>
						<th style="width: 135px;"><label for="f_kategori">Kategori</label></th>
						<th style="width: 135px;"><label for="harga">harga</label></th>
						<th style="width: 135px;"><label for="stok">Stok</label></th>
						<th style="width: 135px;"><label for="stok">Delete</label></th>
					</tr>
					<tbody class="stocking_insert_detail">
					
					</tbody>
				</table>
			</div>
		</div>

		<div class="contract_file_upload">
			<c:forEach items="${ContractFormClassRows.d_list_info_temp_file}"
				var="v_item_file">
				<div class="${v_item_file.file_real_name}">
					<input type='hidden' value='0' id='f_contract_upload_file_id'
						name='f_contract_upload_file_id'> <input type='hidden'
						value="${v_item_file.file_name}" id='f_contract_upload_file_name'
						name='f_contract_upload_file_name'> <input type='hidden'
						value="${v_item_file.file_real_name}"
						id='f_contract_upload_file_real_name'
						name='f_contract_upload_file_real_name'>
				</div>
			</c:forEach>
		</div>
	</workflow:workflowOpenPage>
</div>

	<div class="imui-form-container-narrow">
		<header class="imui-chapter-title">
			<h2>Upload Lampiran Product</h2>
		</header>
		<table class="imui-form">
			<tbody>
				<tr>
					<th width="200"><label class="imui-required">Upload
							File</label></th>
					<td><imui:fileUpload enableDelete="true" uniqueFileName="true"
							storeTo="gpr_contract_flow/" onSuccess="callbackSuccess_contract"
							onError="callbackError_contract"
							onRemove="callbackRemove_contract" /></td>
				</tr>
			</tbody>
		</table>
	</div>
	<br>
	<div class="imui-operation-parts">
		<imart:decision case="0" value="${f:h(StockForm.imwPageType)}">
			<input type="button" value='Apply' id="openPage" name="openPage"
				class="imui-large-button" escapeXml="true" escapeJs="false" />
		</imart:decision>
		<imart:decision case="3" value="${f:h(StockForm.imwPageType)}">
			<input type="button" value='Re-Apply' id="openPage" name="openPage"
				class="imui-large-button" escapeXml="true" escapeJs="false" />
		</imart:decision>
	</div>