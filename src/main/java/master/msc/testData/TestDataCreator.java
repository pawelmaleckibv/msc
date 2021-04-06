package master.msc.testData;

import com.blueveery.core.model.BaseEntity;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class TestDataCreator<T extends BaseEntity> implements Creator<T> {

    @PersistenceContext(unitName = "msc-unit", type = PersistenceContextType.TRANSACTION)
    private EntityManager entityManager;

    private Random random;

    public TestDataCreator() {
        this.random = new Random();
    }

    public TestDataCreator(int seed) {
        this.random = new Random(seed);
    }

    @Override
    public T createEntity() {
        T entity = doCreateEntity();
        return persistEntity(entity);
    }

    protected abstract T doCreateEntity();

    @Override
    public List<T> createCollection(int number) {
        List<T> entities = doCreateCollection(number);
        return persistCollection(entities);
    }

    protected List<T> doCreateCollection(int number) {
        List<T> entities = new ArrayList<>();
        for (int i = 0; i < number; i++) {
            entities.add(createEntity());
        }
        return entities;
    }

    @Override
    public T createEntity(Updater<T> dataUpdater) {
        T entity = createEntity();
        return dataUpdater.update(entity);
    }

    @Override
    public List<T> createCollection(int number, Updater<T> dataUpdater) {
        List<T> entities = createCollection(number);
        return dataUpdater.update(entities);
    }

    @Transactional
    protected T persistEntity(T entity) {
        return entityManager.merge(entity);
    }

    protected List<T> persistCollection(List<T> entities) {
        List<T> mergedEntities = new ArrayList<>();
        for (T entity : entities) {
            mergedEntities.add(persistEntity(entity));
        }
        return mergedEntities;
    }

    protected Random getRandom() {
        return random;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    protected List<String> parseLinesToList(String fileName) {
        List<String> lines = null;
        try {
            lines = scanLinesFromFile(readFileByName(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    protected InputStream readFileByName(String fileName) {
//        ClassLoader classLoader = getClass().getClassLoader();
        try {
            return new FileInputStream(fileName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
//        return classLoader.getResourceAsStream(fileName);
    }

    protected List<String> scanLinesFromFile(InputStream inputStream) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, "UTF8"));
        List<String> results = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null) {
            results.add(line);
        }

        return results;
    }

}
