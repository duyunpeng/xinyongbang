package xinyongbang.application.identitycard;

import xinyongbang.application.identitycard.command.AttestationIdentityCardCommand;
import xinyongbang.core.api.ApiResponse;

import java.io.UnsupportedEncodingException;

/**
 * Created by dyp on 2016/5/11.
 */
public interface IApiIdentityCardAppService {
    ApiResponse attestation(AttestationIdentityCardCommand command);
}
