package master.msc.testData;

import com.blueveery.core.model.BaseEntity;

import java.util.List;

public interface Updater<T extends BaseEntity> {
    List<T> update(List<T> entities);
    T update(T entity);
}
