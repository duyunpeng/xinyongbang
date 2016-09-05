package xinyongbang.interfaces.identitycard.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xinyongbang.application.feedback.command.EditFeedbackCommand;
import xinyongbang.application.identitycard.command.CreateIdentityCardCommand;
import xinyongbang.application.identitycard.command.EditIdentityCardCommand;
import xinyongbang.application.identitycard.command.ListIdentityCardCommand;
import xinyongbang.application.identitycard.IIdentityCardAppService;
import xinyongbang.application.identitycard.representation.IdentityCardRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.interfaces.shared.web.AlertMessage;
import xinyongbang.interfaces.shared.web.BaseController;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by dyp on 2016/4/20.
 */
@Controller
@RequestMapping("/identity_card")
public class IdentityCardController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IIdentityCardAppService identityCardAppService;

    @RequestMapping(value = "/pagination")
    public ModelAndView pagination(ListIdentityCardCommand command) {
        return new ModelAndView("/identityCard/list", "pagination", identityCardAppService.pagination(command))
                .addObject("command", command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute("command") CreateIdentityCardCommand command) {
        return new ModelAndView("/identityCard/create", "command", command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") CreateIdentityCardCommand command, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/identityCard/create", "command", command);
        }
        AlertMessage alertMessage;
        IdentityCardRepresentation identityCard;
        try {
            identityCard = identityCardAppService.create(command);
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        logger.info("创建Area[" + identityCard.getCardNumber() + "]信息成功,时间:" + new Date());
        alertMessage = new AlertMessage(this.getMessage("default.create.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", identityCard.getId());
        return new ModelAndView("redirect:/identity_card/info/{id}");
    }

    @RequestMapping(value = "/info/{id}")
    public ModelAndView info(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        IdentityCardRepresentation identityCard;
        try {
            identityCard = identityCardAppService.searchByID(id);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(this.getMessage("identityCard.id.not.found.message",new Object[]{id},locale));
            redirectAttributes.addAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY,alertMessage);
            return new ModelAndView("redirect:/identity_card/pagination");
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/identityCard/info", "identityCard", identityCard);
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable String id, @ModelAttribute("commamd") EditFeedbackCommand command,
                             RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        IdentityCardRepresentation identityCard;
        try {
            identityCard = identityCardAppService.searchByID(id);
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("identityCard/edit", "identityCard", identityCard).addObject("command", command);
    }

    @RequestMapping(value = "/identity_card/{id}")
    public ModelAndView edit(@Valid @ModelAttribute("command") EditIdentityCardCommand command, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/role/edit", "command", command);
        }
        AlertMessage alertMessage;
        IdentityCardRepresentation identityCard;
        try {
            identityCard = identityCardAppService.edit(command);
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        logger.info("修改Area[" + identityCard.getCardNumber() + "]信息成功,时间:" + new Date());
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", identityCard.getId());
        return new ModelAndView("redirect:/identity_card/info/{id}");
    }

    @RequestMapping(value = "/update_status")
    public ModelAndView updateStatus(SharedCommand command, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        try {
            identityCardAppService.updateStatus(command);
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        logger.info("修改Area[" + command.getId() + "]状态成功,时间:" + new Date());
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/identity_card/pagination");
    }

    @RequestMapping(value = "/search")
    @ResponseBody
    public List<IdentityCardRepresentation> search(@RequestBody ListIdentityCardCommand command) {
        return identityCardAppService.listJSON(command);
    }
}


