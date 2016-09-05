package xinyongbang.interfaces.report.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xinyongbang.application.report.IReportAppService;
import xinyongbang.application.report.command.EditReportCommand;
import xinyongbang.application.report.command.ListReportCommand;
import xinyongbang.application.report.representation.ReportRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.exception.ConcurrencyException;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.interfaces.shared.web.AlertMessage;
import xinyongbang.interfaces.shared.web.BaseController;

import javax.validation.Valid;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2016/4/19.
 */
@Controller
@RequestMapping("/report")
public class ReportController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IReportAppService reportAppService;

    /**
     * 举报列表
     */
    @RequestMapping(value = "/pagination")
    public ModelAndView pagination(ListReportCommand command) {

        return new ModelAndView("/report/list", "pagination", reportAppService.pagination(command))
                .addObject("command", command);
    }

//    @RequestMapping(value = "/create", method = RequestMethod.GET)
//    public ModelAndView create(@ModelAttribute("command") CreateReportCommand command) {
//        return new ModelAndView("/report/create", "command", command);
//    }
//
//    /**
//     * 发起举报
//     */
//    @RequestMapping(value = "/create", method = RequestMethod.POST)
//    public ModelAndView create(@Valid @ModelAttribute("command") CreateReportCommand command, BindingResult bindingResult,
//                               RedirectAttributes redirectAttributes, Locale locale) {
//        if (bindingResult.hasErrors()) {
//            return new ModelAndView("/report/create", "command", command);
//        }
//        AlertMessage alertMessage;
//        ReportRepresentation report;
//        try {
//            report = reportAppService.create(command);
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
//                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
//            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
//        }
//        logger.info("创建Report[" + report.getQuiltReportUser() + "]信息成功,时间:" + new Date());
//        alertMessage = new AlertMessage(this.getMessage("default.create.success.messages", null, locale));
//        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
//        redirectAttributes.addAttribute("id", report.getId());
//        return new ModelAndView("redirect:/report/info/{id}");
//    }

    /**
     * 查看举报详情
     */
    @RequestMapping(value = "/info/{id}")
    public ModelAndView info(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        ReportRepresentation report;
        try {
            report = reportAppService.searchByID(id);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(this.getMessage("report.id.not.found.messages", new Object[]{id}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/report/pagination");
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("report/info", "report", report);
    }

    /**
     *
     * 处理举报
     */
    @RequestMapping(value = "/deal_report")
    public ModelAndView deal(@Valid @ModelAttribute("command") SharedCommand command, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        try {
            reportAppService.updateHandleStatus(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("default.optimistic.locking.failure", new Object[]{command.getId()}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());
            return new ModelAndView("redirect:/report/pagination");
        }catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);

        }
        logger.info("处理[" + command.getId() + "]成功,时间:" + new Date());
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/report/pagination");
    }

    /**
     * 完成处理举报
     */
    @RequestMapping(value = "/finish_report")
    public ModelAndView finish(@Valid @ModelAttribute("command") EditReportCommand command, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        try {
            reportAppService.finishReport(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("default.optimistic.locking.failure", new Object[]{command.getId()}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());
            return new ModelAndView("redirect:/report/pagination");
        }catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);

        }
        logger.info("处理[" + command.getId() + "]成功,时间:" + new Date());
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/report/pagination");
    }
}