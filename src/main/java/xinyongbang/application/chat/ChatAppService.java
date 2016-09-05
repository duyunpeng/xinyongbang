package xinyongbang.application.chat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import xinyongbang.domain.service.chat.IChatService;

/**
 * Created by YJH on 2016/4/22.
 */
@Service("chatAppService")
@Transactional(propagation = Propagation.REQUIRED, readOnly = false, rollbackFor = Exception.class)
public class ChatAppService implements IChatAppService {

    @Autowired
    private IChatService chatService;

}
