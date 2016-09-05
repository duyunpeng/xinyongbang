package xinyongbang.interfaces.golddetailed.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xinyongbang.application.golddetailed.IGoldDetailedAppService;
import xinyongbang.application.golddetailed.command.ListGoldDetailedCommand;
import xinyongbang.application.golddetailed.representation.GoldDetailedRepresentation;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.interfaces.shared.web.AlertMessage;
import xinyongbang.interfaces.shared.web.BaseController;

import java.util.Locale;

/**
 * Created by YJH on 2016/4/19.
 */
@Controller
@RequestMapping("/gold_detailed")
public class GoldDetailedController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IGoldDetailedAppService goldDetailedAppService;

    @RequestMapping(value = "/pagination")
    public ModelAndView pagination(ListGoldDetailedCommand command) {
        return new ModelAndView("/goldDetailed/list", "pagination", goldDetailedAppService.pagination(command))
                .addObject("command", command);
    }

    @RequestMapping(value = "/info/{id}")
    public ModelAndView info(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        GoldDetailedRepresentation goldDetailed;
        try {
            goldDetailed = goldDetailedAppService.searchByID(id);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(this.getMessage("goldDetailed.id.not.found.messages", new Object[]{id}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/gold_detailed/pagination");
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/goldDetailed/info", "goldDetailed", goldDetailed);
    }

}
