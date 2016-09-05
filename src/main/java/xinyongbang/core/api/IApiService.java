package xinyongbang.core.api;

import xinyongbang.core.exception.ApiRemoteCallFailedException;

/**
 * Created by YJH on 2016/4/22.
 */
public interface IApiService {
    ApiResponse request(BasicApi apiRequest, int timeout) throws ApiRemoteCallFailedException;

    ApiResponse request(BasicApi apiRequest) throws ApiRemoteCallFailedException;
}
