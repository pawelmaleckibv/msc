package master.msc.services.api;

import com.blueveery.core.services.BaseService;
import master.msc.model.MscBaseEntity;

import java.util.List;
import java.util.UUID;

public interface BaseObjectService<E extends MscBaseEntity> extends BaseService<E> {

    Long countAll();

    List<E> findAllFromListById(List<UUID> uuidList);
}
