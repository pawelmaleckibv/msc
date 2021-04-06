package master.msc.testData;

import com.blueveery.core.model.BaseEntity;

import java.util.List;

public interface Creator<T extends BaseEntity> {

    T createEntity(Updater<T> dataUpdater);
    List<T> createCollection(int number, Updater<T> dataUpdater);
    T createEntity();
    List<T> createCollection(int number);
}
