<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="imui" uri="http://www.intra-mart.co.jp/taglib/imui"%>
<%@ taglib prefix="imart" uri="http://www.intra-mart.co.jp/taglib/core/standard"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="f" uri="http://terasoluna.org/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="workflow" uri="http://www.intra-mart.co.jp/taglib/imw/workflow" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<imui:head>
    <title>Stock Produck</title>
    <workflow:workflowOpenPageCsjs />
    <script src="ui/libs/jquery-validation-1.9.0/jquery.validate.js"></script>
    <script type="text/javascript">
        var rules = {
            nama_produk : {
                required : true
            },
            kategori : {
                required : true
            },
            harga : {
                required : true
            },
            stok : {
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
                kategori : {
                    required : "Please select the category !"
                },
                harga : {
                    required : "Please input harga !"
                },
                stok : {
                    required : "Please input quantity !"
                },
                nama_toko : {
                    required : "Please input"
                },
                alamat : {
                    required : "Please input"
                }
            };
       
        $(function() {
            $('#openPage').click(function(){
                if (!imuiValidate('#workflowOpenPageForm', rules, messages)) return;
                console.log("workflow:workflowUserContentsAuth")
                console.log("imwApplyBaseDate : "+'${f:h(StockForm.imwApplyBaseDate)}')
                console.log("imwAuthUserCode : " +'${f:h(StockForm.imwAuthUserCode)}')
                console.log("imwFlowId : " + '${f:h(StockForm.imwFlowId)}')
                console.log("imwNodeId : " + '${f:h(StockForm.imwNodeId)}')
                console.log("imwPageType : " +'${f:h(StockForm.imwPageType)}')
                console.log("imwSystemMatterId : " + '${f:h(StockForm.imwSystemMatterId)}')
                console.log("imwUserDataId : " + '${f:h(StockForm.imwUserDataId)}')
             	console.log("---------------------------------------------")
             	console.log("workflowOpenPageForm")
             	
	           imwUserDataId="${f:h(StockForm.imwUserDataId)}"
	           imwSystemMatterId="${f:h(StockForm.imwSystemMatterId)}"
	           imwAuthUserCode="${f:h(StockForm.imwAuthUserCode)}"
	           imwApplyBaseDate="${f:h(StockForm.imwApplyBaseDate)}"
	           imwNodeId="${f:h(StockForm.imwNodeId)}"
	           imwFlowId="${f:h(StockForm.imwFlowId)}"
	           imwCallOriginalParams="${f:h(StockForm.imwCallOriginalParams)}"
	           imwNextScriptPath="${f:h(StockForm.imwCallOriginalPagePath)}"
	           
	           console.log("imwUserDataId : " +"${f:h(StockForm.imwUserDataId)}")
	           console.log("imwSystemMatterId : " + "${f:h(StockForm.imwSystemMatterId)}")
	           console.log("imwAuthUserCode : " + "${f:h(StockForm.imwAuthUserCode)}")
	           console.log("imwApplyBaseDate : " + "${f:h(StockForm.imwApplyBaseDate)}")
	           console.log("imwNodeId : " + "${f:h(StockForm.imwNodeId)}")
	           console.log("imwFlowId : " + "${f:h(StockForm.imwFlowId)}")
	           console.log("imwCallOriginalParams : " + "${f:h(StockForm.imwCallOriginalParams)}")
	           console.log("imwCallOriginalPagePath : " + "${f:h(StockForm.imwCallOriginalPagePath)}")
               
                workflowOpenPage('${f:h(StockForm.imwPageType)}');
                
                
                return false;
            });
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

<workflow:workflowUserContentsAuth imwApplyBaseDate='${f:h(StockForm.imwApplyBaseDate)}'
            imwAuthUserCode = '${f:h(StockForm.imwAuthUserCode)}'
            imwFlowId='${f:h(StockForm.imwFlowId)}'
            imwNodeId ='${f:h(StockForm.imwNodeId)}'
            imwPageType = '${f:h(StockForm.imwPageType)}'
            imwSystemMatterId='${f:h(StockForm.imwSystemMatterId)}'
            imwUserDataId='${f:h(StockForm.imwUserDataId)}'/>
<!-- 画面タイトル -->
<div class="imui-title">
    <h1 id="h1">Stock Produck</h1>
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
           id="workflowOpenPageForm"
           method="POST"
           target="_top"
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
        <h2>Insert New Product</h2>
    </div>
        <table class="imui-form">
            <tbody>
                <tr>
                    <th><label  for="nama_produk">Nama Produk</label></th>
                    <td><imui:textbox id="nama_produk" name="nama_produk" style="width: 200px;" /></td>
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
<div class="imui-operation-parts">
    <imart:decision case="0" value="${f:h(StockForm.imwPageType)}">
        <input type="button" value='Apply' id="openPage" name="openPage" class="imui-large-button" escapeXml="true" escapeJs="false" />
    </imart:decision>
    <imart:decision case="3" value="${f:h(StockForm.imwPageType)}">
        <input type="button" value='Re-Apply' id="openPage" name="openPage" class="imui-large-button" escapeXml="true" escapeJs="false" />
    </imart:decision>
</div>