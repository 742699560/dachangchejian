package cn.tedu.ttms.attachement.service;

import java.io.File;
import java.util.Map;

import cn.tedu.ttms.attachement.entity.Attachement;
import cn.tedu.ttms.common.web.PageObject;

public interface AttachementService {

	File findObjectById(Integer id);

	Map<String, Object> findObjects(Attachement entity, PageObject pageObj);

	int insertProject(Attachement entity);
}
