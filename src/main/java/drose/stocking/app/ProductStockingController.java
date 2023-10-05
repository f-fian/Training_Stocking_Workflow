package drose.stocking.app;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import drose.stocking.model.StockForm;
import org.springframework.ui.Model;
import jp.co.intra_mart.foundation.workflow.code.PageType;
import jp.co.intra_mart.foundation.service.client.information.Identifier;

@Controller
@RequestMapping("stock")
public class ProductStockingController {
    
    @ResponseBody
    @RequestMapping(value = "apply")
    public final String apply(final Model model, StockForm stockForm) throws Exception {
        
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
        return "gpr/contract/apply.jsp";
    }
}
