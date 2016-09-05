package xinyongbang.interfaces.role.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import xinyongbang.application.role.IRoleAppService;
import xinyongbang.application.role.command.CreateRoleCommand;
import xinyongbang.application.role.command.EditRoleCommand;
import xinyongbang.application.role.command.ListRoleCommand;
import xinyongbang.application.role.representation.RoleRepresentation;
import xinyongbang.application.shared.command.SharedCommand;
import xinyongbang.core.exception.ConcurrencyException;
import xinyongbang.core.exception.ExistException;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;
import xinyongbang.interfaces.shared.web.AlertMessage;
import xinyongbang.interfaces.shared.web.BaseController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by YJH on 2016/3/31.
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private IRoleAppService roleAppService;

    @RequestMapping(value = "/pagination")
    public ModelAndView pagination(ListRoleCommand command) {
        return new ModelAndView("/role/list", "pagination", roleAppService.pagination(command)).addObject("command", command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView create(@ModelAttribute("command") CreateRoleCommand command) {
        List<String> data = new ArrayList<String>();
        command.setPermissions(data);
        return new ModelAndView("/role/create", "command", command);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView create(@Valid @ModelAttribute("command") CreateRoleCommand command, BindingResult bindingResult,
                               RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/role/create", "command", command);
        }
        AlertMessage alertMessage;
        RoleRepresentation role;
        try {
            role = roleAppService.create(command);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            if (e.getMessage().indexOf("AppKey") != -1) {
                alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                        this.getMessage("appKey.id.not.found.messages", new Object[]{command.getAppKey()}, locale));
            } else {
                alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                        this.getMessage("permission.id.not.found.messages", new Object[]{command.getPermissions()}, locale));
            }
            return new ModelAndView("/role/create", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (ExistException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("role.name.Exist.messages", new Object[]{command.getName()}, locale));
            return new ModelAndView("/role/create", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        logger.info("创建Role[" + role.getName() + "]信息成功,时间:" + new Date());
        alertMessage = new AlertMessage(this.getMessage("default.create.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", role.getId());
        return new ModelAndView("redirect:/role/info/{id}");
    }

    @RequestMapping(value = "/info/{id}")
    public ModelAndView info(@PathVariable String id, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        RoleRepresentation role;
        try {
            role = roleAppService.searchByID(id);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(this.getMessage("role.id.not.found.messages", new Object[]{id}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/role/pagination");
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/role/info", "role", role);
    }

    @RequestMapping(value = "/edit/{id}")
    public ModelAndView edit(@PathVariable String id, @ModelAttribute("command") EditRoleCommand command,
                             RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        RoleRepresentation role;
        try {
            role = roleAppService.searchByID(id);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(this.getMessage("role.id.not.found.messages", new Object[]{id}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/role/pagination");
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }
        return new ModelAndView("/role/edit", "role", role).addObject("command", command);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ModelAndView edit(@Valid @ModelAttribute("command") EditRoleCommand command, BindingResult bindingResult,
                             RedirectAttributes redirectAttributes, Locale locale) {
        if (bindingResult.hasErrors()) {
            return new ModelAndView("/role/edit", "command", command);
        }
        AlertMessage alertMessage;
        RoleRepresentation role;
        try {
            role = roleAppService.edit(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("default.optimistic.locking.failure", new Object[]{command.getName()}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());
            return new ModelAndView("redirect:/role/edit/{id}");
        } catch (ExistException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("role.name.Exist.messages", new Object[]{command.getName()}, locale));
            return new ModelAndView("/role/edit", "command", command)
                    .addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            if (e.getMessage().indexOf("AppKey") != -1) {
                alertMessage = new AlertMessage(this.getMessage("appKey.id.not.found.messages", new Object[]{command.getAppKey()}, locale));
            } else if (e.getMessage().indexOf("Permission") != -1) {
                alertMessage = new AlertMessage(this.getMessage("permission.id.not.found.messages", new Object[]{command.getPermissions()}, locale));
            } else {
                alertMessage = new AlertMessage(this.getMessage("role.id.not.found.messages", new Object[]{command.getId()}, locale));
                redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
                return new ModelAndView("redirect:/role/pagination");
            }
            return new ModelAndView("/role/edit", "command", command).addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        logger.info("修改Role[" + role.getName() + "]信息成功,时间:" + new Date());
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        redirectAttributes.addAttribute("id", role.getId());
        return new ModelAndView("redirect:/role/info/{id}");
    }

    @RequestMapping(value = "/update_status")
    public ModelAndView updateStatus(SharedCommand command, RedirectAttributes redirectAttributes, Locale locale) {
        AlertMessage alertMessage;
        try {
            roleAppService.updateStatus(command);
        } catch (ConcurrencyException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.WARNING,
                    this.getMessage("default.optimistic.locking.failure", new Object[]{command.getId()}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            redirectAttributes.addAttribute("id", command.getId());
            return new ModelAndView("redirect:/role/pagination");
        } catch (NoFoundException e) {
            logger.warn(e.getMessage());
            alertMessage = new AlertMessage(this.getMessage("role.id.not.found.messages", new Object[]{command.getId()}, locale));
            redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
            return new ModelAndView("redirect:/role/pagination");
        } catch (Exception e) {
            logger.error(e.getMessage());
            alertMessage = new AlertMessage(AlertMessage.MessageType.DANGER,
                    this.getMessage("default.system.exception", new Object[]{e.getMessage()}, locale));
            return new ModelAndView("/error/500").addObject(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        }

        logger.info("修改Role[" + command.getId() + "]状态成功,时间:" + new Date());
        alertMessage = new AlertMessage(this.getMessage("default.edit.success.messages", null, locale));
        redirectAttributes.addFlashAttribute(AlertMessage.MODEL_ATTRIBUTE_KEY, alertMessage);
        return new ModelAndView("redirect:/role/pagination");
    }

    @RequestMapping(value = "/list")
    @ResponseBody
    public Pagination<RoleRepresentation> list(@RequestBody ListRoleCommand command) {
        return roleAppService.paginationJSON(command);
    }

}
