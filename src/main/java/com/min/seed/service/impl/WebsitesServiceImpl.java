package com.min.seed.service.impl;

import com.min.seed.dao.WebsitesMapper;
import com.min.seed.entity.Websites;
import com.min.seed.service.WebsitesService;
import com.min.seed.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by minych on 2022/09/01.
 */
@Service
@Transactional
public class WebsitesServiceImpl extends AbstractService<Websites> implements WebsitesService {
    @Resource
    private WebsitesMapper websitesMapper;

}
