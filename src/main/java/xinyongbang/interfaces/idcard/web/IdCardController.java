package xinyongbang.interfaces.idcard.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xinyongbang.application.idcard.IIdCardAppService;
import xinyongbang.application.idcard.command.ListIdCardCommand;
import xinyongbang.application.idcard.representation.IdCardRepresentation;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.interfaces.shared.web.AlertMessage;
import xinyongbang.interfaces.shared.web.BaseController;

import java.util.Locale;

/**
 * Created by dyp on 2016/5/23.
 */
@Controller
@RequestMapping("/id_card")
public class IdCardController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IIdCardAppService idCardAppService;

    @RequestMapping(value = "/pagination")
    public ModelAndView pagination(ListIdCardCommand command) {
        return new ModelAndView("idCard/list", "pagination", idCardAppService.pagination(command)).addObject("command", command);
    }

    @RequestMapping(value = "/info/{id}")
    public ModelAndView info(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        IdCardRepresentation idCard;
        try {
            idCard = idCardAppService.searchByID(id);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(this.getMessage("idCard.id.not.found.message", new Object[]{id}, locale));
            redirectAttributes.addAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/id_card/pagination");
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception",new Object[]{e.getMessage()},locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY,alertMessage);
        }
        return new ModelAndView("/idCard/info","idCard",idCard);
    }
}
