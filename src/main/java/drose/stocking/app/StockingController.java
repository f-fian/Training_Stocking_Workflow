package drose.stocking.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import drose.stocking.service.app.StockingWorkflowService;

import org.springframework.ui.Model;
import jp.co.intra_mart.foundation.workflow.code.PageType;
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
}