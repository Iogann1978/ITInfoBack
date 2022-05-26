package ru.home.itinfo.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;
import ru.home.itinfo.model.Descript;
import ru.home.itinfo.model.Info;
import ru.home.itinfo.model.Tag;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

@Slf4j
public class InfoEntityListener {
    @PrePersist
    @PreUpdate
    public void prepersist(Info info) {
        log.info("prepersist info");
        if (!CollectionUtils.isEmpty(info.getDescripts())) {
            for (Descript d : info.getDescripts()) {
                d.setInfo(info);
                log.info("d: {}", d);
            }
        }
        if (info.getFile() != null) {
            info.getFile().setInfo(info);
        }
        if (!CollectionUtils.isEmpty(info.getTags())) {
            for (Tag t : info.getTags()) {
                t.addInfo(info);
            }
        }
    }
}
