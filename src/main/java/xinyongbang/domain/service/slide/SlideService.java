package xinyongbang.domain.service.slide;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xinyongbang.application.picture.command.CreatePictureCommand;
import xinyongbang.application.slide.command.CreateSlideCommand;
import xinyongbang.application.slide.command.EditSlideCommand;
import xinyongbang.application.slide.command.ListSlideCommand;
import xinyongbang.core.exception.NoFoundException;
import xinyongbang.core.upload.IFileUploadService;
import xinyongbang.domain.model.picture.Picture;
import xinyongbang.domain.model.slide.ISlideRepository;
import xinyongbang.domain.model.slide.Slide;
import xinyongbang.domain.service.picture.IPictureService;
import xinyongbang.infrastructure.persistence.hibernate.generic.Pagination;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dyp on 2016/5/20.
 */
@Service("slideService")
public class SlideService implements ISlideService {

    @Autowired
    private ISlideRepository<Slide, String> slideRepository;

    @Autowired
    private IPictureService pictureService;

    @Autowired
    private IFileUploadService fileUploadService;

    @Override
    public Pagination<Slide> pagination(ListSlideCommand command) {
        List<Criterion> criterionList = new ArrayList<Criterion>();
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        return slideRepository.pagination(command.getPage(), command.getPageSize(), criterionList, orderList, null);
    }

    @Override
    public List<Slide> allList() {
        List<Order> orderList = new ArrayList<Order>();
        orderList.add(Order.desc("createDate"));
        orderList.add(Order.asc("sort"));
        return slideRepository.list(null, orderList);
    }

    @Override
    public Slide searchByID(String id) {
        Slide slide = slideRepository.getById(id);
        if (null == slide) {
            throw new NoFoundException("没有找到ID[" + id + "]的Slide数据");
        }
        return slide;
    }

    @Override
    public Slide create(CreateSlideCommand command) {

        CreatePictureCommand picCommand = fileUploadService.moveToImg(command.getPicture());
        picCommand.setDescribes("首页幻灯片图片");
        Picture picture = pictureService.create(picCommand);

        Slide slide = new Slide(command.getSort(), picture);
        slideRepository.save(slide);
        return slide;
    }

    @Override
    public Slide edit(EditSlideCommand command) {
        Slide slide = this.searchByID(command.getId());
        Picture oldPicture = slide.getPicture();
        if (!command.getPicture().equals(oldPicture.getPicPath())) {
            CreatePictureCommand picCommand = fileUploadService.moveToImg(command.getPicture());
            picCommand.setDescribes("首页幻灯片图片");
            slide.changePicture(pictureService.create(picCommand));
        }
        slide.changeSort(command.getSort());

        slideRepository.update(slide);

        if (!command.getPicture().equals(oldPicture.getPicPath())) {
            fileUploadService.deleteImg(oldPicture.getPicPath());
            pictureService.delete(oldPicture.getId());
        }
        return slide;
    }
}
