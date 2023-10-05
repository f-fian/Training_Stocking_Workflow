<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="imui" uri="http://www.intra-mart.co.jp/taglib/imui"%>
<%@ taglib prefix="imart" uri="http://www.intra-mart.co.jp/taglib/core/standard"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://terasoluna.org/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="workflow" uri="http://www.intra-mart.co.jp/taglib/imw/workflow" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>






<imui:head>
    <title>Untitled</title>
    <workflow:workflowOpenPageCsjs />
    <script src="ui/libs/jquery-validation-1.9.0/jquery.validate.js"></script>
    <script type="text/javascript">
        var rules = {
            nama_produk : {
                required : true
            },
            harga : {
                required : true
            },
            stok : {
                required : true
            },
            kategori : {
                required : true
            },
            nama_toko : {
                required : true
            },
            alamat : {
                required : true
            }
        };
        var messages = {
        	nama_produk : {
                required : "Plese input the product name !"
            },
            harga : {
                required : "Please input harga !"
            },
            stok : {
                required : "Please input quantity !"
            },
            kategori : {
                required : "Please select the category !"
            },
            nama_toko : {
                required : true
            },
            alamat : {
                required : true
            }
        };
        $(function() {
        	
        	$('#openPage').click(function(){
        		imwUserDataId="${f:h(ContractForm.imwUserDataId)}"
        	           imwSystemMatterId="${f:h(ContractForm.imwSystemMatterId)}"
        	           imwAuthUserCode="${f:h(ContractForm.imwAuthUserCode)}"
        	           imwApplyBaseDate="${f:h(ContractForm.imwApplyBaseDate)}"
        	           imwNodeId="${f:h(ContractForm.imwNodeId)}"
        	           imwFlowId="${f:h(ContractForm.imwFlowId)}"
        	           imwCallOriginalParams="${f:h(ContractForm.imwCallOriginalParams)}"
        	           imwNextScriptPath="${f:h(ContractForm.imwCallOriginalPagePath)}";
        	    
        	    console.log("PUSING")
        	    console.log("${f:h(ContractForm)}")
        	    console.log("userDataid : "+"${f:h(ContractForm.imwUserDataId)}")
        	    console.log("systemMatterId : "+ "${f:h(ContractForm.imwSystemMatterId)}");
        	    console.log("AuthUserCode : "+"${f:h(ContractForm.imwAuthUserCode)}")
        	    console.log("imwApplyBaseDate : "+"${f:h(ContractForm.imwApplyBaseDate)}")
        	    console.log("imwNodeId : "+"${f:h(ContractForm.imwNodeId)}")
        	    console.log("imwFlowId : "+"${f:h(ContractForm.imwFlowId)}")
        	    console.log("imwCallOriginalParams : "+"${f:h(ContractForm.imwCallOriginalParams)}")
        	    console.log("imwCallOriginalPagePath : "+"${f:h(ContractForm.imwCallOriginalPagePath)}")
        	    console.log("ABISSS")
				workflowOpenPage('${f:h(ContractForm.imwPageType)}');
				return false;
			});
            $("#clearbutton").click(()=>{
                $("#nama_produk").val("");
                $("#harga").val("");
                $("#stok").val("");
            })
        });

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
    </script>
</imui:head>
<!-- 画面タイトル -->
<div class="imui-title">
    <h1 id="h1">Insert New Product 11</h1>
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
<workflow:workflowOpenPage name="workflowOpenPageForm"
           id="workflowOpenPageForm"
           method="POST"
           target="_top"
           imwUserDataId="${f:h(ContractForm.imwUserDataId)}"
           imwSystemMatterId="${f:h(ContractForm.imwSystemMatterId)}"
           imwAuthUserCode="${f:h(ContractForm.imwAuthUserCode)}"
           imwApplyBaseDate="${f:h(ContractForm.imwApplyBaseDate)}"
           imwNodeId="${f:h(ContractForm.imwNodeId)}"
           imwFlowId="${f:h(ContractForm.imwFlowId)}"
           imwCallOriginalParams="${f:h(ContractForm.imwCallOriginalParams)}"
           imwNextScriptPath="${f:h(ContractForm.imwCallOriginalPagePath)}">
<div class="imui-form-container-narrow">
    <!-- h2 -->
    <div class="imui-chapter-title">
        <h2>Insert New Product</h2>
    </div>
    <form:form id="insertForm" name="insertForm" method="POST"
        class="target_form mt-10" action="product/insert/data"
        modelAttribute="insertProductData">
        <table class="imui-form">
            <tbody>
                <tr>
                    <th><label  for="nama_produck">Nama Produk</label></th>
                    <td><imui:textbox id="nama_produck" name="nama_produck" style="width: 200px;" /></td>
                </tr>
                <tr>
                    <th><label for="kategori">Kategori</label></th>
                    <td><select name="kategori" id="kategori"
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
                    <th><label for="harga">harga</label></th>
                    <td><input type="number" id="harga" name="harga"
                        style="width: 200px;"></td>
                </tr>
                <tr>
                    <th><label for="stok">Stok</label></th>
                    <td><input type="number" id="stok"
                        name="stok" style="width: 200px;"></td>
                </tr>
                <tr>
                    <th><label for="nama_toko">Nama Toko</label></th>
                    <td><input type="text" id="nama_toko"
                        name="nama_toko" style="width: 200px;"></td>
                </tr>
                <tr>
                    <th><label for="alamat">Alamat</label></th>
                    <td><input type="text" id="alamat"
                        name="alamat" style="width: 200px;"></td>
                </tr>
            </tbody>
        </table>

    </form:form>


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
<div class="contract_file_upload">
	<c:forEach items="${ContractFormClassRows.d_list_info_temp_file}" var="v_item_file">
		<div class="${v_item_file.file_real_name}">
			<input type='hidden' value='0' id='f_contract_upload_file_id' name='f_contract_upload_file_id'>
			<input type='hidden' value="${v_item_file.file_name}" id='f_contract_upload_file_name' name='f_contract_upload_file_name' >
			<input type='hidden' value="${v_item_file.file_real_name}" id='f_contract_upload_file_real_name' name='f_contract_upload_file_real_name'>
		</div>
</c:forEach>
</div>
</workflow:workflowOpenPage>


<div class="imui-operation-parts">
	<imart:decision case="0" value="${f:h(ContractForm.imwPageType)}">
		<input type="button" value='Apply1' id="openPage" name="openPage" class="imui-large-button" escapeXml="true" escapeJs="false" />
	</imart:decision>
	<imart:decision case="3" value="${f:h(ContractForm.imwPageType)}">
		<input type="button" value='Re-Apply2' id="openPage" name="openPage" class="imui-large-button" escapeXml="true" escapeJs="false" />
	</imart:decision>
</div>

