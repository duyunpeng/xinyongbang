package xinyongbang.application.feedback;

import xinyongbang.application.feedback.command.CreateFeedBackCommand;
import xinyongbang.core.api.ApiResponse;

/**
 * Created by dyp on 2016/5/3.
 */
public interface IApiFeedBackAppService {
    ApiResponse apiCreate(CreateFeedBackCommand command);
}
