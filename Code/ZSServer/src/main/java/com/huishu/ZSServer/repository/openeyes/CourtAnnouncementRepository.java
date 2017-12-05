package com.huishu.ZSServer.repository.openeyes;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.huishu.ZSServer.entity.openeyes.CourtAnnouncement;

public interface CourtAnnouncementRepository extends CrudRepository<CourtAnnouncement, Long> {

	List<CourtAnnouncement> findByCname(String cname);

}
