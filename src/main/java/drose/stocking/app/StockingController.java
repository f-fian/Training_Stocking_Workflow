package drose.stocking.app;

import java.io.FileNotFoundException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.HandlerMapping;

import drose.stocking.model.StockingInfoFileModel;
import drose.stocking.repository.StockingInfoFileRepo;
import drose.stocking.service.app.StockingWorkflowService;


import org.springframework.ui.Model;
import jp.co.intra_mart.foundation.workflow.code.PageType;
import jp.co.intra_mart.foundation.service.client.file.PublicStorage;
import jp.co.intra_mart.foundation.service.client.information.Identifier;

@Controller
@RequestMapping("stock/")
public class StockingController {
    
    
    @RequestMapping(value = "apply")
    public final String apply(final Model model, StockingForm stockForm) throws Exception {
        
        if (PageType.pageTyp_App.toString().equals(stockForm.getImwPageType())) {
            // Apply Screen
            String userDataId = "";
            final Identifier identifier = new Identifier();
            userDataId = identifier.get();
             // add to service StockingHeaderInfoService
            stockForm.setImwUserDataId(userDataId);
        } else if (PageType.pageTyp_UnApp.toString().equals(stockForm.getImwPageType())) {
            // Draft Screen
        } else {
            // TempSave or Reapply Screen
//          try {
//          } catch (Exception e) {
//              System.out.println("error approve"+ e);
//          }
        }
//      model.addAttribute("ContractFormClassRows", ContractFormClassRows);
        model.addAttribute("StockForm", stockForm);
        return "drose/stocking/apply.jsp";
    }
    
    
    @RequestMapping(value = "detail")
    public final String detail(final Model model, final StockingForm stockingForm) throws Exception {
        StockingWorkflowService stockingService = new StockingWorkflowService();
        StockingForm stockingFormClassRows = new StockingForm();
        stockingFormClassRows = stockingService.getStockingInfoTemp(stockingForm.getImwSystemMatterId());
        model.addAttribute("StockingFormClassRows", stockingFormClassRows);
        model.addAttribute("StockingForm", stockingForm);
        return "drose/stocking/detail.jsp";
    }
    
    
    @RequestMapping(value = "approve")
    public final String approve(final Model model, final StockingForm stockingForm) throws Exception {
        StockingWorkflowService stockingService = new StockingWorkflowService();
        StockingForm StockingFormClassRows = new StockingForm();
        StockingFormClassRows = stockingService.getStockingInfoTemp(stockingForm.getImwSystemMatterId());
//      JSONObject dataJSON = stockingService.getRequestBodyFullSale(StockingForm.getImwSystemMatterId());
//      System.out.println(dataJSON);
        model.addAttribute("StockingFormClassRows", StockingFormClassRows);
        model.addAttribute("StockingForm", stockingForm);
        return "drose/stocking/approve.jsp";
    }
    
    
    @RequestMapping(value = "download/**")
	public String download(final Model model, HttpServletRequest request) throws Exception {
		String urlStr = (String) request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE);
		StringBuilder urlStrBldr = new StringBuilder(urlStr);
		StringBuilder fileId = urlStrBldr.delete(0, 16);
		System.out.println("FileId");
		System.out.println(fileId);
		
		StockingInfoFileRepo infoFileRepository = new StockingInfoFileRepo();
		List<StockingInfoFileModel> rows_file = new ArrayList<>(infoFileRepository.selectStockingInfoTempFile(fileId.toString(), "id"));
		String file_name = rows_file.get(0).getFile_name();
		String file_real_path = rows_file.get(0).getFile_path();
		String file_decode = URLDecoder.decode(file_real_path.toString(), "UTF-8");
		final PublicStorage storage = new PublicStorage(file_decode);
		if (!storage.isFile()) {
			throw new FileNotFoundException("Could not find a file");
		}
		model.addAttribute("download_file_name", file_name);
		model.addAttribute("storage", storage);
		return "ContractDownloadService.Downloadview";
	}
}	